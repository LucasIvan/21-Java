package main;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
	private String nombre;
    private List<Libro> librosAlquilados;

    public Usuario(String nombre) {
        this.nombre = nombre;
        this.librosAlquilados = new ArrayList<>();
    }

    @Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", librosAlquilados=" + librosAlquilados + "]";
	}

	public String getNombre() {
        return nombre;
    }

    public List<Libro> getLibrosAlquilados() {
        return librosAlquilados;
    }

    public void alquilarLibro(Libro libro) {
        librosAlquilados.add(libro);
    }

    public void devolverLibro(Libro libro) {
        librosAlquilados.remove(libro);
    }
    
    
}
