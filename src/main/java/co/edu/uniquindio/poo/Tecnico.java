package co.edu.uniquindio.poo;
public class Tecnico extends Persona {
    private String codigo;
    private Especialidad especialidad;
    private EstadoTecnico estado;

    public Tecnico(String nombre, String documento, String telefono,
                   String codigo, Especialidad especialidad, EstadoTecnico estado) {
        super(nombre, documento, telefono);
        this.codigo = codigo != null ? codigo : "";
        this.especialidad = especialidad;
        this.estado = estado;
    }

    public boolean estaActivo() {
        return estado == EstadoTecnico.ACTIVO;
    }

    public boolean puedeAtender(CategoriaEquipo categoria) {
        if (!estaActivo() || categoria == null || especialidad == null) {
            return false;
        }

        if (especialidad == Especialidad.SOPORTE_GENERAL) return true;

        if (especialidad == Especialidad.COMPUTADORES) {
            return categoria == CategoriaEquipo.PORTATIL
                    || categoria == CategoriaEquipo.ESCRITORIO;
        }

        return especialidad == Especialidad.DISPOSITIVOS_MOVILES
                && categoria == CategoriaEquipo.TELEFONO;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        if (codigo != null && !codigo.trim().isEmpty()) this.codigo = codigo;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        if (especialidad != null) this.especialidad = especialidad;
    }

    public EstadoTecnico getEstado() {
        return estado;
    }

    public void setEstado(EstadoTecnico estado) {
        if (estado != null) this.estado = estado;
    }
}