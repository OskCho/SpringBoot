package vod.repository;

import vod.model.Brand;

import java.util.List;

public interface BrandDao {

    List<Brand> findAll();

    Brand findById(int id);

    Brand add(Brand b);


}
