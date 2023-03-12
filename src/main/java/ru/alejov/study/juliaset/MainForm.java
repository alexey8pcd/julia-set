package ru.alejov.study.juliaset;

import java.awt.*;

public class MainForm extends javax.swing.JFrame {

    public MainForm() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        sliderThreadCount = new javax.swing.JSlider();
        jLabel2 = new javax.swing.JLabel();
        sliderDepth = new javax.swing.JSlider();
        buttonStart = new javax.swing.JButton();
        threadCountValueLabel = new javax.swing.JLabel();
        depthValueLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setResizable(false);

        jLabel1.setText("Threads count:");

        sliderThreadCount.setMajorTickSpacing(4);
        sliderThreadCount.setMaximum(32);
        sliderThreadCount.setMinimum(1);
        sliderThreadCount.setMinorTickSpacing(1);
        sliderThreadCount.setPaintLabels(true);
        sliderThreadCount.setPaintTicks(true);
        sliderThreadCount.setValue(1);
        sliderThreadCount.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderThreadCountStateChanged(evt);
            }
        });

        jLabel2.setText("Depth:");

        sliderDepth.setMajorTickSpacing(8);
        sliderDepth.setMaximum(64);
        sliderDepth.setMinimum(1);
        sliderDepth.setMinorTickSpacing(2);
        sliderDepth.setPaintLabels(true);
        sliderDepth.setPaintTicks(true);
        sliderDepth.setToolTipText("");
        sliderDepth.setValue(32);
        sliderDepth.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderDepthStateChanged(evt);
            }
        });

        buttonStart.setText("Start");
        buttonStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonStartActionPerformed(evt);
            }
        });

        threadCountValueLabel.setText(String.valueOf(sliderThreadCount.getValue()));

        depthValueLabel.setText(String.valueOf(sliderDepth.getValue()));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sliderThreadCount, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                    .addComponent(sliderDepth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(depthValueLabel))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonStart))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(threadCountValueLabel)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(threadCountValueLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sliderThreadCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(depthValueLabel))
                .addGap(18, 18, 18)
                .addComponent(sliderDepth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(buttonStart)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonStartActionPerformed
        start();
    }//GEN-LAST:event_buttonStartActionPerformed

    private void sliderThreadCountStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderThreadCountStateChanged
        sliderThreadCountStateChanged1();
    }//GEN-LAST:event_sliderThreadCountStateChanged

    private void sliderThreadCountStateChanged1() {
        threadCountValueLabel.setText(String.valueOf(sliderThreadCount.getValue()));
    }

    private void sliderDepthStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderDepthStateChanged
        sliderDepthStateChanged1();
    }//GEN-LAST:event_sliderDepthStateChanged

    private void sliderDepthStateChanged1() {
        depthValueLabel.setText(String.valueOf(sliderDepth.getValue()));
    }

    private void start() {
        ProcessForm processForm = new ProcessForm(this, false, sliderDepth.getValue(), sliderThreadCount.getValue());
        processForm.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException |
                InstantiationException |
                IllegalAccessException |
                javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            new MainForm().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonStart;
    private javax.swing.JLabel depthValueLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSlider sliderDepth;
    private javax.swing.JSlider sliderThreadCount;
    private javax.swing.JLabel threadCountValueLabel;
    // End of variables declaration//GEN-END:variables
}
