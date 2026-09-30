import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import biblioteca.*;

public class GestionBiblioteca extends JFrame implements ActionListener {
    private JPanel miPanel;
    private HashMap<JButton, Runnable> acciones;
    private Biblioteca miBiblioteca;
    private static final int ALTO_BOTON = 25;

    public GestionBiblioteca() { //Constructor de la interfaz.
        configurarBiblioteca();
        configurarVentana();
        configurarPanel();
        establecerBotones();
    }
    
    public Biblioteca getBiblioteca(){
        return this.miBiblioteca;
    }

    public static void main(String[] args) { //Metodo ejecutable. (Hace visible la interfaz gráfica).
        SwingUtilities.invokeLater(() -> new GestionBiblioteca().setVisible(true)); 
    }

    // Configura la apariencia de la ventana
    private void configurarVentana() {
        this.setTitle("Administración de biblioteca: " + this.getBiblioteca().getNombre());// Establece el título de la ventana
        this.setSize(1081,720);// Establece el tamaño de la ventana
        this.setLocationRelativeTo(null);// Inicializa la ventana en el centro de la pantalla
        this.setResizable(false);// Hace estático el tamaño de la ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Permite cerrar la ventana al hacer clic en el botón de cierre
    }

    private void configurarPanel() {// Configura el panel de la interfaz gráfica con una imagen de fondo
        miPanel = new JPanel()
        {
            @Override
            public void paintComponent(Graphics g) 
            { // Carga una imagen de fondo y la dibuja en el panel 
                Image img = new ImageIcon(getClass().getResource("logo.jpeg")).getImage();//captura la ruta de la imagen de fondo
                Dimension size = new Dimension(1081,720);// crea un objeto Dimension con alto y ancho
                setPreferredSize(size); //Establece la dimensión preferida del panel 
                setMinimumSize(size); //Establece la dimensión mínima del panel
                setMaximumSize(size); //Establece la dimensión máxima del panel
                setSize(size); //Establece el tamaño del panel
                setLocationRelativeTo(null);//centra la ventana relación con la pantalla.
                setLayout(null);// Permite que los componentes del panel se coloquen manualmente
                g.drawImage(img, 0, 0, null); // Dibuja la imagen especificada iniciando en las coordenadas 0,0
             }
        };
        miPanel.setLayout(null);// Permite que los componentes del panel se coloquen manualmente
        Container miPanelContenedor = getContentPane(); // Es el contenedor principal de la ventana
        miPanelContenedor.add(miPanel);
    }

    private void configurarBiblioteca() { // Permite crear una nueva instancia de Biblioteca
        String nombreBiblioteca = JOptionPane.showInputDialog("Ingrese nombre de la biblioteca:");
        miBiblioteca = new Biblioteca(nombreBiblioteca);
        acciones = new HashMap<>();
    }

    private void establecerBotones() { // Configura y agrega botones a la interfaz gráfica
        this.agregarBoton("Cantidad de socios", 3, 13, 200, ALTO_BOTON, () -> this.imprimirCantidadSocios());
        this.agregarBoton("Lista de docentes responsables", 205, 13, 220, ALTO_BOTON, () -> this.imprimirListaDocentesResponsables());
        this.agregarBoton("Listar socios", 427, 13, 150, ALTO_BOTON, () -> this.imprimirListaSocios());
        this.agregarBoton("Listar libros", 579, 13, 150, ALTO_BOTON, () -> this.imprimirListaLibros());
        this.agregarBoton("Buscar libro prestado", 731, 13, 175, ALTO_BOTON, () -> this.buscarLibroPrestado());
        this.agregarBoton("Registrar socio", 908, 13, 150, ALTO_BOTON, () -> this.registrarSocio());
        this.agregarBoton("Registrar libro", 3, 53, 150, ALTO_BOTON, () -> this.registrarLibro());
        this.agregarBoton("Prestar libro", 155, 53, 150, ALTO_BOTON, () -> this.prestarLibro());
        this.agregarBoton("Devolver libro", 307, 53, 150, ALTO_BOTON, () -> this.devolverLibro());
        this.agregarBoton("Buscar socio por DNI", 459, 53, 200, ALTO_BOTON, () -> this.buscarSocioxDni());
        this.agregarBoton("Expandir dias de prestamo docente", 661, 53, 270, ALTO_BOTON, () -> this.expandirPrestamo());
        this.agregarBoton("Listar prestamos vencidos actuales", 3, 93, 250, ALTO_BOTON, () -> this.listarVencidos());
        this.agregarBoton("Dar de baja a un socio", 255, 93, 200, ALTO_BOTON, () -> this.darDeBaja());
        this.agregarBoton("Cerrar Ventana", 903, 650, 150, ALTO_BOTON, () -> this.dispose());
    }

