package co.edu.uniquindio.poo;
public class RepuestoUtilizado {
    private Repuesto repuesto;
    private int cantidadRequerida;
    private double precioUnitarioAplicado;

    public RepuestoUtilizado(Repuesto repuesto, int cantidadRequerida) {
        this.repuesto = repuesto;
        this.cantidadRequerida = 0;
        this.precioUnitarioAplicado =
                repuesto != null ? repuesto.getPrecioUnitario() : 0;

        if (repuesto != null && repuesto.descontarCantidad(cantidadRequerida)) {
            this.cantidadRequerida = cantidadRequerida;
        }
    }

    public Repuesto getRepuesto() {
        return repuesto;
    }

    public int getCantidadRequerida() {
        return cantidadRequerida;
    }

    public double getPrecioUnitarioAplicado() {
        return precioUnitarioAplicado;
    }

    public double calcularCosto() {
        return cantidadRequerida * precioUnitarioAplicado;
    }

    public boolean esValido() {
        return repuesto != null && cantidadRequerida > 0;
    }
}