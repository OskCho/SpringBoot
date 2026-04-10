package vod.repository;

import vod.model.Store;
import vod.model.Brand;
import vod.model.Guitar;

import java.util.List;

public interface GuitarDao {

    List<Guitar> findAll();

    Guitar findById(int id);

    List<Guitar> findByBrand(Brand b);

    List<Guitar> findByStore(Store s);

    Guitar add(Guitar g);

}
