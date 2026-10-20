package co.edu.uniquindio.poo;
public class ComputadorPortatil extends Equipo {
    private double tamanoPantalla;
    private int memoriaRAM;
    private int almacenamiento;
    private boolean bateriaExtraible;

    public ComputadorPortatil(String codigoRegistro, String numeroSerie,
                              String marca, String modelo, int anioFabricacion,
                              String descripcionEstadoFisico, double tamanoPantalla,
                              int memoriaRAM, int almacenamiento, boolean bateriaExtraible) {
        super(codigoRegistro, numeroSerie, marca, modelo,
                anioFabricacion, descripcionEstadoFisico);
        this.tamanoPantalla = tamanoPantalla;
        this.memoriaRAM = memoriaRAM;
        this.almacenamiento = almacenamiento;
        this.bateriaExtraible = bateriaExtraible;
    }

    @Override
    public CategoriaEquipo getCategoria() {
        return CategoriaEquipo.PORTATIL;
    }

    public double getTamanoPantalla() {
        return tamanoPantalla;
    }

    public void setTamanoPantalla(double valor) {
        if (valor > 0) tamanoPantalla = valor;
    }

    public int getMemoriaRAM() {
        return memoriaRAM;
    }

    public void setMemoriaRAM(int valor) {
        if (valor > 0) memoriaRAM = valor;
    }

    public int getAlmacenamiento() {
        return almacenamiento;
    }

    public void setAlmacenamiento(int valor) {
        if (valor > 0) almacenamiento = valor;
    }

    public boolean isBateriaExtraible() {
        return bateriaExtraible;
    }

    public void setBateriaExtraible(boolean valor) {
        bateriaExtraible = valor;
    }
}