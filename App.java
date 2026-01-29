import aaApp.aaConsoleApp.aaLogin;
import aaApp.aaDesktopApp.aaExoTrooper;
import aaInfrastructure.CMD;
import aaInfrastructure.aaAppConfig;
import aaInfrastructure.aaAppException;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        try {
            aaAppConfig.load("app.properties");

            boolean ok = aaLogin.aaRunLogin();
            if (!ok) {
                CMD.error("Máximo de intentos alcanzado. Fin.");
                return;
            }

            SwingUtilities.invokeLater(() -> {
                try {
                    new aaExoTrooper().setVisible(true);
                } catch (aaAppException e) {
                    CMD.error(e.getMessage());
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
