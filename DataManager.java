/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enrollment;




import java.io.*;
import java.util.*;


public class DataManager {
private static final String FILENAME = "students.txt"; 



public static List<Student> load() {
List<Student> list = new ArrayList<>();
File f = new File(System.getProperty("user.dir"), FILENAME);
if (!f.exists()) return list;


try (BufferedReader br = new BufferedReader(new FileReader(f))) {
String line;
while ((line = br.readLine()) != null) {

String[] parts = line.split("\\|", -1);
if (parts.length >= 3) {
String id = parts[0].trim();
String name = parts[1].trim();
String course = parts[2].trim();
list.add(new Student(id, name, course));
}
}
} catch (IOException e) {
e.printStackTrace();
}
return list;
}


public static void save(List<Student> list) {
File f = new File(System.getProperty("user.dir"), FILENAME);
try (BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
for (Student s : list) {
bw.write(s.getId() + "|" + s.getName() + "|" + s.getCourse());
bw.newLine();
}
} catch (IOException e) {
e.printStackTrace();
}
}
}
