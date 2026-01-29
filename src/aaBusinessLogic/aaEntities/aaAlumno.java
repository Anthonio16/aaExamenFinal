package aaBusinessLogic.aaEntities;

public class aaAlumno {
    private String aaCedula;
    private String aaNombre;
    private String aaUsuario;
    private String aaClave;

    public aaAlumno(String cedula, String nombre, String usuario, String clave) {
        this.aaCedula = cedula;
        this.aaNombre = nombre;
        this.aaUsuario = usuario;
        this.aaClave = clave;
    }

    public void aaShow() {
        // Método de apoyo (UI/Consola puede usarlo si desea)
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return aaCedula + " | " + aaNombre;
    }

    public String getAaCedula() { return aaCedula; }
    public String getAaNombre() { return aaNombre; }
    public String getAaUsuario() { return aaUsuario; }
    public String getAaClave() { return aaClave; }

    public void setAaCedula(String aaCedula) { this.aaCedula = aaCedula; }
    public void setAaNombre(String aaNombre) { this.aaNombre = aaNombre; }
    public void setAaUsuario(String aaUsuario) { this.aaUsuario = aaUsuario; }
    public void setAaClave(String aaClave) { this.aaClave = aaClave; }
}
