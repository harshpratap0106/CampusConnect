package campusconnect.main;

import campusconnect.views.LoginScreen;
import javax.swing.SwingUtilities;

public class CampusConnectApp {
    public static void main(String[] args) {
        // Run GUI in Event Dispatch Thread for thread safety
        SwingUtilities.invokeLater(() -> {
            new LoginScreen();
        });
    }
}