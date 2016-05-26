/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scify.jthinkfreedom.talkandplay.gui.configuration;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.scify.jthinkfreedom.talkandplay.gui.CreateCategoryFrame;
import org.scify.jthinkfreedom.talkandplay.gui.UpdateCategoryFrame;
import org.scify.jthinkfreedom.talkandplay.models.Category;
import org.scify.jthinkfreedom.talkandplay.models.User;
import org.scify.jthinkfreedom.talkandplay.services.CategoryService;
import org.scify.jthinkfreedom.talkandplay.utils.ConfigurationHandler;

/**
 *
 * @author christina
 */
public class CommunicationModuleSettingsPanel extends javax.swing.JPanel {

    private CategoryService categoryService;
    private User user;
    private List<JPanel> categoriesPanels;
    private ConfigurationHandler configurationHandler;

    public CommunicationModuleSettingsPanel() {
        this.categoryService = new CategoryService();
        this.categoriesPanels = new ArrayList<>();
        this.configurationHandler = new ConfigurationHandler();
        initComponents();
        initCustomComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        categoriesLabel = new javax.swing.JLabel();
        addCategoryButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        categoriesTable = new javax.swing.JTable();
        catExplLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(255, 255, 255));

        categoriesLabel.setText("Κατηγορίες");

        addCategoryButton.setBackground(new java.awt.Color(255, 255, 255));
        addCategoryButton.setForeground(new java.awt.Color(51, 51, 51));
        addCategoryButton.setText("Προσθήκη κατηγορίας");
        addCategoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCategoryButtonActionPerformed(evt);
            }
        });

        saveButton.setBackground(new java.awt.Color(255, 255, 255));
        saveButton.setForeground(new java.awt.Color(51, 51, 51));
        saveButton.setText("Αποθήκευση");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        categoriesTable.setBackground(new java.awt.Color(255, 255, 255));
        categoriesTable.setForeground(new java.awt.Color(51, 51, 51));
        categoriesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Κατηγορία", "Πίνακας", "Ανήκει σε", "# εικόνων"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        categoriesTable.setInheritsPopupMenu(true);
        categoriesTable.setSelectionForeground(new java.awt.Color(0, 0, 0));
        categoriesTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                categoriesTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(categoriesTable);
        if (categoriesTable.getColumnModel().getColumnCount() > 0) {
            categoriesTable.getColumnModel().getColumn(0).setResizable(false);
            categoriesTable.getColumnModel().getColumn(0).setPreferredWidth(10);
            categoriesTable.getColumnModel().getColumn(1).setResizable(false);
            categoriesTable.getColumnModel().getColumn(1).setPreferredWidth(1);
            categoriesTable.getColumnModel().getColumn(2).setResizable(false);
            categoriesTable.getColumnModel().getColumn(2).setPreferredWidth(10);
            categoriesTable.getColumnModel().getColumn(3).setResizable(false);
            categoriesTable.getColumnModel().getColumn(3).setPreferredWidth(1);
        }

        catExplLabel.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        catExplLabel.setText("Πατήστε διπλό κλικ σε μια κατηγορία για την επεξεργαστείτε");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(159, 159, 159)
                                .addComponent(addCategoryButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(saveButton))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(categoriesLabel)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(catExplLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(categoriesLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(catExplLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addCategoryButton)
                    .addComponent(saveButton)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addCategoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCategoryButtonActionPerformed
        CreateCategoryFrame categoryFrame;
        try {
            categoryFrame = new CreateCategoryFrame(this, user);
            categoryFrame.setLocationRelativeTo(null);
            categoryFrame.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(CommunicationModuleSettingsPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_addCategoryButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        //  TableColumn categoriesColumn = categoriesTable.getColumnModel().getColumn(3);
        //  categoriesColumn.setCellEditor(new DefaultCellEditor(comboBox));

        List<Category> categories = new ArrayList<>();
        Category category;

        for (JPanel catPanel : categoriesPanels) {
            String oldName = catPanel.getName().split("-")[1];
            category = new Category();

            for (Component comp : catPanel.getComponents()) {
                if (("name-" + oldName).equals(comp.getName())) {
                    if (comp instanceof JTextField) {
                        category.setName(((JTextField) comp).getText());
                    } else {
                        category.setName(oldName);
                    }
                } else if (("rows-" + oldName).equals(comp.getName())) {
                    category.setRows(Integer.parseInt(((JTextField) comp).getText()));
                } else if (("columns-" + oldName).equals(comp.getName())) {
                    category.setColumns(Integer.parseInt(((JTextField) comp).getText()));
                } else if (("parent-" + oldName).equals(comp.getName())) {
                    category.setParentCategory(new Category(((JComboBox) comp).getSelectedItem().toString()));
                }
            }
            categories.add(category);

        }

        // categoryService.save(categories, user);
    }//GEN-LAST:event_saveButtonActionPerformed

    private void categoriesTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categoriesTableMouseClicked
        if (evt.getClickCount() == 2 && !evt.isConsumed()) {
            evt.consume();
            int row = categoriesTable.rowAtPoint(evt.getPoint());
            int column = categoriesTable.columnAtPoint(evt.getPoint());

            Category category = new Category();
            try {
                for (Category c : categoryService.getCategories(user.getName())) {
                    if (c.getName().equals(categoriesTable.getValueAt(row, 0))) {
                        category = c;
                        break;
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(CommunicationModuleSettingsPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            UpdateCategoryFrame categoryFrame;

            try {
                categoryFrame = new UpdateCategoryFrame(category, this, user);
                categoryFrame.setLocationRelativeTo(null);
                categoryFrame.setVisible(true);
            } catch (Exception ex) {
                Logger.getLogger(CommunicationModuleSettingsPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_categoriesTableMouseClicked

    private void initCustomComponents() {
        jScrollPane1.setBackground(Color.white);
        hideElements();
    }

    public void repaintSettings(User user) throws Exception {
        showElements();

        this.user = user;

        DefaultTableModel model = (DefaultTableModel) categoriesTable.getModel();

        if (user.getCategories().size() > 0) {
            drawCategories(categoryService.getCategories(user.getName()));

        } else {
            categoriesLabel.setVisible(false);
        }
    }

    /**
     * Recursively draw the categories and subcategories, no matter how deep the
     * levels are
     *
     * @param categories
     */
    public void drawCategories(List<Category> categories) throws Exception {

        DefaultTableModel model = (DefaultTableModel) categoriesTable.getModel();

        int rowCount = model.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }

        String parentName;

        for (Category category : categories) {
            if (category.getParentCategory() != null) {
                parentName = category.getParentCategory().getName();
            } else {
                parentName = "-";
            }

            model.addRow(new Object[]{
                category.getName(),
                category.getRows() + "x" + category.getColumns(),
                "-",
                category.getImages().size()});
        }
    }

    public void showElements() {
        categoriesLabel.setVisible(true);
        catExplLabel.setVisible(true);
        categoriesTable.setVisible(true);
        jScrollPane1.setVisible(true);
        addCategoryButton.setVisible(true);
        saveButton.setVisible(true);
    }

    public void hideElements() {
        categoriesLabel.setVisible(false);
        catExplLabel.setVisible(false);
        categoriesTable.setVisible(false);
        jScrollPane1.setVisible(false);
        addCategoryButton.setVisible(false);
        saveButton.setVisible(false);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addCategoryButton;
    private javax.swing.JLabel catExplLabel;
    private javax.swing.JLabel categoriesLabel;
    private javax.swing.JTable categoriesTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton saveButton;
    // End of variables declaration//GEN-END:variables
}
