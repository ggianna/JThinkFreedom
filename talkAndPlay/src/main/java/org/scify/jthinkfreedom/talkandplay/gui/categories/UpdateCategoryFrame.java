package org.scify.jthinkfreedom.talkandplay.gui.categories;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.scify.jthinkfreedom.talkandplay.gui.configuration.CommunicationModuleSettingsPanel;
import org.scify.jthinkfreedom.talkandplay.gui.helpers.GuiHelper;
import org.scify.jthinkfreedom.talkandplay.gui.grid.CreateImageFrame;
import org.scify.jthinkfreedom.talkandplay.models.Category;
import org.scify.jthinkfreedom.talkandplay.models.Tile;
import org.scify.jthinkfreedom.talkandplay.models.User;
import org.scify.jthinkfreedom.talkandplay.services.CategoryService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author christina
 */
public class UpdateCategoryFrame extends javax.swing.JFrame {

    private CommunicationModuleSettingsPanel parent;
    private List<Category> allCategories;
    private String categoryImage;
    private GuiHelper guiHelper;
    private CategoryService categoryService;
    private User user;
    private Category category;
    private String categoryName;

    /**
     * Creates new form CreateCategoryFrame
     */
    public UpdateCategoryFrame() {
        initComponents();
    }

    public UpdateCategoryFrame(String categoryName, CommunicationModuleSettingsPanel parent, User user) throws Exception {
        this.parent = parent;
        this.guiHelper = new GuiHelper();
        this.user = user;
        this.categoryName = categoryName;
        this.categoryService = new CategoryService();
        this.allCategories = categoryService.getCategories(user.getName());
        initComponents();
        initCustomComponents();
        setTitle("Επεξεργασία Κατηγορίας - Talk&Play");
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
        imageLabel = new javax.swing.JLabel();
        uploadButton = new javax.swing.JButton();
        nameLabel = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        gridLabel = new javax.swing.JLabel();
        rowsTextField = new javax.swing.JTextField();
        columnsTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        parentLabel = new javax.swing.JLabel();
        parentComboBox = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        imagesTable = new javax.swing.JTable();
        saveButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        addImageButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Talk&Play");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        imageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/scify/jthinkfreedom/talkandplay/resources/no-photo.png"))); // NOI18N

        uploadButton.setBackground(new java.awt.Color(255, 255, 255));
        uploadButton.setForeground(new java.awt.Color(51, 51, 51));
        uploadButton.setText("Ανεβάστε εικόνα");
        uploadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadButtonActionPerformed(evt);
            }
        });

        nameLabel.setText("Όνομα κατηγορίας:");

        nameTextField.setMinimumSize(new java.awt.Dimension(100, 23));

        gridLabel.setText("Πίνακας:");

        jLabel3.setText("x");

        parentLabel.setText("Ανήκει σε:");

        jLabel1.setText("Εικόνες κατηγορίας");

        imagesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Εικόνα", "Όνομα", "Ήχος"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(imagesTable);
        if (imagesTable.getColumnModel().getColumnCount() > 0) {
            imagesTable.getColumnModel().getColumn(0).setResizable(false);
            imagesTable.getColumnModel().getColumn(1).setResizable(false);
            imagesTable.getColumnModel().getColumn(2).setResizable(false);
        }

        saveButton.setBackground(new java.awt.Color(255, 255, 255));
        saveButton.setForeground(new java.awt.Color(51, 51, 51));
        saveButton.setText("Αποθήκευση");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        deleteButton.setBackground(new java.awt.Color(255, 255, 255));
        deleteButton.setForeground(new java.awt.Color(51, 51, 51));
        deleteButton.setText("Διαγραφή");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        addImageButton.setBackground(new java.awt.Color(255, 255, 255));
        addImageButton.setForeground(new java.awt.Color(51, 51, 51));
        addImageButton.setText("Προσθήκη εικόνας");
        addImageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addImageButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(imageLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(nameLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(parentLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(parentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(gridLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rowsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(columnsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel1))
                        .addGap(0, 17, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(uploadButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(deleteButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(addImageButton)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imageLabel)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nameLabel)
                            .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(gridLabel)
                            .addComponent(rowsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(columnsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(parentLabel)
                            .addComponent(parentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(uploadButton)
                    .addComponent(saveButton)
                    .addComponent(deleteButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addImageButton)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        String name = nameTextField.getText();
        int rows = Integer.parseInt(rowsTextField.getText());
        int columns = Integer.parseInt(columnsTextField.getText());

        if (!name.replaceAll("\\s+", "").isEmpty()) {

            Category updatedCategory = new Category(name, rows, columns, categoryImage);
            category.setParentCategory(new Category(parentComboBox.getSelectedItem().toString()));

            try {
                categoryService.update(updatedCategory, user, category.getName());
                parent.drawCategories(categoryService.getCategories(user.getName()));
            } catch (Exception ex) {
                Logger.getLogger(UpdateCategoryFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        dispose();
    }//GEN-LAST:event_saveButtonActionPerformed

    private void uploadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadButtonActionPerformed
        categoryImage = "";
        JFileChooser chooser = new JFileChooser();

        chooser.setDialogTitle("Διαλέξτε εικόνα");
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setFileFilter(new FileNameExtensionFilter("Image Files", "png", "jpg", "jpeg", "JPG", "JPEG", "gif"));
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            categoryImage = chooser.getSelectedFile().getAbsolutePath();
            imageLabel.setIcon(guiHelper.getIcon(categoryImage));
        }
    }//GEN-LAST:event_uploadButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        String name = nameTextField.getText();

        if (!name.replaceAll("\\s+", "").isEmpty()) {

            int dialogResult = JOptionPane.showConfirmDialog(null, "Είστε σίγουροι ότι θέλετε να διαγράψετε την κατηγορία?", "Warning", 0);
            if (dialogResult == JOptionPane.YES_OPTION) {
                try {
                    categoryService.delete(name, user);
                    parent.drawCategories(categoryService.getCategories(user.getName()));
                } catch (Exception ex) {
                    Logger.getLogger(UpdateCategoryFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        dispose();
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void addImageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addImageButtonActionPerformed
        CreateImageFrame createImageFrame = new CreateImageFrame(this, user, category);
        createImageFrame.setLocationRelativeTo(null);
        createImageFrame.setVisible(true);
    }//GEN-LAST:event_addImageButtonActionPerformed

    private void initCustomComponents() throws IOException, Exception {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        category = categoryService.getCategory(categoryName, user.getName());

        nameTextField.setText(category.getName());
        rowsTextField.setText(String.valueOf(category.getRows()));
        columnsTextField.setText(String.valueOf(category.getColumns()));
        imageLabel.setIcon(guiHelper.getIcon((category.getImage())));
        drawTiles();

        if (!category.isEditable()) {
            nameTextField.setEditable(false);
        }

        for (Category c : allCategories) {
            parentComboBox.addItem(c.getName());
        }

        if (category.getParentCategory() != null) {
            parentComboBox.setSelectedItem(category.getParentCategory().getName());
        } else {
            parentComboBox.setSelectedItem("Επικοινωνία");
        }
        parentComboBox.setEnabled(false);
    }

    public void drawTiles() throws Exception {

        DefaultTableModel model = (DefaultTableModel) imagesTable.getModel();

        int rowCount = model.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }

        String parentName;

        for (Tile tile : category.getTiles()) {
            model.addRow(new Object[]{
                tile.getName(),
                tile.getImage(),
                tile.getSound()
            });
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addImageButton;
    private javax.swing.JTextField columnsTextField;
    private javax.swing.JButton deleteButton;
    private javax.swing.JLabel gridLabel;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JTable imagesTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JComboBox parentComboBox;
    private javax.swing.JLabel parentLabel;
    private javax.swing.JTextField rowsTextField;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton uploadButton;
    // End of variables declaration//GEN-END:variables
}
