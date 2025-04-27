package Dao;

import Conexao.Conexao;
import Controller.controllerDados;
import Model.dadoTabela;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class leituraInserirDados {

    public void adicionarBanco(dadoTabela d) {
        String sql = "INSERT INTO trabalhoPratico.financas (nome, classificacao, valor, data_entrada) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.obterConexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, d.getNome());
            stmt.setString(2, d.getClassificacao());
            stmt.setDouble(3, d.getValor());
            stmt.setDate(4, d.getData());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // ou logar o erro
        }
    }

    public void preencherTabela(JTable t) {
        DefaultTableModel modelo = (DefaultTableModel) t.getModel();
        modelo.setRowCount(0); // Limpa a tabela antes de inserir novos dados

        String sql = "SELECT nome, classificacao, valor, data_entrada, data_cadastro FROM trabalhoPratico.financas WHERE excluido = FALSE ORDER BY data_entrada DESC";

        try (Connection con = Conexao.obterConexao(); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String nome = rs.getString("nome");
                String classificacao = rs.getString("classificacao");
                double valor = rs.getDouble("valor");
                Date data = rs.getDate("data_entrada");
                Date cadastro = rs.getDate("data_cadastro");

                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

                String data1 = (data != null) ? formato.format(data) : "";
                String cadastro1 = (cadastro != null) ? formato.format(cadastro) : "";

                modelo.addRow(new Object[]{nome, classificacao, valor, data1, cadastro1});
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao preencher a tabela: " + e.getMessage());
        }
    }
 
    public void preencherTabelaMesAtual(JTable tabela) {
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setRowCount(0); // Limpa a tabela antes de inserir novos dados

        String sql = """
        SELECT nome, classificacao, valor, data_entrada, data_cadastro 
        FROM trabalhoPratico.financas 
        WHERE excluido = FALSE 
          AND EXTRACT(MONTH FROM data_entrada) = EXTRACT(MONTH FROM CURRENT_DATE)
          AND EXTRACT(YEAR FROM data_entrada) = EXTRACT(YEAR FROM CURRENT_DATE)
        ORDER BY data_entrada DESC
        """;

        try (Connection con = Conexao.obterConexao(); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String nome = rs.getString("nome");
                String classificacao = rs.getString("classificacao");
                double valor = rs.getDouble("valor");
                Date data = rs.getDate("data_entrada");
                Date cadastro = rs.getDate("data_cadastro");

                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

                String dataFormatada = formato.format(data);
                String cadastroFormatado = formato.format(cadastro);

                modelo.addRow(new Object[]{nome, classificacao, valor, dataFormatada, cadastroFormatado});
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao preencher a tabela: " + e.getMessage());
        }
    }

}
