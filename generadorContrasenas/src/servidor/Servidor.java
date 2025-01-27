package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	
	private final int PUERTO = 4321;
	private ServerSocket serverSocket;
	private Socket socket;
	private ServicioPass servicioPass; 
	
	//Creamos el constructor 
	public Servidor() throws IOException{
		
		serverSocket = new ServerSocket(PUERTO); //Creamos la conexión
		socket = new Socket(); //Iniciamos el cliente
		
	}
	
	//Creamos el método para inciar el servidor y la interación con el cliente
	public void start() throws IOException {
		
		System.out.println("Servidor arrancado");
		System.out.println("Esperando al cliente...");
		
		socket = serverSocket.accept(); //Esperamos a que el cliente acepte la conexión
		System.out.println("Cliente conectado desde " + socket.getInetAddress());
		//Para la entrada de datos
		DataInputStream entradaCliente = new DataInputStream(socket.getInputStream());
		//Para la salida de datos
        DataOutputStream salidaCliente = new DataOutputStream(socket.getOutputStream());
        
              
        try {
          //Enviamos el primer mensaje al cliente
           salidaCliente.writeUTF("Hola, soy un servidor. ¿Cómo te llamas?");
          // Recibimos el nombre del cliente
            String nombreCliente = entradaCliente.readUTF();
            System.out.println("Nombre del cliente: " + nombreCliente);

            //Enviamos el saludo al cliente
            salidaCliente.writeUTF("Te doy la bienvenida, " + nombreCliente);

            //Preguntamos los requisitos que va a tener la contraseña
            salidaCliente.writeUTF("Voy a solicitarte distintos requisitos para la contraseña que voy a generar.");

            salidaCliente.writeUTF("¿Cuántas mayúsculas debe tener la contraseña?");
            int numMayusculas = entradaCliente.readInt();
            
            salidaCliente.writeUTF("¿Cuántas minúsculas debe tener la contraseña?");
            int numMinusculas = entradaCliente.readInt();
            
            salidaCliente.writeUTF("¿Cuántos dígitos debe tener la contraseña?");
            int numDigitos = entradaCliente.readInt();// 
            
            salidaCliente.writeUTF("¿Cuántos caracteres especiales debe tener la contraseña?");
            int numEspeciales = entradaCliente.readInt();
            
            //Validamos que los requisitos sean un número mayor a 0
            if (numMayusculas < 0 || numMinusculas < 0 || numDigitos < 0 || numEspeciales < 0) {
                salidaCliente.writeUTF("Error: Los valores ingresados deben ser mayores a 0.");
                throw new IllegalArgumentException("Valores no válidos recibidos por el cliente");
            }

            //Creamos el objeto RequisitosPass con los datos recibidos de la contraseña
            RequisitosPass requisitosPass = new RequisitosPass(numMayusculas,numMinusculas, numDigitos, numEspeciales);
            System.out.println("Requisitos recibidos: " + requisitosPass);
        
            //Inicializamos ServicioPass con los requisitos de la contraseña
            servicioPass = new ServicioPass(requisitosPass);

            //Calculamos la longitud de la contraseña al cliente y se la enviamos al cliente 
            String longitudMensaje = servicioPass.longitudPass();
            salidaCliente.writeUTF(longitudMensaje);
            System.out.println("Se ha enviado la longitud de la contraseña al cliente");
            
            //Preguntamos si quiere generar la contraseña
            salidaCliente.writeUTF("¿Quieres generar una contraseña ahora? [si/no]");           
            String respuesta = entradaCliente.readUTF();

            //Si dice que si, creamos la contraseña y se la enviamos            
            if (respuesta.equalsIgnoreCase("si")) {
            	
            	String password = servicioPass.generarPass();
                salidaCliente.writeUTF(password);
              
            } else {
                salidaCliente.writeUTF("El cliente no desea generar ninguna contraseña.");
            }
                  
        } finally {
            //Cerramos la entrada y salida y el socket
            entradaCliente.close();
            salidaCliente.close();
            socket.close();
            serverSocket.close();
        }
	}	
}
