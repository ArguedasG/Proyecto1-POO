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
    
    public static void borrarLista() {
        DelLista dialog = new DelLista(MainFrame.getInstance(), true);
        dialog.setVisible(true);
    }
    
    public static void mostrarLista() {
        MostrarLista dialog = new MostrarLista(MainFrame.getInstance(), true);
        dialog.setVisible(true);
    }
    
    public static void finalMessage(JDialog dialog, String mensaje) {
        dialog.dispose();
        JOptionPane.showMessageDialog(MainFrame.getInstance(), mensaje);
    }
    
    public static String getValorCliente(String valor, String cliente) {
        Pattern patron = Pattern.compile("(\\w+)=([^,]+)");
        Matcher valores = patron.matcher(cliente);
        
        while (valores.find()) {
            if (valores.group(1).equals(valor)) {
                return valores.group(2);
            }
        }
        
        return "";
    }
    
    public static String getNombreCliente(String cliente) {
        return getValorCliente("nombre", cliente);
    }
    
    public static String getEmailCliente(String cliente) {
        return getValorCliente("email", cliente);
    }
    
}
