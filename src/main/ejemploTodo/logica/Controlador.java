package logica;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import datatypes.DtClase;
import datatypes.DtEntrenamiento;
import datatypes.DtSocio;
import datatypes.DtSpinning;
import interfaces.IControlador;

public class Controlador implements IControlador {
	
	private static EntityManager em;
	private static EntityManagerFactory emf;
	
	public Controlador() {
		super();
	}

	@Override
	public void agregarSocio(String ci, String nombre) {
		//Configuramos el EMF a través de la unidad de persistencia
		emf = Persistence.createEntityManagerFactory("Conexion");
		
		//Generamos un EntityManager
		em = emf.createEntityManager();
		
		//Iniciamos una transacción
		em.getTransaction().begin();
		
		//Construimos el objeto a persistir
		Socio socio = new Socio(ci,nombre);
		
		//Persistimos el objeto
		em.persist(socio);
		
		//Commmiteamos la transacción
		em.getTransaction().commit();
		
		//Cerramos el EntityManager
		em.close();
	}

	@Override
	public void agregarDtSpinning(DtSpinning clase) {
		emf = Persistence.createEntityManagerFactory("Conexion");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		Clase nuevaClase = new Spinning(clase.getId(),clase.getNombre(),clase.getTurno(),((DtSpinning) clase).getCantBicicletas()); 
		em.persist(nuevaClase);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void agregarDtEntrenamiento(DtEntrenamiento clase) {
		emf = Persistence.createEntityManagerFactory("Conexion");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		Clase nuevaClase = new Entrenamiento(clase.getId(),clase.getNombre(),clase.getTurno(),((DtEntrenamiento) clase).getEnRambla());
		em.persist(nuevaClase);
		em.getTransaction().commit();
		em.close();
	}
	
	@Override
	public void agregarInscripcion(String ciSocio, int idClase, Date fecha) {
		emf = Persistence.createEntityManagerFactory("Conexion");
		em = emf.createEntityManager();
		
		em.getTransaction().begin();
		Socio socio = em.find(Socio.class, ciSocio);
		Clase clase = em.find(Clase.class, idClase);
		clase.agregarInscripcion(socio, fecha);
		em.flush();
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void borrarInscripcion(String ciSocio, int idClase) {
		emf = Persistence.createEntityManagerFactory("Conexion");
		em = emf.createEntityManager();
		
		em.getTransaction().begin();
		Socio socio = em.find(Socio.class, ciSocio);
		Clase clase = em.find(Clase.class, idClase);
		clase.borrarInscripcion(socio);
		
		InscripcionID claveIns = new InscripcionID();
		claveIns.setClase(idClase);
		claveIns.setSocio(ciSocio);
		em.createQuery("delete from Inscripcion where id = :id").setParameter("id", claveIns).executeUpdate();
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public List<DtSocio> obtenerInfoSociosPorClase(int idClase) {
		emf = Persistence.createEntityManagerFactory("Conexion");
		em = emf.createEntityManager();
		
		Clase clase = em.find(Clase.class, idClase);
		List<DtSocio> infoSocios = clase.obtenerSocios();
		em.close();
		return infoSocios;
	}

	@Override
	public DtClase obtenerClase(int idClase) {
		emf = Persistence.createEntityManagerFactory("Conexion");
		em = emf.createEntityManager();
		
		Clase clase = em.find(Clase.class, idClase);
		DtClase dtClase = clase.getDtClase();
		em.close();
		return dtClase;
	}

}
