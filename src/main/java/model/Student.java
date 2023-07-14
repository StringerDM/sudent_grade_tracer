package model;

import java.util.ArrayList;
import java.util.List;

public class Student implements Comparable<Student> {
    final String name;
    final List<Integer> grades;

    public Student(String name) {
        this.name = name;
        this.grades = new ArrayList<>();
    }

    public Student(String name, List<Integer> grades) {
        this.name = name;
        this.grades = new ArrayList<>(grades);
    }

    public List<Integer> getGrades() {
        return grades;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Student otherStudent) {
        return name.compareTo(otherStudent.name);
    }

    public void addGrade(Integer grade) {
        grades.add(grade);
    }
}
