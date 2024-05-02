package Application;

import Control.Control;
import UI.MainFrame;

public class Proyecto0 {

    public static void main(String[] args) {
        try {
            Control.cargarDatos();
        } catch (Exception e) {
            System.out.println("No se cargó");
        }
        
        MainFrame.main(args);
    }
}
