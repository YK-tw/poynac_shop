package by.poynac.shop.model.wrapper;

import by.poynac.shop.model.Product;

import java.util.ArrayDeque;
import java.util.Queue;

public class LookedProductsWrapper {

    Queue<Product> queue;

    LookedProductsWrapper() {
        queue = new ArrayDeque<>(4);
    }

    public Queue<Product> addProduct(Product product) {
        queue.add(product);
        return queue;
    }
}
