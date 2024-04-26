/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

/**
 *
 * @author samia
 */
public class Cita {
    private static int consecutivo = 0;
    private int numero;
    private LocalDateTime fecha;
    private boolean estado;

    public Cita(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public int getNumero() {
        return numero;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    
    public DayOfWeek getDia(){
        DayOfWeek dia = this.fecha.getDayOfWeek();
        return dia;
    }
    
    public int getHora(){
        int hora = this.fecha.getHour();
        return hora;
    }
    
    public void setHora(int hora){
        this.fecha.withHour(hora);
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    public void confirmar(){
        
    }

    @Override
    public String toString() {
        return "Cita{" + "numero=" + numero + ", fecha=" + fecha + ", estado=" + estado + '}';
    }
    
}
