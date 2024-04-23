package by.alst;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListUser {
    private List<User> userList = new ArrayList<>();

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListUser listUser = (ListUser) o;
        return Objects.equals(userList, listUser.userList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userList);
    }

    @Override
    public String toString() {
        return "ListUser{" +
                "userList=" + userList +
                '}';
    }
}
