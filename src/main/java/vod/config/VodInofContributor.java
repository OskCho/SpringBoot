package vod.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;
import vod.service.GuitarService;

@Component
@RequiredArgsConstructor
public class VodInofContributor implements InfoContributor {

    private final GuitarService guitarService;

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("guitar", guitarService.getAllGuitars().size());
    }
}
