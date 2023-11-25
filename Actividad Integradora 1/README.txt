Actividad Integradora 1 - Registro de Personas

-------------------------------------------------------------

La aplicación comienza con un bucle infinito que presenta un menú principal al usuario utilizando JOptionPane. El menú tiene tres opciones: registrar persona, listar personas y salir.

-------------------------------------------------------------

Al seleccionar la opción "Registrar Persona", la aplicación solicita al usuario que ingrese el nombre, apellido, DNI y fecha de nacimiento utilizando JOptionPane.

-------------------------------------------------------------

VALIDACIONES:
Las validaciones de los datos se realizó mediante diferentes métodos:

- Validación de Nombre y Apellido: Verifica que tanto el nombre como el apellido no estén en blanco. Si alguno de ellos está en blanco, muestra un mensaje de error utilizando JOptionPane y se regresa al menú principal.

- Validación de DNI: Utiliza una expresión regular para verificar que el DNI ingresado sea un número válido de 8 dígitos. Si no cumple con esta condición, muestra un mensaje de error y se regresa al menú principal.

- Validación de Fecha de Nacimiento: Pide al usuario que ingrese la fecha de nacimiento con el formato "dd/mm/yyyy". Utiliza la clase SimpleDateFormat con el formato "dd/mm/yyyy" y establece setLenient(false) para que no permita fechas inválidas. Si la fecha no es válida, muestra un mensaje de error y se regresa al menú principal.

-------------------------------------------------------------

Si el registro pasa satisfactoriamente las validaciones se crea una instancia de la clase "Persona" con los datos ingresados y lo agrega a un ArrayList llamado listaPersonas.

-------------------------------------------------------------

Al seleccionar la opción "Listar Personas", la aplicación verifica si hay personas registradas en listaPersonas.

Si no hay personas registradas, muestra un mensaje informando al usuario. De lo contrario, construye un mensaje que contiene los detalles de cada persona y lo muestra utilizando JOptionPane.

-------------------------------------------------------------

Lucas Ivan Molina - DNI 36243366