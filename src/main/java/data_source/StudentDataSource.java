package data_source;

import model.Student;

import java.util.*;

public class StudentDataSource {
    HashSet<Student> students;

    public StudentDataSource(HashSet<Student> students) {
        this.students = students;
    }

    public void setStudents(HashSet<Student> students) {
        this.students = students;
    }

    public boolean save(Student student) {
        if (get(student.getName()) != null) {
            return false;
        }
        return students.add(student);
    }

    public boolean remove(String name) {
        Student student = get(name);
        return students.remove(student);
    }

    public Student get(String name) {
      return students.stream().filter(s -> s.getName().equals(name)).findFirst().orElse(null);
    }

    public List<Student> getAll() {
        return students.stream().sorted().toList();
    }

    public void addGrade(String name, Integer grade) {
        Student student = get(name);
        student.addGrade(grade);
    }

    public void updateGrade(String name, int index, int grade) {
        Student student = get(name);
        student.updateGrade(index, grade);
    }
}
