package vod.service.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import vod.model.Store;
import vod.model.Guitar;
import vod.repository.StoreDao;
import vod.repository.GuitarDao;
import vod.service.StoreService;

import java.util.List;
import java.util.logging.Logger;

@Service
@Scope("prototype")
public class StoreServiceBean implements StoreService {

    private static final Logger log = Logger.getLogger(StoreService.class.getName());

    private StoreDao storeDao;

    private GuitarDao guitarDao;

    public StoreServiceBean(StoreDao storeDao, GuitarDao guitarDao) {
        log.info("creating Store service bean");
        this.storeDao = storeDao;
        this.guitarDao = guitarDao;
    }

    @Override
    public Store getStoreById(int id) {
        log.info("searching Store by id " + id);
        return storeDao.findById(id);
    }

    @Override
    public List<Guitar> getGuitarsInStore(Store s) {
        log.info("searching Guitars played in Store " + s.getId());
        return guitarDao.findByStore(s);
    }

    @Override
    public List<Store> getAllStores() {
        log.info("searching all Stores");
        return storeDao.findAll();
    }

    @Override
    public List<Store> getStoresByGuitar(Guitar g) {
        log.info("searching Stores by Guitar " + g.getId());
        return storeDao.findByGuitar(g);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public Store addStore(Store s) {
        log.info("adding Store " + s.getId());
        return storeDao.save(s);
    }

}
