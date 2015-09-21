/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scify.jthinkfreedom.reactors.XmlScreens;

import java.io.PrintWriter;
import java.io.StringWriter;
import javax.swing.JFrame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author xrousakis
 */
public class XmlScreen extends javax.swing.JFrame {

    private static final Logger logger = LogManager.getLogger(XmlScreen.class);

    /**
     * Creates new form XmlScreen
     */
    public XmlScreen() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        addImageButton = new javax.swing.JButton();
        deleteImageButton = new javax.swing.JButton();
        editImageButton = new javax.swing.JButton();
        addCategoryButton = new javax.swing.JButton();
        deleteCategoryButton = new javax.swing.JButton();
        editCategoryButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
        jLabel1.setText("Xml Configuration");

        jLabel2.setText("Select action");

        addImageButton.setText("Add Image");
        addImageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addImageButtonActionPerformed(evt);
            }
        });

        deleteImageButton.setText("Delete Image");
        deleteImageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteImageButtonActionPerformed(evt);
            }
        });

        editImageButton.setText("Edit Image");
        editImageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editImageButtonActionPerformed(evt);
            }
        });

        addCategoryButton.setText("Add Category");
        addCategoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCategoryButtonActionPerformed(evt);
            }
        });

        deleteCategoryButton.setText("Delete Category");
        deleteCategoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteCategoryButtonActionPerformed(evt);
            }
        });

        editCategoryButton.setText("Edit Category");
        editCategoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editCategoryButtonActionPerformed(evt);
            }
        });

        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addCategoryButton)
                            .addComponent(addImageButton))
                        .addGap(28, 28, 28)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(editCategoryButton, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(editImageButton, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.CENTER, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.CENTER, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                .addComponent(deleteImageButton)
                                .addComponent(jLabel2))
                            .addGroup(javax.swing.GroupLayout.Alignment.CENTER, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                .addComponent(deleteCategoryButton)
                                .addComponent(closeButton)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {addCategoryButton, addImageButton, closeButton, deleteCategoryButton, deleteImageButton, editCategoryButton, editImageButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(39, 39, 39)
                .addComponent(jLabel2)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addImageButton)
                    .addComponent(deleteImageButton)
                    .addComponent(editImageButton))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addCategoryButton)
                    .addComponent(deleteCategoryButton)
                    .addComponent(editCategoryButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(closeButton)
                .addGap(29, 29, 29))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {addCategoryButton, addImageButton, deleteCategoryButton, deleteImageButton, editCategoryButton, editImageButton});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_closeButtonActionPerformed

    private String returnStackTrace(Exception ex) {
        StringWriter sWriter = new StringWriter();
        PrintWriter pWriter = new PrintWriter(sWriter);
        ex.printStackTrace(pWriter);
        return sWriter.toString();
    }

    private void addImageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addImageButtonActionPerformed
        // TODO add your handling code here:
        try {
            AddTileScreen frame = new AddTileScreen();
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        } catch (Exception ex) {
            logger.error(returnStackTrace(ex));
        }
    }//GEN-LAST:event_addImageButtonActionPerformed

    private void deleteImageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteImageButtonActionPerformed
        // TODO add your handling code here:
        try {
            RemoveTileScreen frame = new RemoveTileScreen();
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        } catch (Exception ex) {
            logger.error(returnStackTrace(ex));
        }

    }//GEN-LAST:event_deleteImageButtonActionPerformed

    private void editImageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editImageButtonActionPerformed
        // TODO add your handling code here:
        try {
            EditTileScreen frame = new EditTileScreen();
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        } catch (Exception ex) {
            logger.error(returnStackTrace(ex));
        }


    }//GEN-LAST:event_editImageButtonActionPerformed

    private void addCategoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCategoryButtonActionPerformed
        // TODO add your handling code here:
        try {
            AddCategoryScreen frame = new AddCategoryScreen();
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        } catch (Exception ex) {
            logger.error(returnStackTrace(ex));
        }

    }//GEN-LAST:event_addCategoryButtonActionPerformed

    private void deleteCategoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteCategoryButtonActionPerformed
        // TODO add your handling code here:
        try {
            RemoveCategoryScreen frame = new RemoveCategoryScreen();
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        } catch (Exception ex) {
            logger.error(returnStackTrace(ex));
        }

    }//GEN-LAST:event_deleteCategoryButtonActionPerformed

    private void editCategoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editCategoryButtonActionPerformed
        // TODO add your handling code here:
        try {
            EditCategoryScreen frame = new EditCategoryScreen();
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        } catch (Exception ex) {
            logger.error(returnStackTrace(ex));
        }

    }//GEN-LAST:event_editCategoryButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addCategoryButton;
    private javax.swing.JButton addImageButton;
    private javax.swing.JButton closeButton;
    private javax.swing.JButton deleteCategoryButton;
    private javax.swing.JButton deleteImageButton;
    private javax.swing.JButton editCategoryButton;
    private javax.swing.JButton editImageButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
