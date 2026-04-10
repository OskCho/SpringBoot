package vod;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vod.model.Store;
import vod.service.StoreService;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class VodComponent {

    private final StoreService StoreService;

    @PostConstruct
    public void init() {
        List<Store> Stores = StoreService.getAllStores();
        log.info("Stores found: {}", Stores.size());
        Stores.forEach(Store -> log.info("{}", Store));
    }
}