package co.edu.uniquindio.poo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cliente extends Persona {
    private String correo;
    private String direccion;
    private List<Equipo> equipos;
    private List<OrdenServicio> ordenes;

    public Cliente(String nombre, String documento, String telefono,
                   String correo, String direccion) {
        super(nombre, documento, telefono);
        this.correo = correo != null ? correo : "";
        this.direccion = direccion != null ? direccion : "";
        this.equipos = new ArrayList<>();
        this.ordenes = new ArrayList<>();
    }

    public void agregarEquipo(Equipo equipo) {
        if (equipo != null && buscarEquipo(equipo.getCodigoRegistro()).isEmpty()) {
            equipos.add(equipo);
        }
    }

    public Optional<Equipo> buscarEquipo(String codigo) {
        if (codigo == null) return Optional.empty();

        return equipos.stream()
                .filter(e -> e.getCodigoRegistro().equals(codigo))
                .findFirst();
    }

    public void agregarOrden(OrdenServicio orden) {
        if (orden != null && buscarOrden(orden.getCodigo()).isEmpty()) {
            ordenes.add(orden);
        }
    }

    public Optional<OrdenServicio> buscarOrden(String codigo) {
        if (codigo == null) return Optional.empty();

        return ordenes.stream()
                .filter(o -> o.getCodigo().equals(codigo))
                .findFirst();
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo != null ? correo : "";
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion != null ? direccion : "";
    }

    public List<Equipo> getEquipos() {
        return new ArrayList<>(equipos);
    }

    public List<OrdenServicio> getOrdenes() {
        return new ArrayList<>(ordenes);
    }
}