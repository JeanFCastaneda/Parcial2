package co.edu.uniquindio.poo;
public abstract class Persona {
    private String nombre;
    private String documento;
    private String telefono;

    public Persona(String nombre, String documento, String telefono) {
        this.nombre = nombre != null ? nombre : "";
        this.documento = documento != null ? documento : "";
        this.telefono = telefono != null ? telefono : "";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        if (documento != null && !documento.trim().isEmpty()) this.documento = documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (telefono != null && !telefono.trim().isEmpty()) this.telefono = telefono;
    }
}