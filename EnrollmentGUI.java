/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enrollment;



import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class EnrollmentGUI extends JFrame {
private JTextField idField, nameField, searchField;
private JComboBox<Course> courseCombo;
private JButton enrollBtn, editBtn, deleteBtn, saveBtn, loadBtn, clearBtn, searchBtn;
private DefaultListModel<String> listModel;
private JList<String> displayList;


private java.util.List<Student> students = new ArrayList<>();

public EnrollmentGUI() {
setTitle("Enrollment System");
setSize(700, 520);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setLayout(new BorderLayout(8,8));



JPanel form = new JPanel(new GridLayout(4,2,8,8));
form.setBorder(BorderFactory.createTitledBorder("Student Information"));
form.setPreferredSize(new Dimension(320, 180));


form.add(new JLabel("Student ID:"));
idField = new JTextField();
form.add(idField);


form.add(new JLabel("Full Name:"));
nameField = new JTextField();
form.add(nameField);


form.add(new JLabel("Course:"));
courseCombo = new JComboBox<>(Course.values());
form.add(courseCombo);


form.add(new JLabel("Search (ID or Name):"));
searchField = new JTextField();
form.add(searchField);


add(form, BorderLayout.WEST);



JPanel right = new JPanel(new BorderLayout(6,6));



JPanel buttons = new JPanel(new GridLayout(2,3,8,8));
buttons.setBorder(BorderFactory.createTitledBorder("Actions"));


enrollBtn = new JButton("Enroll");
editBtn = new JButton("Edit");
deleteBtn = new JButton("Delete");
saveBtn = new JButton("Save to File");
loadBtn = new JButton("Load from File");
clearBtn = new JButton("Clear Fields");


buttons.add(enrollBtn);
buttons.add(editBtn);
buttons.add(deleteBtn);
buttons.add(saveBtn);
buttons.add(loadBtn);
buttons.add(clearBtn);



right.add(buttons, BorderLayout.NORTH);



listModel = new DefaultListModel<>();
displayList = new JList<>(listModel);
displayList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
JScrollPane sp = new JScrollPane(displayList);
sp.setBorder(BorderFactory.createTitledBorder("Enrolled Students"));
right.add(sp, BorderLayout.CENTER);



JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
searchBtn = new JButton("Search");
bottom.add(searchBtn);
right.add(bottom, BorderLayout.SOUTH);


add(right, BorderLayout.CENTER);



students = DataManager.load();
refreshList();

enrollBtn.addActionListener(e -> enrollStudent());
saveBtn.addActionListener(e -> saveToFile());
loadBtn.addActionListener(e -> loadFromFile());
clearBtn.addActionListener(e -> clearFields());
deleteBtn.addActionListener(e -> deleteStudent());
editBtn.addActionListener(e -> editStudent());
searchBtn.addActionListener(e -> searchStudents());


displayList.addListSelectionListener(new ListSelectionListener() {
public void valueChanged(ListSelectionEvent e) {
if (!e.getValueIsAdjusting()) {
int idx = displayList.getSelectedIndex();
if (idx >= 0 && idx < students.size()) {
Student s = students.get(idx);
idField.setText(s.getId());
nameField.setText(s.getName());
courseCombo.setSelectedItem(parseCourse(s.getCourse()));
}
}
}
});

displayList.addMouseListener(new MouseAdapter() {
public void mouseClicked(MouseEvent evt) {
if (evt.getClickCount() == 2) {
editStudent();
}
}
});
}


private Course parseCourse(String label) {
for (Course c : Course.values()) if (c.toString().equals(label)) return c;
return Course.OTHERS;
}


private void enrollStudent() {
String id = idField.getText().trim();
String name = nameField.getText().trim();
String course = ((Course)courseCombo.getSelectedItem()).toString();


if (id.isEmpty() || name.isEmpty()) {
JOptionPane.showMessageDialog(this, "Please fill ID and Name", "Validation", JOptionPane.WARNING_MESSAGE);
return;
}

for (Student s : students) {
if (s.getId().equals(id)) {
JOptionPane.showMessageDialog(this, "ID already exists. Use Edit to modify.", "Duplicate ID", JOptionPane.WARNING_MESSAGE);
return;
}
}


students.add(new Student(id, name, course));
refreshList();
clearFields();
JOptionPane.showMessageDialog(this, "Student enrolled successfully.");
}


private void editStudent() {
int idx = displayList.getSelectedIndex();
if (idx < 0) {
JOptionPane.showMessageDialog(this, "Select a student to edit.");
return;
}


String id = idField.getText().trim();
String name = nameField.getText().trim();
String course = ((Course)courseCombo.getSelectedItem()).toString();


if (id.isEmpty() || name.isEmpty()) {
JOptionPane.showMessageDialog(this, "Please fill ID and Name", "Validation", JOptionPane.WARNING_MESSAGE);
return;
}

int opt = JOptionPane.showConfirmDialog(this, "Save changes to this student?", "Confirm Edit", JOptionPane.YES_NO_OPTION);
if (opt != JOptionPane.YES_OPTION) return;


Student s = students.get(idx);

if (!s.getId().equals(id)) {
for (Student other : students) if (other.getId().equals(id)) {
JOptionPane.showMessageDialog(this, "Another student already uses this ID.", "Duplicate ID", JOptionPane.ERROR_MESSAGE);
return;
}
}

s.setId(id);
s.setName(name);
s.setCourse(course);
refreshList();
JOptionPane.showMessageDialog(this, "Student updated.");
}


private void deleteStudent() {
int idx = displayList.getSelectedIndex();
if (idx < 0) {
JOptionPane.showMessageDialog(this, "Select a student to delete.");
return;
}


int opt = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete selected student?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
if (opt != JOptionPane.YES_OPTION) return;


students.remove(idx);
refreshList();
clearFields();
JOptionPane.showMessageDialog(this, "Student deleted.");
}

private void saveToFile() {
DataManager.save(students);
JOptionPane.showMessageDialog(this, "Saved to file.");
}


private void loadFromFile() {
int opt = JOptionPane.showConfirmDialog(this, "This will replace current list with file contents. Continue?", "Load From File", JOptionPane.YES_NO_OPTION);
if (opt != JOptionPane.YES_OPTION) return;
students = DataManager.load();
refreshList();
clearFields();
JOptionPane.showMessageDialog(this, "Loaded from file.");
}


private void searchStudents() {
String q = searchField.getText().trim().toLowerCase();
if (q.isEmpty()) {
refreshList();
return;
}

listModel.clear();
for (Student s : students) {
if (s.getId().toLowerCase().contains(q) || s.getName().toLowerCase().contains(q)) {
listModel.addElement(s.toString());
}
}
}


private void refreshList() {
listModel.clear();
for (Student s : students) listModel.addElement(s.toString());
}


private void clearFields() {
idField.setText("");
nameField.setText("");
courseCombo.setSelectedIndex(0);
searchField.setText("");
displayList.clearSelection();
}
}

