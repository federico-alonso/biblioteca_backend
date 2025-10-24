package datatypes;

import java.util.ArrayList;
import java.util.List;

public class DtEstadoPorZona {
    private Zona zona;
    private List<ResumenEstado> resumen;

    public DtEstadoPorZona() {}
    
    public DtEstadoPorZona(Zona zona, List<ResumenEstado> resumen) {
        this.zona = zona;
        this.resumen = resumen;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona value) {
        this.zona = value;
    }

    public List<ResumenEstado> getResumen() {
        if (resumen == null) {
            resumen = new ArrayList<>();
        }
        return resumen;
    }

    public void setResumen(List<ResumenEstado> value) {
        this.resumen = value;
    }
    
    // Clase interna para representar cada entrada del resumen
    public static class ResumenEstado {
        private EstadoPmo estado;
        private int cantidad;
        
        public ResumenEstado() {}
        
        public ResumenEstado(EstadoPmo estado, int cantidad) {
            this.estado = estado;
            this.cantidad = cantidad;
        }
        
        public EstadoPmo getEstado() {
            return estado;
        }
        
        public void setEstado(EstadoPmo estado) {
            this.estado = estado;
        }
        
        public int getCantidad() {
            return cantidad;
        }
        
        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }
    }
}