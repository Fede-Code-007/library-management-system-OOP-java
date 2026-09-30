package biblioteca;

import java.util.ArrayList; 
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Biblioteca{
    private String nombre;
    private ArrayList <Libro> libros;
    private ArrayList <Socio> socios; 
    
    public Biblioteca(String p_nombre){
        this.setNombre(p_nombre);
        this.setSocios(new ArrayList <Socio>());
        this.setLibros(new ArrayList <Libro>());
    }
    
    public Biblioteca(String p_nombres, ArrayList<Socio> p_socios, ArrayList<Libro> p_libros){
        this.setNombre(p_nombres);
        this.setSocios(p_socios);
        this.setLibros(p_libros); 
    }
    
    private void setNombre(String p_nombre){
        this.nombre = p_nombre;
    }
    
    private void setSocios(ArrayList<Socio> p_socios){
        this.socios = p_socios;
    }
    
    private void setLibros(ArrayList<Libro> p_libros){
        this.libros = p_libros;
    }
    
    public String getNombre(){
        return this.nombre; 
    }
    
    public ArrayList<Socio> getSocios(){
        return this.socios;
    }
    
    public ArrayList<Libro> getLibros(){
        return this.libros; 
    }
    
    public boolean addSocio(Socio p_socio){
        if (this.buscarSocio(p_socio.getDniSocio()) == null){
            return this.getSocios().add(p_socio);
        } else {
            return false;
        }
    }
    
    public boolean removeSocio(Socio p_socio){
        if (p_socio.cantLibrosPrestados() > 0){
            return false;
        } else {
            return this.getSocios().remove(p_socio);
        } 
    }
    
    public boolean addLibro(Libro p_libro){
        return this.getLibros().add(p_libro);
    }
    
    public boolean removeLibro(Libro p_libro){
        return this.getLibros().remove(p_libro);
    }
    
    /**
     * descripcion: instancia y agrega a un nuevo libro a la biblioteca.
     * @param String p_titulo, int p_edicion, String p_editorial, int p_anio
     */
    public void nuevoLibro(String p_titulo, int p_edicion, String p_editorial, int p_anio){
        this.addLibro(new Libro(p_titulo,p_edicion,p_editorial,p_anio));
    }
    
    /**
     * descripcion: instancia y agrega a un nuevo socio de clase Estudiante a la biblioteca.
     * @param int p_dniSocio, String p_nombre, String p_carrera
     */
    public void nuevoSocioEstudiante(int p_dniSocio, String p_nombre, String p_carrera){
        this.addSocio(new Estudiante(p_dniSocio,p_nombre,p_carrera));
    }
    
    /**
     * descripcion: instancia y agrega a un nuevo socio de clase Docente a la biblioteca.
     * @param int p_dniSocio, String p_nombre, String p_area
     */
    public void nuevoSocioDocente(int p_dniSocio, String p_nombre, String p_area){
        this.addSocio(new Docente(p_dniSocio,p_nombre,p_area));
    }
    
    public int cantidadSociosPorTipo(String p_objeto){
        int cantSocios = 0;
        for(Socio unSocio: this.getSocios()){
            if(unSocio.soyDeLaClase().equalsIgnoreCase(p_objeto)){
                cantSocios += 1;
            }
        }
        return cantSocios;
    }
    
    /**
     * Método prestarLibro
     * @param Calendar p_fechaRetiro, Socio p_socio, Libro p_libro
     */
    public boolean prestarLibro(Calendar p_fechaRetiro, Socio p_socio, Libro p_libro){
        boolean prestar= false;
        if(p_socio.puedePedir()&&!p_libro.prestado()&&this.getLibros().contains(p_libro)&&this.getSocios().contains(p_socio)){
            Prestamo unPrestamo= new Prestamo(p_fechaRetiro,p_socio,p_libro);
            p_libro.addPrestamo(unPrestamo);
            p_socio.addPrestamo(unPrestamo);
            prestar = true;
        } 
        return prestar;
    }
    
    /**
     * Método devolverLibro
     * @param Libro p_libro
     */
    public void devolverLibro(Libro p_libro){
        Calendar fechaActual= new GregorianCalendar();
        if(p_libro.prestado() && this.getLibros().contains(p_libro)){
            p_libro.getPrestamo().registrarFechaDevolucion(fechaActual);
        } 
    }
    
    /**
     * Método prestamosVencidos
     * @return una lista de los prestamos de libros que se vencieron
     */
    public ArrayList<Prestamo> prestamosVencidos(){
        ArrayList<Prestamo> prestamosVencidos= new ArrayList<Prestamo>();
        Calendar fecha= new GregorianCalendar();
        for(Libro libro: this.getLibros()){
            if(libro.prestado() && libro.getPrestamo().vencido(fecha)){ 
                prestamosVencidos.add(libro.getPrestamo());
            }
        }
        return prestamosVencidos;
    } 
    
    /**
     * @return una lista en donde se almacenaran unicamente los docentes responsables.
    */
    public ArrayList<Docente> docentesResponsables(){
        ArrayList<Docente> docentesResponsables = new ArrayList<Docente>();
        for(Socio socio: this.getSocios()){
            if(socio instanceof Docente && ((Docente)socio).esResponsable()){
                docentesResponsables.add((Docente)socio);
            }
        }
        return docentesResponsables;
    }
    
    /**
     * @param p_libro un objeto de tipo libro que contiene un titulo,edicion,editorial y anio. Servira para realizar la busqueda de ese libro.
     * @return una cadena (String) con el nombre del socio al que se le asocio el prestamo del libro pasado por parametro.
    */
    public String quienTieneElLibro(Libro p_libro){
        String auxString;
        if (p_libro.prestado()){
            auxString = p_libro.getPrestamo().getSocio().getNombre();
        } else {
            auxString = "El libro se encuentra en la biblioteca";
        } 
        return auxString;
    }
    
    /**
     * @return una lista con los socios y la cantidad de los mismos,
     * separados entre el tipo al que pertenezcan (estudiantes y docentes respectivamente).
     */
    public String listaDeSocios(){
        int cantDocentes = 0;
        int cantEstudiantes = 0;
        String auxString;
        if (this.getSocios().isEmpty()){
            auxString = "La biblioteca no posee socios aún.\n";
        } else {
            auxString = "Lista de socios: \n";
            for(int i = 0; i < this.getSocios().size(); i++){
                auxString = auxString + (i+1) + ")" + this.getSocios().get(i).toString() + "\n";
                if(this.getSocios().get(i).soyDeLaClase().equalsIgnoreCase("Docente")){
                    cantDocentes += 1;
                } else{
                    cantEstudiantes += 1;
                }
            }
        }
        return (auxString + "**************************************\n" + "Cant. Socios tipo Estudiante: " + cantEstudiantes + "\n"  + "Cant. Socios tipo Docente: " + cantDocentes);
    }
    
    /**
     * @return una cadena (String) con los libros separados por titulo y si han sido prestados o no. 
    */
    public String listaDeLibros(){
        String auxString;
        if (this.getLibros().isEmpty()){
            auxString = "La biblioteca no posee libros aún.\n";
        } else {
            auxString = "Lista de libros: \n";
            for(int i = 0; i < this.getLibros().size(); i++){
                if (this.getLibros().get(i).prestado()){
                    auxString = auxString + (i+1) + ")" + this.getLibros().get(i).toString() + " || Prestado: (Si)\n";
                } else {
                    auxString = auxString + (i+1) + ")" + this.getLibros().get(i).toString() + " || Prestado: (No)\n";
                }
            }
        }
        return auxString;
    }
    
    /**
     * @return una cadena (String) con los docentes responsables.
    */
    public String listaDeDocentesResponsables(){
        int i = 0;
        String auxString;
        if (this.docentesResponsables().isEmpty()){
            auxString = "La biblioteca no tiene registros de docentes responsables.";
        } else {
            auxString = "Lista de Docentes Responsables: \n";
            for(i = 0; i < this.docentesResponsables().size(); i++){
                auxString = auxString + this.docentesResponsables().get(i).toString() + " || Libros Prestados: " + this.docentesResponsables().get(i).cantLibrosPrestados() + "\n";
            }
        }
        return auxString;
    }
    
    /**
     * @param dni Es un dni, utilizado para realizar la busqueda del socio que este asociado al mismo.
     * @return un objeto de tipo socio en caso de ser verdadero, caso contrario, retornara null.
    */
    public Socio buscarSocio(int dni){
        for(Socio socio: this.getSocios()){
            if(socio.getDniSocio() == dni){
                return socio;
            } 
        }
        return null;
    }
}