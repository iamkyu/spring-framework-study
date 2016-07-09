package springbook.user.dao;

import springbook.user.domain.User;

import java.sql.SQLException;

public class UserDaoTest {
    public static void main(String [] args) throws ClassNotFoundException, SQLException {
        ConnectionMaker connectionMaker = new NConnectionMaker();

        UserDao dao = new UserDao(connectionMaker);

        User user = new User();
        user.setId("iamkyu");
        user.setName("kyukyu");
        user.setPassword("topsecret");

        dao.add(user);
        System.out.println(user.getId() + " add Success!");

        User user2 = dao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());
        System.out.println(user2.getId() + " get Success!");
    }
}
