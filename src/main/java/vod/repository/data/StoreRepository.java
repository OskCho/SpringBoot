package vod.repository.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vod.model.Store;
import vod.model.Guitar;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Integer> {

    List<Store> findAllByNameContaining(String name);

    @Query("select s from Store s inner join s.guitars guitar where guitar=:guitar")
    List<Store> findAllByGuitar(@Param("guitar") Guitar guitar);

}
