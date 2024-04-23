package by.alst;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;

public class UserDao {

    private static ListUser userList;
    private final static String path = "d:\\JSONListUser\\ListUser.json";

    public static ListUser getUserList() {
        return userList;
    }

    public static void setUserList(ListUser userList) {
        UserDao.userList = userList;
    }

    private static File getFile() throws IOException {
        File dir = new File(path).getParentFile();
        if (!dir.exists()) {
            dir.mkdir();
        }
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    public static void writeToJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(getFile(), userList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ListUser readFromJson() throws IOException {
        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(getFile()))) {
            byte[] jsonData = inputStream.readAllBytes();
            ObjectMapper objectMapper = new ObjectMapper();
            userList = jsonData.length == 0 ? new ListUser() : objectMapper.readValue(jsonData, ListUser.class);
        }
        return userList;
    }

    public static void addUser(User user) throws IOException {
        userList = readFromJson();
        List<User> list = userList.getUserList();
        if (list.stream().filter(u -> Objects.equals(u.getId(), user.getId())).count() == 0) {
            list.add(user);
        } else {
            User userWithSameId = list.stream()
                    .filter(u -> Objects.equals(u.getId(), user.getId())).findFirst()
                    .get();
            list.remove(userWithSameId);
            list.add(user);
        }
        userList.setUserList(list);
        writeToJson();
    }

    public static void removeUser(User user) throws IOException {
        userList = readFromJson();
        List<User> list = userList.getUserList();
        list.removeIf(((Predicate<User>) u -> Objects.equals(u.getId(), user.getId()))
                .and(u -> u.getName().equals(user.getName())));
        userList.setUserList(list);
        writeToJson();
    }

    public static Optional<User> findById(Long id) throws IOException {
        userList = readFromJson();
        return userList.getUserList().stream().filter(s -> Objects.equals(s.getId(), id)).findFirst();
    }

    public static void sortByIdPrint() {
        userList.getUserList().stream()
                .sorted((u1, u2) -> (int) (u1.getId() - u2.getId())).peek(System.out::println)
                .toList();
        System.out.println();
    }

    public static void printUserList() throws IOException {
        userList = readFromJson();
        userList.getUserList().forEach(System.out::println);
        System.out.println();
    }

}
