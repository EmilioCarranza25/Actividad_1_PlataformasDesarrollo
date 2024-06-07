package aplicacionfarmacia;


import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author eacarranza
 */
public class OrderSummary extends JFrame{
    
    private JLabel lblSummary;
    private JButton btnCancel;
    private JButton btnSend;
    
    public OrderSummary(String distributor, String medicineName, String medicineType, int quantity, String branches) {
        setTitle("Pedido al distribuidor " + distributor);
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        lblSummary = new JLabel("<html>" + quantity + " unidades del " + medicineType + " " + medicineName + 
            "<br>Para la farmacia situada en " + (branches.contains("Principal") ? "Calle de la Rosa n. 28" : "") +
            (branches.contains("Secundaria") ? " y para la situada en Calle Alcazabilla n. 3" : "") + "</html>");
        
        btnCancel = new JButton("Cancelar");
        btnSend = new JButton("Enviar");
        
        setLayout(new BorderLayout());
        add(lblSummary, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnCancel);
        buttonPanel.add(btnSend);
        add(buttonPanel, BorderLayout.SOUTH);
        
        addListeners();
    }
    
    private void addListeners() {
        btnCancel.addActionListener(e -> dispose());
        btnSend.addActionListener(e -> {
            System.out.println("Pedido enviado");
            dispose();
        });
    }
    
}
