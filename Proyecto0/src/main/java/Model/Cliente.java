package Model;

import java.util.ArrayList;

public class Cliente {
    private String nombre;
    private String email;
    private String telefono;
    private ArrayList<Cita> citas = new ArrayList<Cita>();


    public Cliente(String nombre, String email, String telefono) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isTelefono(String telefono){
        // Expresión regular que coincide con el formato de número de teléfono
        // ^\+? indica que el número puede comenzar con un '+' opcional
        // \d* indica cualquier número de dígitos
        // -? indica un guión opcional
        // \d+ indica uno o más dígitos
        // $ indica el final de la línea
        String regex = "^\\+?\\d*-?\\d+$";
    
        // Devuelve true si el número de teléfono coincide con la expresión regular, false en caso contrario
        return telefono.matches(regex);
    }
    
    public boolean isEmail(String email){
        // Expresión regular que coincide con el formato de correo electrónico
        // ^ indica el inicio de la línea
        // [a-zA-Z0-9._%+-]+ indica uno o más caracteres alfanuméricos, '.', '_', '%', '+' o '-'
        // @ indica un arroba
        // [a-zA-Z0-9.-]+ indica uno o más caracteres alfanuméricos, '.' o '-'
        // \. indica un punto
        // [a-zA-Z]{2,} indica dos o más caracteres alfabéticos
        // $ indica el final de la línea
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        
        // Devuelve true si el correo electrónico coincide con la expresión regular, false en caso contrario
        return email.matches(regex);
    }

    public void agregarCita(Cita cita){
        citas.add(cita);
    }

    public void borrarCita(Cita cita){
        citas.remove(cita);
    }

    public ArrayList<Cita> getCitas(Cita cita){
        return citas;
    }

    @Override
    public String toString() {
        return "Cliente{" + "nombre=" + nombre + ", email=" + email + ", telefono=" + telefono + '}';
    }

}
