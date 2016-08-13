package springbook.user.service;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import springbook.user.dao.UserDao;
import springbook.user.domain.Level;
import springbook.user.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kj Nam
 * @since 2016-07-23
 */
public class UserServiceImpl implements UserService {
    public static final int MIN_LOGCOUNT_FOR_SILVER = 50;
    public static final int MIN_RECCOMEND_FOR_GOLD = 30;

    private MailSender mailSender;

    UserDao userDao;

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void upgradeLevels() throws Exception {
        try {
            List<User> users = userDao.getAll();
            for (User user : users) {
                if (canUpgradeLevel(user)) {
                    upgradeLevel(user);
                }
            }

        } catch (Exception e) {
            throw e;
        }
    }

    protected void upgradeLevel(User user) {
        user.upgradeLevel();
        userDao.update(user);
        sendUpgradeEmail(user);
    }

    private void sendUpgradeEmail(User user) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setFrom("test@iamkyu.io");
        mailMessage.setSubject("Upgrade 안내");
        mailMessage.setText("사용자님의 등급이 " + user.getLevel().name());

        this.mailSender.send(mailMessage);
    }

    private boolean canUpgradeLevel(User user) {
        Level currentLevel = user.getLevel();

        switch (currentLevel) {
            case BASIC: return (user.getLogin() >= 50);
            case SILVER: return (user.getRecommend() >= 30);
            case GOLD: return false;
            default: throw new IllegalArgumentException("Unknown Level: " + currentLevel);
        }
    }


    @Override
    public void add(User user) {
        if (user.getLevel() == null) user.setLevel(Level.BASIC);
        userDao.add(user);
    }
}


class TestUserService extends UserServiceImpl {
    private String id;

    public TestUserService(String id) {
        this.id = id;
    }

    @Override
    protected void upgradeLevel(User user) {
        if (user.getId().equals(this.id)) throw new TestUserServiceException();

        super.upgradeLevel(user);
    }
}

class TestUserServiceException extends RuntimeException {

}

class MockUserDao implements UserDao {
    private List<User> users;
    private List<User> updated = new ArrayList<>();

    public MockUserDao(List<User> users) {
        this.users = users;
    }

    public List<User> getUpdated() {
        return this.updated;
    }

    @Override
    public List<User> getAll() {
        return this.users;
    }

    @Override
    public void update(User user) {
        updated.add(user);
    }

    @Override
    public void add(User user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public User get(String id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getCount() {
        throw new UnsupportedOperationException();
    }
}