package vod.web.rest.ui;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vod.model.Store;
import vod.model.Guitar;
import vod.service.StoreService;
import vod.service.GuitarService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class StoreController {

    private final StoreService storeService;
    private final GuitarService guitarService;

    @GetMapping("/stores") // /Stores?GuitarId=3
    String getStores(
            Model model,
            @RequestParam(value = "guitarId", required = false) Integer guitarId) {

        log.info("about to display Stores list playing Guitar {}", guitarId);

        if (guitarId != null) {
            Guitar guitar = guitarService.getGuitarById(guitarId);
            List<Store> stores = storeService.getStoresByGuitar(guitar);

            model.addAttribute("stores", stores);
            model.addAttribute("title", "stores playing '" + guitar.getModelName() + "'");
        } else {
            List<Store> stores = storeService.getAllStores();

            model.addAttribute("stores", stores);
            model.addAttribute("title", "stores");
        }

        return "storesView";
    }
}
