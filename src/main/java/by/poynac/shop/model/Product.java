package by.poynac.shop.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Double price;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "product_attribute",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    private List<Attribute> attributes;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "products")
    private List<Order> orders;

    public Product(Long id, String name, Double price, String description, List<Attribute> attributes) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.attributes = attributes;
    }

    public Product(Long id, String name, Double price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id)
                && name.equals(product.name)
                && price.equals(product.price)
                && description.equals(product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, description);
    }
}
