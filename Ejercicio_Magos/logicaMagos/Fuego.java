package logicaMagos;

public class Fuego extends Hechizo {
	
	private int DuracionQuemadura;
	
	public Fuego(String NombreHechizo, int danio, int DuracionQuemadura) {
		super(NombreHechizo, danio);
		
		this.DuracionQuemadura = DuracionQuemadura;
	}

}
