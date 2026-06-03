package logicaMagos;

public abstract class Hechizo {
	private String NombreHechizo;
	private int danio;
	
	public Hechizo(String NombreHechizo, int danio) {
		
		this.NombreHechizo = NombreHechizo;
		this.danio = danio;
	}

	public String getNombreHechizo() {
		return NombreHechizo;
	}

	public int getDanio() {
		return danio;
	}
	
	
}
