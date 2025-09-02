package logica;

import datatypes.DtEstadoPorZona;
import datatypes.EstadoPmo;
import datatypes.Zona;
import interfaces.IControladorListarPrestamosZona;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;

public class ControladorListarPrestamosZona implements IControladorListarPrestamosZona {

    public ControladorListarPrestamosZona() {
        super();
    }

    @Override
    public List<DtEstadoPorZona> getResumenPrestamosPorZona() {
        List<Prestamo> prestamos = ManejadorPrestamo.getInstancia().listarPrestamos();

        Map<Zona, Map<EstadoPmo, Integer>> conteo = new HashMap<>();

        for (Prestamo p : prestamos) {
            Zona zona = p.getLector().getZona();
            EstadoPmo estado = p.getEstado();

            conteo.putIfAbsent(zona, new EnumMap<>(EstadoPmo.class));
            Map<EstadoPmo, Integer> estados = conteo.get(zona);
            estados.put(estado, estados.getOrDefault(estado, 0) + 1);
        }

        List<DtEstadoPorZona> resultado = new ArrayList<>();
        for (Zona z : Zona.values()) {
            Map<EstadoPmo, Integer> resumen = conteo.getOrDefault(z, new EnumMap<>(EstadoPmo.class));
            for (EstadoPmo e : EstadoPmo.values()) {
                resumen.putIfAbsent(e, 0);
            }
            resultado.add(new DtEstadoPorZona(z, resumen));
        }

        return resultado;
    }
}
