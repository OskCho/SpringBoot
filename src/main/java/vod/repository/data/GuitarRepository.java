package vod.repository.data;

import org.springframework.data.jpa.repository.JpaRepository;
import vod.model.Brand;
import vod.model.Guitar;
import vod.model.Store;

import java.util.List;

public interface GuitarRepository extends JpaRepository<Guitar, Integer> {

    List<Guitar> findAllByBrand(Brand b);

    List<Guitar> findAllByStores(Store s);
}
