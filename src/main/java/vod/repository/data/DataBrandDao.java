package vod.repository.data;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import vod.model.Brand;
import vod.repository.BrandDao;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Primary
public class DataBrandDao implements BrandDao {

    private final BrandRepository repository;

    @Override
    public List<Brand> findAll() {
        return repository.findAll();
    }

    @Override
    public Brand findById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Brand add(Brand b) {
        return repository.save(b);
    }
}
