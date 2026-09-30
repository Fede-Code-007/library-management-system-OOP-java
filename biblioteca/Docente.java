package biblioteca;

import java.util.Calendar;//para poder usar el método esResponsable

public class Docente extends Socio
{
    private String area;
    
    public Docente(int p_dniSocio,String p_nombre, String p_area)
    {
       super(p_dniSocio,p_nombre,5);
       this.setArea(p_area);
    }
    
    //Setters
    private void setArea(String p_area)
    {
        this.area = p_area;
    }

    //Getters 
    public String getArea()
    {
        return this.area;
    }
    
    /**
     * Metodo que devuelve true si el Docente nunca tuvo ni tiene un préstamo vencido.
     * return devuelve un valor booleano Verdadero/Falso, si cumple la condicion mencionada.
     */
    public boolean esResponsable() 
    {
        boolean esResponsable = true;
        try {
            for (Prestamo unPrestamo: this.getPrestamos()){
                if (unPrestamo.getLibro().prestado() && unPrestamo.vencido(Calendar.getInstance())){ 
                    //si el docente tiene prestado un libro, se fija si su prestamo esta vencido comparando la fecha actual
                    esResponsable = false;
                } else if (unPrestamo.vencido(unPrestamo.getFechaDevolucion())){ 
                    // se fija si el prestamo se devolvio a tiempo, pasando como parametro la fecha de devolucion
                    esResponsable = false;
                }
            }
            
        } catch(NullPointerException e) { // captura la excepcion en el caso en que el docente no tenga prestamos 
            //en caso de que nunca haya tenido prestamos no hace nada, esResponsable sigue siendo verdadero.
        } finally {
            return esResponsable;    
        }        
    }
    
    /**
     * Metodo que adiciona días de préstamo al docente, Es un "premio a la responsabilidad".
     * @param int p_dias.
     * @return devuelve un valor int, siendo este los dias agregados.
     */
    public int agregarDiasDePrestamos(int p_dias)
    {
        if (this.esResponsable()) { //si es responsable agrega los dias de prestamos indicados
            this.setDiasPrestamo(this.getDiasPrestamo() + p_dias);
        }else{
            p_dias = 0;
        }   
        return p_dias;
    }
    
    /**
     * Metodo que devuelve el String “Docente”.
     * @return devuelve un String "Docente"
     */
    public String soyDeLaClase()
    {
        return "Docente";
    }
}
