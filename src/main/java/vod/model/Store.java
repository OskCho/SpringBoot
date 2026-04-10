package vod.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(min = 2, max = 30)
    private String name;

    @NotNull
    private String logo;

    @ManyToMany(mappedBy = "stores")
    @JsonIgnore
    private List<Guitar> guitars = new ArrayList<>();

    public Store(int id, String name, String logo) {
        this.id = id;
        this.name = name;
        this.logo = logo;
    }

    public Store() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogo(String logo)  { this.logo = logo; }

    public String getLogo() { return logo; }

    public List<Guitar> getGuitars() {
        return guitars;
    }

    public void setGuitars(List<Guitar> guitars) {
        this.guitars = guitars;
    }

    public void addGuitar(Guitar g) {
        this.guitars.add(g);
    }

    @Override
    public String toString() {
        return "Store{" +
                "name='" + name + '\'' +
                '}';
    }
}