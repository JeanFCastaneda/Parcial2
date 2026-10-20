package co.edu.uniquindio.poo;
public class MantenimientoPreventivo extends Servicio {

    public MantenimientoPreventivo(String codigo, String nombre,
                                   String descripcion) {
        super(codigo, nombre, descripcion, 0);
    }

    @Override
    public double calcularCosto(Equipo equipo, int horas) {
        if (equipo == null) return 0;

        if (equipo.getCategoria() == CategoriaEquipo.PORTATIL) {
            return 60000;
        }

        if (equipo.getCategoria() == CategoriaEquipo.ESCRITORIO) {
            return 70000;
        }

        if (equipo.getCategoria() == CategoriaEquipo.TELEFONO) {
            return 40000;
        }

        return 0;
    }
}