    // Añade un botón al panel con las propiedades y la acción proporcionadas
    private void agregarBoton(String texto, int x, int y, int ancho, int alto, Runnable accion) {
        JButton boton = new JButton(texto);// Crea un nuevo botón con el texto proporcionado
        // Agrega el objeto actual como un ActionListener para manejar eventos de clic en el botón
        boton.addActionListener(this);
        boton.setBounds(x, y, ancho, alto);// Establece la posición y el tamaño del botón en el panel
        miPanel.add(boton);// Agrega el botón al panel
        // Asocia el botón con la acción proporcionada en el mapa de acciones
        acciones.put(boton, accion);
            
    }

    // Implementa el método de la interfaz ActionListener para manejar eventos de botones
    public void actionPerformed(ActionEvent e) {
        JButton botonSeleccionado = (JButton) e.getSource();// Obtiene el botón que generó el evento
        acciones.getOrDefault(botonSeleccionado, () -> {}).run();// Ejecuta la acción asociada al botón seleccionado
    }
    
    // Métodos de utilidad para acciones de los botones
    
    private void imprimirCantidadSocios() {
        String tipoSocio = JOptionPane.showInputDialog("Indique el tipo de socio (Estudiante/Docente):");
        if (tipoSocio == null){
            //Si se apreto cancelar o borrar que termine el metodo.
        } else if (!tipoSocio.equalsIgnoreCase("docente") && !tipoSocio.equalsIgnoreCase("estudiante")) {
            JOptionPane.showMessageDialog(null,"Error, el tipo de socio ingresado no corresponde!!!");
        } else {
            JOptionPane.showMessageDialog(null,"La cantidad de " + tipoSocio + "s es: " + this.getBiblioteca().cantidadSociosPorTipo(tipoSocio));    
        }
    }

    private void imprimirListaDocentesResponsables() {
        JOptionPane.showMessageDialog(null, this.getBiblioteca().listaDeDocentesResponsables());
    }

    private void imprimirListaSocios() {
        JOptionPane.showMessageDialog(null, this.getBiblioteca().listaDeSocios());
    }
    
    private void imprimirListaLibros() {
        JOptionPane.showMessageDialog(null, this.getBiblioteca().listaDeLibros());
    }
    
    private void buscarLibroPrestado() {
        if (this.getBiblioteca().getLibros().isEmpty()) {
            JOptionPane.showMessageDialog(null,"Error, no hay libros registrados!!!");
        } else {
            String auxNombre = JOptionPane.showInputDialog("Ingrese el nombre del libro");
            if (auxNombre != null){ //Si no se apreto la opcion de cancelar o cerrar ventana.
                Libro auxLibro = this.obtenerLibroPorNombre(auxNombre);
                try{
                    JOptionPane.showMessageDialog(null, this.getBiblioteca().quienTieneElLibro(auxLibro));
                } catch (NullPointerException e) {
                    JOptionPane.showMessageDialog(null,"Error, libro no registrado!!!");
                }
            } 
        }
    }
    
    private Libro obtenerLibroPorNombre (String p_libro){
        for (Libro unLibro: this.getBiblioteca().getLibros()) {
            if (unLibro.getTitulo().equalsIgnoreCase(p_libro)) {
                return unLibro;
            }
        }
        return null;
    }
    
