package by.alst;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserProducer {

    public final static int ZERO_CHAR = 0;
    public final static int MIN_AMOUNT_CHAR = 2;
    public final static int MAX_AMOUNT_CHAR = 11;
    public final static int MIN_ASCII_CHAR = 97;
    public final static int MAX_ASCII_CHAR = 123;
    public final static long MIN_ID = 1L;
    public final static long MAX_ID = 21L;

    public static String getName() {
        Random random = new Random();
        int charCounter = random.nextInt(MIN_AMOUNT_CHAR, MAX_AMOUNT_CHAR);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < charCounter; i++) {
            builder.append((char) random.nextInt(MIN_ASCII_CHAR, MAX_ASCII_CHAR));
        }
        builder.setCharAt(ZERO_CHAR, Character.toUpperCase(builder.charAt(ZERO_CHAR)));
        return builder.toString();
    }

    public static long geId() {
        Random random = new Random();
        return random.nextLong(MIN_ID, MAX_ID);
    }

    public static ListUser formUserList(List<User> list, int numberUser) {
        User user;
        List<User> userList = new ArrayList<>(numberUser);
        ListUser listUser = new ListUser();
        List<Long> idList = new ArrayList<>();
        for (int i = 0; i < numberUser; i++) {
            user = new User();
            user.setName(getName());
            do {
                Long id = geId();
                if (idList.isEmpty()) {
                    user.setId(id);
                    idList.add(id);
                    break;
                } else if (!idList.contains(id)) {
                    user.setId(id);
                    idList.add(id);
                    break;
                }
            } while (true);
            userList.add(user);
            listUser.setUserList(userList);
        }
        return listUser;
    }
}
