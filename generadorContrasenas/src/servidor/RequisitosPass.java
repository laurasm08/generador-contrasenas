package servidor;

public class RequisitosPass {
	
	//Atributos
	private int numMayusculas;
	private int numMinusculas;
	private int numDigitos;
	private int numCaractEspeciales;
	
		
	//Creamos su constructor
	public RequisitosPass(int numMayusculas, int numMinusculas, int numDigitos, int numCaractEspeciales) {
		
		this.numMayusculas = numMayusculas;
		this.numMinusculas = numMinusculas;
		this.numDigitos = numDigitos;
		this.numCaractEspeciales = numCaractEspeciales;
				
	}

		
	//Getters y setters 
	public int getNumMayusculas() {
		return numMayusculas;
	}
	public void setNumMayusculas(int numMayusculas) {
		this.numMayusculas = numMayusculas;
	}
	public int getNumMinusculas() {
		return numMinusculas;
	}
	public void setNumMinusculas(int numMinusculas) {
		this.numMinusculas = numMinusculas;
	}
	public int getNumDigitos() {
		return numDigitos;
	}
	public void setNumDigitos(int numDigitos) {
		this.numDigitos = numDigitos;
	}
	public int getNumCaractEspeciales() {
		return numCaractEspeciales;
	}
	public void setNumCaractEspeciales(int numCaractEspeciales) {
		this.numCaractEspeciales = numCaractEspeciales;
	}
	
		
	//Método toString
	@Override
	public String toString(){
		
		return "RequisitosPass{"
				+ "numMayusculas = " +numMayusculas + 
				", numMinusculas = " +numMinusculas + 
				", numDigitos = " +numDigitos + 
				", numCaractEspeciales = " +numCaractEspeciales +
				"}";					
	}
}
