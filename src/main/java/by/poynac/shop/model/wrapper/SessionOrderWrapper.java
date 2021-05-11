package by.poynac.shop.model.wrapper;


import by.poynac.shop.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class SessionOrderWrapper implements Serializable {

    private Map<Product, Integer> products;

    public SessionOrderWrapper() {
        products = new HashMap<>();
    }

    public void addProduct(Product product) {
        if (products.containsKey(product)) {
            products.put(product, products.get(product) + 1);
        } else {
            products.put(product, 1);
        }
    }

    public void removeSingleProduct(Product product) {
        if (products.containsKey(product)) {
            if (products.get(product) > 1) {
                products.put(product, products.get(product) - 1);
            } else {
                products.remove(product);
            }
        }
    }

    public void setProductAmount(Product product, Integer amount) {
        if (amount != null && amount > 0) {
            products.put(product, amount);
        } else if (amount == 0) {
            products.remove(product);
        }
    }

    public Double countFullPrice() {
        double result = 0.0;
        for (Product key : products.keySet()) {
            result += key.getPrice() * products.get(key);
        }
        return result;
    }

}
