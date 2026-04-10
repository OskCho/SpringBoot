package vod.repository.mem;

import org.springframework.stereotype.Repository;
import vod.repository.GuitarDao;
import vod.model.Store;
import vod.model.Brand;
import vod.model.Guitar;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MemGuitarDao implements GuitarDao {
    @Override
    public List<Guitar> findAll() {
        return SampleData.guitars;
    }

    @Override
    public Guitar findById(int id) {
        return SampleData.guitars.stream().filter(m -> m.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Guitar> findByBrand(Brand b) {
       return SampleData.guitars.stream().filter(g -> g.getBrand() == b).collect(Collectors.toList());
    }

    @Override
    public List<Guitar> findByStore(Store c) {
        return SampleData.guitars.stream().filter(m -> m.getStores().contains(c)).collect(Collectors.toList());
    }

    @Override
    public Guitar add(Guitar m) {
        int max = SampleData.guitars.stream().max((m1, m2) -> m1.getId() - m2.getId()).get().getId();
        m.setId(++max);
        SampleData.guitars.add(m);
        return m;
    }
}
