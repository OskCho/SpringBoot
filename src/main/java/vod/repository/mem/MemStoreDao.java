package vod.repository.mem;

import org.springframework.stereotype.Repository;
import vod.repository.StoreDao;
import vod.model.Store;
import vod.model.Guitar;

import java.util.List;
import java.util.stream.Collectors;

@Repository("StoreDao")
public class MemStoreDao implements StoreDao {

    @Override
    public List<Store> findAll() {
        return SampleData.stores;
    }

    @Override
    public Store findById(int id) {
        return SampleData.stores.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Store> findByGuitar(Guitar m) {
        return SampleData.stores.stream().filter(c -> c.getGuitars().contains(m)).collect(Collectors.toList());
    }

    @Override
    public Store save(Store store) {
        int maxId = SampleData.stores.stream()
                .sorted( (c1,c2)->c2.getId()-c1.getId() )
                .findFirst()
                .map(c->c.getId())
                .orElse(0);
        store.setId(maxId+1);
        SampleData.stores.add(store);
        return store;
    }
}
