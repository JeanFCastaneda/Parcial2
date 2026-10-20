package co.edu.uniquindio.poo;
public class TelefonoInteligente extends Equipo {
    private String sistemaOperativo;
    private int almacenamiento;
    private int capacidadBateria;
    private boolean compatible5G;

    public TelefonoInteligente(String codigoRegistro, String numeroSerie,
                               String marca, String modelo, int anioFabricacion,
                               String descripcionEstadoFisico, String sistemaOperativo,
                               int almacenamiento, int capacidadBateria, boolean compatible5G) {
        super(codigoRegistro, numeroSerie, marca, modelo,
                anioFabricacion, descripcionEstadoFisico);
        this.sistemaOperativo = sistemaOperativo != null ? sistemaOperativo : "";
        this.almacenamiento = almacenamiento;
        this.capacidadBateria = capacidadBateria;
        this.compatible5G = compatible5G;
    }

    @Override
    public CategoriaEquipo getCategoria() {
        return CategoriaEquipo.TELEFONO;
    }

    public String getSistemaOperativo() {
        return sistemaOperativo;
    }

    public void setSistemaOperativo(String valor) {
        if (valor != null && !valor.trim().isEmpty()) sistemaOperativo = valor;
    }

    public int getAlmacenamiento() {
        return almacenamiento;
    }

    public void setAlmacenamiento(int valor) {
        if (valor > 0) almacenamiento = valor;
    }

    public int getCapacidadBateria() {
        return capacidadBateria;
    }

    public void setCapacidadBateria(int valor) {
        if (valor > 0) capacidadBateria = valor;
    }

    public boolean isCompatible5G() {
        return compatible5G;
    }

    public void setCompatible5G(boolean valor) {
        compatible5G = valor;
    }
}