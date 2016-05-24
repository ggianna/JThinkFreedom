/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scify.jthinkfreedom.talkandplay.gui.configuration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.scify.jthinkfreedom.talkandplay.models.Category;
import org.scify.jthinkfreedom.talkandplay.models.User;
import org.scify.jthinkfreedom.talkandplay.services.CategoryService;
import org.scify.jthinkfreedom.talkandplay.utils.ConfigurationHandler;

/**
 *
 * @author christina
 */
public class CommunicationModuleSettingsPanelOLD extends javax.swing.JPanel {

    private ConfigurationHandler configurationHandler;
    private CategoryService categoryService;
    private List<User> profiles;
    private Set<String> allCategories;
    private JPanel categoryPanel;

    public CommunicationModuleSettingsPanelOLD(List<User> profiles) {
        this.categoryService = new CategoryService();
        this.profiles = profiles;
        this.allCategories = new HashSet<>();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        categoriesTable = new javax.swing.JTable();
        saveButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(255, 255, 255));

        categoriesLabel.setText("Categories");

        addCategoryButton.setText("Προσθήκη κατηγορίας");
        addCategoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCategoryButtonActionPerformed(evt);
            }
        });

        categoriesTable.setBackground(new java.awt.Color(255, 255, 255));
        categoriesTable.setForeground(new java.awt.Color(51, 51, 51));
        categoriesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Κατηγορία", "Στήλες", "Γραμμές", "Ανήκει στην κατηγορία"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        categoriesTable.getTableHeader().setResizingAllowed(false);
        categoriesTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(categoriesTable);
        if (categoriesTable.getColumnModel().getColumnCount() > 0) {
            categoriesTable.getColumnModel().getColumn(0).setResizable(false);
            categoriesTable.getColumnModel().getColumn(0).setPreferredWidth(20);
            categoriesTable.getColumnModel().getColumn(1).setResizable(false);
            categoriesTable.getColumnModel().getColumn(1).setPreferredWidth(10);
            categoriesTable.getColumnModel().getColumn(2).setResizable(false);
            categoriesTable.getColumnModel().getColumn(2).setPreferredWidth(10);
            categoriesTable.getColumnModel().getColumn(3).setResizable(false);
        }

        saveButton.setText("Αποθήκευση");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(categoriesLabel)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(addCategoryButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(categoriesLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addCategoryButton)
                    .addComponent(saveButton)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addCategoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCategoryButtonActionPerformed
        DefaultTableModel model = (DefaultTableModel) categoriesTable.getModel();
        model.addRow(new Object[]{"", "", "", ""});
    }//GEN-LAST:event_addCategoryButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        TableColumn categoriesColumn = categoriesTable.getColumnModel().getColumn(3);
        //  categoriesColumn.setCellEditor(new DefaultCellEditor(comboBox));

        categoryService.save(null, null);
    }//GEN-LAST:event_saveButtonActionPerformed

    private void initCustomComponents() {
        hideElements();
        /*  categoriesPanel.setLayout(new BoxLayout(categoriesPanel, BoxLayout.Y_AXIS));
         categoriesPanel.setAlignmentX(Component.LEFT_ALIGNMENT);*/
    }

    public void repaintSettings(User user) {

        DefaultTableModel model = (DefaultTableModel) categoriesTable.getModel();
        int rowCount = model.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }

        if (user.getCategories().size() > 0) {
            categoriesLabel.setVisible(true);

            if (user.getCategories().size() > 0) {
                drawCategories(user.getCategories(), null);

                //init the dropdown that holds all the categories names
                JComboBox comboBox = new JComboBox();
                for (String category : allCategories) {
                    comboBox.addItem(category);
                }
                TableColumn categoriesColumn = categoriesTable.getColumnModel().getColumn(3);
                categoriesColumn.setCellEditor(new DefaultCellEditor(comboBox));

               categoriesTable.getModel().addTableModelListener(new TableModelListener() {

                    public void tableChanged(TableModelEvent e) {
                        System.out.println(categoriesTable.getModel().getValueAt(e.getFirstRow(), e.getColumn()));
                    }
                });
            }
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
    private void drawCategories(List<Category> categories, String parentName) {
        if (categories.size() == 0) {
            return;
        } else {
            for (Category category : categories) {

                if (parentName != null) {
                    DefaultTableModel model = (DefaultTableModel) categoriesTable.getModel();
                    model.addRow(new Object[]{category.getName(), category.getColumns(), category.getRows(), parentName});
                }
                allCategories.add(category.getName());
                drawCategories(category.getSubCategories(), category.getName());
            }
        }
    }

    public void hideElements() {
        categoriesLabel.setVisible(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addCategoryButton;
    private javax.swing.JLabel categoriesLabel;
    private javax.swing.JTable categoriesTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton saveButton;
    // End of variables declaration//GEN-END:variables
}
