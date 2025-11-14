package fr.vat.amapg.amapg.configuration.bean;

import org.springframework.ui.Model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MockedModel implements Model {

    private final HashMap<String, Object> attributes = new HashMap<>();

    @Override
    public Model addAttribute(String attributeName, Object attributeValue) {
        attributes.put(attributeName, attributeValue);
        return this;
    }

    @Override
    public Model addAttribute(Object attributeValue) {
        return null;
    }

    @Override
    public Model addAllAttributes(Collection<?> attributeValues) {
        return null;
    }

    @Override
    public Model addAllAttributes(Map<String, ?> attributes) {
        return null;
    }

    @Override
    public Model mergeAttributes(Map<String, ?> attributes) {
        return null;
    }

    @Override
    public boolean containsAttribute(String attributeName) {
        return false;
    }

    // Implement other methods from Model interface as no-op or throw UnsupportedOperationException
    // For brevity, only addAttribute is implemented here

    public Object getAttribute(String attributeName) {
        return attributes.get(attributeName);
    }

    @Override
    public Map<String, Object> asMap() {
        return Map.of();
    }

}