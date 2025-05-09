/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.controllerDados;
import Dao.leituraInserirDados;
import Model.dadoTabela;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Márcia
 */
public class TabelaFinanceira extends javax.swing.JFrame {

    /**
     * Creates new form TabelaFinanceira
     */
    public TabelaFinanceira() {
        initComponents();
        leituraInserirDados lid = new leituraInserirDados();
        lid.preencherTabela(tabela);

        controllerDados cd = new controllerDados();
        cd.atualizaDados(tabela);

        dadoTabela dt = new dadoTabela();
        valorRecebido.setText(String.valueOf(dt.getTotalGanhos()));
        valorGastos.setText(String.valueOf(dt.getTotalDespesas()));
        valorDiferenca.setText(String.valueOf(dt.getDiferenca()));

        dataComecoBusca.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                verificarPreenchimento();
            }

            public void removeUpdate(DocumentEvent e) {
                verificarPreenchimento();
            }

            public void changedUpdate(DocumentEvent e) {
                verificarPreenchimento();
            }
        });

        dataFinalBusca.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                verificarPreenchimento();
            }

            public void removeUpdate(DocumentEvent e) {
                verificarPreenchimento();
            }

            public void changedUpdate(DocumentEvent e) {
                verificarPreenchimento();
            }
        });

        verificarTabela();
    }

    private void verificarTabela() {
        if (tabela.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Não há registros a serem mostrados!");
            valorDiferenca.setVisible(false);
            valorGastos.setVisible(false);
            valorRecebido.setVisible(false);
            textoDiferenca.setVisible(false);
            textoGastos.setVisible(false);
            textoRecebido.setVisible(false);
            botaoDeletar.setVisible(false);
        } else {
            valorDiferenca.setVisible(true);
            valorGastos.setVisible(true);
            valorRecebido.setVisible(true);
            textoDiferenca.setVisible(true);
            textoGastos.setVisible(true);
            textoRecebido.setVisible(true);
            botaoDeletar.setVisible(true);
        }
    }

    private void verificarPreenchimento() {
        String inicio = dataComecoBusca.getText();
        String fim = dataFinalBusca.getText();

        if (validarData(inicio) && validarData(fim)) {
            atualizarTabela();
        } else {
            controllerDados cd = new controllerDados();

            cd.preencherTabela(tabela);
            cd.atualizaDados(tabela);

            dadoTabela dt = new dadoTabela();
            valorRecebido.setText(String.valueOf(dt.getTotalGanhos()));
            valorGastos.setText(String.valueOf(dt.getTotalDespesas()));
            valorDiferenca.setText(String.valueOf(dt.getDiferenca()));

            verificarTabela();
        }
    }

    private boolean validarData(String data) {
        if (data.length() != 10) {
            return false;
        }

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        formato.setLenient(false);

        try {
            formato.parse(data);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private void atualizarTabela() {
        try {
            System.out.println("Datas corretas! Atualizando a tabela...");
            // Aqui você chama o seu método que faz o SELECT no banco e atualiza a JTable
            //JOptionPane.showInternalMessageDialog(null,"Olá");
            controllerDados cd = new controllerDados();
            java.util.Date comeco;
            java.util.Date fina;

            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

            comeco = formato.parse(dataComecoBusca.getText());
            fina = formato.parse(dataFinalBusca.getText());

            java.sql.Date comeco1 = new java.sql.Date(comeco.getTime());
            java.sql.Date fina1 = new java.sql.Date(fina.getTime());

            cd.atualizarTabelaIntervalo(tabela, comeco1, fina1);
        } catch (ParseException ex) {
            Logger.getLogger(TabelaFinanceira.class.getName()).log(Level.SEVERE, null, ex);
        }
        controllerDados cd = new controllerDados();
        cd.atualizaDados(tabela);

        dadoTabela dt = new dadoTabela();
        valorRecebido.setText(String.valueOf(dt.getTotalGanhos()));
        valorGastos.setText(String.valueOf(dt.getTotalDespesas()));
        valorDiferenca.setText(String.valueOf(dt.getDiferenca()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        botaoCadastrar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        dataComecoBusca = new javax.swing.JFormattedTextField();
        dataFinalBusca = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        botaoDeletar = new javax.swing.JButton();
        textoRecebido = new javax.swing.JLabel();
        valorRecebido = new javax.swing.JLabel();
        textoGastos = new javax.swing.JLabel();
        valorGastos = new javax.swing.JLabel();
        textoDiferenca = new javax.swing.JLabel();
        valorDiferenca = new javax.swing.JLabel();
        nome = new javax.swing.JTextField();
        classificacao = new javax.swing.JTextField();
        valor = new javax.swing.JTextField();
        dataDaEntrada = new javax.swing.JTextField();
        botaoGanho = new javax.swing.JButton();
        botaoPerda = new javax.swing.JButton();
        botaoMesAtual1 = new javax.swing.JToggleButton();
        botaoBackup = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nome", "Classificação", "Valor", "Data", "Cadastro"
            }
        ));
        jScrollPane1.setViewportView(tabela);

        jLabel1.setText("Nome:");

        jLabel2.setText("Classificação:");

        jLabel3.setText("Valor:");

        jLabel4.setText("Data Entrada:");

        botaoCadastrar.setBackground(new java.awt.Color(0, 102, 255));
        botaoCadastrar.setForeground(new java.awt.Color(255, 255, 255));
        botaoCadastrar.setText("Cadastrar");
        botaoCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCadastrarActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel5.setText("Finanças Anual Seu José");

        dataComecoBusca.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));

        dataFinalBusca.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));

        jLabel6.setText("Até:");

        jLabel7.setText("De:");

        botaoDeletar.setBackground(new java.awt.Color(255, 0, 0));
        botaoDeletar.setForeground(new java.awt.Color(255, 255, 255));
        botaoDeletar.setText("X");
        botaoDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoDeletarActionPerformed(evt);
            }
        });

        textoRecebido.setForeground(new java.awt.Color(51, 51, 51));
        textoRecebido.setText("Recebido:");

        valorRecebido.setForeground(new java.awt.Color(51, 51, 51));
        valorRecebido.setText("0,0R$");

        textoGastos.setForeground(new java.awt.Color(51, 51, 51));
        textoGastos.setText("Gastos:");

        valorGastos.setForeground(new java.awt.Color(51, 51, 51));
        valorGastos.setText("0,0R$");

        textoDiferenca.setForeground(new java.awt.Color(51, 51, 51));
        textoDiferenca.setText("Diferença:");

        valorDiferenca.setForeground(new java.awt.Color(51, 51, 51));
        valorDiferenca.setText("0,0R$");

        botaoGanho.setBackground(new java.awt.Color(0, 204, 0));
        botaoGanho.setForeground(new java.awt.Color(255, 255, 255));
        botaoGanho.setText("Ganho + ");
        botaoGanho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoGanhoActionPerformed(evt);
            }
        });

        botaoPerda.setBackground(new java.awt.Color(204, 0, 0));
        botaoPerda.setForeground(new java.awt.Color(255, 255, 255));
        botaoPerda.setText("Perda -");
        botaoPerda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPerdaActionPerformed(evt);
            }
        });

        botaoMesAtual1.setBackground(new java.awt.Color(0, 153, 0));
        botaoMesAtual1.setForeground(new java.awt.Color(255, 255, 255));
        botaoMesAtual1.setText("MÊS ATUAL");
        botaoMesAtual1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoMesAtual1ActionPerformed(evt);
            }
        });

        botaoBackup.setBackground(new java.awt.Color(0, 204, 204));
        botaoBackup.setForeground(new java.awt.Color(255, 255, 255));
        botaoBackup.setText("Desfazer última exclusão");
        botaoBackup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoBackupActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(212, 212, 212))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(valor, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botaoBackup, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(botaoCadastrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(classificacao))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(4, 4, 4)
                                .addComponent(dataDaEntrada))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(botaoGanho, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botaoPerda, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nome))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(botaoMesAtual1)
                                .addGap(116, 116, 116)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dataComecoBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dataFinalBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addComponent(botaoDeletar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(textoRecebido)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valorRecebido)
                        .addGap(146, 146, 146)
                        .addComponent(textoGastos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valorGastos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textoDiferenca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valorDiferenca)
                        .addGap(85, 85, 85))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(dataComecoBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoMesAtual1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(dataFinalBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(botaoBackup)
                                .addGap(26, 26, 26)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(classificacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(valor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(dataDaEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(botaoGanho)
                                    .addComponent(botaoPerda))
                                .addGap(18, 18, 18)
                                .addComponent(botaoCadastrar))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addComponent(botaoDeletar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoRecebido)
                    .addComponent(valorRecebido)
                    .addComponent(textoGastos)
                    .addComponent(valorGastos)
                    .addComponent(textoDiferenca)
                    .addComponent(valorDiferenca))
                .addGap(44, 44, 44))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoDeletarActionPerformed
        // TODO add your handling code here:
        controllerDados cd = new controllerDados();

        if (JOptionPane.showConfirmDialog(null, "Você deseja realmente excluir esse registro?") == 0) {

            cd.excluirDado(tabela);
        }
        cd.preencherTabela(tabela);
        cd.atualizaDados(tabela);

        dadoTabela dt = new dadoTabela();
        valorRecebido.setText(String.valueOf(dt.getTotalGanhos()));
        valorGastos.setText(String.valueOf(dt.getTotalDespesas()));
        valorDiferenca.setText(String.valueOf(dt.getDiferenca()));

        verificarTabela();
    }//GEN-LAST:event_botaoDeletarActionPerformed

    private void botaoCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrarActionPerformed
        // TODO add your handling code here:
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date data = null;

        try {
            data = formato.parse(dataDaEntrada.getText()); // Retorna um java.util.Date
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Certifique-se de converter para java.sql.Date
        Date data_sql = new Date(data.getTime()); // Correção: usa getTime() para criar java.sql.Date

        // Agora passa o java.sql.Date para o dadoTabela
        dadoTabela d = new dadoTabela(nome.getText(), classificacao.getText(), Double.parseDouble(valor.getText()), data_sql);

        controllerDados cd = new controllerDados();
        cd.inserirDado(d, tabela);

        nome.setText("");
        classificacao.setText("");
        valor.setText("");
        dataDaEntrada.setText("");

        cd.atualizaDados(tabela);

        dadoTabela dt = new dadoTabela();
        valorRecebido.setText(String.valueOf(dt.getTotalGanhos()));
        valorGastos.setText(String.valueOf(dt.getTotalDespesas()));
        valorDiferenca.setText(String.valueOf(dt.getDiferenca()));

        verificarTabela();
    }//GEN-LAST:event_botaoCadastrarActionPerformed

    private void botaoPerdaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPerdaActionPerformed
        // TODO add your handling code here:
        if (Double.parseDouble(valor.getText()) > 0) {
            double x = Double.parseDouble(valor.getText()) * -1;
            String y = String.valueOf(x);
            valor.setText(y);
        }
    }//GEN-LAST:event_botaoPerdaActionPerformed

    private void botaoGanhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoGanhoActionPerformed
        if (Double.parseDouble(valor.getText()) < 0) {
            double x = Double.parseDouble(valor.getText()) * -1;
            String y = String.valueOf(x);
            valor.setText(y);
        }
    }//GEN-LAST:event_botaoGanhoActionPerformed

    private void botaoMesAtual1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoMesAtual1ActionPerformed
        // TODO add your handling code here:
        if (botaoMesAtual1.isSelected()) {
            dataComecoBusca.setText("");
            dataFinalBusca.setText("");

            controllerDados cd = new controllerDados();

            cd.dadosMesAtual(tabela);

            cd.atualizaDados(tabela);

            dadoTabela dt = new dadoTabela();
            valorRecebido.setText(String.valueOf(dt.getTotalGanhos()));
            valorGastos.setText(String.valueOf(dt.getTotalDespesas()));
            valorDiferenca.setText(String.valueOf(dt.getDiferenca()));
        } else {
            dataComecoBusca.setText("");
            dataFinalBusca.setText("");

            leituraInserirDados lid = new leituraInserirDados();
            lid.preencherTabela(tabela);

            controllerDados cd = new controllerDados();
            cd.atualizaDados(tabela);

            dadoTabela dt = new dadoTabela();
            valorRecebido.setText(String.valueOf(dt.getTotalGanhos()));
            valorGastos.setText(String.valueOf(dt.getTotalDespesas()));
            valorDiferenca.setText(String.valueOf(dt.getDiferenca()));
        }

        verificarTabela();
    }//GEN-LAST:event_botaoMesAtual1ActionPerformed

    private void botaoBackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoBackupActionPerformed
        // TODO add your handling code here:
        controllerDados cd = new controllerDados();
        cd.desfazerExclusao(tabela);

        verificarTabela();

        cd.atualizaDados(tabela);

        dadoTabela dt = new dadoTabela();
        valorRecebido.setText(String.valueOf(dt.getTotalGanhos()));
        valorGastos.setText(String.valueOf(dt.getTotalDespesas()));
        valorDiferenca.setText(String.valueOf(dt.getDiferenca()));
    }//GEN-LAST:event_botaoBackupActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TabelaFinanceira.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TabelaFinanceira.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TabelaFinanceira.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TabelaFinanceira.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TabelaFinanceira().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoBackup;
    private javax.swing.JButton botaoCadastrar;
    private javax.swing.JButton botaoDeletar;
    private javax.swing.JButton botaoGanho;
    private javax.swing.JToggleButton botaoMesAtual1;
    private javax.swing.JButton botaoPerda;
    private javax.swing.JTextField classificacao;
    private javax.swing.JFormattedTextField dataComecoBusca;
    private javax.swing.JTextField dataDaEntrada;
    private javax.swing.JFormattedTextField dataFinalBusca;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nome;
    private javax.swing.JTable tabela;
    private javax.swing.JLabel textoDiferenca;
    private javax.swing.JLabel textoGastos;
    private javax.swing.JLabel textoRecebido;
    private javax.swing.JTextField valor;
    private javax.swing.JLabel valorDiferenca;
    private javax.swing.JLabel valorGastos;
    private javax.swing.JLabel valorRecebido;
    // End of variables declaration//GEN-END:variables
}
