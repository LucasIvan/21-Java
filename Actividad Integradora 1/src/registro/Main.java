package registro;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class Main {
	static ArrayList<Persona> listaPersonas = new ArrayList<>();
	
	public static void main(String[] args) {
		while (true) {
			mostrarMenu();
		}
	}
	
	private static void mostrarMenu() {
		String[] opciones = { "Registrar Persona", "Listar Personas", "Salir" };
		int seleccion = JOptionPane.showOptionDialog(null, "Seleccione una opción", "Registro de Personas",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
		
		switch (seleccion) {
		case 0:
			registrarPersona();
			break;
		case 1:
			listarPersonas();
			break;
		case 2:
			System.exit(0);
			break;
		default:
			break;
		}
	}

	private static void registrarPersona() {
		String nombre = JOptionPane.showInputDialog("Ingrese el nombre:");
		String apellido = JOptionPane.showInputDialog("Ingrese el apellido:");

		if (nombre.isBlank() || apellido.isBlank()) {
			JOptionPane.showMessageDialog(null, "El nombre y el apellido no pueden estar en blanco.",
					"Error de validación", JOptionPane.ERROR_MESSAGE);
			return;
		}

		String dniString = JOptionPane.showInputDialog("Ingrese el DNI:");

		if (!validarDNI(dniString)) {
			JOptionPane.showMessageDialog(null, "El DNI debe ser un número válido de 8 dígitos.", "Error de validación",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		String fechaNacimientoString = JOptionPane.showInputDialog("Ingrese Fecha de nacimiento (dd/mm/yyyy):");

		if (!validarFechaNacimiento(fechaNacimientoString)) {
			JOptionPane.showMessageDialog(null,
					"La fecha de nacimiento debe ser una fecha válida en el formato dd/mm/yyyy.", "Error de validación",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		try {
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
			Date fechaNacimiento = formatoFecha.parse(fechaNacimientoString);

			Persona persona = new Persona(nombre, apellido, dniString, fechaNacimiento);
			listaPersonas.add(persona);

			JOptionPane.showMessageDialog(null, "Persona registrada con éxito.", "Registro exitoso",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Error al convertir la fecha de nacimiento.",
					"Error de formato de fecha", JOptionPane.ERROR_MESSAGE);
		}
	}

	private static boolean validarDNI(String dni) {
		Pattern pattern = Pattern.compile("\\d{8}");
		Matcher matcher = pattern.matcher(dni);
		return matcher.matches();
	}

	private static boolean validarFechaNacimiento(String fechaNacimiento) {
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		formatoFecha.setLenient(false);
		try {
			formatoFecha.parse(fechaNacimiento);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	private static void listarPersonas() {
		if (listaPersonas.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No hay personas registradas.", "Lista vacía",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			StringBuilder mensaje = new StringBuilder("Lista de Personas Registradas:\n");
			for (Persona persona : listaPersonas) {
				mensaje.append(persona.toString()).append("\n");
			}
			JOptionPane.showMessageDialog(null, mensaje.toString(), "Lista de Personas",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
}

class Persona {
	private String nombre;
	private String apellido;
	private String dni;
	private Date fechaNacimiento;

	public Persona(String nombre, String apellido, String dni, Date fechaNacimiento) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.fechaNacimiento = fechaNacimiento;
	}

	@Override
	public String toString() {
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		return "Nombre: " + nombre + ", Apellido: " + apellido + ", DNI: " + dni + ", Fecha de Nacimiento: "
				+ formatoFecha.format(fechaNacimiento);
	}

}
