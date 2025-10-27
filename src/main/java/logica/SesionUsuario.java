package logica;

import datatypes.DtUsuario;
import datatypes.DtBibliotecario;
import datatypes.DtLector;

/**
 * Singleton para mantener la sesión del usuario logueado
 */
public class SesionUsuario {
    private static SesionUsuario instancia = null;
    
    private String tipoUsuario; // "bibliotecario" o "lector"
    private DtUsuario usuario;
    
    private SesionUsuario() {}
    
    public static SesionUsuario getInstancia() {
        if (instancia == null) {
            instancia = new SesionUsuario();
        }
        return instancia;
    }
    
    /**
     * Inicia sesión con un usuario
     */
    public void iniciarSesion(String tipo, DtUsuario usuario) {
        this.tipoUsuario = tipo;
        this.usuario = usuario;
    }
    
    /**
     * Cierra la sesión
     */
    public void cerrarSesion() {
        this.tipoUsuario = null;
        this.usuario = null;
    }
    
    /**
     * Verifica si hay un usuario logueado
     */
    public boolean hayUsuarioLogueado() {
        return usuario != null;
    }
    
    /**
     * Verifica si el usuario logueado es bibliotecario
     */
    public boolean esBibliotecario() {
        return "bibliotecario".equals(tipoUsuario);
    }
    
    /**
     * Verifica si el usuario logueado es lector
     */
    public boolean esLector() {
        return "lector".equals(tipoUsuario);
    }
    
    /**
     * Obtiene el nombre del usuario logueado
     */
    public String getNombreUsuario() {
        return usuario != null ? usuario.getNombre() : null;
    }
    
    /**
     * Obtiene el email del usuario logueado
     */
    public String getEmailUsuario() {
        return usuario != null ? usuario.getEmail() : null;
    }
    
    /**
     * Obtiene el tipo de usuario
     */
    public String getTipoUsuario() {
        return tipoUsuario;
    }
    
    /**
     * Obtiene el usuario completo
     */
    public DtUsuario getUsuario() {
        return usuario;
    }
    
    /**
     * Obtiene el bibliotecario si está logueado
     */
    public DtBibliotecario getBibliotecario() {
        if (esBibliotecario()) {
            return (DtBibliotecario) usuario;
        }
        return null;
    }
    
    /**
     * Obtiene el lector si está logueado
     */
    public DtLector getLector() {
        if (esLector()) {
            return (DtLector) usuario;
        }
        return null;
    }
}

