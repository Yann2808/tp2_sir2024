package dao;

import entity.Organisateur;

public class OrganisateurDao extends AbstractJpaDao<Long, Organisateur> {
    public OrganisateurDao() {
        setClazz(Organisateur.class);
    }
}
