package co.edu.uniquindio.poo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TechRepair {
    private List<Cliente> clientes;
    private List<Tecnico> tecnicos;
    private List<Repuesto> repuestos;
    private List<Servicio> servicios;
    private List<OrdenServicio> ordenes;

    public TechRepair() {
        clientes = new ArrayList<>();
        tecnicos = new ArrayList<>();
        repuestos = new ArrayList<>();
        servicios = new ArrayList<>();
        ordenes = new ArrayList<>();

        servicios.add(new Diagnostico("S001", "Diagnóstico", "Identificación de fallas"));
        servicios.add(new MantenimientoPreventivo("S002", "Mantenimiento preventivo", "Limpieza y optimización"));
        servicios.add(new ReparacionCorrectiva("S003", "Reparación correctiva", "Corrección de fallas"));
    }

    public boolean registrarCliente(Cliente cliente) {
        if (cliente == null || buscarCliente(cliente.getDocumento()).isPresent()) {
            return false;
        }

        clientes.add(cliente);
        return true;
    }

    public boolean registrarTecnico(Tecnico tecnico) {
        if (tecnico == null || buscarTecnico(tecnico.getCodigo()).isPresent()) {
            return false;
        }

        tecnicos.add(tecnico);
        return true;
    }

    public boolean registrarRepuesto(Repuesto repuesto) {
        if (repuesto == null || buscarRepuesto(repuesto.getCodigo()).isPresent()) {
            return false;
        }

        repuestos.add(repuesto);
        return true;
    }

    public boolean registrarEquipo(String documentoCliente, Equipo equipo) {
        Optional<Cliente> cliente = buscarCliente(documentoCliente);

        if (cliente.isEmpty() || equipo == null
                || buscarEquipo(equipo.getCodigoRegistro()).isPresent()
                || buscarEquipoPorSerie(equipo.getNumeroSerie()).isPresent()) {
            return false;
        }

        cliente.get().agregarEquipo(equipo);
        return true;
    }

    public boolean crearOrden(String codigo, String documentoCliente,
                              String observaciones) {
        Optional<Cliente> cliente = buscarCliente(documentoCliente);

        if (cliente.isEmpty() || buscarOrden(codigo).isPresent()) {
            return false;
        }

        OrdenServicio orden = new OrdenServicio(codigo, cliente.get(), observaciones);
        ordenes.add(orden);
        cliente.get().agregarOrden(orden);
        return true;
    }

    public boolean agregarDetalle(String codigoOrden, DetalleServicio detalle) {
        Optional<OrdenServicio> orden = buscarOrden(codigoOrden);

        if (orden.isEmpty() || detalle == null || detalle.getEquipo() == null) {
            return false;
        }

        boolean equipoDelCliente = orden.get().getCliente().buscarEquipo(
                detalle.getEquipo().getCodigoRegistro()).isPresent();

        if (!equipoDelCliente) return false;

        return orden.get().agregarDetalle(detalle);
    }

    public boolean asignarTecnico(String codigoOrden, String codigoDetalle,
                                  String codigoTecnico) {
        Optional<OrdenServicio> orden = buscarOrden(codigoOrden);
        Optional<Tecnico> tecnico = buscarTecnico(codigoTecnico);

        if (orden.isEmpty() || tecnico.isEmpty()) return false;

        Optional<DetalleServicio> detalle =
                orden.get().buscarDetalle(codigoDetalle);

        return detalle.isPresent()
                && detalle.get().asignarTecnico(tecnico.get());
    }

    public boolean agregarRepuesto(String codigoOrden, String codigoDetalle,
                                   String codigoRepuesto, int cantidad) {
        Optional<OrdenServicio> orden = buscarOrden(codigoOrden);
        Optional<Repuesto> repuesto = buscarRepuesto(codigoRepuesto);

        if (orden.isEmpty() || repuesto.isEmpty()) return false;

        Optional<DetalleServicio> detalle =
                orden.get().buscarDetalle(codigoDetalle);

        return detalle.isPresent()
                && detalle.get().agregarRepuesto(repuesto.get(), cantidad);
    }

    public boolean actualizarEstadoDetalle(String codigoOrden,
                                           String codigoDetalle,
                                           EstadoDetalle estado) {
        Optional<OrdenServicio> orden = buscarOrden(codigoOrden);

        if (orden.isEmpty() || estado == null) return false;

        Optional<DetalleServicio> detalle =
                orden.get().buscarDetalle(codigoDetalle);

        if (detalle.isEmpty()) return false;

        detalle.get().actualizarEstado(estado);
        detalle.get().calcularCosto();
        return true;
    }

    public boolean finalizarOrden(String codigoOrden) {
        Optional<OrdenServicio> orden = buscarOrden(codigoOrden);
        return orden.isPresent() && orden.get().finalizarOrden();
    }

    public Optional<Cliente> buscarCliente(String documento) {
        if (documento == null) return Optional.empty();

        return clientes.stream()
                .filter(c -> c.getDocumento().equals(documento))
                .findFirst();
    }

    public Optional<Tecnico> buscarTecnico(String codigo) {
        if (codigo == null) return Optional.empty();

        return tecnicos.stream()
                .filter(t -> t.getCodigo().equals(codigo))
                .findFirst();
    }

    public Optional<Repuesto> buscarRepuesto(String codigo) {
        if (codigo == null) return Optional.empty();

        return repuestos.stream()
                .filter(r -> r.getCodigo().equals(codigo))
                .findFirst();
    }

    public Optional<OrdenServicio> buscarOrden(String codigo) {
        if (codigo == null) return Optional.empty();

        return ordenes.stream()
                .filter(o -> o.getCodigo().equals(codigo))
                .findFirst();
    }

    public Optional<Equipo> buscarEquipo(String codigo) {
        if (codigo == null) return Optional.empty();

        return clientes.stream()
                .flatMap(c -> c.getEquipos().stream())
                .filter(e -> e.getCodigoRegistro().equals(codigo))
                .findFirst();
    }

    public Optional<Equipo> buscarEquipoPorSerie(String numeroSerie) {
        if (numeroSerie == null) return Optional.empty();

        return clientes.stream()
                .flatMap(c -> c.getEquipos().stream())
                .filter(e -> e.getNumeroSerie().equals(numeroSerie))
                .findFirst();
    }

    public List<OrdenServicio> consultarOrdenesCliente(String documento) {
        Optional<Cliente> cliente = buscarCliente(documento);

        return cliente.map(Cliente::getOrdenes).orElseGet(ArrayList::new);
    }

    public List<DetalleServicio> consultarTrabajosTecnico(String codigoTecnico) {
        return ordenes.stream()
                .flatMap(o -> o.getDetalles().stream())
                .filter(d -> d.getTecnico() != null
                        && d.getTecnico().getCodigo().equals(codigoTecnico))
                .collect(Collectors.toList());
    }

    public List<Equipo> consultarEquiposPendientes() {
        return ordenes.stream()
                .flatMap(o -> o.getDetalles().stream())
                .filter(d -> d.getEstado() != EstadoDetalle.FINALIZADO)
                .map(DetalleServicio::getEquipo)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Repuesto> consultarInventarioCritico() {
        return repuestos.stream()
                .filter(Repuesto::esCritico)
                .collect(Collectors.toList());
    }

    public long contarServiciosPorTipo(Class<?> tipoServicio) {
        return ordenes.stream()
                .flatMap(o -> o.getDetalles().stream())
                .filter(d -> tipoServicio.isInstance(d.getServicio()))
                .count();
    }

    public double calcularFacturacionPorCategoria(CategoriaEquipo categoria) {
        return ordenes.stream()
                .flatMap(o -> o.getDetalles().stream())
                .filter(d -> d.getEquipo() != null
                        && d.getEquipo().getCategoria() == categoria)
                .mapToDouble(DetalleServicio::getCostoCalculado)
                .sum();
    }

    public long contarTrabajosFinalizados(String codigoTecnico) {
        return consultarTrabajosTecnico(codigoTecnico).stream()
                .filter(d -> d.getEstado() == EstadoDetalle.FINALIZADO)
                .count();
    }

    public double calcularFacturacionTecnico(String codigoTecnico) {
        return consultarTrabajosTecnico(codigoTecnico).stream()
                .filter(d -> d.getEstado() == EstadoDetalle.FINALIZADO)
                .mapToDouble(DetalleServicio::getCostoCalculado)
                .sum();
    }

    public List<Cliente> getClientes() {
        return new ArrayList<>(clientes);
    }

    public List<Tecnico> getTecnicos() {
        return new ArrayList<>(tecnicos);
    }

    public List<Repuesto> getRepuestos() {
        return new ArrayList<>(repuestos);
    }

    public List<Servicio> getServicios() {
        return new ArrayList<>(servicios);
    }

    public List<OrdenServicio> getOrdenes() {
        return new ArrayList<>(ordenes);
    }
 /*   public List<Cliente>  (){
       if(CategoriaEquipo.TELEFONO){
         List<Cliente> = new ArrayList<>(add.)
       }


        for()

    }
    //obtener lista de clientes que hayan realizado la reparacion de un celular
}*\

  */
}
