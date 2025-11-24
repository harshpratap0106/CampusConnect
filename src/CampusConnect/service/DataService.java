package campusconnect.services;

import campusconnect.models.Grievance;
import campusconnect.models.User;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class DataService {
    private static final String USER_FILE = "users.txt";
    private static final String DATA_FILE = "grievances.txt";

    public DataService() {
        initFiles();
    }

    private void initFiles() {
        try {
            File uFile = new File(USER_FILE);
            if (uFile.createNewFile()) {
                // Default Admin: admin / admin123
                saveUser(new User("admin", hashPassword("admin123"), "admin"));
            }
            new File(DATA_FILE).createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // --- NON-FUNCTIONAL REQ: SECURITY (Hashing) ---
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            return password; // Fallback if SHA-256 missing
        }
    }

    // --- User Operations ---
    public User authenticate(String username, String password) {
        String hashedInput = hashPassword(password); // Hash input to compare
        try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                // Compare Stored Hash with Input Hash
                if (parts[0].equals(username) && parts[1].equals(hashedInput)) {
                    return new User(parts[0], parts[1], parts[2]);
                }
            }
        } catch (IOException e) { e.printStackTrace(); }
        return null;
    }

    public boolean registerUser(String username, String password) {
        saveUser(new User(username, hashPassword(password), "student")); // Save Hash
        return true;
    }

    private void saveUser(User user) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USER_FILE, true))) {
            bw.write(user.getUsername() + "," + user.getPassword() + "," + user.getRole());
            bw.newLine();
        } catch (IOException e) { e.printStackTrace(); }
    }

    // --- Grievance Operations (Unchanged) ---
    public void saveGrievance(Grievance g) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DATA_FILE, true))) {
            bw.write(g.toString());
            bw.newLine();
        } catch (IOException e) { e.printStackTrace(); }
    }

    public List<Grievance> loadGrievances() {
        List<Grievance> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if(parts.length >= 5) {
                    list.add(new Grievance(parts[0], parts[1], parts[2], parts[3], parts[4]));
                }
            }
        } catch (IOException e) { e.printStackTrace(); }
        return list;
    }

    public void updateGrievanceStatus(String id, String newStatus) {
        List<Grievance> all = loadGrievances();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DATA_FILE))) {
            for (Grievance g : all) {
                if (g.getId().equals(id)) {
                    g.setStatus(newStatus);
                }
                bw.write(g.toString());
                bw.newLine();
            }
        } catch (IOException e) { e.printStackTrace(); }
    }
}


