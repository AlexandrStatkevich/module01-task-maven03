package by.alst;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UserService {

    public Optional<UserDto> getUser(Long id) throws IOException {
        return UserDao.findById(id).map(it -> new UserDto(it.getName(), it.getId()));
    }

    public List<UserDto> listUserDto() throws IOException {
        return UserDao.readFromJson().getUserList().stream()
                .map(it -> new UserDto(it.getName(), it.getId())).toList();
    }

    public String replaceUserNameById(Long id, String name) throws IOException {
        String resultReplacement;
        User userInList = UserDao.findById(id).isPresent()
                ? UserDao.findById(id).get() : new User();
        if (!userInList.equals(new User())) {
            User userToReplace = new User();
            userToReplace.setName(name);
            userToReplace.setId(id);
            UserDao.removeUser(userInList);
            UserDao.addUser(userToReplace);
            resultReplacement = "Пользователь c id=" + id + " заменен!";
        } else {
            resultReplacement = "Нет пользователя с id=" + id;
        }
        return resultReplacement;
    }
}
