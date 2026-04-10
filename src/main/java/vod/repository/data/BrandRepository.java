package vod.repository.data;

import org.springframework.data.jpa.repository.JpaRepository;
import vod.model.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
}
