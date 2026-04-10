package vod.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "guitar")
public class Guitar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String modelName;

    @NotNull
    private String image;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    private float price;

    @ManyToMany
    @JoinColumn(name = "brand_id")
    @JoinTable(
            name = "guitar_store",
            joinColumns = @JoinColumn(name = "guitar_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "store_id", referencedColumnName = "id")
    )
    private List<Store> stores = new ArrayList<>();

    public Guitar(int id, String modelName, String image, Brand brand, float price) {
        this.id = id;
        this.modelName = modelName;
        this.image = image;
        this.brand = brand;
        this.price = price;
    }

    public Guitar() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<Store> getStores() {
        return stores;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }

    public void addStore(Store s) {
        this.stores.add(s);
    }

    @Override
    public String toString() {
        return "Guitar{" +
                "modelName='" + modelName + '\'' +
                ", brand=" + brand +
                ", price=" + price +
                '}';
    }
}