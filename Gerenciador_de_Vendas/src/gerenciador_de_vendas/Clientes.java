    package gerenciador_de_vendas;

/**
 *
 * @author PEDROBORGESPOSPICHIL
 */
public class Clientes {
    
    private int cid;
    private String nome, curso, cpf;
    private double saldo;

    public Clientes(int cid, String nome, String curso, String cpf, double saldo) {
        this.cid = cid;
        this.nome = nome;
        this.curso = curso;
        this.cpf = cpf;
        this.saldo = saldo;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
