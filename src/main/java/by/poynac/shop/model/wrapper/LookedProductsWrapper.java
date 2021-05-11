package by.poynac.shop.model.wrapper;

import by.poynac.shop.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayDeque;
import java.util.Queue;

@Getter
@Setter
public class LookedProductsWrapper {

    final int LOOKED_WIDGET_SIZE = 4;

    private Queue<Product> products;

    public LookedProductsWrapper() {
        products = new ArrayDeque<>(LOOKED_WIDGET_SIZE);
    }

    public void addProduct(Product product) {
        if (!products.contains(product)) {
            if (products.size() == LOOKED_WIDGET_SIZE) {
                products.remove();
            }
            products.add(product);
        }
    }
}
