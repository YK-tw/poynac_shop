package by.poynac.shop.model.wrapper;

import by.poynac.shop.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayDeque;
import java.util.Queue;

@Getter
@Setter
public class LookedProductsWrapper {

    private Queue<Product> products;

    public LookedProductsWrapper() {
        products = new ArrayDeque<>(4);
    }

    public void addProduct(Product product) {
        if (!products.contains(product)) {
            products.add(product);
        }
    }
}
