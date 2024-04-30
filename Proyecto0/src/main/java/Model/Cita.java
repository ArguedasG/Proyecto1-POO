package Model;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Cita implements Serializable {
    private static int consecutivo = 0;
    private int numero;
    private LocalDateTime fecha;
    private boolean estado;
    private Cliente cliente = null;
    private Servicio servicio;
    private String emailFrom = "barberialosmechudos@gmail.com";
    private String passwordFrom = "uhpl cxnl eqsz hyrb";
    private String emailTo;
    private String subject = "CONFIRMAR VISITA";
    private String content = "Saludos estimado cliente de Barberia Los Mechudos, "
            + "por este medio le indicamos que debe comunicarse lo más antes "
            + "posible con nosotros para poder confirmar su cita, "
            + "la fecha límite de confirmación es hoy antes de las 11:59pm, un saludo!";
    
    
    private Properties mProperties;
    private Session mSession;
    private MimeMessage mCorreo;

    public Cita(LocalDateTime fecha, Cliente cliente, Servicio servicio) {
        this.fecha = fecha;
        this.cliente = cliente;
        this.estado = false;
        this.numero = consecutivo;
        this.servicio = servicio;
        this.emailTo = cliente.getEmail();
        consecutivo++;
        mProperties = new Properties();
        
    }
    

    public int getNumero() {
        return numero;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha.withYear(fecha.getYear());
        this.fecha.withMonth(fecha.getMonthValue());
        this.fecha.withDayOfMonth(fecha.getDayOfMonth());
    }
    
    public boolean isEstado() {
        return estado;
    }
    
    public void setEstado(boolean estado) {
        this.estado = true
                ;
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

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }
    
    
    public void confirmar(){
        mProperties.put("mail.smtp.host", "smtp.gmail.com");
        mProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        mProperties.setProperty("mail.smtp.starttls.enable", "true");
        mProperties.setProperty("mail.smtp.port", "587");
        mProperties.setProperty("mail.smtp.user", emailFrom);
        mProperties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        mProperties.setProperty("mail.smtp.auth", "true");
        
        mSession = Session.getDefaultInstance(mProperties);
        try {
            mCorreo = new MimeMessage(mSession);
            mCorreo.setFrom(new InternetAddress(emailFrom));
            mCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            mCorreo.setSubject(subject);
            mCorreo.setText(content, "ISO-8859-1", "html");
            
            
        } catch (AddressException ex) {
            Logger.getLogger(Cita.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(Cita.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            Transport mTransport = mSession.getTransport("smtp");
            mTransport.connect(emailFrom, passwordFrom);
            mTransport.sendMessage(mCorreo, mCorreo.getRecipients(Message.RecipientType.TO));
            mTransport.close();
            System.out.println("Correo enviado");
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(Cita.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(Cita.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        return "Cita{" + "numero=" + numero + ", fecha=" + fecha + ", estado=" + estado + '}';
    }
    
}
