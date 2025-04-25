package Controller;

import Dao.leituraInserirDados;
import Model.dadoTabela;
import javax.swing.JTable;

public class controllerDados {

    public void inserirDado(dadoTabela d,JTable t) {
        leituraInserirDados lid = new leituraInserirDados();
        
        lid.adicionarBanco(d);
        lid.preencherTabela(t);
    }
    
    public void dadosMesAtual(JTable t){
        leituraInserirDados lid = new leituraInserirDados();
        lid.preencherTabelaMesAtual(t);
    }
}
