package dao;

import entity.User;

public class UserDao extends AbstractJpaDao<Long, User> {
    public UserDao() {
        setClazz(User.class);
    }
}
