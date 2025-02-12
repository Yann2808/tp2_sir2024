package dao;

import entity.Evenement;

public class EvenementDao extends AbstractJpaDao<Long, Evenement> {
    public EvenementDao() {
        setClazz(Evenement.class);
    }
}
