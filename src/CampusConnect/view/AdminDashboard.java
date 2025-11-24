package campusconnect.views;

import campusconnect.models.Grievance;
import campusconnect.models.User;
import campusconnect.services.DataService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AdminDashboard extends JFrame {
    private DataService dataService;
    private JTable table;
    private DefaultTableModel model;

    public AdminDashboard(User user) {
        this.dataService = new DataService();
        setTitle("Admin Dashboard");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Table
        String[] cols = {"ID", "Student", "Category", "Description", "Status"};
        model = new DefaultTableModel(cols, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Bottom Panel: Update Action
        JPanel bottomPanel = new JPanel();
        JComboBox<String> statusBox = new JComboBox<>(new String[]{"In Progress", "Resolved"});
        JButton updateBtn = new JButton("Update Selected Status");
        JButton logoutBtn = new JButton("Logout");

        bottomPanel.add(new JLabel("Set Status:"));
        bottomPanel.add(statusBox);
        bottomPanel.add(updateBtn);
        bottomPanel.add(logoutBtn);
        add(bottomPanel, BorderLayout.SOUTH);

        // Events
        updateBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if(row != -1) {
                String id = (String) model.getValueAt(row, 0);
                String newStatus = (String) statusBox.getSelectedItem();
                dataService.updateGrievanceStatus(id, newStatus);
                refreshTable();
                JOptionPane.showMessageDialog(this, "Status Updated!");
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row first.");
            }
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
            model.addRow(new Object[]{g.getId(), g.getStudentName(), g.getCategory(), g.getDescription(), g.getStatus()});
        }
    }
}


