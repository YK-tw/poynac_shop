package by.poynac.shop.model.wrapper;

import java.util.List;

public class AttributeFilterWrapper {

    private List<String> attributes;

    public AttributeFilterWrapper(List<String> attributes) {
        this.attributes = attributes;
    }

    public AttributeFilterWrapper() {
    }

    public List<String> getAttributes() {
        return this.attributes;
    }

    public void setAttributes(List<String> attributes) {
        this.attributes = attributes;
    }
}