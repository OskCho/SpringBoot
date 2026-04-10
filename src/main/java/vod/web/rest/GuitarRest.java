package vod.web.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import vod.model.Store;
import vod.model.Guitar;
import vod.service.StoreService;
import vod.service.GuitarService;
import vod.web.rest.dto.GuitarDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/webapi")
public class GuitarRest {

    private final StoreService storeService;
    private final GuitarService guitarService;
    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;

    @GetMapping("/guitars")
    public List<Guitar> getGuitars() {
        log.info("about to retrieve Guitars list");
        List<Guitar> guitars = guitarService.getAllGuitars();
        log.info("{} Guitars found", guitars.size());
        return guitars;
    }

    @GetMapping("/guitars/{id}")
    public ResponseEntity<Guitar> getGuitar(@PathVariable("id") int id) {
        log.info("about to retrieve Guitar {}", id);

        Guitar guitar = guitarService.getGuitarById(id);

        log.info("Guitar found: {}", guitar);

        if (guitar != null) {
            return ResponseEntity.ok(guitar);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/stores/{storeId}/guitars")
    public ResponseEntity<List<Guitar>> getGuitarsPlayedAtStore(@PathVariable("storeId") int storeId) {
        log.info("about to retrieve Guitars playing at store {}", storeId);

        Store store = storeService.getStoreById(storeId);

        if (store == null) {
            return ResponseEntity.notFound().build();
        } else {
            List<Guitar> guitars = storeService.getGuitarsInStore(store);
            log.info("there's {} Guitars played at store {}", guitars.size(), store.getName());
            return ResponseEntity.ok(guitars);
        }
    }

    @PostMapping("/guitars")
    public ResponseEntity<?> addGuitar(@RequestBody GuitarDTO guitarDTO, Errors errors) {
        log.info("about to add new Guitar {}", guitarDTO);
        if(errors.hasErrors()){
            return ResponseEntity.badRequest().build();
        }

        Guitar guitar = new Guitar();
        guitar.setModelName(guitarDTO.getModelName());
        guitar.setImage(guitarDTO.getImage());
        guitar.setPrice(guitarDTO.getPrice());
        guitar.setBrand(guitarService.getBrandById(guitarDTO.getBrandId()));

        guitar = guitarService.addGuitar(guitar);
        log.info("new Guitar added: {}", guitar);

        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromCurrentRequestUri()
                        .path("/" + guitar.getId())
                        .build()
                        .toUri()
        ).body(guitar);
    }
}
