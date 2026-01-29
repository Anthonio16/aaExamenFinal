package aaBusinessLogic.Helpers;
//Refactorización

import aaBusinessLogic.aaEntities.*;
import aaDataAccess.aaDAOs.aaArchivoDAO;
import aaInfrastructure.CMD;
import aaInfrastructure.aaAppException;

import java.util.ArrayList;
import java.util.List;

public class aaExobotBL {
    private int aaNextId = 1;
    private final aaArchivoDAO aaArchivoDAO;
    private final List<aaSoldadoExperto> aaSoldados;

    public aaExobotBL() throws aaAppException {
        this.aaArchivoDAO = new aaArchivoDAO();
        this.aaSoldados = new ArrayList<>();
        // Soldados expertos por tipo arma (nombres simples)
        aaSoldados.add(new aaSoldadoExperto("SoldadoExpertoFusil", aaTipoArma.Fusil));
        aaSoldados.add(new aaSoldadoExperto("SoldadoExpertoRevolver", aaTipoArma.Revolver));
        aaSoldados.add(new aaSoldadoExperto("SoldadoExpertoGPS", aaTipoArma.GPS));
        aaSoldados.add(new aaSoldadoExperto("SoldadoExpertoGiroscopio", aaTipoArma.Giroscopio));
        aaSoldados.add(new aaSoldadoExperto("SoldadoExpertoMisil", aaTipoArma.Misil));
        aaSoldados.add(new aaSoldadoExperto("SoldadoExpertoMortero", aaTipoArma.Mortero));
        aaSoldados.add(new aaSoldadoExperto("SoldadoExpertoBioSensor", aaTipoArma.BioSensor));
        aaSoldados.add(new aaSoldadoExperto("SoldadoExpertoBioEscaner", aaTipoArma.BioEscaner));
        aaSoldados.add(new aaSoldadoExperto("SoldadoExpertoLaser", aaTipoArma.Laser));
        aaSoldados.add(new aaSoldadoExperto("SoldadoExpertoBayoneta", aaTipoArma.Bayoneta));
    }

    public aaExobot aaCrearExobot(aaTipoExobot tipo) {
        switch (tipo) {
            case ExoAsalto:
                return new aaExobot(aaNextId++, tipo, aaTipoArma.Fusil, aaTipoArma.Revolver, aaAccionArma.disparar);
            case ExoExplorador:
                return new aaExobot(aaNextId++, tipo, aaTipoArma.GPS, aaTipoArma.Giroscopio, aaAccionArma.detectar);
            case ExoInfanteria:
                return new aaExobot(aaNextId++, tipo, aaTipoArma.Misil, aaTipoArma.Mortero, aaAccionArma.disparar);
            case ExoMedico:
                return new aaExobot(aaNextId++, tipo, aaTipoArma.BioSensor, aaTipoArma.BioEscaner, aaAccionArma.asistir);
            case ExoComando:
            default:
                return new aaExobot(aaNextId++, tipo, aaTipoArma.Laser, aaTipoArma.Bayoneta, aaAccionArma.disparar);
        }
    }

    private aaSoldadoExperto aaBuscarExperto(aaTipoArma arma) {
        for (aaSoldadoExperto s : aaSoldados) {
            if (s.getAaEspecialidad() == arma) return s;
        }
        return null;
    }

    public void aaEntrenar(aaExobot exobot) throws aaAppException {
        if (exobot == null) throw new aaAppException("Seleccione un Exobot.");
        aaTipoArma arma = exobot.armaSegunTurno();
        exobot.aaGarantizarAccion(arma);

        aaSoldadoExperto experto = aaBuscarExperto(arma);
        if (experto == null) {
            CMD.error("SoldadoExperto ? " + arma + " " + exobot.getAaAccionArma());
            throw new aaAppException("No existe soldado experto para " + arma);
        }

        exobot.setAaEntreno(true);
        CMD.good(experto.getAaNombre() + " " + arma + " " + exobot.getAaAccionArma());
    }

    public String aaAccionArma(aaExobot exobot) throws aaAppException {
    if (exobot == null) throw new aaAppException("Seleccione un Exobot.");

    aaTipoArma arma = exobot.armaSegunTurno();
    exobot.aaGarantizarAccion(arma);

    aaExoComando comando = new aaExoComando(arma.name(), exobot.getAaAccionArma().name());
    String recurso = comando.aaLeerMunicion("", aaArchivoDAO); // ruta se toma desde AppConfig/DAO
    exobot.incAaNoAccion();

    try {
        recurso = comando.aaDispararMunicion(recurso);
        CMD.good(arma + " " + exobot.getAaAccionArma() + " " + recurso);
        return recurso;
    } catch (aaAppException ex) {
        CMD.error(arma + " " + exobot.getAaAccionArma() + " " + (recurso == null ? "?" : recurso));
        throw ex;
    }
}

}