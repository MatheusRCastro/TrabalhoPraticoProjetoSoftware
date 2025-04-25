package Model;

import java.sql.Date;

public class dadoTabela {

    private String nome;
    private String classificacao;
    private double valor;
    private Date data;
    private Date Cadastro;
    private boolean excluido;

    private double totalGanhos;
    private double totalDespesas;
    private double diferenca;

    public dadoTabela(String nome, String classificacao, double valor, Date data, Date Cadastro, boolean excluido) {
        this.nome = nome;
        this.classificacao = classificacao;
        this.valor = valor;
        this.data = data;
        this.Cadastro = Cadastro;
        this.excluido = excluido;
    }

    public dadoTabela(String nome, String classificacao, double valor, Date data) {
        this.nome = nome;
        this.classificacao = classificacao;
        this.valor = valor;
        this.data = data;
    }

    public dadoTabela(double totalGanhos, double totalDespesas, double diferenca) {
        this.totalGanhos = totalGanhos;
        this.totalDespesas = totalDespesas;
        this.diferenca = diferenca;
    }

    public dadoTabela() {
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

    public double getTotalGanhos() {
        return totalGanhos;
    }

    public double getTotalDespesas() {
        return totalDespesas;
    }

    public double getDiferenca() {
        return diferenca;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setCadastro(Date Cadastro) {
        this.Cadastro = Cadastro;
    }

    public void setTotalGanhos(double totalGanhos) {
        this.totalGanhos = totalGanhos;
    }

    public void setTotalDespesas(double totalDespesas) {
        this.totalDespesas = totalDespesas;
    }

    public void setDiferenca(double diferenca) {
        this.diferenca = diferenca;
    }
    
    
}
