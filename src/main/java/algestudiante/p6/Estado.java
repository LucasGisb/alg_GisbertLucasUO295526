package algestudiante.p6;

public interface Estado {

	public abstract boolean esSoulucion();
	
	public abstract Estado nextHijo();
	
	public abstract boolean hasNextHijos();
}
