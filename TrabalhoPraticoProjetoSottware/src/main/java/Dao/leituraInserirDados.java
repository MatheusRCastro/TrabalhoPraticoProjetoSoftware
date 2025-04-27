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

    public void preencherTabelaIntervalo(JTable tabela, Date dataInicio, Date dataFim) {
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setRowCount(0); // Limpa a tabela antes de inserir novos dados

        String sql = "SELECT nome, classificacao, valor, data_entrada, data_cadastro "
                + "FROM trabalhoPratico.financas "
                + "WHERE excluido = FALSE AND data_entrada BETWEEN ? AND ? "
                + "ORDER BY data_entrada ASC";

        try (Connection con = Conexao.obterConexao(); PreparedStatement stmt = con.prepareStatement(sql)) {

            // Define os parâmetros do intervalo
            stmt.setDate(1, new java.sql.Date(dataInicio.getTime()));
            stmt.setDate(2, new java.sql.Date(dataFim.getTime()));

            try (ResultSet rs = stmt.executeQuery()) {
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); // Para formatar a data no padrão BR

                while (rs.next()) {
                    String nome = rs.getString("nome");
                    String classificacao = rs.getString("classificacao");
                    double valor = rs.getDouble("valor");
                    String dataEntrada = formato.format(rs.getDate("data_entrada"));
                    String dataCadastro = formato.format(rs.getDate("data_cadastro"));

                    // Adiciona uma nova linha na tabela
                    modelo.addRow(new Object[]{nome, classificacao, valor, dataEntrada, dataCadastro});
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao preencher tabela no intervalo: " + e.getMessage());
        }
    }

    public void excluirDado(JTable tabela) {
        // Obtém a linha selecionada na JTable
        int linhaSelecionada = tabela.getSelectedRow();

        if (linhaSelecionada != -1) { // Verifica se uma linha foi realmente selecionada
            // Obtém os valores da linha selecionada
            String nome = tabela.getValueAt(linhaSelecionada, 0).toString(); // Primeiro campo (nome)
            String classificacao = tabela.getValueAt(linhaSelecionada, 1).toString(); // Segundo campo (classificação)
            double valor = Double.parseDouble(tabela.getValueAt(linhaSelecionada, 2).toString()); // Terceiro campo (valor)
            String dataEntrada = tabela.getValueAt(linhaSelecionada, 3).toString(); // Quarto campo (data_entrada)

            try {
                // Converte as Strings para java.sql.Date
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                java.util.Date dataEntradaUtil = formato.parse(dataEntrada);

                // Converte para java.sql.Date
                java.sql.Date dataEntradaSql = new java.sql.Date(dataEntradaUtil.getTime());

                // A consulta SQL usa os valores obtidos para identificar o registro a ser atualizado
                String sql = "UPDATE trabalhoPratico.financas SET excluido = TRUE WHERE nome = ? AND classificacao = ? AND valor = ? AND data_entrada = ?";

                try (Connection con = Conexao.obterConexao(); PreparedStatement stmt = con.prepareStatement(sql)) {

                    // Substitui os parâmetros '?' pela variável correspondente
                    stmt.setString(1, nome);
                    stmt.setString(2, classificacao);
                    stmt.setDouble(3, valor);
                    stmt.setDate(4, dataEntradaSql); // Passa a data convertida para SQL

                    // Executa a atualização (marcando como excluído)
                    int linhasAfetadas = stmt.executeUpdate();

                    if (linhasAfetadas > 0) {
                        System.out.println("Registro marcado como excluído com sucesso!");
                    } else {
                        System.out.println("Nenhum registro encontrado com os dados fornecidos.");
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao marcar como excluído: " + e.getMessage());
                }
            } catch (ParseException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro ao converter as datas: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma linha para excluir.");
        }
    }
    
    public void desfazerExclusao() {
    String sql = "UPDATE trabalhoPratico.financas " +
                 "SET excluido = FALSE, data_exclusao = NULL " +
                 "WHERE id = (" +
                 "    SELECT id FROM trabalhoPratico.financas " +
                 "    WHERE excluido = TRUE " +
                 "    ORDER BY data_exclusao DESC " +
                 "    LIMIT 1" +
                 ");";

    try (Connection conn = Conexao.obterConexao();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        int rowsAffected = stmt.executeUpdate();
        
        if (rowsAffected > 0) {
            System.out.println("Último registro excluído restaurado com sucesso!");
        } else {
            System.out.println("Nenhum registro para restaurar.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


}
