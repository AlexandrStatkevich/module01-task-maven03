package by.alst;

import java.io.IOException;

public class Main {

    private static final int NUMBER_USERS = 10;

    static {
        try {
            boolean isEmptyJson = UserDao.readFromJson().getUserList().isEmpty();
            ListUser listUser = !isEmptyJson ? UserDao.readFromJson()
                    : UserProducer.formUserList(new ListUser().getUserList(), NUMBER_USERS);
            if (isEmptyJson) {
                UserDao.setUserList(listUser);
                UserDao.writeToJson();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            UserDao.printUserList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {

        UserDao.sortByIdPrint();

        User user1 = new User();
        user1.setName("Ken");
        user1.setId(8L);
        User user2 = new User();
        user2.setName("Men");
        user2.setId(5L);
        User user3 = new User();
        user3.setName("Bob");
        user3.setId(8L);

        UserDao.addUser(user1);
        UserDao.printUserList();

        UserDao.removeUser(user3);
        UserDao.printUserList();

        UserDao.addUser(user2);
        UserDao.printUserList();
    }
}