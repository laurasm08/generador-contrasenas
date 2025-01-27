package servidor;

import java.io.IOException;

public class MainServidor {

	public static void main(String[] args) throws IOException {
		
		try {
			//Definimos el objeto del servidor
            Servidor servidor = new Servidor(); 
            servidor.start(); //Iciamos el servidor
        } catch (IOException e) {
        	//Creamos una excepción por si ocurre algún error tenerlo controlado
            System.out.println("Error al iniciar el servidor: " + e.getMessage()); 
        }
	}

}
