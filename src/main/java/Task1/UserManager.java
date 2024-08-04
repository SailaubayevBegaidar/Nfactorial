package Task1;
import java.util.*;

public class UserManager {
    private Map<Integer, String> users;
    private int nextId;

    public UserManager() {
        users = new HashMap<>();
        nextId = 1;
    }

    public int addUser(String name) {
        int userId = nextId++;
        users.put(userId, name);
        return userId;
    }

    public String getUser(int id) {
        return users.getOrDefault(id, null);
    }

    public boolean deleteUser(int id) {
        if (users.containsKey(id)) {
            users.remove(id);
            return true;
        }
        return false;
    }

    public List<Integer> findUserByName(String name) {
        List<Integer> foundUsers = new ArrayList<>();
        for (Map.Entry<Integer, String> entry : users.entrySet()) {
            if (entry.getValue().equals(name)) {
                foundUsers.add(entry.getKey());
            }
        }
        return foundUsers;
    }

    public void commandLineInterface() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Выберите действие: add, delete, find, get, exit");
            String action = scanner.nextLine().trim().toLowerCase();

            switch (action) {
                case "add":
                    System.out.print("Введите имя пользователя: ");
                    String name = scanner.nextLine().trim();
                    int id = addUser(name);
                    System.out.println("Пользователь добавлен с ID: " + id);
                    break;

                case "delete":
                    System.out.print("Введите ID пользователя для удаления: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();
                    if (deleteUser(deleteId)) {
                        System.out.println("Пользователь с ID " + deleteId + " успешно удален.");
                    } else {
                        System.out.println("Пользователь с ID " + deleteId + " не найден.");
                    }
                    break;

                case "find":
                    System.out.print("Введите имя пользователя для поиска: ");
                    String searchName = scanner.nextLine().trim();
                    List<Integer> foundUsers = findUserByName(searchName);
                    if (foundUsers.isEmpty()) {
                        System.out.println("Пользователи с именем " + searchName + " не найдены.");
                    } else {
                        System.out.println("Пользователи с именем " + searchName + ": " + foundUsers);
                    }
                    break;

                case "get":
                    System.out.print("Введите ID пользователя: ");
                    int getId = scanner.nextInt();
                    scanner.nextLine();
                    String userName = getUser(getId);
                    if (userName != null) {
                        System.out.println("Пользователь с ID " + getId + ": " + userName);
                    } else {
                        System.out.println("Пользователь с ID " + getId + " не найден.");
                    }
                    break;

                case "exit":
                    System.out.println("Выход из программы.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Неизвестное действие. Пожалуйста, выберите одно из: add, delete, find, get, exit.");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        userManager.commandLineInterface();
    }
}

