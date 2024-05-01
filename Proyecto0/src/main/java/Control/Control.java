package Control;

import Model.*;
import java.io.*;
import java.time.*;
import java.time.chrono.ChronoLocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Control implements Serializable {
    private static Control instancia;
    private LocalDate fecha;
    private Map<String, Cliente> clientes;
    private Map<Integer, Cita> citas;
    private Map<String, Servicio> servicios;
    private Map<DayOfWeek, Dia> horario;
    private Queue<Cliente> listaEspera;
    
    private Control() {
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

    public String modificarCliente(String newEmail, String telefono, String email) {
        Cliente cliente = clientes.get(email);
    
        if (!cliente.isEmail(newEmail)) {
            return "Email no válido";
        }
    
        if (!cliente.isTelefono(telefono)) {
            return "Teléfono no válido";
        }
    
        cliente.setTelefono(telefono);
        cliente.setEmail(newEmail);
        return "Cliente modificado exitosamente";
    }
    
    public String borrarCliente(String email) {
        if (clientes.remove(email) != null) {
            return "Cliente borrado exitosamente";
        } else {
            return "Cliente no encontrado";
        }
    }
    
    public String consultarCliente(String email) {
        Cliente cliente = clientes.get(email);
        this.clientes.size();
        if (cliente == null) {
            return "Cliente no encontrado";
        }
    
        return cliente.toString();
    }

    public Map<String, String> listaClientes() {
        Map<String, String> lista = new HashMap<>();
        for (Map.Entry<String, Cliente> entry : clientes.entrySet()) {
            String detalleCliente = entry.getValue().getNombre() + ", " + entry.getValue().getTelefono();
            lista.put(entry.getKey(), detalleCliente);
        }
        return lista;
    }
    
    //
    // Cita
    //
    
    public void crearCita(LocalDate fecha, LocalTime hora, String telefonoCliente, String tipoElegido) throws Exception {
        DayOfWeek dia = fecha.getDayOfWeek();
        LocalDateTime fechaAValidar = LocalDateTime.of(fecha.getYear(), fecha.getMonthValue(), fecha.getDayOfMonth(), hora.getHour(), hora.getMinute());
        if (validarDiaHoraYFecha(dia, hora, fechaAValidar) == 1){
            boolean clienteEncontrado = false;
            boolean servicioEncontrado = false;
            for (Map.Entry<String, Cliente> clientesCita : clientes.entrySet()){
                if (clientesCita.getValue().getTelefono().equals(telefonoCliente)){
                    clienteEncontrado = true;
                    for (Map.Entry<String, Servicio> serviciosCita : servicios.entrySet()){
                        if (serviciosCita.getKey().equals(tipoElegido)){
                            LocalDateTime fechaYHora = fecha.atTime(hora);
                            Cita cita = new Cita(fechaYHora, clientesCita.getValue(), serviciosCita.getValue());
                            citas.put(cita.getNumero(), cita);
                            servicioEncontrado = true;
                            break;
                        }
                    }
                    if (!servicioEncontrado) {
                        throw new Exception("El servicio no existe");
                    }
                    break;
                }
            }
            if (!clienteEncontrado) {
                throw new Exception("El teléfono que proveyó no coincide con ningúno de los teléfonos de los clientes");
            }
        }
    }
    
    private int validarDiaHoraYFecha (DayOfWeek dia, LocalTime hora, LocalDateTime fechaValidar)throws Exception{
        int aprobado = 0;
        LocalDateTime fechaAValidar = fechaValidar;
        for (Map.Entry<DayOfWeek, Dia> dias : horario.entrySet()){
            if (dias.getKey().equals(dia)){
                if (hora.isAfter(dias.getValue().getHoraInicio()) && hora.isBefore(dias.getValue().getHoraCierre())){
                    for (Map.Entry<Integer, Cita> cita : citas.entrySet()){
                        Cita citaAValidar = cita.getValue();
                        if (citaAValidar.getFecha().equals(fechaAValidar)){
                            throw new Exception("La fecha de la cita ya se encuentra ocupada");
                        }
                    }
                    aprobado = 1;
                    return aprobado;
                    
                }
                else{
                    throw new Exception("La hora no está dentro del horario de atención de ese día");
                }
                
            }
        }
        throw new Exception("El día no está dentro del horario de atencíon");
    }
    
    private int validarDiaYHora (DayOfWeek dia, LocalTime hora)throws Exception{
        int aprobado = 0;
        for (Map.Entry<DayOfWeek, Dia> dias : horario.entrySet()){
            if (dias.getKey().equals(dia)){
                if (hora.isAfter(dias.getValue().getHoraInicio()) && hora.isBefore(dias.getValue().getHoraCierre())){
                    aprobado = 1;
                    return aprobado;
                }
                else{
                    throw new Exception("La hora no está dentro del horario de atención de ese día");
                }
                
            }
        }
        throw new Exception("El día no está dentro del horario de atencíon");
    }
    
    public int modificarCitaExistente(int numero, LocalDate fecha, LocalTime hora, Servicio tipoElegido)throws Exception {
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
        Map<Integer, String> lista = new HashMap<>();
        for (Map.Entry<Integer, Cita> entry : citas.entrySet()) {
            lista.put(entry.getKey(), entry.getValue().toString());
        }
        return lista;
    }
    
    
    public void enviarNotificacion()throws Exception{
        ArrayList<Cita> citasANotificar = new ArrayList<Cita>();
        LocalDateTime fecha = LocalDateTime.now();
        int año = fecha.getYear();
        int diaDelAño = fecha.getDayOfYear();
        if (diaDelAño == 365){
            fecha.withYear(año++);
            fecha.withDayOfYear(1);
            for (Map.Entry<Integer, Cita> cita : citas.entrySet()){
                if (cita.getValue().getFecha().getYear() == fecha.getYear() && cita.getValue().getFecha().getDayOfYear() == fecha.getDayOfYear()){
                           citasANotificar.add(cita.getValue());
                }
            }
            if (citasANotificar.isEmpty()){
                throw new Exception("No hay citas para el dia de mañana");
            }
            else{
                for (Cita citaANotificar : citasANotificar){
                    try {
                        citaANotificar.confirmar();
                    }catch (Exception ex){
                        
                    }
                }
            }
        }
        else{
            fecha.withDayOfYear(diaDelAño++);
            for (Map.Entry<Integer, Cita> cita : citas.entrySet()){
                if (cita.getValue().getFecha().getYear() == fecha.getYear() && cita.getValue().getFecha().getDayOfYear() == fecha.getDayOfYear()){
                           citasANotificar.add(cita.getValue());
                }
            }
            if (citasANotificar.isEmpty()){
                throw new Exception("No hay citas para el dia de mañana");
            }
            else{
                for (Cita citaANotificar : citasANotificar){
                    try {
                        citaANotificar.confirmar();
                    }catch (Exception ex){
                        
                    }
                }
            }
        }
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
    
    public void modificarServicio(String tipo, String descripcion)throws Exception {
        System.out.println("Esta es la lista de servicios");
        servicios.toString();
        
        if (this.servicios.keySet().contains(tipo)){
            Servicio servicioAModificar = this.servicios.get(tipo);
            servicioAModificar.setTipo(tipo);
            servicioAModificar.setDescripcion(descripcion);
        } else {
            throw new Exception ("El tipo de servicio elegido no existe");
        }
    }
    
    public void borrarServicio(String tipo)throws Exception {
        System.out.println("Esta es la lista de servicios");
        servicios.toString();
        
        if (this.servicios.keySet().contains(tipo)){
            for (Map.Entry<Integer, Cita> cita : citas.entrySet()){
                if (cita.getValue().getServicio().getTipo().equals(tipo)){
                    throw new Exception ("El tipo de servicio elegido está siendo utilizado por una cita");
                }
            }
            
            servicios.remove(tipo);
        } else {
            throw new Exception ("El tipo de servicio elegido no existe");
        }
    }
    
    public String consultarServicio(String tipo)throws Exception {
        System.out.println("Esta es la lista de servicios");
        servicios.toString();
        
        if (this.servicios.keySet().contains(tipo)){
            Servicio servicioAMostrar = this.servicios.get(tipo);
            return servicioAMostrar.toString();
        } else {
            throw new Exception ("El tipo de servicio elegido no existe");
        }
    }
    
    public List<String> listaServicio() {
        Set<String> keys = servicios.keySet();
        List<String> serv = new ArrayList(keys);
            
        return serv;
    }

    public Servicio getServicioPorNombre(String nombre) throws Exception {
        Servicio servicio = this.servicios.get(nombre);
        if (servicio == null) {
            throw new Exception("No se encontró ningún servicio con el nombre: " + nombre);
        }
        return servicio;
    }
    
    //
    // Lista Espera
    //
    
    public ArrayList<String> mostrarListaEspera() {
        ArrayList<String> lista = new ArrayList();
        
        for (Cliente cliente : this.listaEspera) {
            lista.add(cliente.toString());
        }
        
        return lista;
    }
    
    public void agregarListaEspera(String email) throws Exception {
        Cliente cliente = this.getCliente(email);
        
        if (this.listaEspera.contains(cliente)) {
            throw new Exception("El cliente ya está en la lista de espera");
        }
        
        this.listaEspera.add(cliente);
    }
    
    public void borrarListaEspera(String email) throws Exception {
        Cliente cliente = this.getCliente(email);
        
        listaEspera.remove(cliente);
    }
    
    
    //
    // Horario
    //
    
    public void establecerHorario(DayOfWeek diaSemana, String inicio, String cierre) {
        Dia dia = this.horario.get(diaSemana);
        dia.setHoraInicio(LocalTime.parse(inicio));
        dia.setHoraCierre(LocalTime.parse(cierre));
    }
    
    public Map<DayOfWeek, String[]> consultarHorario() {
        Map<DayOfWeek, String[]> listaHorarios = new HashMap();
        
        for (Map.Entry<DayOfWeek, Dia> entry : this.horario.entrySet()) {
            Dia dia = entry.getValue();
            String[] lapso = {dia.getHoraInicio().toString(), dia.getHoraCierre().toString()};
            listaHorarios.put(entry.getKey(), lapso);
        }
        
        return listaHorarios;
    }
    
    //
    // Otros
    //
    
    private Cliente getCliente(String email) throws Exception {
        if (!this.clientes.keySet().contains(email)) {
            throw new Exception("Ningún cliente está registrado con el correo " + email);
        }
        
        return this.clientes.get(email);
    }
    
    private void inicializarHorario() {
        LocalTime inicio = LocalTime.of(6, 0);
        LocalTime cierre = LocalTime.of(18, 0);
        this.horario = new HashMap();
       
        for (DayOfWeek diaSemana : DayOfWeek.values()) {
            Dia dia = new Dia(diaSemana, inicio, cierre);
            this.horario.put(diaSemana, dia);
        }
    }
    
    public void salvarDatos() throws FileNotFoundException, IOException, ClassNotFoundException {
        String dir = System.getProperty("user.dir") + "\\src\\main";
        File carpeta = new File(dir + "\\respaldo");
        carpeta.mkdir();
        FileInputStream archivo = new FileInputStream(carpeta.getAbsolutePath() + "\\Data.bin");
        
        ObjectInputStream objeto = new ObjectInputStream(archivo);
        Control objetoControl = (Control) objeto.readObject();
        objeto.close();
        
        Control.instancia = objetoControl;
    }
    
    public void cargarDatos() throws FileNotFoundException, IOException, ClassNotFoundException {
        String dir = System.getProperty("user.dir") + "\\src\\main";
        File carpeta = new File(dir + "\\respaldo");
        FileOutputStream archivo = new FileOutputStream(carpeta.getAbsolutePath() + "\\Data.bin");
        
        ObjectOutputStream objeto = new ObjectOutputStream(archivo);
        objeto.writeObject(Control.instancia);
        objeto.close();
    }
    
}