/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enrollment;

import javax.swing.SwingUtilities;


public class Main {
public static void main(String[] args) {
SwingUtilities.invokeLater(() -> {
LoginGUI login = new LoginGUI();
login.setVisible(true);
});
}
}