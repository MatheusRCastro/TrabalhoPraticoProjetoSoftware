package Controller;

import Dao.leituraInserirDados;
import Model.dadoTabela;

public class controllerDados {

    public void inserirDado(dadoTabela d) {
        leituraInserirDados lid = new leituraInserirDados();
        lid.adicionarBanco(d);
    }
}
