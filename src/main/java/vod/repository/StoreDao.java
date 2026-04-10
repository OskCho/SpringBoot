package vod.repository;

import vod.model.Store;
import vod.model.Guitar;

import java.util.List;

public interface StoreDao {

    List<Store> findAll();

    Store findById(int id);

    List<Store> findByGuitar(Guitar g);

    Store save(Store Store);
}
