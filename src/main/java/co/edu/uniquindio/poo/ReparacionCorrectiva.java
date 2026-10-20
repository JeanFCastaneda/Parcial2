package co.edu.uniquindio.poo;
public class ReparacionCorrectiva extends Servicio {

    public ReparacionCorrectiva(String codigo, String nombre,
                                String descripcion) {
        super(codigo, nombre, descripcion, 45000);
    }

    @Override
    public double calcularCosto(Equipo equipo, int horas) {
        if (equipo == null || horas <= 0) return 0;
        return 45000.0 * horas;
    }
}