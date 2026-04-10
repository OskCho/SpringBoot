package vod.service;

import vod.model.Brand;
import vod.model.Guitar;

import java.util.List;

public interface GuitarService {


    List<Guitar> getAllGuitars();

    List<Guitar> getGuitarsByBrand(Brand d);

    Guitar getGuitarById(int id);

    Guitar addGuitar(Guitar m);


    List<Brand> getAllBrands();

    Brand getBrandById(int id);

    Brand addBrand(Brand d);
}
