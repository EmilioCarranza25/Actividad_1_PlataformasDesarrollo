/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacionfarmacia;

import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author eacarranza
 */
public class OrderForm extends JFrame {
    private JTextField txtMedicineName;
    private JComboBox<String> cmbMedicineType;
    private JTextField txtQuantity;
    private JRadioButton rbtnCofarma;
    private JRadioButton rbtnEmpsephar;
    private JRadioButton rbtnCemefar;
    private JCheckBox chkPrincipal;
    private JCheckBox chkSecondary;
    private JButton btnClear;
    private JButton btnConfirm;
    
    public OrderForm() {
        setTitle("Pedido de Medicamentos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initComponents();
        addComponents();
        addListeners();
    }
    
    private void initComponents() {
        txtMedicineName = new JTextField(20);
        
        //Coleccion de tipos de MEdicamentos para la seleccion
        String[] medicineTypes = {"Analgesico", "Analeptico", "Anestesico", "Antiacido", "Antidepresivo", "Antibiotico"};
        cmbMedicineType = new JComboBox<>(medicineTypes);
        
        txtQuantity = new JTextField(20);
        
        rbtnCofarma = new JRadioButton("Cofarma");
        rbtnEmpsephar = new JRadioButton("Empsephar");
        rbtnCemefar = new JRadioButton("Cemefar");
        
        ButtonGroup distributorGroup = new ButtonGroup();
        distributorGroup.add(rbtnCofarma);
        distributorGroup.add(rbtnEmpsephar);
        distributorGroup.add(rbtnCemefar);
        
        chkPrincipal = new JCheckBox("Farmacia Principal");
        chkSecondary = new JCheckBox("Farmacia Secundaria");
        
        btnClear = new JButton("Borrar");
        btnConfirm = new JButton("Confirmar");
    }
    
    private void addComponents() {
        setLayout(new GridLayout(7, 2));
        
        add(new JLabel("Nombre del Medicamento:"));
        add(txtMedicineName);
        
        add(new JLabel("Tipo de Medicamento:"));
        add(cmbMedicineType);
        
        add(new JLabel("Cantidad de Producto:"));
        add(txtQuantity);
        
        add(new JLabel("Distribuidor:"));
        JPanel distributorPanel = new JPanel();
        distributorPanel.add(rbtnCofarma);
        distributorPanel.add(rbtnEmpsephar);
        distributorPanel.add(rbtnCemefar);
        add(distributorPanel);
        
        add(new JLabel("Sucursal:"));
        JPanel branchPanel = new JPanel();
        branchPanel.add(chkPrincipal);
        branchPanel.add(chkSecondary);
        add(branchPanel);
        
        add(btnClear);
        add(btnConfirm);
    }
    
    private void addListeners() {
        btnClear.addActionListener(e -> clearForm());
        btnConfirm.addActionListener(e -> validateAndSubmitForm());
    }
    
    private void clearForm() {
        txtMedicineName.setText("");
        cmbMedicineType.setSelectedIndex(0);
        txtQuantity.setText("");
        rbtnCofarma.setSelected(false);
        rbtnEmpsephar.setSelected(false);
        rbtnCemefar.setSelected(false);
        chkPrincipal.setSelected(false);
        chkSecondary.setSelected(false);
    }
    
    private void validateAndSubmitForm() {
        String medicineName = txtMedicineName.getText().trim();
        String medicineType = (String) cmbMedicineType.getSelectedItem();
        String quantityStr = txtQuantity.getText().trim();
        String distributor = null;
        if (rbtnCofarma.isSelected()) distributor = "Cofarma";
        else if (rbtnEmpsephar.isSelected()) distributor = "Empsephar";
        else if (rbtnCemefar.isSelected()) distributor = "Cemefar";
        
        boolean isPrincipalSelected = chkPrincipal.isSelected();
        boolean isSecondarySelected = chkSecondary.isSelected();
        
        if (medicineName.isEmpty() || !medicineName.matches("[a-zA-Z0-9 ]+")) {
            showError("Nombre del medicamento inválido.");
            return;
        }
        if (medicineType == null) {
            showError("Debe seleccionar un tipo de medicamento.");
            return;
        }
        int quantity;
        try {
            quantity = Integer.parseInt(quantityStr);
            if (quantity <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            showError("Cantidad de producto inválida.");
            return;
        }
        if (distributor == null) {
            showError("Debe seleccionar un distribuidor.");
            return;
        }
        if (!isPrincipalSelected && !isSecondarySelected) {
            showError("Debe seleccionar una sucursal.");
            return;
        }
        
        String branches = "";
        if (isPrincipalSelected) branches += "Farmacia Principal ";
        if (isSecondarySelected) branches += "y Farmacia Secundaria";
        
        new OrderSummary(distributor, medicineName, medicineType, quantity, branches).setVisible(true);
    }
    
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public void createAndShowGUI() {
        setVisible(true);
    }
    
}
    

