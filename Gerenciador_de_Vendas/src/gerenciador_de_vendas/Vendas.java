package gerenciador_de_vendas;

/**
 *
 * @author PEDROBORGESPOSPICHIL
 */
public class Vendas {

    private int vid;
    private String data;
    private Double valor;
    private int id_c;

    public Vendas(int vid, String data, Double valor, int id_c) {
        this.vid = vid;
        this.data = data;
        this.valor = valor;
        this.id_c = id_c;
    }

    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public int getId_c() {
        return id_c;
    }

    public void setId_c(int id_c) {
        this.id_c = id_c;
    }
}
