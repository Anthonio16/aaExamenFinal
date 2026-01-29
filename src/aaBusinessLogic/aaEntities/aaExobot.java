package aaBusinessLogic.aaEntities;

public class aaExobot extends aaIAEXO {
    private int aaIdExobot;
    private aaTipoExobot aaTipoExobot;
    private aaTipoArma aaArma1;
    private aaTipoArma aaArma2;
    private aaAccionArma aaAccionArma;
    private boolean aaEntreno;
    private int aaNoAccion;

    public aaExobot(int id, aaTipoExobot tipo, aaTipoArma arma1, aaTipoArma arma2, aaAccionArma accion) {
        this.aaIdExobot = id;
        this.aaTipoExobot = tipo;
        this.aaArma1 = arma1;
        this.aaArma2 = arma2;
        this.aaAccionArma = accion;
        this.aaEntreno = false;
        this.aaNoAccion = 0;
    }

    public int getAaIdExobot() { return aaIdExobot; }
    public aaTipoExobot getAaTipoExobot() { return aaTipoExobot; }
    public aaTipoArma getAaArma1() { return aaArma1; }
    public aaTipoArma getAaArma2() { return aaArma2; }
    public aaAccionArma getAaAccionArma() { return aaAccionArma; }
    public boolean isAaEntreno() { return aaEntreno; }
    public int getAaNoAccion() { return aaNoAccion; }

    public void setAaEntreno(boolean v) { aaEntreno = v; }
    public void incAaNoAccion() { aaNoAccion++; }

    public aaTipoArma armaSegunTurno() {
        // alterna arma por número de acción
        return (aaNoAccion % 2 == 0) ? aaArma1 : (aaArma2 != null ? aaArma2 : aaArma1);
    }
}
