package Control;

import Model.*;
import java.time.*;
import java.time.chrono.ChronoLocalDateTime;
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
    private Queue<Cliente> listaEspera;
    
    public Control() {
        this.fecha = LocalDate.now();
        this.clientes = new HashMap();
        this.citas = new HashMap();
        this.servicios = new HashMap();
        this.inicializarHorario();
        this.listaEspera = new ConcurrentLinkedQueue();
    }
    
    public static Control getInstance(){
         if (Control.instancia == null) {
             Control.instancia = new Control();
         }
         
         return Control.instancia;
    }
    
    //
    // Cliente
    //
    
    public String crearCliente(String nombre, String email, String telefono) {
        Cliente nuevoCliente = new Cliente(nombre, email, telefono);
    
        if (!nuevoCliente.isEmail(email)) {
            return "Email no válido";
        }
    
        if (!nuevoCliente.isTelefono(telefono)) {
            return "Teléfono no válido";
        }
    
        if (clientes.containsKey(email)) {
            return "Cliente ya existe";
        }
    
        clientes.put(email, nuevoCliente);
        return "Cliente creado exitosamente";
    }

    public String modificarCliente(String nombre, String email, String telefono) {
        Cliente cliente = clientes.get(telefono);
        if (cliente == null) {
            return "Cliente no encontrado";
        }

        cliente.setNombre(nombre);
        cliente.setEmail(email);
        return "Cliente modificado exitosamente";
    }

    public String borrarCliente(String telefono) {
        if (clientes.remove(telefono) != null) {
            return "Cliente borrado exitosamente";
        } else {
            return "Cliente no encontrado";
        }
    }

    public String consultarCliente(String telefono) {
        Cliente cliente = clientes.get(telefono);
        if (cliente == null) {
            return "Cliente no encontrado";
        }

        return cliente.toString();
    }

    public Map<String, String> listaClientes() {
        Map<String, String> lista = new HashMap<>();
        for (Map.Entry<String, Cliente> entry : clientes.entrySet()) {
            lista.put(entry.getKey(), entry.getValue().toString());
        }
        return lista;
    }
    //
    // Cita
    //
    
    public void crearCita(LocalDate fecha, LocalTime hora, String telefonoCliente, String tipoElegido) throws Exception {
        DayOfWeek dia = fecha.getDayOfWeek();
        if (validarDiaYHora(dia, hora) == 1){
            for (Map.Entry<String, Cliente> clientesCita : clientes.entrySet()){
                if (clientesCita.getValue().getTelefono().equals(telefonoCliente)){
                    for (Map.Entry<String, Servicio> serviciosCita : servicios.entrySet()){
                        if (serviciosCita.getKey().equals(tipoElegido)){
                            LocalDateTime fechaYHora = fecha.atTime(hora);
                            Cita cita = new Cita(fechaYHora, clientesCita.getValue(), serviciosCita.getValue());
                            citas.put(cita.getNumero(), cita);
                            break;
                        }
                    }
                    throw new Exception("El servicio no existe");
                
                }
            }
            throw new Exception("El teléfono que proveyó no coincide con ningúno de los teléfonos de los clientes");
        }
    }
    
    private int validarDiaYHora (DayOfWeek dia, LocalTime hora)throws Exception{
        int aprobado = 1;
        for (Map.Entry<DayOfWeek, Dia> dias : horario.entrySet()){
            if (dias.getKey().equals(dia)){
                if (hora.isAfter(dias.getValue().getHoraInicio()) && hora.isBefore(dias.getValue().getHoraCierre())){
                    return aprobado;
                }
                else{
                    aprobado=0;
                    throw new Exception("La hora no está dentro del horario de atención de ese día");
                }
            }
        }
        aprobado=0;
        throw new Exception("El día no está dentro del horario de atencíon");
    }
    
    public int mostrarCitaExistente(int numero, LocalDate fecha, LocalTime hora, Servicio tipoElegido)throws Exception {
        DayOfWeek dia = fecha.getDayOfWeek();
        for (Map.Entry<Integer, Cita> cita : citas.entrySet()){
            if (cita.getKey().equals(numero)){
                System.out.println("Aquí está la información de la cita: ");
                Cita citaAModificar = cita.getValue();
                citaAModificar.toString();
                if (validarDiaYHora(dia, hora) == 1){
                    for (Map.Entry<String, Servicio> servicio : servicios.entrySet()){
                        if (servicio.getValue().equals(tipoElegido)){
                            citaAModificar.setFecha(fecha);
                            citaAModificar.setHora(hora.getHour());
                            citaAModificar.setServicio(tipoElegido);
                            return 1;
                        }
                    }
                    throw new Exception("El servicio no existe dentro del sistema");
                }
            }
        }
        throw new Exception("El número de cita no coincidee con ninguna cita existente");
    }

    public int borrarCita(int numero) throws Exception {
        for (Map.Entry<Integer, Cita> cita : citas.entrySet()){
            if (cita.getKey().equals(numero)){
                Cita citaAEliminar = cita.getValue();
                citas.remove(citaAEliminar);
                //eliminar del calendario
                return 1;
            }
        }
        throw new Exception("El número de cita no coincidee con ninguna cita existente");
    }
    
    public String consultarCita(int numero)throws Exception {
        for (Map.Entry<Integer, Cita> cita : citas.entrySet()){
            if (cita.getKey().equals(numero)){
                Cita citaAMostrar = cita.getValue();
                return citaAMostrar.toString();
            }
        }
        throw new Exception("El número de cita no coincidee con ninguna cita existente");
    }
    
    public int confirmarCita(int numero) throws Exception {
        for (Map.Entry<Integer, Cita> cita : citas.entrySet()){
            if (cita.getKey().equals(numero)){
                Cita citaAConfirmar = cita.getValue();
                citaAConfirmar.setEstado(true);
                return 1;
            }
        }
        throw new Exception("El número de cita no coincidee con ninguna cita existente");
    }
    
    public Map<Integer, String> listaCitas() {
        return new HashMap();
    }
    
    public Map<LocalDateTime, String> verCalendarioCitas(String opcion, LocalDate inicio) {
        Map<LocalDateTime, String> calendario = new HashMap();
        ChronoLocalDateTime fechaInit = ChronoLocalDateTime.from(inicio.atStartOfDay());
        ChronoLocalDateTime fechaFin;
        
        switch (opcion) {
            case "Week" -> {
                fechaFin = ChronoLocalDateTime.from(inicio.plusDays(7).atStartOfDay());
                break;
            }
            case "Month" -> {
                fechaFin = ChronoLocalDateTime.from(inicio.plusMonths(1).atStartOfDay());
                break;
            }
            default -> {
                fechaFin = ChronoLocalDateTime.from(LocalDateTime.now());
                break;
            }
        }
        
        for (Map.Entry<Integer, Cita> entry : this.citas.entrySet()) {
            Cita cita = entry.getValue();
            LocalDateTime fecha = cita.getFecha();
            
            if (fecha.isAfter(fechaInit) & fecha.isBefore(fechaFin)) {
                calendario.put(fecha, cita.toString());
            }
            
        }
        
        return calendario;
        
    }
    
    //
    // Servicio
    //
    
    public void crearServicio(String tipo, String descripcion) {
        System.out.println("Esta es la lista de servicios");
        servicios.toString();
        Servicio servicio = new Servicio(tipo, descripcion);
        servicios.put(tipo, servicio);
    }
    
    public int modificarServicio(String tipo, String descripcion)throws Exception {
        System.out.println("Esta es la lista de servicios");
        servicios.toString();
        for (Map.Entry<String, Servicio> servicio : servicios.entrySet()){
            if (servicio.getKey().equals(tipo)){
                Servicio servicioAModificar = servicio.getValue();
                servicioAModificar.setTipo(tipo);
                servicioAModificar.setDescripcion(descripcion);
                return 1;
            }
        }
        throw new Exception ("El tipo de servicio elegido no existe");
    }
    
    public int borrarServicio(String tipo)throws Exception {
        System.out.println("Esta es la lista de servicios");
        servicios.toString();
        for (Map.Entry<String, Servicio> servicio : servicios.entrySet()){
            if (servicio.getKey().equals(tipo)){
                for (Map.Entry<Integer, Cita> cita : citas.entrySet()){
                    if (cita.getValue().getServicio().getTipo().equals(tipo)){
                        throw new Exception ("El tipo de servicio elegido está siendo utilizado por una cita");
                    }
                }
                Servicio servicioABorrar = servicio.getValue();
                servicios.remove(servicioABorrar);
                return 1;
            }
        }
        throw new Exception ("El tipo de servicio elegido no existe");
    }
    
    public String consultarServicio(String tipo)throws Exception {
        System.out.println("Esta es la lista de servicios");
        servicios.toString();
        for (Map.Entry<String, Servicio> servicio : servicios.entrySet()){
            if (servicio.getKey().equals(tipo)){
                Servicio servicioAMostrar = servicio.getValue();
                return servicioAMostrar.toString();
            }
        }
        throw new Exception ("El tipo de servicio elegido no existe");
    }
    
    public Map<String, String> listaServicio() {
        return new HashMap();
    }
    
    //
    // Lista Espera
    //
    
    public ConcurrentLinkedQueue<Cliente> mostrarListaEspera() {
        return (ConcurrentLinkedQueue<Cliente>) this.listaEspera;
    }
    
    public void agregarListaEspera(String telefono) {
        for (Map.Entry<String, Cliente> cliente : clientes.entrySet()){
            if (cliente.getValue().getTelefono().equals(telefono)){
                listaEspera.add(cliente.getValue());
            }
        }
    }
    
    public void borrarListaEspera(Cliente cliente) {
        listaEspera.remove(cliente);
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
        this.horario = new HashMap();
       
        for (DayOfWeek diaSemana : DayOfWeek.values()) {
            Dia dia = new Dia(diaSemana, inicio, cierre);
            this.horario.put(diaSemana, dia);
        }
    }
    
}