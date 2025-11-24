/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enrollment;


public enum Course {
BSIT("BSIT"),
BSCS("BSCS"),
BSED("BSED"),
BSHM("BSHM"),
BSBA("BSBA"),
OTHERS("OTHERS");


private final String label;
Course(String label) { this.label = label; }
@Override
public String toString() { return label; }
}