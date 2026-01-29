package aaBusinessLogic.aaEntities;

import aaDataAccess.aaDAOs.aaArchivoDAO;
import aaInfrastructure.aaAppException;

public class aaExoComando {
    private String aaTipoArma;
    private String aaAccionArma;

    public aaExoComando(String tipoArma, String accionArma) {
        this.aaTipoArma = tipoArma;
        this.aaAccionArma = accionArma;
    }

    public String getAaTipoArma() { return aaTipoArma; }
    public String getAaAccionArma() { return aaAccionArma; }

    public void setAaTipoArma(String aaTipoArma) { this.aaTipoArma = aaTipoArma; }
    public void setAaAccionArma(String aaAccionArma) { this.aaAccionArma = aaAccionArma; }

    /**
     * Lee ExoMunision.txt y devuelve la munición/energía correspondiente al arma.
     * (Se deja el método aquí porque así está en tu diagrama de clases)
     */
    public String aaLeerMunicion(String fileNamePath, aaArchivoDAO archivoDAO) throws aaAppException {
        // El DAO ya conoce la ruta por AppConfig, pero se respeta el parámetro del diagrama.
        // Si fileNamePath viene vacío, el DAO usa su configuración.
        if (archivoDAO == null) throw new aaAppException("DAO no inicializado (ExoComando).");
        return archivoDAO.aaBuscarRecursoPorArma(aaTipoArma);
    }

    /**
     * Ejecuta la acción (disparar/detectar/asistir) usando el recurso obtenido.
     * Devuelve el recurso para que se muestre en tracer/UI.
     */
    public String aaDispararMunicion(String recurso) throws aaAppException {
        if (recurso == null || recurso.isBlank()) {
            throw new aaAppException("No existe munición/energía para: " + aaTipoArma);
        }
        return recurso;
    }
}
