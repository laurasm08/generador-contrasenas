package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
	
	private final String HOST = "localhost";
	private final int PUERTO = 4321;
	private Socket socket;
	
	
	//Creamos el constructor
	public Cliente() throws IOException{
		
		socket = new Socket(HOST, PUERTO);
	} 
	
	public void interactua() throws IOException{
				
		//Iniciamos la entrada de datos que recibimos del servidor
		DataInputStream entradaServidor = new DataInputStream(socket.getInputStream());
		
		//Para la salida de datos al servidor
		DataOutputStream salidaServidor = new DataOutputStream(socket.getOutputStream());
		
		Scanner scanner = new Scanner(System.in);
				
		try {	
			System.out.println(entradaServidor.readUTF()); //Leemos los datos recibidos por el servidor
			
			//Interactuar con el servidor
			String nombre = scanner.nextLine();
			salidaServidor.writeUTF(nombre); //Envía el nombre al servidor
			
			//Lee la respuesta del servidor y lo muestra
			System.out.println(entradaServidor.readUTF());
			
			//Muestra lo de los requerimientos de la contraseña
			System.out.println(entradaServidor.readUTF());
			
			
			//¿Cuántas mayúsculas debe tener la contraseña?"
			System.out.println(entradaServidor.readUTF());
			int numMayusculas = scanner.nextInt();
			scanner.nextLine();
			salidaServidor.writeInt(numMayusculas);
			
			//¿Cuántas minúsculas debe tener la contraseña?
			System.out.println(entradaServidor.readUTF());
			int numMinusculas = scanner.nextInt();
			scanner.nextLine();
			salidaServidor.writeInt(numMinusculas);
				
			//¿Cuántos dígitos debe tener la contraseña?
			System.out.println(entradaServidor.readUTF());
			int numDigitos = scanner.nextInt();
			scanner.nextLine();
			salidaServidor.writeInt(numDigitos);
			
			//¿Cuántos caracteres especiales debe tener la contraseña?
			System.out.println(entradaServidor.readUTF());
			int numEspeciales = scanner.nextInt();
			scanner.nextLine();
			salidaServidor.writeInt(numEspeciales);
			
			//Lee las respuestas y muestra la longitud de la contraseña
			System.out.println(entradaServidor.readUTF());
			
			//Confirma si quieres crear la contraseña
			
			System.out.print("¿Quieres generar una contraseña ahora? [si/no]: ");
			String respuesta = scanner.nextLine();
			salidaServidor.writeUTF(respuesta); // Enviar respuesta al servidor
			
					
			//Generar las opciones para si o no
			if(respuesta.equalsIgnoreCase("si")) {
				
				String password = entradaServidor.readUTF();
				System.out.println(entradaServidor.readUTF());
								
			}else {
				System.out.println("No se ha generado ninguna contraseña. Hasta la próxima.");
			}
			
		}finally {
			//Cerrar todos los recursos
			entradaServidor.close();
			salidaServidor.close();
			socket.close();
			scanner.close();
		}
	}
	
}
