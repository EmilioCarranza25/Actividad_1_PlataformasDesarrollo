/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package aplicacionfarmacia;

/**
 *
 * @author eacarranza
 */
public class AplicacionFarmacia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        javax.swing.SwingUtilities.invokeLater(() -> {
            new OrderForm().createAndShowGUI();
        });
        
    }
    
}
