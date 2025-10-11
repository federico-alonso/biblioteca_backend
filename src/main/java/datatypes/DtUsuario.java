package datatypes;

public abstract class DtUsuario {
    private String email;
    private String nombre;
    private String contrasena;

    DtUsuario(){}
    DtUsuario(String nombre, String email, String contrasena){
        this.email = email;
        this.nombre = nombre;
        this.contrasena = contrasena;
    }
    public String getEmail() {
        return email;
    }
    public String getNombre(){
        return nombre;
    }
    public String getContrasena(){
        return contrasena;
    }
}