    private void registrarSocio(){
        try {
            String auxImput;
            auxImput = JOptionPane.showInputDialog("Ingrese dni:");
            if (auxImput == null){ //Si se apreto el boton de cancelar forzamos a q se termine el metodo.
                return; 
            }
            int dni = Integer.parseInt(auxImput);
            if (this.getBiblioteca().buscarSocio(dni) == null){
                auxImput = JOptionPane.showInputDialog("Ingrese nombre");
                String nombre = auxImput;
                if (auxImput == null){
                    return;
                }
                auxImput = JOptionPane.showInputDialog("Seleccione tipo (1: Estudiante, 2: Docente):");
                if (auxImput == null){
                    return;
                }
                int tipo = Integer.parseInt(auxImput);
                switch (tipo) {
                    case 1: 
                        auxImput = JOptionPane.showInputDialog("Ingrese la carrera que cursa:");
                        if (auxImput == null){
                            return;
                        }
                        this.getBiblioteca().nuevoSocioEstudiante(dni, nombre, auxImput);
                        JOptionPane.showMessageDialog(null, "Socio registrado con exito.");
                        break;
                    case 2: 
                        auxImput = JOptionPane.showInputDialog("Ingrese el area en la que ejerce:");
                        if (auxImput == null){
                            return;
                        }
                        this.getBiblioteca().nuevoSocioDocente(dni, nombre, auxImput);
                        JOptionPane.showMessageDialog(null, "Socio registrado con exito.");
                        break;
                    default: 
                        JOptionPane.showMessageDialog(this, "Error, valor ingresado no valido. No se pudo registrar al socio!!!");
                        break; 
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error, el DNI ya se encuentra registrado.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error, valor ingresado no valido. No se pudo registrar al socio!!!");
        }
    }
    
    private void registrarLibro(){
        try {
            String auxImput =JOptionPane.showInputDialog("Ingrese titulo"); 
            if (auxImput == null){
                return;
            }
            String titulo = auxImput;
            auxImput = JOptionPane.showInputDialog("Ingrese edición:");
            if (auxImput == null){
                return;
            }
            int edicion = Integer.parseInt(auxImput);
            auxImput = JOptionPane.showInputDialog("Ingrese la editorial");
            if (auxImput == null){
                return;
            }
            String editorial = auxImput;
            auxImput = JOptionPane.showInputDialog("Ingrese el año de publicación:");
            if (auxImput == null){
                return;
            }
            int anio = Integer.parseInt(auxImput);
            this.getBiblioteca().nuevoLibro(titulo, edicion, editorial, anio);
            JOptionPane.showMessageDialog(null, "Libro registrado con exito.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error, valor ingresado no valido. No se pudo registrar al libro!!!");
        }
    }
    
    private void prestarLibro() {
        Libro auxLibro;
        ArrayList <Libro> librosDisponibles = new ArrayList <Libro> ();
        boolean hayLibrosDisponibles = false;
        
        //Comprobamos que haya libros disponibles:
        for (Libro unLibro: this.getBiblioteca().getLibros()){
            if (!unLibro.prestado()){
                librosDisponibles.add(unLibro);
                hayLibrosDisponibles = true;
            }
        }
        
        if (this.getBiblioteca().getLibros().isEmpty()) {
            JOptionPane.showMessageDialog(null,"Error, no hay libros registrados!!!");
        } else if (!hayLibrosDisponibles){
            JOptionPane.showMessageDialog(null,"Error, no hay libros disponibles!!!");
        } else if (this.getBiblioteca().getSocios().isEmpty()){
            JOptionPane.showMessageDialog(null,"Error, no hay Socios registrados!!!");
        } else {
             try {
                    String auxImput = JOptionPane.showInputDialog("Ingrese el DNI del socio:");
                    if (auxImput == null){
                        return;
                    }
                    int dni = Integer.parseInt(auxImput);  
                    Socio socioEncontrado = this.getBiblioteca().buscarSocio(dni);
                    if (socioEncontrado == null) { //Si no se encuentra al socio
                        JOptionPane.showMessageDialog(null, "Error, el DNI ingresado no corresponde a un socio!!!");
                    } else if (!socioEncontrado.puedePedir()){ //Si el socio encontrado no puede pedir más libros.
                        JOptionPane.showMessageDialog(null,"Error! El socio no puede realizar más prestamos.");
                    } else if (this.getBiblioteca().prestarLibro(new GregorianCalendar (),socioEncontrado,this.elegirLibroPorNombre(librosDisponibles))){ //Si se logro el prestamo.
                        JOptionPane.showMessageDialog(null,"Libro prestado con exito!");
                    } else { //Si debido a un factor x no contemplado en los if no se pudo realizar el prestamo.
                        JOptionPane.showMessageDialog(null,"Error! Ha ocurrido un problema, no se ha podido realizar el prestamo.");
                    }
            } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Error, ingrese un valor válido para el DNI.");
            } catch (NullPointerException e) {
                 //Cierra la ventana
            }
        }
    }
    
    private void devolverLibro() {
        Libro auxLibro;
        ArrayList <Libro> librosPrestados = new ArrayList <Libro> ();
        boolean hayLibrosPrestados = false;
        
        //Comprobamos que haya libros prestados:
        for (Libro unLibro: this.getBiblioteca().getLibros()){
            if (unLibro.prestado()){
                librosPrestados.add(unLibro);
                hayLibrosPrestados = true;
            }
        }
        
        //Si hay libros prestados enviamos a elegir un libro prestado.
        try {
            if (hayLibrosPrestados) {
                auxLibro = this.elegirLibroPorPrestamo(librosPrestados);
                this.getBiblioteca().devolverLibro(auxLibro);
                JOptionPane.showMessageDialog(null, "Libro devuelto con éxito.");
            } else {
                JOptionPane.showMessageDialog(null,"Error, no hay libros prestados!!!");    
            }
        } catch (NullPointerException e){
            //Cierra la ventana.
        }
    }
    
    private Libro elegirLibroPorNombre (ArrayList <Libro> p_libros){
        String listaLibros;
        Libro leerLibro;
        String auxImput;
        
        listaLibros = "";
        for (int i = 0; i < p_libros.size(); i++) {
            listaLibros += i + ") " + p_libros.get(i).toString() + "\n";
        }
        
        try {
            auxImput = JOptionPane.showInputDialog("Escriba el número de indice del libro: \n" + listaLibros);
            if (auxImput == null){
                leerLibro = null;
            } else {
                leerLibro = p_libros.get(Integer.parseInt(auxImput));
            }
        } catch (NumberFormatException e) {
            leerLibro = null;   
            JOptionPane.showMessageDialog(null, "Error! Opcion no valida.");
        } catch (IndexOutOfBoundsException e){
            leerLibro = null;   
            JOptionPane.showMessageDialog(null,"Error! Opcion no valida.");
        }
        
        return leerLibro;
    }
    
    private Libro elegirLibroPorPrestamo (ArrayList <Libro> p_libros){
        String listaLibros;
        Libro leerLibro;
        String auxImput;
        
        listaLibros = "";
        for (int i = 0; i < p_libros.size(); i++) {
            listaLibros += i + ") " + p_libros.get(i).getPrestamo().toString() + "\n";
        }
        try {
            auxImput = JOptionPane.showInputDialog("Escriba el número de indice del libro: \n" + listaLibros);
            if (auxImput == null){
                leerLibro = null;
            } else {
                leerLibro = p_libros.get(Integer.parseInt(auxImput));
            }
        } catch (NumberFormatException e) {
            leerLibro = null;   
            JOptionPane.showMessageDialog(null, "Error! Opcion no valida.");
        } catch (IndexOutOfBoundsException e){
            leerLibro = null;   
            JOptionPane.showMessageDialog(null,"Error! Opcion no valida.");
        }
        
        return leerLibro;
    }
    
    private void buscarSocioxDni() {
        if (this.getBiblioteca().getSocios().isEmpty()) {
             JOptionPane.showMessageDialog(null, "Error, no existen socios registrados!!!");
        } else {
            try {
                String auxImput = JOptionPane.showInputDialog("Ingrese el DNI del socio:");
                if (auxImput != null){ //Si no se apreto el boton de cancelar.
                    int dni = Integer.parseInt(auxImput);  
                    Socio socioEncontrado = this.getBiblioteca().buscarSocio(dni);
                    JOptionPane.showMessageDialog(null,socioEncontrado.toString());
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error, ingrese un valor válido para el DNI");
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(null, "Error, el DNI ingresado no corresponde a un socio!!!");
            }
        }
    }

    private void expandirPrestamo() {
        if (this.getBiblioteca().docentesResponsables().isEmpty()) {
            JOptionPane.showMessageDialog(null,"Error, no hay docentes responsables registrados!!!");
        } else {
            try {
                int auxIndice;
                String auxImput;
                String listaDocentes = "";
                for (int i = 0;i < this.getBiblioteca().docentesResponsables().size();i++) {
                    listaDocentes += i + ") " + this.getBiblioteca().docentesResponsables().get(i).toString() + "\n"; 
                }
                auxImput = JOptionPane.showInputDialog("Seleccione el docente:\n" + listaDocentes);
                if (auxImput == null){
                    return;
                }
                auxIndice = Integer.parseInt(auxImput);
                auxImput = JOptionPane.showInputDialog("Ingrese la cantidad de dias de prestamo a agregar:");
                if (auxImput == null){
                    return;
                }
                ((Docente)this.getBiblioteca().getSocios().get(this.getBiblioteca().getSocios().indexOf(this.getBiblioteca().docentesResponsables().get(auxIndice)))).agregarDiasDePrestamos(Integer.parseInt(auxImput));
                JOptionPane.showMessageDialog(null,"Dias de prestamo expandidos con exito!!");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error, valor no valido, la operación ha sido cancelada.");
            } catch (IndexOutOfBoundsException e){
                JOptionPane.showMessageDialog(null, "Error, valor no valido, la operación ha sido cancelada.");
            }
        }
    }
    
    private void darDeBaja() {
        if (this.getBiblioteca().getSocios().isEmpty()) {
             JOptionPane.showMessageDialog(null, "Error, no existen socios registrados!!!");
        } else {
            try {
                String auxImput;
                auxImput = JOptionPane.showInputDialog("Ingrese el DNI del socio:");
                if (auxImput == null){
                    return;
                }
                int dni = Integer.parseInt(auxImput);  
                Socio socioEncontrado = this.getBiblioteca().buscarSocio(dni);
                if (socioEncontrado.cantLibrosPrestados() > 0) {
                    JOptionPane.showMessageDialog(null, "No se pudo dar de baja al socio debido a que posee libros en su poder");
                } else {
                    this.getBiblioteca().removeSocio(socioEncontrado);
                    JOptionPane.showMessageDialog(null, "Socio dado de baja con éxito!");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error, ingrese un valor válido para el DNI");
            } catch (NullPointerException e){
                JOptionPane.showMessageDialog(null, "Error, el DNI ingresado no corresponde a un socio!!!");
            }
        }
    }
    
    private void listarVencidos(){
        if (this.getBiblioteca().prestamosVencidos().isEmpty()){
            JOptionPane.showMessageDialog(null, "No existen prestamos vencidos!!!");
        } else {
            String auxString = "Lista de prestamos vencidos:\n";
            int i = 1;
            for (Prestamo unPrestamo: this.getBiblioteca().prestamosVencidos()){
                auxString += i + ") Fecha del prestamo: " + unPrestamo.getFechaRetiro().get(Calendar.DAY_OF_MONTH) +
                "/" + (unPrestamo.getFechaRetiro().get(Calendar.MONTH) + 1) + "/" + 
                unPrestamo.getFechaRetiro().get(Calendar.YEAR) + " Titular: " + unPrestamo.getSocio().getNombre() + 
                " (DNI: " + unPrestamo.getSocio().getDniSocio() + ") Libro que se llevo: " + 
                unPrestamo.getLibro().toString()+"\n";
                i++;
            }
            JOptionPane.showMessageDialog(null, auxString);
        }
    }
}
