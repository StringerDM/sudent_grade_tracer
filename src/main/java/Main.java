import Service.FileService;
import Service.StudentService;
import View.Item;
import View.ConsoleView;
import data_source.StudentDataSource;

import java.util.HashSet;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        StudentDataSource studentDataSource = new StudentDataSource(new HashSet<>());
        StudentService studentService = new StudentService(studentDataSource);
        ConsoleView menu = new ConsoleView("Меню", studentService);
        FileService fileService = new FileService(studentDataSource);

        menu.addItem("1", new Item("Добавьте нового ученика", menu::add));
        menu.addItem("2", new Item("Удалите ученика", menu::remove));
        menu.addItem("3", new Item("Добавить оценку ученику", menu::inputGrade));
        menu.addItem("4", new Item("Обновить оценку ученику", menu::updateGrade));
        menu.addItem("5", new Item("Просмотр оценок всех учащихся", menu::printAll));
        menu.addItem("6", new Item("Просмотр оценок конкретного учащегося", menu::printStudent));
        menu.addItem("7", new Item("Сохранить в файл", fileService::save));
        menu.addItem("8", new Item("Загрузить из файла", fileService::load));
        menu.addItem("9", new Item("Выход", menu::exit));

        init(fileService);

        while (!menu.isExit()) {
            menu.showMenu();
        }
    }

    public static void init(FileService fileService) {
        String answer;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Загрузить существующий файл данных yes/no ?");
            answer = scanner.nextLine();
            if ("yes".equalsIgnoreCase(answer) || "no".equalsIgnoreCase(answer)) {
                break;
            } else {
                System.out.println("Введите yes или no");
            }
        }
        if (answer.equalsIgnoreCase("yes")) {
            fileService.load();
        }
    }

}
