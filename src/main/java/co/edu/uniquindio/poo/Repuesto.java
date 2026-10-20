package co.edu.uniquindio.poo;
public class Repuesto {
    private String codigo;
    private String nombre;
    private String descripcion;
    private double precioUnitario;
    private int cantidadDisponible;
    private CategoriaEquipo categoriaCompatibilidad;

    public Repuesto(String codigo, String nombre, String descripcion,
                    double precioUnitario, int cantidadDisponible,
                    CategoriaEquipo categoriaCompatibilidad) {
        this.codigo = codigo != null ? codigo : "";
        this.nombre = nombre != null ? nombre : "";
        this.descripcion = descripcion != null ? descripcion : "";
        this.precioUnitario = Math.max(0, precioUnitario);
        this.cantidadDisponible = Math.max(0, cantidadDisponible);
        this.categoriaCompatibilidad = categoriaCompatibilidad;
    }

    public boolean hayDisponibilidad(int cantidad) {
        return cantidad > 0 && cantidad <= cantidadDisponible;
    }

    public boolean descontarCantidad(int cantidad) {
        if (!hayDisponibilidad(cantidad)) return false;
        cantidadDisponible -= cantidad;
        return true;
    }

    public void aumentarCantidad(int cantidad) {
        if (cantidad > 0) cantidadDisponible += cantidad;
    }

    public boolean esCritico() {
        return cantidadDisponible < 5;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        if (precioUnitario >= 0) this.precioUnitario = precioUnitario;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public CategoriaEquipo getCategoriaCompatibilidad() {
        return categoriaCompatibilidad;
    }
}