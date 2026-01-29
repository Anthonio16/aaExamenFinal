package aaBusinessLogic.aaEntities.Interfaces;

import aaBusinessLogic.aaEntities.aaTipoArma;
import aaInfrastructure.aaAppException;

public interface aaIIAEXO {
    void aaAccionarArma(aaTipoArma arma) throws aaAppException;

    // Compatibilidad con el nombre anterior usado en el código
    default void aaGarantizarAccion(aaTipoArma arma) throws aaAppException {
        aaAccionarArma(arma);
    }
}
