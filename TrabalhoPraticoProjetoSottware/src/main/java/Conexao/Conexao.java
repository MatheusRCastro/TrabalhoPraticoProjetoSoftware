package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    public static Connection obterConexao() throws SQLException{
        String d="jdbc:postgresql://localhost:5432/Inter";
        String u="postgres",senha="fofura190507";
        return DriverManager.getConnection(d, u, senha);
    }
    
}
