package biblioteca;
//Importamos la java.util.*, para poder manipular listas enlazadas:
import java.util.*;

//Definición de la clase Libro:
public class Libro
{
    //Atributos:
    private String titulo;
    private int edicion;
    private String editorial;
    private int anio;
    private ArrayList <Prestamo> prestamos;
    
    //Constructor:
    public Libro (String p_titulo, int p_edicion, String p_editorial, int p_anio){
        this.setTitulo(p_titulo);
        this.setEdicion(p_edicion);
        this.setEditorial(p_editorial);
        this.setAnio(p_anio);
        this.setPrestamos(new ArrayList <Prestamo>());
    }
    
    public Libro (String p_titulo, int p_edicion, String p_editorial, int p_anio, ArrayList <Prestamo> p_prestamos){
        this.setTitulo(p_titulo);
        this.setEdicion(p_edicion);
        this.setEditorial(p_editorial);
        this.setAnio(p_anio);
        this.setPrestamos(p_prestamos);
    }
    
    //Setters:
    private void setTitulo(String p_titulo){
        this.titulo = p_titulo;
    }
    
    private void setEdicion(int p_edicion){
        this.edicion = p_edicion;
    }
    
    private void setEditorial(String p_editorial){
        this.editorial = p_editorial;
    }
    
    private void setAnio(int p_anio){
        this.anio = p_anio;
    }
    
    private void setPrestamos (ArrayList <Prestamo> p_prestamos){
        this.prestamos = p_prestamos;
    }
    
    //Getters:
    public String getTitulo(){
        return this.titulo;
    }
    
    public int getEdicion(){
        return this.edicion;
    }
    
    public String getEditorial(){
        return this.editorial;
    }
    
    public int getAnio(){
        return this.anio;
    }
    
    public ArrayList <Prestamo> getPrestamos (){
        return this.prestamos;
    }
    
    public Prestamo getPrestamo(){//se refiere al último préstamo por la fecha en que se agregó
        Calendar auxUltimoPrestamo = new GregorianCalendar(10,1,1);
        int auxIndicePrestamo = -1;
        if (this.getPrestamos().isEmpty()) {
            return null;
        } else {
            for (int i = 0; i < this.getPrestamos().size(); i++) {
                if (this.getPrestamos().get(i).getFechaRetiro().equals(auxUltimoPrestamo) || this.getPrestamos().get(i).getFechaRetiro().after(auxUltimoPrestamo)) {
                    auxUltimoPrestamo = this.getPrestamos().get(i).getFechaRetiro();
                    auxIndicePrestamo = i;
                }
            }
            return this.getPrestamos().get(auxIndicePrestamo);
        }
    }
    
    //Otros métodos:
    
    /**
     * Este método permite añadir un prestamo a la lista de prestamos.
     * @param p_prestamo representa el prestamo que se desea añadir a la lista.
     * @return retorna true en caso de lograr añadir el prestamo a la lista, false 
     en caso contrario.
     */
    public boolean addPrestamo (Prestamo p_prestamo){
        return this.getPrestamos().add(p_prestamo);
    }
    
    /**
     * Este método permite remover un prestamo de la lista de prestamos.
     * @param p_prestamo representa el prestamo que se desea quitar de la lista.
     * @return retorna true en caso de lograr quitar el prestamo de la lista, false 
     en caso contrario.
     */
    public boolean removePrestamo (Prestamo p_prestamo){
        return this.getPrestamos().remove(p_prestamo);
    }
    
    /**
     * Este método verifica si el libro se encuentra prestado.
     * @return devuelve true si el libro se encuentra prestado.
     */
    public boolean prestado(){
        if (this.getPrestamos().isEmpty()){
            return false;
        } else {
            return this.getPrestamo().getFechaDevolucion() == null; 
        }
    }
    
    /**
     * @return Este método retorna un String con el título del libro.
     */
    public String toString(){
        return "Titulo: " + this.getTitulo();
    }
}
