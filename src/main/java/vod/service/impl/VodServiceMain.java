package vod.service.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import vod.model.Store;
import vod.service.StoreService;

import java.util.List;

public class VodServiceMain {

    public static void main(String[] args) {

        System.out.println("Let's find Stores!");

        ApplicationContext context = new AnnotationConfigApplicationContext("vod");
        StoreService service = context.getBean(StoreService.class);
        StoreService service2 = context.getBean(StoreService.class);

        List<Store> Stores = service.getAllStores();
        System.out.println(Stores.size() + " Stores found:");
        Stores.forEach(System.out::println);

        String foo = context.getBean(String.class);
        System.out.println("foo string: " + foo);
    }
}
