package Model;

import java.sql.Date;

public class dadoTabela {

    private String nome;
    private String classificacao;
    private double valor;
    private Date data;
    private Date Cadastro;

    public dadoTabela(String nome, String classificacao, double valor, Date data, Date Cadastro) {
        this.nome = nome;
        this.classificacao = classificacao;
        this.valor = valor;
        this.data = data;
        this.Cadastro = Cadastro;
    }

    public String getNome() {
        return nome;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public double getValor() {
        return valor;
    }

    public Date getData() {
        return data;
    }

    public Date getCadastro() {
        return Cadastro;
    }
    
    

}
