package by.poynac.shop.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SessionOrderWrapper implements Serializable {

    private Map<Product, Integer> products;

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }

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
        Double result = 0.0;
        for (Product key : products.keySet()) {
            result += key.getPrice() * products.get(key);
        }
        return result;
    }
}
