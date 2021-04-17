package by.poynac.shop.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

}
