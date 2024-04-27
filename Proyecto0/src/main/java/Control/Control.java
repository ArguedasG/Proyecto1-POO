package Control;

import Model.*;
import java.time.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Control {
    private static Control instancia;
    private LocalDate fecha;
    private Map<String, Cliente> clientes;
    private Map<Integer, Cita> citas;
    private Map<String, Servicio> servicios;
    private Map<DayOfWeek, Dia> horario;
    private Queue<Cita> listaEspera;
    
    private Control() {
        this.fecha = LocalDate.now();
        this.clientes = new HashMap();
        this.citas = new HashMap();
        this.servicios = new HashMap();
        this.inicializarHorario();
        this.listaEspera = new ConcurrentLinkedQueue();
    }
    
    public Control getInstance(){
         if (Control.instancia == null) {
             Control.instancia = new Control();
         }
         
         return Control.instancia;
    }
    
    //
    // Cliente
    //
    
    public String crearCliente(String nombre, String email, String telefono) {
        return new String();
    }
    
    public void modificarCliente(String nombre, String email, String telefono) {
        
    }
    
    public void borrarCliente(String telefono) {
        
    }
    
    public String consultarCliente(String telefono) {
        return new String();
    }
    
    public Map<String, String> listaClientes() {
        return new HashMap();
    }
    
    //
    // Cita
    //
    
    public void crearCita(LocalDate fecha, LocalTime hora, String telefonoCliente, String tipoElegido) {
        
    }
    
    public void modificarCita(int numero, LocalDate fecha, LocalTime hora, String tipoElegido) {
        
    }
    
    public void borrarCita(int numero) {
        
    }
    
    public String consultarCita(int numero) {
        return new String();
    }
    
    public void confirmarCita(int numero) {
        
    }
    
    public Map<Integer, String> listaCitas() {
        return new HashMap();
    }
    
    public Map<LocalDateTime, String> verCalendarioCitas(String opcion, LocalDate inicio){
        Map<LocalDateTime, String> calendario = new HashMap();
        return new HashMap();
    }
    
    //
    // Servicio
    //
    
    public int crearServicio(String tipo, String descripcion) {
        return 0;
    }
    
    public void modificarServicio(String tipo, String descripcion) {
        
    }
    
    public void borrarServicio(String tipo) {
        
    }
    
    public String consultarServicio(String tipo) {
        return new String();
    }
    
    public Map<String, String> listaServicio() {
        return new HashMap();
    }
    
    //
    // Lista Espera
    //
    
    public void crearListaEspera() {
        
    }
    
    public Queue<String> modificarListaEspera() {
        return new ConcurrentLinkedQueue();
    }
    
    public void borrarListaEspera() {
        
    }
    
    public void enviarNotificacion() {
        
    }
    
    //
    // Horario
    //
    
    public void establecerHorario(String diaSemana, LocalTime inicio, LocalTime cierre) {
        Dia dia = this.horario.get(DayOfWeek.valueOf(diaSemana));
        dia.setHoraInicio(inicio);
        dia.setHoraCierre(cierre);
    }
    
    public Map<String, LocalTime[]> consultarHorario() {
        Set<DayOfWeek> keys= this.horario.keySet();
        Map<String, LocalTime[]> listaHorarios = new HashMap();
        
        for (DayOfWeek llave : keys) {
            Dia dia = this.horario.get(llave);
            LocalTime[] lapso = {dia.getHoraInicio(), dia.getHoraCierre()};
            listaHorarios.put(String.valueOf(llave), lapso);
        }
        
        return listaHorarios;
    }
    
    //
    // Otros
    //
    
    private void inicializarHorario() {
        LocalTime inicio = LocalTime.of(6, 0);
        LocalTime cierre = LocalTime.of(18, 0);
       
        for (DayOfWeek diaSemana : DayOfWeek.values()) {
            Dia dia = new Dia(diaSemana, inicio, cierre);
            this.horario.put(diaSemana, dia);
        }
    }
    
}