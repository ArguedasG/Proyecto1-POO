package UI.listaEspera;

import UI.MainFrame;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class ListaOptionPane {
    
    public static void agregarLista() {
        InsLista dialog = new InsLista(MainFrame.getInstance(), true);
        dialog.setVisible(true);
    }
    
    public static void finalMessage(JDialog dialog, String mensaje) {
        dialog.dispose();
        JOptionPane.showMessageDialog(MainFrame.getInstance(), mensaje);
    }
    
    public static String getNombreCliente(String cliente) {
        Pattern patron = Pattern.compile("(\\w+)=([^,]+)");
        Matcher valores = patron.matcher(cliente);
        
        while (valores.find()) {
            if (valores.group(1).equals("nombre")) {
                return valores.group(2);
            }
        }
        
        return "";
    }
    
}
