package logicaMagos;

public class Agua extends Hechizo {
	private int CantidadHeal;
	private double PresionAgua;
	
	public Agua(String NombreHechizo, int danio, int cantidadHeal, double PresionAgua) {
		super(NombreHechizo, danio);
		CantidadHeal = cantidadHeal;
		this.PresionAgua = PresionAgua;
	}
	
	
}
