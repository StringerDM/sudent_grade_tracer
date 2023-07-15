package Service;

import data_source.StudentDataSource;
import model.Student;

import java.util.List;

public class StudentService {
    final StudentDataSource dataSource;

    public StudentService(StudentDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean save(String name) {
        if (name.isEmpty()) {
            System.out.println("Имя не может быть пустым");
            return false;
        }
        if (!dataSource.save(new Student(name))) {
            System.out.println("Ученик с таким именем уже существует");
            return false;
        }
        return true;
    }

    public Student get(String name) {
        Student student = dataSource.get(name);
        if (student == null) {
            System.out.println("Ученик с таким именем отсутствует");
        }
        return student;
    }

    public void remove(String name) {
        if (!dataSource.remove(name)) {
            System.out.println("Ученик с таким именем отсутствует");
        }
    }

    public void addGrade(String name, Integer index) {
        dataSource.addGrade(name, index);
    }

    public void updateGrade(String name, int index, int grade) {
        dataSource.updateGrade(name, index, grade);
    }

    public List<Student> getAll() {
        return dataSource.getAll();
    }

}
