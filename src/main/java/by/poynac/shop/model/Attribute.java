package by.poynac.shop.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Attribute {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String value;

    @ManyToMany(mappedBy = "attributes")
    private List<Product> products;

    public Attribute() {
    }

    public Attribute(Long id, String name, String value, List<Product> products) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
