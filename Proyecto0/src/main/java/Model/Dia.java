package Model;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Dia {
    private DayOfWeek dia;
    private LocalTime inicio;
    private LocalTime cierre;
    
    public Dia(DayOfWeek dia, LocalTime inicio, LocalTime cierre) {
        this.dia = dia;
        this.inicio = inicio;
        this.cierre = cierre;
    }
    
    public LocalTime getHoraInicio() {
        return this.inicio;
    }
    
    public void setHoraInicio(LocalTime inicio) {
        this.inicio = inicio;
    }
    
    public LocalTime getHoraCierre() {
        return this.cierre;
    }
    
    public void setHoraCierre(LocalTime cierre) {
        this.cierre = cierre;
    }
    
    public DayOfWeek getDia() {
        return this.dia;
    }
    
    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm");
        return (dia.toString() + ": " + this.inicio.format(formato) + " - " +
                this.cierre.format(formato));
    }
    
}
