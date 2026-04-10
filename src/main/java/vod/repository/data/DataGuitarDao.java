package vod.repository.data;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import vod.model.Brand;
import vod.model.Guitar;
import vod.model.Store;
import vod.repository.GuitarDao;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Primary
public class DataGuitarDao implements GuitarDao {

    private final GuitarRepository repository;

    @Override
    public List<Guitar> findAll() {
        return repository.findAll();
    }

    @Override
    public Guitar findById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Guitar> findByBrand(Brand b) {
        return repository.findAllByBrand(b);
    }

    @Override
    public List<Guitar> findByStore(Store s) {
        return repository.findAllByStores(s);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public Guitar add(Guitar g) {
        return repository.save(g);
    }
}
