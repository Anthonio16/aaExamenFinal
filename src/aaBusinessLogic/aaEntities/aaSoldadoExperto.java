package aaBusinessLogic.aaEntities;

public class aaSoldadoExperto {
    private String aaNombre;
    private aaTipoArma aaEspecialidad;

    public aaSoldadoExperto(String nombre, aaTipoArma especialidad) {
        this.aaNombre = nombre;
        this.aaEspecialidad = especialidad;
    }

    public String getAaNombre() { return aaNombre; }
    public aaTipoArma getAaEspecialidad() { return aaEspecialidad; }

public void aaEntrenar(aaIAEXO iaexo) {
    // Entrenamiento genérico según tu diagrama (se ejecuta desde la capa BL/UI)
    if (iaexo instanceof aaExobot exobot) {
        exobot.setAaEntreno(true);
    }
}
}
