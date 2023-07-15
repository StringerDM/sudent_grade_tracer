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
        String menuItem = inputString("Выберете пункт меню: ");
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
        String name = inputString("Введите имя ученика: ");
        if (!studentService.save(name)) {
            add();
        }
    }

    public void remove() {
        String name = inputString("Введите имя ученика: ");
        studentService.remove(name);
    }

    public void inputGrade() {
        String name = inputString("Введите имя ученика: ");
        if (studentService.get(name) != null) {
            int grade = getIntGrade();
            studentService.addGrade(name, grade);
        }
    }

    private int getIntGrade() {
        String grade = inputString("Введите новую оценку: ");
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
        String name = inputString("Введите имя ученика: ");
        Student student = studentService.get(name);
        if (student != null) {
            System.out.println(student.getName() + " " + student.getGrades());
        }
    }

    private String inputString(String s) {
        System.out.print(s);
        return scanner.nextLine();
    }


    public void exit() {
        isExit = true;
    }

    public boolean isExit() {
        return isExit;
    }

    public void updateGrade() {
    }


}
