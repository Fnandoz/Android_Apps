package fernando.databaseteste.model;

/**
 * Created by Luis Fernando Gomes Sales on 15/01/2017.
 */

public class Municipios {
    int id;
    String nome;
    String uf_estado;

    public Municipios() {
    }

    public Municipios(int id, String nome, String uf_estado) {
        this.id = id;
        this.nome = nome;
        this.uf_estado = uf_estado;
    }

    public Municipios(String nome, String uf_estado) {
        this.nome = nome;
        this.uf_estado = uf_estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf_estado() {
        return uf_estado;
    }

    public void setUf_estado(String uf_estado) {
        this.uf_estado = uf_estado;
    }
}
