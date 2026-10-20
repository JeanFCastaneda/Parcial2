package co.edu.uniquindio.poo;
public class Diagnostico extends Servicio {

    public Diagnostico(String codigo, String nombre, String descripcion) {
        super(codigo, nombre, descripcion, 35000);
    }

    @Override
    public double calcularCosto(Equipo equipo, int horas) {
        return equipo != null ? 35000 : 0;
    }
}