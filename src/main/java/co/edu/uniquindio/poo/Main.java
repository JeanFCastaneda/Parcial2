package co.edu.uniquindio.poo;
import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {
        TechRepair sistema = new TechRepair();
        int opcion;

        do {
            String menu = """
                    TECHREPAIR
                    1. Registrar cliente
                    2. Registrar técnico
                    3. Registrar repuesto
                    4. Consultar clientes
                    5. Consultar técnicos
                    6. Consultar inventario crítico
                    7. Consultar órdenes
                    8. Consultar equipos pendientes
                    9. Reporte de servicios
                    10. Salir
                    """;

            String entrada = JOptionPane.showInputDialog(null, menu);

            if (entrada == null) break;

            try {
                opcion = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingresa una opción válida.");
                continue;
            }

            switch (opcion) {
                case 1 -> registrarCliente(sistema);
                case 2 -> registrarTecnico(sistema);
                case 3 -> registrarRepuesto(sistema);
                case 4 -> consultarClientes(sistema);
                case 5 -> consultarTecnicos(sistema);
                case 6 -> consultarInventario(sistema);
                case 7 -> consultarOrdenes(sistema);
                case 8 -> consultarPendientes(sistema);
                case 9 -> reporteServicios(sistema);
                case 10 -> JOptionPane.showMessageDialog(null, "Hasta pronto.");
                default -> JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        } while (true);
    }

    private static void registrarCliente(TechRepair sistema) {
        String nombre = JOptionPane.showInputDialog("Nombre completo:");
        if (nombre == null) return;

        String documento = JOptionPane.showInputDialog("Documento:");
        if (documento == null) return;

        String telefono = JOptionPane.showInputDialog("Teléfono:");
        if (telefono == null) return;

        String correo = JOptionPane.showInputDialog("Correo:");
        if (correo == null) return;

        String direccion = JOptionPane.showInputDialog("Dirección:");
        if (direccion == null) return;

        Cliente cliente = new Cliente(nombre, documento, telefono, correo, direccion);

        JOptionPane.showMessageDialog(null,
                sistema.registrarCliente(cliente)
                        ? "Cliente registrado."
                        : "No se pudo registrar. Verifica el documento.");
    }

    private static void registrarTecnico(TechRepair sistema) {
        String codigo = JOptionPane.showInputDialog("Código del técnico:");
        if (codigo == null) return;

        String nombre = JOptionPane.showInputDialog("Nombre:");
        if (nombre == null) return;

        String documento = JOptionPane.showInputDialog("Documento:");
        if (documento == null) return;

        String telefono = JOptionPane.showInputDialog("Teléfono:");
        if (telefono == null) return;

        Especialidad especialidad = (Especialidad) JOptionPane.showInputDialog(
                null, "Especialidad:", "Técnico", JOptionPane.QUESTION_MESSAGE,
                null, Especialidad.values(), Especialidad.SOPORTE_GENERAL);

        if (especialidad == null) return;

        EstadoTecnico estado = (EstadoTecnico) JOptionPane.showInputDialog(
                null, "Estado:", "Técnico", JOptionPane.QUESTION_MESSAGE,
                null, EstadoTecnico.values(), EstadoTecnico.ACTIVO);

        if (estado == null) return;

        Tecnico tecnico = new Tecnico(nombre, documento, telefono,
                codigo, especialidad, estado);

        JOptionPane.showMessageDialog(null,
                sistema.registrarTecnico(tecnico)
                        ? "Técnico registrado."
                        : "No se pudo registrar. Verifica el código.");
    }

    private static void registrarRepuesto(TechRepair sistema) {
        String codigo = JOptionPane.showInputDialog("Código del repuesto:");
        if (codigo == null) return;

        String nombre = JOptionPane.showInputDialog("Nombre:");
        if (nombre == null) return;

        String descripcion = JOptionPane.showInputDialog("Descripción:");
        if (descripcion == null) return;

        String precioTexto = JOptionPane.showInputDialog("Precio unitario:");
        if (precioTexto == null) return;

        String cantidadTexto = JOptionPane.showInputDialog("Cantidad disponible:");
        if (cantidadTexto == null) return;

        try {
            double precio = Double.parseDouble(precioTexto);
            int cantidad = Integer.parseInt(cantidadTexto);

            CategoriaEquipo categoria = (CategoriaEquipo) JOptionPane.showInputDialog(
                    null, "Categoría compatible:", "Repuesto",
                    JOptionPane.QUESTION_MESSAGE, null,
                    CategoriaEquipo.values(), CategoriaEquipo.PORTATIL);

            if (categoria == null) return;

            if (precio < 0 || cantidad < 0) {
                JOptionPane.showMessageDialog(null, "Los valores no pueden ser negativos.");
                return;
            }

            Repuesto repuesto = new Repuesto(codigo, nombre, descripcion,
                    precio, cantidad, categoria);

            JOptionPane.showMessageDialog(null,
                    sistema.registrarRepuesto(repuesto)
                            ? "Repuesto registrado."
                            : "No se pudo registrar. Verifica el código.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Precio o cantidad no válidos.");
        }
    }

    private static void consultarClientes(TechRepair sistema) {
        StringBuilder texto = new StringBuilder();

        for (Cliente cliente : sistema.getClientes()) {
            texto.append(cliente.getNombre())
                    .append(" | Documento: ").append(cliente.getDocumento())
                    .append(" | Teléfono: ").append(cliente.getTelefono())
                    .append("\n");
        }

        JOptionPane.showMessageDialog(null,
                texto.length() > 0 ? texto.toString() : "No hay clientes registrados.");
    }

    private static void consultarTecnicos(TechRepair sistema) {
        StringBuilder texto = new StringBuilder();

        for (Tecnico tecnico : sistema.getTecnicos()) {
            texto.append(tecnico.getNombre())
                    .append(" | Código: ").append(tecnico.getCodigo())
                    .append(" | Especialidad: ").append(tecnico.getEspecialidad())
                    .append(" | Estado: ").append(tecnico.getEstado())
                    .append("\n");
        }

        JOptionPane.showMessageDialog(null,
                texto.length() > 0 ? texto.toString() : "No hay técnicos registrados.");
    }

    private static void consultarInventario(TechRepair sistema) {
        StringBuilder texto = new StringBuilder();

        for (Repuesto repuesto : sistema.consultarInventarioCritico()) {
            texto.append(repuesto.getNombre())
                    .append(" | Código: ").append(repuesto.getCodigo())
                    .append(" | Disponibles: ").append(repuesto.getCantidadDisponible())
                    .append("\n");
        }

        JOptionPane.showMessageDialog(null,
                texto.length() > 0 ? texto.toString() : "No hay repuestos críticos.");
    }

    private static void consultarOrdenes(TechRepair sistema) {
        StringBuilder texto = new StringBuilder();

        for (OrdenServicio orden : sistema.getOrdenes()) {
            texto.append("Orden: ").append(orden.getCodigo())
                    .append(" | Cliente: ").append(orden.getCliente().getNombre())
                    .append(" | Estado: ").append(orden.getEstado())
                    .append(" | Total: $").append(orden.calcularValorTotal())
                    .append("\n");
        }

        JOptionPane.showMessageDialog(null,
                texto.length() > 0 ? texto.toString() : "No hay órdenes registradas.");
    }

    private static void consultarPendientes(TechRepair sistema) {
        StringBuilder texto = new StringBuilder();

        for (Equipo equipo : sistema.consultarEquiposPendientes()) {
            texto.append(equipo.getCodigoRegistro())
                    .append(" | ").append(equipo.getMarca())
                    .append(" ").append(equipo.getModelo())
                    .append("\n");
        }

        JOptionPane.showMessageDialog(null,
                texto.length() > 0 ? texto.toString() : "No hay equipos pendientes.");
    }

    private static void reporteServicios(TechRepair sistema) {
        String texto = "Servicios registrados:\n"
                + "Diagnósticos: "
                + sistema.contarServiciosPorTipo(Diagnostico.class) + "\n"
                + "Mantenimientos preventivos: "
                + sistema.contarServiciosPorTipo(MantenimientoPreventivo.class) + "\n"
                + "Reparaciones correctivas: "
                + sistema.contarServiciosPorTipo(ReparacionCorrectiva.class) + "\n\n"
                + "Facturación por categoría:\n"
                + "Portátiles: $"
                + sistema.calcularFacturacionPorCategoria(CategoriaEquipo.PORTATIL) + "\n"
                + "Escritorio: $"
                + sistema.calcularFacturacionPorCategoria(CategoriaEquipo.ESCRITORIO) + "\n"
                + "Teléfonos: $"
                + sistema.calcularFacturacionPorCategoria(CategoriaEquipo.TELEFONO);

        JOptionPane.showMessageDialog(null, texto);
    }
}