package logicaMagos;

public class Planta extends Hechizo {
	private int DuracionStun;
	private double CantPlantas;
	
	public Planta(String NombreHechizo, int danio, int duracionStun, double CantPlantas) {
		super(NombreHechizo, danio);
		DuracionStun = duracionStun;
		this.CantPlantas = CantPlantas;
	}
	
	
}
