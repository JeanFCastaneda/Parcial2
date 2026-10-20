package co.edu.uniquindio.poo;
public abstract class Equipo {
    private String codigoRegistro;
    private String numeroSerie;
    private String marca;
    private String modelo;
    private int anioFabricacion;
    private String descripcionEstadoFisico;

    public Equipo(String codigoRegistro, String numeroSerie, String marca,
                  String modelo, int anioFabricacion,
                  String descripcionEstadoFisico) {
        this.codigoRegistro = codigoRegistro != null ? codigoRegistro : "";
        this.numeroSerie = numeroSerie != null ? numeroSerie : "";
        this.marca = marca != null ? marca : "";
        this.modelo = modelo != null ? modelo : "";
        this.anioFabricacion = anioFabricacion;
        this.descripcionEstadoFisico =
                descripcionEstadoFisico != null ? descripcionEstadoFisico : "";
    }

    public abstract CategoriaEquipo getCategoria();

    public String getCodigoRegistro() {
        return codigoRegistro;
    }

    public void setCodigoRegistro(String codigoRegistro) {
        if (codigoRegistro != null && !codigoRegistro.trim().isEmpty()) {
            this.codigoRegistro = codigoRegistro;
        }
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        if (numeroSerie != null && !numeroSerie.trim().isEmpty()) {
            this.numeroSerie = numeroSerie;
        }
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        if (marca != null && !marca.trim().isEmpty()) this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        if (modelo != null && !modelo.trim().isEmpty()) this.modelo = modelo;
    }

    public int getAnioFabricacion() {
        return anioFabricacion;
    }

    public void setAnioFabricacion(int anioFabricacion) {
        if (anioFabricacion > 0) this.anioFabricacion = anioFabricacion;
    }

    public String getDescripcionEstadoFisico() {
        return descripcionEstadoFisico;
    }

    public void setDescripcionEstadoFisico(String descripcionEstadoFisico) {
        if (descripcionEstadoFisico != null) {
            this.descripcionEstadoFisico = descripcionEstadoFisico;
        }
    }
}
