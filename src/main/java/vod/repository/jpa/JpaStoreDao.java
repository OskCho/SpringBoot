package vod.repository.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import vod.model.Store;
import vod.model.Guitar;
import vod.repository.StoreDao;

import java.util.List;

@Repository
public class JpaStoreDao implements StoreDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Store> findAll(){
        return  entityManager
                .createQuery("select s from Store s")
                .getResultList();
    }

    @Override
    public Store findById(int id) { return entityManager.find(Store.class, id); }

    @Override
    public List<Store> findByGuitar(Guitar g){
        return entityManager
                .createQuery("select s from Store s join s.guitars m where m = :guitar")
                .setParameter("guitar", g)
                .getResultList();
    }

    @Override
    public Store save(Store Store) {
        entityManager.persist(Store);
        return Store;
    }

}
