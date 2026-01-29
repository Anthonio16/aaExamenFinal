package aaBusinessLogic.Helpers;
//Refactorización

import aaBusinessLogic.aaEntities.aaAlumno;
import aaInfrastructure.aaAppConfig;
import aaInfrastructure.aaAppException;

public class aaAlumnoBL {
    private final String aaUser;
    private final String aaPass;

    public aaAlumnoBL() throws aaAppException {
        this.aaUser = aaAppConfig.get("auth.user");
        this.aaPass = aaAppConfig.get("auth.pass");
    }

    public boolean aaAutenticar(String usuario, String clave) {
        return aaUser.equals(usuario) && aaPass.equals(clave);
    }

    public aaAlumno aaGetAlumno() throws aaAppException {
        String cedula = aaAppConfig.get("alumno.cedula");
        String nombre = aaAppConfig.get("alumno.nombre");
        return new aaAlumno(cedula, nombre, aaUser, aaPass);
    }
}