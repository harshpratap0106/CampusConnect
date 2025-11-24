package campusconnect.views;

import campusconnect.models.Grievance;
import campusconnect.models.User;
import campusconnect.services.DataService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.UUID;

public class StudentDashboard extends JFrame {
    private User user;
    private DataService dataService;
    private JTable table;
    private DefaultTableModel model;

    public StudentDashboard(User user) {
        this.user = user;
        this.dataService = new DataService();

        setTitle("Student Dashboard - " + user.getUsername());
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Top Panel: Submission Form
        JPanel topPanel = new JPanel(new GridLayout(3, 2));
        topPanel.setBorder(BorderFactory.createTitledBorder("Report New Issue"));

        JComboBox<String> categoryBox = new JComboBox<>(new String[]{"Academic", "Hostel", "Infrastructure"});
        JTextField descField = new JTextField();
        JButton submitBtn = new JButton("Submit Grievance");

        topPanel.add(new JLabel("Category:"));
        topPanel.add(categoryBox);
        topPanel.add(new JLabel("Description:"));
        topPanel.add(descField);
        topPanel.add(new JLabel("")); // Spacer
        topPanel.add(submitBtn);

        add(topPanel, BorderLayout.NORTH);

        // Center Panel: History Table
        String[] cols = {"ID", "Category", "Description", "Status"};
        model = new DefaultTableModel(cols, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Logout Button
        JButton logoutBtn = new JButton("Logout");
        add(logoutBtn, BorderLayout.SOUTH);

        // Events
        submitBtn.addActionListener(e -> {
            String id = UUID.randomUUID().toString().substring(0, 8);
            String cat = (String) categoryBox.getSelectedItem();
            String desc = descField.getText();

            Grievance g = new Grievance(id, user.getUsername(), cat, desc, "Pending");
            dataService.saveGrievance(g);
            refreshTable();
            descField.setText("");
            JOptionPane.showMessageDialog(this, "Grievance Submitted!");
        });

        logoutBtn.addActionListener(e -> {
            this.dispose();
            new LoginScreen();
        });

        refreshTable();
        setVisible(true);
    }

    private void refreshTable() {
        model.setRowCount(0);
        List<Grievance> list = dataService.loadGrievances();
        for(Grievance g : list) {
            if(g.getStudentName().equals(user.getUsername())) {
                model.addRow(new Object[]{g.getId(), g.getCategory(), g.getDescription(), g.getStatus()});
            }
        }
    }
}

