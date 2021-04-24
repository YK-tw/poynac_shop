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

    public Attribute(Long id, String name, String value, List<Product> products) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.products = products;
    }

    public Attribute() {
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.value;
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
