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
        // Verifica si el teléfono es nulo
        if (telefono == null) {
            return false;
        }
    
        // Expresión regular que coincide con el formato de número de teléfono
        String regex = "^\\+?\\d*-?\\d+$";
    
        // Devuelve true si el número de teléfono coincide con la expresión regular, false en caso contrario
        return telefono.matches(regex);
    }
    
    public boolean isEmail(String email){
        // Verifica si el correo electrónico es nulo
        if (email == null) {
            return false;
        }
    
        // Expresión regular que coincide con el formato de correo electrónico
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
