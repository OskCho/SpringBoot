package vod.repository.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import vod.model.Store;
import vod.model.Brand;
import vod.model.Guitar;
import vod.repository.GuitarDao;

import java.util.List;

@Repository
public class JpaGuitarDao implements GuitarDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Guitar> findAll() {
        return entityManager
                .createQuery("select m from Guitar m")
                .getResultList();
    }

    @Override
    public Guitar findById(int id) {
        return entityManager.find(Guitar.class, id);
    }

    @Override
    public List<Guitar> findByBrand(Brand b) {
        return entityManager
                .createQuery("select m from Guitar m where m.brandId = :brand")
                .setParameter("brand", b)
                .getResultList();
    }

    @Override
    public Guitar add(Guitar m) {
        entityManager.persist(m);
        return m;
    }

    @Override
    public List<Guitar> findByStore(Store s) {
        return entityManager
                .createQuery("select g from Guitar g join g.stores s where s = :store")
                .setParameter("store", s)
                .getResultList();
    }
}
