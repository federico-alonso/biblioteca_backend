package datatypes;

import java.util.Map;

public class DtEstadoPorZona {

    private Zona zona;
    private Map<EstadoPmo, Integer> resumen;

    public DtEstadoPorZona(Zona zona, Map<EstadoPmo, Integer> resumen) {
        this.zona = zona;
        this.resumen = resumen;
    }

    public Zona getZona() {
        return zona;
    }

    public Map<EstadoPmo, Integer> getResumen() {
        return resumen;
    }
}
