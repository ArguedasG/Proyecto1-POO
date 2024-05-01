package Application;

import Control.Control;
import UI.MainFrame;

public class Proyecto0 {

    public static void main(String[] args) {
        
        try {
            Control.getInstance().cargarDatos();
        } catch (Exception e) {
            
        }
        
        MainFrame.main(args);
        
        try {
            Control.getInstance().salvarDatos();
        } catch (Exception e) {
            
        }
        
    }
}
