package biblioteca;

public class Estudiante extends Socio
{
    private String carrera;
    
    /**
     * Metodo constructor para la clase Estudiante.
     * @param int p_dniSocio,String p_nombre,int p_diasPrestamo (Super).
     * @param String p_carrera.
     */
    public Estudiante(int p_dniSocio,String p_nombre, String p_carrera)
    {
        super(p_dniSocio,p_nombre,20);
        this.setCarrera(p_carrera);
    }
 
    //Setters
    private void setCarrera(String p_carrera)
    {
        this.carrera = p_carrera;
    }
    //Getters
    public String getCarrera()
    {
        return this.carrera;
    }
    
    /**
     * Metodo que devuelve true si el estudiante no tiene ningún préstamo vencido y si no tiene más de 3 libros prestados.
     * 
     * @return devuelve un valor booleano Verdadero/Falso segun la condicion mencionada.
     */
    public boolean puedePedir()
    {
        //puede tener hasta 4 libros máximos prestados
        return super.puedePedir() && this.cantLibrosPrestados() <= 3;
    }
    
    /**
     * Metodo que devuelve el String “Estudiante”
     *
     *@return devuelve el String "Estudiante".
     */
    public String soyDeLaClase()
    {
        return "Estudiante";
    }
}
