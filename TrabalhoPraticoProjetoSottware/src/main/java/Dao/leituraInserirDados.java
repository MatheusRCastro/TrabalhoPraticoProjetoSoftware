package Dao;

import Conexao.Conexao;
import Model.dadoTabela;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class leituraInserirDados {
    
    public void adicionarBanco(dadoTabela d){
         String sql = "INSERT INTO trabalhoPratico.financas (nome, classificacao, valor, data_entrada) " +
                     "VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, d.getNome());
            stmt.setString(2, d.getClassificacao());
            stmt.setDouble(3, d.getValor());
            stmt.setDate(4, d.getData());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // ou logar o erro
        }
    }

}
