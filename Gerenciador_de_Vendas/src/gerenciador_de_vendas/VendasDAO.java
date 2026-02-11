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
public class VendasDAO {

    private Connection connection;

    public VendasDAO() throws SQLException {
        connection = DBConnection.getConnection();
    }

    // CREATE 
    public int inserirVenda(String data, double valor, int idCliente) {
        try {
            String sql = "INSERT INTO vendas (data, valor, id_c) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, data);
            stmt.setDouble(2, valor);
            stmt.setInt(3, idCliente);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    //SELECT /READ usuario
    public List<Vendas> getVendas() {
        List<Vendas> vendas = new ArrayList<>();

        try {
            String sql = "select * from vendas";
            PreparedStatement stmt = connection.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String data = rs.getString("data");
                Double valor = rs.getDouble("valor");
                int id_c = rs.getInt("id_c");

                vendas.add(new Vendas(id, data, valor, id_c));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vendas;
    }

    //UPDATE
    public int updateVendas(Vendas vendas) {
        try {
            String sql = "update vendas set data=?, valor=?, id_c=? where id=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, vendas.getData());
            stmt.setDouble(2, vendas.getValor());
            stmt.setInt(3, vendas.getId_c());
            stmt.setInt(4, vendas.getVid());
            stmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            return 0;
        }
    }

    //DELETE
    public void deleteVendas(int id) {
        try {
            String sql = "delete from vendas where id=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }    
    
    //PAGAR
     public int pagarVendasPorCliente(int idCliente) {
        try {
            String sql = "DELETE FROM vendas WHERE id_c = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            return stmt.executeUpdate(); // retorna quantas vendas foram apagadas
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
