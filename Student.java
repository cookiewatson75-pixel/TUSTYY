/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enrollment;


import java.io.Serializable;


public class Student implements Serializable {
private String id;
private String name;
private String course;


public Student(String id, String name, String course) {
this.id = id;
this.name = name;
this.course = course;
}


public String getId() { return id; }
public void setId(String id) { this.id = id; }


public String getName() { return name; }
public void setName(String name) { this.name = name; }


public String getCourse() { return course; }
public void setCourse(String course) { this.course = course; }


@Override
public String toString() {
return id + " | " + name + " | " + course;
}
}
