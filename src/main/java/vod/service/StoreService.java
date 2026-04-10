package vod.service;

import vod.model.Store;
import vod.model.Guitar;

import java.util.List;

public interface StoreService {
    Store getStoreById(int id);

    List<Store> getAllStores();

    List<Store> getStoresByGuitar(Guitar m);

    List<Guitar> getGuitarsInStore(Store c);

    Store addStore(Store c);

}
