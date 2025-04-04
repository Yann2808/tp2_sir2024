package dao;

import entity.User;


/**
 * On est pas obligé de créer ça, car on ne fera jamais d'action directement
 * sur User mais vu qu'on a email ici, on peut se le permettre
 */
public class UserDao extends AbstractJpaDao<Long, User> {
    public UserDao() {
        setClazz(User.class);
    }
}
