package vod.web.rest;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import vod.model.Store;
import vod.model.Guitar;
import vod.service.StoreService;
import vod.service.GuitarService;

import java.util.List;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/webapi")
public class StoreRest {

    private final StoreService StoreService;
    private final GuitarService GuitarService;
    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;

    @GetMapping("/stores")
    List<Store> getStores(
            @RequestParam(value = "phrase", required = false) String phrase,
            @RequestHeader(value = "custom-header", required = false) String customHeader,
            @CookieValue(value = "some-cookie", required = false) String someCookie
    ) {
        log.info("about to retrieve Store list");
        log.info("phrase: {}", phrase);
        log.info("custom-header: {}", customHeader);
        log.info("some-cookie: {}", someCookie);

        if(phrase!=null && phrase.equals("foo")){
            throw new IllegalArgumentException("Foo!");
        }

        List<Store> stores = StoreService.getAllStores();
        log.info("{} stores found", stores.size());
        return stores;
    }

    @GetMapping("/stores/{id}")
    Object getStore(@PathVariable("id") int id) {
        log.info("about to retrieve Store {}", id);
        Store store = StoreService.getStoreById(id);
        if (store != null) {
            return ResponseEntity.ok(store);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/guitars/{guitarId}/stores")
    ResponseEntity<Object> getStoresPlayingGuitar(@PathVariable("guitarId") int guitarId) {
        log.info("about to retrieve Guitar Store {}", guitarId);
        Guitar guitar = GuitarService.getGuitarById(guitarId);
        if (guitar == null) {
            return ResponseEntity.notFound().build();
        }
        else{
            List<Store> Stores = StoreService.getStoresByGuitar(guitar);
            log.info("there's Stores playing Guitar {}", Stores.size());
            return ResponseEntity.ok(Stores);
        }
    }

    @PostMapping("/stores")
    ResponseEntity<?> addStore(@Validated @RequestBody Store store, Errors errors, HttpServletRequest request) {
        log.info("about to add Store {}", store);

        if(errors.hasErrors()) {
            Locale locale = localeResolver.resolveLocale(request);

            String errorMessage = errors.getAllErrors().stream()
                    .map(oe -> messageSource.getMessage(oe.getCode(), new Object[0], locale))
                    .reduce("errors\n", (accu, oe) -> accu + oe + "\n");

            return ResponseEntity.badRequest().body(errorMessage);
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("authentication: {}", authentication);
        log.info("authentication name: {}", authentication.getName());

        store = StoreService.addStore(store);
        log.info("added Store {}", store);
        return ResponseEntity.status(HttpStatus.CREATED).body(store);
    }
}