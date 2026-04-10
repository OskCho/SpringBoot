package vod.web.rest;

import org.springframework.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import vod.model.Brand;
import vod.service.GuitarService;
import vod.web.rest.dto.GuitarDTO;

@Component
@RequiredArgsConstructor
public class GuitarValidator implements Validator {

    private final GuitarService GuitarService;

    @Override
    public boolean supports(Class<?> clazz) { return clazz.isAssignableFrom(GuitarDTO.class); }

    @Override
    public void validate(Object target, Errors errors) {
        GuitarDTO Guitar = (GuitarDTO) target;
        Brand brand = GuitarService.getBrandById(Guitar.getBrandId());
        if (brand == null) {
            errors.rejectValue("BrandId", "Guitar.Brand.missing");
        }
    }
}
