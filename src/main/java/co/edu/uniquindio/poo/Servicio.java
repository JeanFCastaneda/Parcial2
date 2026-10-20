package co.edu.uniquindio.poo;
public abstract class Servicio {
    private String codigo;
    private String nombre;
    private String descripcion;
    private double tarifaBase;

    public Servicio(String codigo, String nombre, String descripcion,
                    double tarifaBase) {
        this.codigo = codigo != null ? codigo : "";
        this.nombre = nombre != null ? nombre : "";
        this.descripcion = descripcion != null ? descripcion : "";
        this.tarifaBase = Math.max(0, tarifaBase);
    }

    public abstract double calcularCosto(Equipo equipo, int horas);

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getTarifaBase() {
        return tarifaBase;
    }

    public void setTarifaBase(double tarifaBase) {
        if (tarifaBase >= 0) this.tarifaBase = tarifaBase;
    }
}