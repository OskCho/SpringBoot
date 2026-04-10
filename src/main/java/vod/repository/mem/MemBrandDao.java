package vod.repository.mem;

import org.springframework.stereotype.Repository;
import vod.repository.BrandDao;
import vod.model.Brand;

import java.util.List;

@Repository
public class MemBrandDao implements BrandDao {
    @Override
    public List<Brand> findAll() {
        return SampleData.brands;
    }

    @Override
    public Brand findById(int id) {
        return SampleData.brands.stream().filter(d -> d.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Brand add(Brand d) {
        int max = SampleData.brands.stream().max((d1, d2) -> d1.getId() - d2.getId()).get().getId();
        d.setId(++max);
        SampleData.brands.add(d);
        return d;
    }
}
