package fernando.databaseteste.model;

/**
 * Created by Luis Fernando Gomes Sales on 15/01/2017.
 */

public class Estados {
    int id;
    String nome;
    String uf;

    public Estados() {
    }

    public Estados(int id, String nome, String uf) {
        this.id = id;
        this.nome = nome;
        this.uf = uf;
    }

    public Estados(String nome, String uf) {
        this.nome = nome;
        this.uf = uf;
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

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
