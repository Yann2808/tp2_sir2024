package dao;

import entity.UtilisateurParticulier;

public class UtilisateurParticulierDao extends AbstractJpaDao<Long, UtilisateurParticulier> {
    public UtilisateurParticulierDao() {
        setClazz(UtilisateurParticulier.class);
    }

}
