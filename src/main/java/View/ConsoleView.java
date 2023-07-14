package View;

import Service.StudentService;
import model.Student;

import java.util.*;

public class ConsoleView {

    final private String name;
    final private StudentService studentService;
    final private Map<String, Item> items = new HashMap<>();
    final private Scanner scanner = new Scanner(System.in);
    private boolean isExit = false;

    public ConsoleView(String name, StudentService studentService) {
        this.studentService = studentService;
        this.name = name;
    }

    public void showMenu() {
        System.out.println(name);
        for (Map.Entry<String, Item> entry : items.entrySet()) {
            String s = entry.getKey() + ". " + entry.getValue().getName();
            System.out.println(s);
        }
        System.out.print("Выберете пункт меню: ");
        String menuItem = scanner.nextLine();
        Item item = items.get(menuItem);
        if (item != null) {
            item.action.execute();
        } else {
            System.out.println("Такого пункта меню не существует.");
        }
    }

    public void addItem(String p, Item item) {
        items.put(p, item);
    }

    public void add() {
        System.out.print("Введите имя ученика: ");
        String name = scanner.nextLine();
        if (!studentService.save(name)) {
            add();
        }
    }

    public void remove() {
        System.out.print("Введите имя ученика: ");
        String name = scanner.nextLine();
        studentService.remove(name);
    }

    public void addGrade() {
        System.out.print("Введите имя ученика: ");
        String name = scanner.nextLine();
        if (studentService.get(name) != null) {
            int grade = getIntGrade();
            studentService.addGrade(name, grade);
        }
    }

    private int getIntGrade() {
        System.out.print("Введите новую оценку: ");
        String grade = scanner.nextLine();
        try {
            return Integer.parseInt(grade);
        } catch (Exception e) {
            System.out.println("Вы ввели не число");
        }
        return getIntGrade();
    }

    public void printAll() {
        List<Student> students = studentService.getAll();
        for (Student student : students) {
            System.out.println(student.getName() + " " + student.getGrades());
        }
    }

    public void printStudent() {
        System.out.print("Введите имя ученика: ");
        String name = scanner.nextLine();
        Student student = studentService.get(name);
        if (student != null) {
            System.out.println(student.getName() + " " + student.getGrades());
        }
    }

    public void exit() {
        isExit = true;
    }

    public boolean isExit() {
        return isExit;
    }
}
