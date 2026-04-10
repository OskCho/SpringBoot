package vod.repository.data;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import vod.model.Store;
import vod.model.Guitar;
import vod.repository.StoreDao;

import java.util.List;

@Repository
@Primary
@RequiredArgsConstructor
public class DataStoreDao implements StoreDao {

    private final StoreRepository repository;

    @Override
    public List<Store> findAll() {
        return repository.findAll();
    }

    @Override
    public Store findById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Store> findByGuitar(Guitar m) {
        return repository.findAllByGuitar(m);
    }

    @Override
    public Store save(Store Store) {
        return repository.save(Store);
    }
}
