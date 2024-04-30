package UI.servicio;

import UI.MainFrame;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class ServicioOptionPane {
    
    public static void insertarServicio() {
        InsServicio dialog = new InsServicio(MainFrame.getInstance(), true);
        dialog.setVisible(true);
    }
    
    public static void modificarServicio() {
        ModServicio dialog = new ModServicio(MainFrame.getInstance(), true);
        dialog.setVisible(true);
    }
    
    public static void borrarServicio() {
        DelServicio dialog = new DelServicio(MainFrame.getInstance(), true);
        dialog.setVisible(true);
    }
    
    public static void consultarServicio() {
        ConServicio dialog = new ConServicio(MainFrame.getInstance(), true);
        dialog.setVisible(true);
    }
    
    public static void finalMessage(JDialog dialog, String mensaje) {
        dialog.dispose();
        JOptionPane.showMessageDialog(MainFrame.getInstance(), mensaje);
    }
    
}
