package main;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
	private List<Libro> libros;
    private List<Usuario> usuarios;

    public Biblioteca() {
        libros = new ArrayList<>();
        usuarios = new ArrayList<>();
    }

    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    public void eliminarLibro(Libro libro) {
        libros.remove(libro);
    }

    public List<Libro> listarLibros() {
        return new ArrayList<>(libros);
    }

    public void registrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return new ArrayList<>(usuarios);
    }

    public List<Libro> buscarLibros(String criterio, String valor) {
        List<Libro> resultados = new ArrayList<>();
        for (Libro libro : libros) {
            if ("titulo".equalsIgnoreCase(criterio) && libro.getTitulo().toLowerCase().contains(valor.toLowerCase())) {
                resultados.add(libro);
            } else if ("autor".equalsIgnoreCase(criterio) && libro.getAutor().toLowerCase().contains(valor.toLowerCase())) {
                resultados.add(libro);
            } else if ("genero".equalsIgnoreCase(criterio) && libro.getGenero().toLowerCase().contains(valor.toLowerCase())) {
                resultados.add(libro);
            }
        }
        return resultados;
    }

    public Usuario obtenerUsuario(String nombreUsuario) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equalsIgnoreCase(nombreUsuario)) {
                return usuario;
            }
        }
        return null;
    }

    public long contarLibrosAlquilados() {
        long count = 0;
        for (Usuario usuario : usuarios) {
            count += usuario.getLibrosAlquilados().size();
        }
        return count;
    }
}
