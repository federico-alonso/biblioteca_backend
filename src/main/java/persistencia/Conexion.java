package persistencia;


public class Conexion {
	private static Conexion instancia = null;
	private static EntityManagerFactory emf;
	
	
	public static Conexion getInstancia() {
		if (instancia == null) {
			instancia = new Conexion();
		}
		return instancia;
	}
	
	public EntityManager getEntityManager() {
	}
	
	public void close() {
	}
}
