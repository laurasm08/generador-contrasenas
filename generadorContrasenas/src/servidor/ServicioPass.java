package servidor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ServicioPass{
	
	//Declaramos lo atributos
	private RequisitosPass requisitosPass;
	
	//Creamos el constructor
	public ServicioPass(RequisitosPass requisitosPass){
		
		this.requisitosPass = requisitosPass;
	}
		
	//Creamos el método que nos va a generear la coontraseña
	public String generarPass(){
		//Creamos una lista para guardar los caracteres generados
		List<Character> passwordChar = new ArrayList<>();
		
		//Utilizamos la clase Random para generar una comntraseña de caracteres aleatorios.
		Random random = new Random();
		
		//Generamos las letras mayúsculas de la A-Z
		for(int i=0; i<requisitosPass.getNumMayusculas(); i++){
			passwordChar.add((char) (random.nextInt(26) + 'A')); //26 por las letras que hay en el abecedario sin contar la 'Ñ' y empezamos por 'A'						
		}
		
		//Generamos las letras minúsculas de la a-Z
		for(int i=0; i<requisitosPass.getNumMinusculas(); i++){
			passwordChar.add((char)(random.nextInt(26) + 'a'));		
		}
		
		//Generamos los números del 0-9
		for(int i=0; i<requisitosPass.getNumDigitos(); i++){
			passwordChar.add((char)(random.nextInt(10) + '0')); //Empieza por el 0 
		}
		
		//Generamos los caracteres especiales
		String especiales = "!@#$%^&*()_-+=.:?";
		for(int i=0;i<requisitosPass.getNumCaractEspeciales(); i++){
			passwordChar.add(especiales.charAt(random.nextInt(especiales.length())));
		}
					
		
		//Intercalar los caracteres de la contraseña para que aparezcan en orden aleatorio
		Collections.shuffle(passwordChar);
		
		//Lo convertimos en una cadena de texto
		//Utilizamos un StringBuilder para mejorar la eficiencia del código y que los caracteres puedan ser mutables.
		StringBuilder password = new StringBuilder();
		for(char c : passwordChar) {
			password.append(c);
		}
				
		return "La contraseña generada es " + password.toString();
				
	}
	
	//Creamos el método para determinar la longitud de nuestra contraseña
	public String longitudPass(){
		
		int longitud = requisitosPass.getNumMayusculas() +
				requisitosPass.getNumMinusculas() +
				requisitosPass.getNumDigitos() +
				requisitosPass.getNumCaractEspeciales();
		return "La longitud de la contraseña que se va a generar es de " + longitud + " caracteres";
								
	}
	
}
