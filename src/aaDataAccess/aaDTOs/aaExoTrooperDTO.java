package aaDataAccess.aaDTOs;

public class aaExoTrooperDTO {
    private int aaIdExobot;
    private String aaTipoExobot;
    private String aaEntreno;
    private int aaNoAccion;

    public aaExoTrooperDTO(int idExobot, String tipoExobot, String entreno, int noAccion) {
        this.aaIdExobot = idExobot;
        this.aaTipoExobot = tipoExobot;
        this.aaEntreno = entreno;
        this.aaNoAccion = noAccion;
    }

    public int getAaIdExobot() { return aaIdExobot; }
    public String getAaTipoExobot() { return aaTipoExobot; }
    public String getAaEntreno() { return aaEntreno; }
    public int getAaNoAccion() { return aaNoAccion; }

    public void setAaEntreno(String aaEntreno) { this.aaEntreno = aaEntreno; }
    public void setAaNoAccion(int aaNoAccion) { this.aaNoAccion = aaNoAccion; }
}
