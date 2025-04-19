package com.bean_core.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ParamBuilder {
    private ObjectNode root;
    private static final ObjectMapper mapper = new ObjectMapper();

    public ParamBuilder() {
        this.root = mapper.createObjectNode();
    }

    public ParamBuilder add(String key, String value) {
        root.put(key, value);
        return this;
    }

    public ParamBuilder add(String key, int value) {
        root.put(key, value);
        return this;
    }

    public ParamBuilder add(String key, boolean value) {
        root.put(key, value);
        return this;
    }

    public ParamBuilder add(String key, double value) {
        root.put(key, value);
        return this;
    }

    // Add more overloads as needed (e.g., for arrays or nested objects)

    public String build() {
        return root.toString();
    }
}
