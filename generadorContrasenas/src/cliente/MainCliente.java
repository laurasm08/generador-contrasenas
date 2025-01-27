package cliente;


import java.io.IOException;

public class MainCliente {

	public static void main(String[] args) {
		
		try {
            //Creamos el objeto del Cliente
            Cliente cliente = new Cliente();

            cliente.interactua();//Iniciamos la comunicación con el servidor
        } catch (IOException e) {
        	//Manejamos la excepción por si ocurre algún error
            System.out.println("Error al conectar con el servidor: " + e.getMessage());
        }

	}

}
