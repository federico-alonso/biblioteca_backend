package datatypes;

public abstract class DtUsuario {
    private String email;
    private String nombre;

    DtUsuario(){}
    DtUsuario(String email, String nombre){
        this.email = email;
        this.nombre = nombre;
    }
    public String getEmail() {
        return email;
    }
    public String getNombre(){
        return nombre;
    }
}
