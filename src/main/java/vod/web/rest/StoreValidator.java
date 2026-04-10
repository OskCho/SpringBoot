package vod.web.rest;

import org.springframework.validation.Validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import vod.model.Store;
import vod.service.StoreService;

@Component
@RequiredArgsConstructor
public class StoreValidator implements Validator {

    private final StoreService StoreService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(Store.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Store validateStore = (Store) target;

        boolean duplicated = StoreService.getAllStores().stream()
                .anyMatch(Store -> Store.getName().equalsIgnoreCase(validateStore.getName()));

        if (duplicated) {
            errors.rejectValue("name", "Store.name.duplicated", "Nazwa kina już istnieje!");
        }
    }
}