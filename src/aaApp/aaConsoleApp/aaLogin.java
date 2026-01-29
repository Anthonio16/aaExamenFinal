package aaApp.aaConsoleApp;

import aaBusinessLogic.FactoryBL;
import aaBusinessLogic.Helpers.aaAlumnoBL;
import aaInfrastructure.CMD;
import aaInfrastructure.CMDInput;
import aaInfrastructure.aaAppException;

public class aaLogin {

    public static boolean aaRunLogin() throws aaAppException {
        aaAlumnoBL usuarioBL = FactoryBL.aaAlumnoBL();

        int intentos = 0;
        while (intentos < 3) {
            String u = CMDInput.readLine("Usuario: ");
            String p = CMDInput.readLine("Clave: ");
            boolean ok = usuarioBL.aaAutenticar(u, p);

            if (ok) {
                CMD.good("Acceso concedido");
                return true;
            } else {
                intentos++;
                CMD.error("Acceso denegado");
            }
        }
        return false;
    }
}
