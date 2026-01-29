package aaDataAccess.aaDAOs;

import aaInfrastructure.aaAppConfig;
import aaInfrastructure.aaAppException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class aaArchivoDAO {

    private final String aaPathMunision;

    public aaArchivoDAO() throws aaAppException {
        this.aaPathMunision = aaAppConfig.get("storage.exoMunision");
    }

    // Busca un token que contenga el arma (por ejemplo "Laser") y devuelve el primer match
    public String aaBuscarRecursoPorArma(String arma) throws aaAppException {
        ArrayList<String> tokens = aaLeerTokens();
        String needle = "_" + arma;
        for (String t : tokens) {
            if (t.contains(needle)) {
                return t.trim();
            }
        }
        // fallback: contiene el arma sin _
        for (String t : tokens) {
            if (t.toLowerCase().contains(arma.toLowerCase())) {
                return t.trim();
            }
        }
        return null;
    }

    private ArrayList<String> aaLeerTokens() throws aaAppException {
        ArrayList<String> out = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(aaPathMunision))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Separar por coma
                String[] parts = line.split(",");
                for (String p : parts) {
                    String t = p.replace("\t", " ").replaceAll("\s+", " ").trim();
                    if (!t.isBlank()) out.add(t);
                }
            }
        } catch (IOException e) {
            throw new aaAppException("No se pudo leer ExoMunision.txt en: " + aaPathMunision, e);
        }
        return out;
    }
}
