package main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

public class BibliotecaGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private Biblioteca biblioteca;
    private JTextField busquedaTextField;
    private JTextArea resultadoTextArea;

    public BibliotecaGUI() {
        // Inicializar la biblioteca y la interfaz
        biblioteca = new Biblioteca();

        // Precargar algunos datos de prueba
        precargarDatos();

        // Configuración de la ventana principal
        setTitle("Biblioteca App");
        setSize(850, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear y agregar componentes
        initComponents();

        // Mostrar la ventana
        setVisible(true);
    }

    private void precargarDatos() {
        // Datos de prueba
        Libro libro1 = new Libro("Java Programming", "John Doe", "Programación");
        Libro libro2 = new Libro("Clean Code", "Robert Martin", "Programación");
        Libro libro3 = new Libro("The Hobbit", "J.R.R. Tolkien", "Fantasía");

        Usuario usuario1 = new Usuario("Lucas");
        Usuario usuario2 = new Usuario("Ivan");

        // Agregar libros a la biblioteca
        biblioteca.agregarLibro(libro1);
        biblioteca.agregarLibro(libro2);
        biblioteca.agregarLibro(libro3);

        // Registrar usuarios en la biblioteca
        biblioteca.registrarUsuario(usuario1);
        biblioteca.registrarUsuario(usuario2);

        // Alquilar y devolver libros
        usuario1.alquilarLibro(libro1);
        usuario1.alquilarLibro(libro2);

        usuario2.alquilarLibro(libro3);

        usuario1.devolverLibro(libro1);
    }

    private void initComponents() {
        // Layout manager
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Componentes
        JLabel busquedaLabel = new JLabel("Buscar por título, autor o género:");
        busquedaTextField = new JTextField(30);
        JButton buscarButton = new JButton("Buscar");
        resultadoTextArea = new JTextArea(10, 70);
        JScrollPane scrollPane = new JScrollPane(resultadoTextArea);
        JButton generarInformeButton = new JButton("Generar Informe");
        JButton agregarLibroButton = new JButton("Agregar Libro");
        JButton registrarUsuarioButton = new JButton("Registrar Usuario");
        JButton listarLibrosButton = new JButton("Listar Libros");
        JButton listarUsuariosButton = new JButton("Listar Usuarios");
        JButton prestarLibroButton = new JButton("Prestar Libro");
        JButton devolverLibroButton = new JButton("Devolver Libro");
        JButton eliminarLibroButton = new JButton("Eliminar Libro");
        JButton eliminarPrestamoButton = new JButton("Eliminar Préstamo");

        // Agregar componentes al contenedor
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(busquedaLabel, gbc);

        gbc.gridx = 1;
        add(busquedaTextField, gbc);

        gbc.gridx = 2;
        add(buscarButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        add(scrollPane, gbc);

        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(agregarLibroButton, gbc);

        gbc.gridx = 1;
        add(registrarUsuarioButton, gbc);

        gbc.gridx = 2;
        add(listarLibrosButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(listarUsuariosButton, gbc);

        gbc.gridx = 1;
        add(prestarLibroButton, gbc);

        gbc.gridx = 2;
        add(devolverLibroButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(eliminarLibroButton, gbc);

        gbc.gridx = 1;
        add(eliminarPrestamoButton, gbc);

        gbc.gridx = 2;
        add(generarInformeButton, gbc);

        // Configurar acciones para los botones
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarBusqueda();
            }
        });

        generarInformeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarInforme();
            }
        });

        agregarLibroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarLibro();
            }
        });

        registrarUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarUsuario();
            }
        });

        listarLibrosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarLibros();
            }
        });

        listarUsuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarUsuarios();
            }
        });

        prestarLibroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prestarLibro();
            }
        });

        devolverLibroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                devolverLibro();
            }
        });

        eliminarLibroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarLibro();
            }
        });

        eliminarPrestamoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarPrestamo();
            }
        });
    }

    private void realizarBusqueda() {
        String criterio = "titulo"; // Puedes agregar lógica para seleccionar el criterio
        String valor = busquedaTextField.getText().trim();

        List<Libro> resultados = biblioteca.buscarLibros(criterio, valor);

        // Mostrar resultados en el área de texto
        resultadoTextArea.setText("");
        for (Libro libro : resultados) {
            resultadoTextArea.append(libro.getTitulo() + " - " + libro.getAutor() + " - " + libro.getGenero() + "\n");
        }
    }

    private void generarInforme() {
        long librosAlquilados = biblioteca.contarLibrosAlquilados();
        JOptionPane.showMessageDialog(this, "Número total de libros alquilados: " + librosAlquilados);
    }
    
    // Método para obtener entrada del usuario, manejando el caso de cancelación
    private String obtenerEntrada(String mensaje) throws Exception {
        String input = JOptionPane.showInputDialog(this, mensaje);
        if (input == null || input.trim().isEmpty()) {
            throw new Exception("Debe ingresar algún valor");
        }
        return input;
    }

    private void agregarLibro() {
        try {
            String titulo = obtenerEntrada("Ingrese el título del nuevo libro:");
            String autor = obtenerEntrada("Ingrese el autor del nuevo libro:");
            String genero = obtenerEntrada("Ingrese el género del nuevo libro:");

            Libro nuevoLibro = new Libro(titulo, autor, genero);
            biblioteca.agregarLibro(nuevoLibro);

            JOptionPane.showMessageDialog(this, "Libro agregado correctamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al agregar el libro: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void registrarUsuario() {
        try {
            String nombreUsuario = obtenerEntrada("Ingrese el nombre del nuevo usuario:");

            Usuario nuevoUsuario = new Usuario(nombreUsuario);
            biblioteca.registrarUsuario(nuevoUsuario);

            JOptionPane.showMessageDialog(this, "Usuario registrado correctamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al registrar el usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listarLibros() {
        List<Libro> libros = biblioteca.listarLibros();
        mostrarResultados(libros, "Libros en la biblioteca:");
    }

    private void listarUsuarios() {
        List<Usuario> usuarios = biblioteca.listarUsuarios();
        mostrarResultados(usuarios, "Usuarios en la biblioteca:");
    }

    private void prestarLibro() {
        try {
            String nombreUsuario = obtenerEntrada("Ingrese el nombre del usuario:");
            String tituloLibro = obtenerEntrada("Ingrese el título del libro a prestar:");

            Usuario usuario = biblioteca.obtenerUsuario(nombreUsuario);
            Libro libro = biblioteca.buscarLibros("titulo", tituloLibro).stream().findFirst().orElse(null);

            if (usuario != null && libro != null) {
                usuario.alquilarLibro(libro);
                JOptionPane.showMessageDialog(this, "Libro prestado correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o libro no encontrado.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al prestar el libro: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void devolverLibro() {
        try {
            String nombreUsuario = obtenerEntrada("Ingrese el nombre del usuario:");
            String tituloLibro = obtenerEntrada("Ingrese el título del libro a devolver:");

            Usuario usuario = biblioteca.obtenerUsuario(nombreUsuario);
            Libro libro = biblioteca.buscarLibros("titulo", tituloLibro).stream().findFirst().orElse(null);

            if (usuario != null && libro != null) {
                usuario.devolverLibro(libro);
                JOptionPane.showMessageDialog(this, "Libro devuelto correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o libro no encontrado.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al devolver el libro: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarLibro() {
        try {
            String tituloLibro = obtenerEntrada("Ingrese el título del libro a eliminar:");

            Libro libro = biblioteca.buscarLibros("titulo", tituloLibro).stream().findFirst().orElse(null);

            if (libro != null) {
                biblioteca.eliminarLibro(libro);
                JOptionPane.showMessageDialog(this, "Libro eliminado correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "Libro no encontrado.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar el libro: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarPrestamo() {
        try {
            String nombreUsuario = obtenerEntrada("Ingrese el nombre del usuario:");
            String tituloLibro = obtenerEntrada("Ingrese el título del libro:");

            Usuario usuario = biblioteca.obtenerUsuario(nombreUsuario);
            Libro libro = biblioteca.buscarLibros("titulo", tituloLibro).stream().findFirst().orElse(null);

            if (usuario != null && libro != null) {
                usuario.devolverLibro(libro);
                JOptionPane.showMessageDialog(this, "Préstamo eliminado correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o libro no encontrado.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar el préstamo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private <T> void mostrarResultados(List<T> lista, String mensaje) {
        resultadoTextArea.setText("");
        resultadoTextArea.append(mensaje + "\n");

        for (T elemento : lista) {
            resultadoTextArea.append(elemento.toString() + "\n");
        }
    }
}