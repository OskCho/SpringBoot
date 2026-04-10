package vod.web.rest.ui;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vod.model.Store;
import vod.model.Brand;
import vod.model.Guitar;
import vod.service.StoreService;
import vod.service.GuitarService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class GuitarController {

    private final GuitarService guitarService;
    private final StoreService storeService;

    @GetMapping("/guitars")
    String getGuitars(
            Model model,
            @RequestParam(value = "storeId", required = false) Integer storeId,
            @RequestParam(value = "brandId", required = false) Integer brandId){

        log.info("about to display Guitars actually played");

        if(storeId != null){

            Store store = storeService.getStoreById(storeId);
            List<Guitar> guitars = storeService.getGuitarsInStore(store);

            model.addAttribute("Guitars", guitars);
            model.addAttribute("title", "Guitars played in " + store.getName());

        }
        else if(brandId != null){

            Brand brand = guitarService.getBrandById(brandId);
            List<Guitar> guitars = guitarService.getGuitarsByBrand(brand);

            model.addAttribute("guitars", guitars);
            model.addAttribute("title", "guitar directed by " + brand.getName());

        }
        else
        {

            List<Guitar> guitars = guitarService.getAllGuitars();

            model.addAttribute("guitars", guitars);
            model.addAttribute("title", "guitars");

        }
        return "guitarsView";
    }
}
