package co.edu.uniquindio.poo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DetalleServicio {
    private String codigo;
    private Equipo equipo;
    private Servicio servicio;
    private String descripcionFalla;
    private Tecnico tecnico;
    private int horasTrabajo;
    private String observacionesTecnicas;
    private EstadoDetalle estado;
    private double costoCalculado;
    private List<RepuestoUtilizado> repuestosUtilizados;

    public DetalleServicio(String codigo, Equipo equipo, Servicio servicio,
                           String descripcionFalla, int horasTrabajo) {
        this.codigo = codigo != null ? codigo : "";
        this.equipo = equipo;
        this.servicio = servicio;
        this.descripcionFalla = descripcionFalla != null ? descripcionFalla : "";
        this.horasTrabajo = Math.max(0, horasTrabajo);
        this.observacionesTecnicas = "";
        this.estado = EstadoDetalle.PENDIENTE;
        this.costoCalculado = 0;
        this.repuestosUtilizados = new ArrayList<>();
    }

    public boolean asignarTecnico(Tecnico tecnico) {
        if (tecnico == null || equipo == null
                || !tecnico.puedeAtender(equipo.getCategoria())) {
            return false;
        }

        this.tecnico = tecnico;
        return true;
    }

    public boolean agregarRepuesto(Repuesto repuesto, int cantidad) {
        if (!(servicio instanceof ReparacionCorrectiva)
                || repuesto == null || equipo == null
                || repuesto.getCategoriaCompatibilidad() != equipo.getCategoria()) {
            return false;
        }

        Optional<RepuestoUtilizado> existente = repuestosUtilizados.stream()
                .filter(r -> r.getRepuesto().getCodigo().equals(repuesto.getCodigo()))
                .findFirst();

        RepuestoUtilizado nuevo = new RepuestoUtilizado(repuesto, cantidad);

        if (!nuevo.esValido()) return false;

        if (existente.isPresent()) {
            RepuestoUtilizado anterior = existente.get();
            repuestosUtilizados.remove(anterior);
            repuestosUtilizados.add(anterior);
        }

        repuestosUtilizados.add(nuevo);
        calcularCosto();
        return true;
    }

    public double calcularCosto() {
        if (servicio == null || equipo == null) {
            costoCalculado = 0;
            return costoCalculado;
        }

        costoCalculado = servicio.calcularCosto(equipo, horasTrabajo);

        if (servicio instanceof ReparacionCorrectiva) {
            for (RepuestoUtilizado repuesto : repuestosUtilizados) {
                costoCalculado += repuesto.calcularCosto();
            }
        }

        return costoCalculado;
    }

    public void actualizarEstado(EstadoDetalle estado) {
        if (estado != null) this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public String getDescripcionFalla() {
        return descripcionFalla;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public int getHorasTrabajo() {
        return horasTrabajo;
    }

    public String getObservacionesTecnicas() {
        return observacionesTecnicas;
    }

    public void setObservacionesTecnicas(String observacionesTecnicas) {
        this.observacionesTecnicas =
                observacionesTecnicas != null ? observacionesTecnicas : "";
    }

    public EstadoDetalle getEstado() {
        return estado;
    }

    public double getCostoCalculado() {
        return costoCalculado;
    }

    public List<RepuestoUtilizado> getRepuestosUtilizados() {
        return new ArrayList<>(repuestosUtilizados);
    }
}