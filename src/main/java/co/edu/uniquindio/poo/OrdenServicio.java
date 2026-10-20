package co.edu.uniquindio.poo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrdenServicio {
    private String codigo;
    private LocalDate fechaCreacion;
    private Cliente cliente;
    private String observacionesGenerales;
    private EstadoOrden estado;
    private List<DetalleServicio> detalles;

    public OrdenServicio(String codigo, Cliente cliente,
                         String observacionesGenerales) {
        this.codigo = codigo != null ? codigo : "";
        this.fechaCreacion = LocalDate.now();
        this.cliente = cliente;
        this.observacionesGenerales =
                observacionesGenerales != null ? observacionesGenerales : "";
        this.estado = EstadoOrden.ABIERTA;
        this.detalles = new ArrayList<>();
    }

    public boolean agregarDetalle(DetalleServicio detalle) {
        if (detalle == null || estado != EstadoOrden.ABIERTA
                || buscarDetalle(detalle.getCodigo()).isPresent()) {
            return false;
        }

        detalles.add(detalle);
        estado = EstadoOrden.EN_PROCESO;
        return true;
    }

    public Optional<DetalleServicio> buscarDetalle(String codigo) {
        if (codigo == null) return Optional.empty();

        return detalles.stream()
                .filter(d -> d.getCodigo().equals(codigo))
                .findFirst();
    }

    public double calcularValorTotal() {
        double total = 0;

        for (DetalleServicio detalle : detalles) {
            total += detalle.calcularCosto();
        }

        return total;
    }

    public boolean finalizarOrden() {
        if (detalles.isEmpty()) return false;

        for (DetalleServicio detalle : detalles) {
            if (detalle.getEstado() != EstadoDetalle.FINALIZADO) {
                return false;
            }
        }

        estado = EstadoOrden.FINALIZADA;
        return true;
    }

    public boolean entregarOrden() {
        if (estado != EstadoOrden.FINALIZADA) return false;

        estado = EstadoOrden.ENTREGADA;
        return true;
    }

    public String getCodigo() {
        return codigo;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getObservacionesGenerales() {
        return observacionesGenerales;
    }

    public void setObservacionesGenerales(String observacionesGenerales) {
        if (observacionesGenerales != null) {
            this.observacionesGenerales = observacionesGenerales;
        }
    }

    public EstadoOrden getEstado() {
        return estado;
    }

    public void setEstado(EstadoOrden estado) {
        if (estado != null) this.estado = estado;
    }

    public List<DetalleServicio> getDetalles() {
        return new ArrayList<>(detalles);
    }
}