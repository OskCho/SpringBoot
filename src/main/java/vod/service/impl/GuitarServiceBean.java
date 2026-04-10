package vod.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import vod.repository.StoreDao;
import vod.repository.BrandDao;
import vod.repository.GuitarDao;
import vod.model.Store;
import vod.model.Brand;
import vod.model.Guitar;
import vod.service.GuitarService;

import java.util.List;
import java.util.logging.Logger;

@Service
public class GuitarServiceBean implements GuitarService {

    private static final Logger log = Logger.getLogger(GuitarService.class.getName());


    private final BrandDao brandDao;

    private final StoreDao storeDao;

    private final  GuitarDao guitarDao;

    private PlatformTransactionManager transactionManager;

    public GuitarServiceBean(BrandDao brandDao, StoreDao storeDao, GuitarDao guitarDao) {
        this.brandDao = brandDao;
        this.storeDao = storeDao;
        this.guitarDao = guitarDao;
    }

    public List<Guitar> getAllGuitars() {
        log.info("searching all Guitars...");
        return guitarDao.findAll();
    }

    public List<Guitar> getGuitarsByBrand(Brand b) {
        log.info("serching Guitars by diretor " + b.getId());
        return guitarDao.findByBrand(b);
    }

    public List<Guitar> getGuitarsInStore(Store s) {
        log.info("searching Guitars played in Store " + s.getId());
        return guitarDao.findByStore(s);
    }

    public Guitar getGuitarById(int id) {
        log.info("searching Guitar by id " + id);
        return guitarDao.findById(id);
    }

    public List<Store> getAllStores() {
        log.info("searching all Stores");
        return storeDao.findAll();
    }

    public List<Store> getStoresByGuitar(Guitar g) {
        log.info("searching Stores by Guitar " + g.getId());
        return storeDao.findByGuitar(g);
    }

    public Store getStoreById(int id) {
        log.info("searching Store by id " + id);
        return storeDao.findById(id);
    }

    public List<Brand> getAllBrands() {
        log.info("searching all Brands");
        return brandDao.findAll();
    }

    public Brand getBrandById(int id) {
        log.info("searching Brand by id " + id);
        return brandDao.findById(id);
    }

    @Override
    public Brand addBrand(Brand b) {
        log.info("about to add Brand " + b);
        return brandDao.add(b);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Guitar addGuitar(Guitar g) {
        log.info("about to add guitar " + g);
        g = guitarDao.add(g);
        if(g.getModelName().equals("ESP")){
            throw new RuntimeException("not yet");
        }
        return g;
    }
}
