package biblioteca;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Representación de una clase Socio
 */
public abstract class Socio
{
    private int dniSocio;
    private String nombre;
    private int diasPrestamo; 
    private ArrayList<Prestamo> prestamos;

    /**
     * Constructor de objectos de clase Socio
     */
    public Socio(int p_dniSocio,String p_nombre,int p_diasPrestamo){
        this.setDniSocio(p_dniSocio);
        this.setNombre(p_nombre);
        this.setDiasPrestamo(p_diasPrestamo);
        this.setPrestamos(new ArrayList());
    }
    
    /**
     * Segundo constructor de objetos de clase Socio
     */
    public Socio(int p_dniSocio,String p_nombre,int p_diasPrestamo,ArrayList<Prestamo> p_prestamos){
        this.setDniSocio(p_dniSocio);
        this.setNombre(p_nombre);
        this.setDiasPrestamo(p_diasPrestamo);
        this.setPrestamos(p_prestamos);
    }

    //Accesos
    private void setDniSocio(int p_dniSocio){
        this.dniSocio = p_dniSocio;
    }
    
    public int getDniSocio(){
        return this.dniSocio;
    }
    
    private void setNombre(String p_nombre){
        this.nombre = p_nombre;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    private void setPrestamos(ArrayList<Prestamo> p_prestamos){
        this.prestamos = p_prestamos;
    }
    
    public void setDiasPrestamo(int p_dias){
        this.diasPrestamo = p_dias;
    }
    
    public int getDiasPrestamo(){
        return this.diasPrestamo;
    }
    
    public ArrayList<Prestamo> getPrestamos(){
        return this.prestamos;
    }
    
    /**
     * Añade un prestamo a la lista
     * 
     * @param p_prestamo prestamo que será agregado
     * @return true si se añadió correctamente
     */
    public boolean addPrestamo(Prestamo p_prestamo){
        return this.getPrestamos().add(p_prestamo);
    }
    
    /**
     * Elimina un prestamo de la lista
     * 
     * @param p_prestamo prestamo que será eliminado
     * @return true si se eliminó correctamente
     */
    public boolean removePrestamo(Prestamo p_prestamo){
        return this.getPrestamos().remove(p_prestamo);
    }
    
    /**
     * Devuelve la cantidad de libros en poder del Socio
     * 
     * @return la cantidad de libros en poder del Socio
     */
    public int cantLibrosPrestados(){
        int cantLibrosPrestados = 0;
        for (int i = 0;i < this.getPrestamos().size();i++) {
            if (this.getPrestamos().get(i).getLibro().prestado()){
                cantLibrosPrestados += 1;//suma 1 si el libro está prestado
            }
        }
        return cantLibrosPrestados;
    }
    
    /**
     * Devuelve los datos principales del Socio 
     * 
     * @return los datos principales del Socio
     */
    public String toString(){
        return "D.N.I.:" + this.getDniSocio() + "||" + this.getNombre() + "(" + this.soyDeLaClase() + ")||Libros prestados: " + this.cantLibrosPrestados();
    }
    
    /**
     * Devuelve true si el socio no tiene ningún préstamo vencido
     * 
     * @return true si el socio no tiene ningún préstamo vencido
     */
    public boolean puedePedir(){
        Calendar fechaHoy = Calendar.getInstance();//fecha del dia en el que se activa el método
        boolean puedePedir = true;
        for (Prestamo unPrestamo: this.getPrestamos()){
            if (unPrestamo.getLibro().prestado() && unPrestamo.vencido(fechaHoy)) {
                puedePedir = false;
            }
        }
        return puedePedir;
    }
    
    public abstract String soyDeLaClase();
}
