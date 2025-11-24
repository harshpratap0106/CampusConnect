package campusconnect.views;

import campusconnect.models.User;
import campusconnect.services.DataService;
import javax.swing.*;
import java.awt.*;

public class LoginScreen extends JFrame {
    private JTextField userField;
    private JPasswordField passField;
    private DataService dataService;

    public LoginScreen() {
        dataService = new DataService();
        setTitle("CampusConnect - Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel("  Username:"));
        userField = new JTextField();
        add(userField);

        add(new JLabel("  Password:"));
        passField = new JPasswordField();
        add(passField);

        JButton loginBtn = new JButton("Login");
        JButton regBtn = new JButton("Register (Student)");

        add(loginBtn);
        add(regBtn);

        loginBtn.addActionListener(e -> handleLogin());
        regBtn.addActionListener(e -> handleRegister());

        setVisible(true);
    }

    private void handleLogin() {
        String u = userField.getText();
        String p = new String(passField.getPassword());
        User user = dataService.authenticate(u, p);

        if (user != null) {
            JOptionPane.showMessageDialog(this, "Login Successful!");
            this.dispose(); // Close login window
            if (user.getRole().equals("admin")) {
                new AdminDashboard(user);
            } else {
                new StudentDashboard(user);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Credentials");
        }
    }

    private void handleRegister() {
        String u = userField.getText();
        String p = new String(passField.getPassword());
        if(!u.isEmpty() && !p.isEmpty()) {
            dataService.registerUser(u, p);
            JOptionPane.showMessageDialog(this, "Registered! Please Login.");
        } else {
            JOptionPane.showMessageDialog(this, "Fields cannot be empty.");
        }
    }
}