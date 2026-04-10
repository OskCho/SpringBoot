package vod.repository.mem;

import org.springframework.stereotype.Repository;
import vod.model.Store;
import vod.model.Brand;
import vod.model.Guitar;

import java.util.ArrayList;
import java.util.List;

@Repository
class SampleData {

    static List<Store> stores = new ArrayList<>();

    static List<Brand> brands = new ArrayList<>();

    static List<Guitar> guitars = new ArrayList<>();

    static {
        Brand fender = new Brand(1, "Fender", "USA");
        Brand gibson = new Brand(2, "Gibson", "USA");
        Brand ibanez = new Brand(3, "Ibanez", "Japan");
        Brand prs = new Brand(4, "PRS", "USA");

        Guitar strat = new Guitar(1, "Stratocaster", "https://www.fmicassets.com/Damroot/ZoomJpg/10001/0113010705_gtr_frt_001_rr.jpg", fender, (float) 4.9);
        Guitar tele = new Guitar(2, "Telecaster", "https://www.fmicassets.com/Damroot/ZoomJpg/10001/0113060700_gtr_frt_001_rr.jpg", fender, (float) 4.7);

        Guitar lesPaul = new Guitar(3, "Les Paul Standard", "https://images.gibson.com/Products/Electric-Guitars/2019/USA/Les-Paul-Standard-50s/LPS500HSNH1_h_Front.jpg", gibson, (float) 4.8);
        Guitar sg = new Guitar(4, "SG Modern", "https://images.gibson.com/Products/Electric-Guitars/2019/USA/SG-Modern/SGM00ebnh1_h_Front.jpg", gibson, (float) 4.5);

        Guitar rg = new Guitar(5, "RG550 Genesis", "https://www.ibanez.com/common/product_artist_file/file/p_region_RG550_RF_00_03.png", ibanez, (float) 4.6);
        Guitar jem = new Guitar(6, "JEM7V Steve Vai", "https://www.ibanez.com/common/product_artist_file/file/p_region_JEM7V_WH_00_02.png", ibanez, (float) 4.9);

        Guitar custom24 = new Guitar(7, "Custom 24", "https://prsguitars.com/images/electrics/c24_2020_charcoal.jpg", prs, (float) 5.0);
        Guitar silverSky = new Guitar(8, "Silver Sky", "https://prsguitars.com/images/electrics/silversky_dodgemblue.jpg", prs, (float) 4.7);

        bind(strat, fender);
        bind(tele, fender);

        bind(lesPaul, gibson);
        bind(sg, gibson);

        bind(rg, ibanez);
        bind(jem, ibanez);

        bind(custom24, prs);
        bind(silverSky, prs);

        Store guitarCenter = new Store(1, "Guitar Center", "https://upload.wikimedia.org/wikipedia/en/thumb/3/33/Guitar_Center_logo.svg/1200px-Guitar_Center_logo.svg.png");
        Store thomann = new Store(2, "Thomann Music", "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c5/Thomann_Logo.svg/1200px-Thomann_Logo.svg.png");
        Store krakowMusic = new Store(3, "Kraków Music", "https://muzyczny.pl/img/logo.png");
        Store woodstock = new Store(4, "Woodstock Sklep", "https://woodstock.pl/logo.jpg");

        bind(guitarCenter, strat);
        bind(thomann, strat);
        bind(thomann, lesPaul);
        bind(krakowMusic, lesPaul);

        bind(guitarCenter, rg);
        bind(woodstock, rg);
        bind(woodstock, custom24);
        bind(krakowMusic, custom24);
        bind(krakowMusic, jem);

        guitars.add(strat);
        guitars.add(tele);
        guitars.add(lesPaul);
        guitars.add(sg);
        guitars.add(rg);
        guitars.add(jem);
        guitars.add(custom24);
        guitars.add(silverSky);

        brands.add(fender);
        brands.add(gibson);
        brands.add(ibanez);
        brands.add(prs);

        stores.add(guitarCenter);
        stores.add(thomann);
        stores.add(krakowMusic);
        stores.add(woodstock);
    }

    private static void bind(Store s, Guitar g) {
        s.addGuitar(g);
        g.addStore(s);
    }

    private static void bind(Guitar g, Brand b) {
        b.addGuitar(g);
        g.setBrand(b);
    }
}