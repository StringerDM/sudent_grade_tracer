package Service;

import data_source.StudentDataSource;
import model.Student;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

public class FileService {

    StudentDataSource studentDataSource;
    Scanner scanner = new Scanner(System.in);

    public FileService(StudentDataSource studentDataSource) {
        this.studentDataSource = studentDataSource;
    }

    public void load() {
        File file;
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        } else {
            System.out.println("Введите путь");
            file = new File(scanner.nextLine());
        }
        HashSet<Student> students = new HashSet<>(loadStudents(file));
        studentDataSource.setStudents(students);
    }

    public void save() {
        File file;
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        } else {
            System.out.println("Введите путь");
            file = new File(scanner.nextLine());
        }

        saveStudents(file);
    }

    private Set<Student> loadStudents(File file) {
        List<String> data;
        try {
            data = Files.readAllLines(file.toPath());
            return data.stream()
                    .map(d -> {
                        String[] line = d.split(",");
                        List<Integer> grades = Arrays.stream(line).skip(1).map(Integer::parseInt).toList();
                        return new Student(line[0], grades);
                    })
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            System.out.println("Не удалось загрузить файл, попробуйте снова.");
        }
        return new HashSet<>();
    }

    private void saveStudents(File file) {
        List<String> lines = studentDataSource.getAll()
                .stream()
                .map(student -> student.getName() + "," + student.getGrades()
                        .stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(",")))
                .toList();
        try {
            Files.write(file.toPath(), lines);
        } catch (IOException e) {
            System.out.println("не удалось сохранить файл по указанному пути, попробуйте снова");
        }
    }
}
