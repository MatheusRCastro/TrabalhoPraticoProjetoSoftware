package Controller;

import Dao.leituraInserirDados;
import Model.dadoTabela;
import View.TabelaFinanceira;
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
    
    public void atualizaDados(JTable t){
        
        
        dadoTabela dt = new dadoTabela();
        dt.setTotalGanhos(0);
        dt.setTotalDespesas(0);
        dt.setDiferenca(0);
        
        for (int i = 0; i < t.getRowCount(); i++) {
            if (Double.parseDouble(t.getValueAt(i, 2).toString()) > 0) {
                dt.setTotalGanhos(Double.parseDouble(t.getValueAt(i, 2).toString())+ dt.getTotalGanhos());
            }else{
                dt.setTotalDespesas(Double.parseDouble(t.getValueAt(i, 2).toString())+ dt.getTotalDespesas());
            }
        }
        
        dt.setDiferenca(dt.getTotalGanhos() + dt.getTotalDespesas());
    }
}
