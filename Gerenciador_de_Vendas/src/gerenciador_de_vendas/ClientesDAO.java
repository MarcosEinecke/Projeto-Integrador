package gerenciador_de_vendas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PEDROBORGESPOSPICHIL
 */
public class ClientesDAO {

    private Connection connection;

    public ClientesDAO() throws SQLException {
        connection = DBConnection.getConnection();
    }

    // CREATE 
    public int InserirClientes(Clientes clientes) {
        try {
            String sql = "insert into clientes(nome,curso,cpf,saldo) values (?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, clientes.getNome());
            stmt.setString(2, clientes.getCurso());
            stmt.setString(3, clientes.getCpf());
            stmt.setDouble(4, clientes.getSaldo());
            stmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            return 0;
        }

    }

    //SELECT /READ usuario
    public List<Clientes> getClientes() {
        List<Clientes> clientes = new ArrayList<>();

        try {
            String sql = "select c.id,c.nome,c.curso, c.cpf,(c.saldo - IFNULL(SUM(v.valor),0)) AS saldo_atual from clientes c \n" +
"LEFT JOIN vendas v ON c.id = v.id_c\n" +
"GROUP BY c.id, c.nome, c.curso, c.saldo;";
            PreparedStatement stmt = connection.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String curso = rs.getString("curso");
                String cpf = rs.getString("cpf");
                Double saldo = rs.getDouble("saldo_atual");

                clientes.add(new Clientes(id, nome, curso, cpf, saldo));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    //UPDATE
    public int updateClientes(Clientes clientes) {
        try {
            String sql = "update clientes set nome=?, curso=?, cpf=?, saldo=? where id=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, clientes.getNome());
            stmt.setString(2, clientes.getCurso());
            stmt.setString(3, clientes.getCpf());
            stmt.setDouble(4, clientes.getSaldo());
            stmt.setInt(5, clientes.getCid());
            stmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            return 0;
        }
    }

    //DELETE
    public void deleteClientes(int id) {
        try {
            String sql = "delete from clientes where id=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Consultar CPF
    public ResultSet consultarCPF(String cpf) {
        try {
            String sql = "SELECT * FROM clientes WHERE cpf = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, cpf);
            return stmt.executeQuery(); 
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
