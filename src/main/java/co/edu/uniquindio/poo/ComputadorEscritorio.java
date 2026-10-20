package co.edu.uniquindio.poo;
public class ComputadorEscritorio extends Equipo {
    private String tipoProcesador;
    private int memoriaRAM;
    private int almacenamiento;
    private boolean tarjetaGraficaDedicada;

    public ComputadorEscritorio(String codigoRegistro, String numeroSerie,
                                String marca, String modelo, int anioFabricacion,
                                String descripcionEstadoFisico, String tipoProcesador,
                                int memoriaRAM, int almacenamiento, boolean tarjetaGraficaDedicada) {
        super(codigoRegistro, numeroSerie, marca, modelo,
                anioFabricacion, descripcionEstadoFisico);
        this.tipoProcesador = tipoProcesador != null ? tipoProcesador : "";
        this.memoriaRAM = memoriaRAM;
        this.almacenamiento = almacenamiento;
        this.tarjetaGraficaDedicada = tarjetaGraficaDedicada;
    }

    @Override
    public CategoriaEquipo getCategoria() {
        return CategoriaEquipo.ESCRITORIO;
    }

    public String getTipoProcesador() {
        return tipoProcesador;
    }

    public void setTipoProcesador(String valor) {
        if (valor != null && !valor.trim().isEmpty()) tipoProcesador = valor;
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

    public boolean isTarjetaGraficaDedicada() {
        return tarjetaGraficaDedicada;
    }

    public void setTarjetaGraficaDedicada(boolean valor) {
        tarjetaGraficaDedicada = valor;
    }
}