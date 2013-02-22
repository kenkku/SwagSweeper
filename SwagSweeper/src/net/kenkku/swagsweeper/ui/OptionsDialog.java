package net.kenkku.swagsweeper.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Tero Keinänen <kenkku@kenkku.net>
 */
class OptionsDialog extends JDialog {

    private JSpinner heightSpinner;
    private JSpinner widthSpinner;

    protected OptionsDialog(JFrame frame) {
        super(frame, true);

        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        initializeComponents();
    }

    int getWidthValue() {
        return (Integer) widthSpinner.getValue();
    }

    int getHeightValue() {
        return (Integer) heightSpinner.getValue();
    }

    private void presetButtonsActionPerformed(java.awt.event.ActionEvent evt) {
        if (evt.getActionCommand().equals("Easy")) {
            heightSpinner.setValue(10);
            widthSpinner.setValue(10);
        } else if (evt.getActionCommand().equals("Medium")) {
            heightSpinner.setValue(15);
            widthSpinner.setValue(20);
        } else if (evt.getActionCommand().equals("Hard")) {
            heightSpinner.setValue(20);
            widthSpinner.setValue(30);
        }
        
    }

    private void initializeComponents() {
        widthSpinner = new javax.swing.JSpinner(new SpinnerNumberModel(10, 5, 30, 1));
        heightSpinner = new javax.swing.JSpinner(new SpinnerNumberModel(10, 5, 20, 1));
        JButton easyButton = new javax.swing.JButton("Easy");
        JButton mediumButton = new javax.swing.JButton("Medium");
        JButton hardButton = new javax.swing.JButton("Hard");
        JButton playButton = new javax.swing.JButton("Play!");
        JLabel widthLabel = new javax.swing.JLabel("Width: ");
        JLabel heightLabel = new javax.swing.JLabel("Height: ");

        easyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                presetButtonsActionPerformed(evt);
            }
        });

        mediumButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                presetButtonsActionPerformed(evt);
            }
        });

        hardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                presetButtonsActionPerformed(evt);
            }
        });
        
        playButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setVisible(false);
            }
        });
        
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(playButton)
                .addGroup(layout.createSequentialGroup()
                .addComponent(easyButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mediumButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hardButton))))
                .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(layout.createSequentialGroup()
                .addComponent(heightLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(heightSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createSequentialGroup()
                .addComponent(widthLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(widthSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(easyButton)
                .addComponent(mediumButton)
                .addComponent(hardButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(widthSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(widthLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(heightSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(heightLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(playButton)
                .addContainerGap()));

        pack();
        setResizable(false);
    }
}
