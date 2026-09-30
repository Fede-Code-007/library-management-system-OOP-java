package biblioteca;

import java.util.Calendar;

/**
 * Representación de una clase Prestamo
 */
public class Prestamo
{
    Calendar fechaRetiro;
    Calendar fechaDevolucion;
    Socio socio;
    Libro libro;

    /**
     * Constructor de objectos de clase Prestamo
     */
    public Prestamo(Calendar p_fechaRetiro,Socio p_socio,Libro p_libro){
        this.setFechaRetiro(p_fechaRetiro);
        this.setSocio(p_socio);
        this.setLibro(p_libro);
        this.setFechaDevolucion(null);
    }

    //Accesos
    private void setFechaRetiro(Calendar p_fechaRetiro){
        this.fechaRetiro = p_fechaRetiro;
    }
    
    public Calendar getFechaRetiro(){
        return this.fechaRetiro;
    }
    
    private void setFechaDevolucion(Calendar p_fechaDevolucion){
        this.fechaDevolucion = p_fechaDevolucion;
    }
    
    public Calendar getFechaDevolucion(){
        return this.fechaDevolucion;
    }
    
    private void setSocio(Socio p_socio){
        this.socio = p_socio;
    }
    
    public Socio getSocio(){
        return this.socio;
    }
    
    private void setLibro(Libro p_libro){
        this.libro = p_libro;
    }
    
    public Libro getLibro(){
        return this.libro;
    }
    
    /**
     * Registra la fecha de devolución
     * 
     * @param p_fechaDevolucion fecha de devolucion a registrar
     */
    public void registrarFechaDevolucion(Calendar p_fechaDevolucion){
        this.setFechaDevolucion(p_fechaDevolucion);
    }
    
    /**
     * Devuelve true si la fecha pasada como parámetro es mayor que la fecha de vencimiento
     * 
     * @param p_fecha fecha con la que se calculará el vencimiento
     * @return true si la fecha pasada como parámetro es mayor que la fecha de vencimiento
     */
    public boolean vencido(Calendar p_fecha){
        Calendar fechaVencimiento = this.getFechaRetiro();
        fechaVencimiento.add(Calendar.DAY_OF_MONTH,this.getSocio().getDiasPrestamo());
        return (p_fecha.after(fechaVencimiento)); 
    }
    
    /**
     * Devuelve los datos principales del prestamo 
     * 
     * @return los datos principales del prestamo 
     */
    public String toString(){
        String auxString;
        if (this.getFechaDevolucion() != null){
            auxString = "Retiro: " + this.getFechaRetiro().get(Calendar.YEAR) + "/" + (this.getFechaRetiro().get(Calendar.MONTH)+1) + "/" + this.getFechaRetiro().get(Calendar.DAY_OF_MONTH) + " - Devolución: " + this.getFechaDevolucion().get(Calendar.YEAR) + "/" + (this.getFechaDevolucion().get(Calendar.MONTH) + 1) + "/" + this.getFechaDevolucion().get(Calendar.DAY_OF_MONTH) + "\nLibro: " + this.getLibro().getTitulo() + "\nSocio: " + this.getSocio().getNombre();
        } else {
            auxString = "Retiro: " + this.getFechaRetiro().get(Calendar.YEAR) + "/" + (this.getFechaRetiro().get(Calendar.MONTH)+1) + "/" + this.getFechaRetiro().get(Calendar.DAY_OF_MONTH) + "\nLibro: " + this.getLibro().getTitulo() + "\nSocio: " + this.getSocio().getNombre();
        }
        return auxString;
    }
}
