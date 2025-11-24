/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enrollment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class LoginGUI extends JFrame {
private JTextField userField;
private JPasswordField passField;
private JButton loginBtn;


// hardcoded credentials (simple)
private final String USER = "admin";
private final String PASS = "admin";


public LoginGUI() {
setTitle("Login - Enrollment System");
setSize(360, 200);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setLayout(new BorderLayout(8, 8));


JPanel center = new JPanel(new GridLayout(2,2,6,6));
center.setBorder(BorderFactory.createEmptyBorder(12,12,12,12));


center.add(new JLabel("Username:"));
userField = new JTextField();
center.add(userField);


center.add(new JLabel("Password:"));
passField = new JPasswordField();
center.add(passField);


add(center, BorderLayout.CENTER);


JPanel south = new JPanel(new FlowLayout(FlowLayout.RIGHT));
loginBtn = new JButton("Login");
south.add(loginBtn);
add(south, BorderLayout.SOUTH);



passField.addActionListener(e -> doLogin());
loginBtn.addActionListener(e -> doLogin());
}


private void doLogin() {
String u = userField.getText().trim();
String p = new String(passField.getPassword());


if (u.equals(USER) && p.equals(PASS)) {
// open main GUI
SwingUtilities.invokeLater(() -> {
EnrollmentGUI gui = new EnrollmentGUI();
gui.setVisible(true);
});
dispose();
} else {
JOptionPane.showMessageDialog(this, "Invalid credentials", "Login Failed", JOptionPane.ERROR_MESSAGE);
}
}
}