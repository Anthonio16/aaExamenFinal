package aaBusinessLogic.aaEntities;

import aaBusinessLogic.aaEntities.Interfaces.aaIIAEXO;
import aaInfrastructure.aaAppException;

public abstract class aaIAEXO implements aaIIAEXO {

    @Override
    public void aaAccionarArma(aaTipoArma arma) throws aaAppException {
        if (arma == null) throw new aaAppException("Arma no definida (IAEXO).");
        // aquí solo garantizamos que exista un arma válida.
    }
}
