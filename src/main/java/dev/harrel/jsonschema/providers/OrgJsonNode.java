package dev.harrel.jsonschema.providers;

import dev.harrel.jsonschema.JsonNode;
import dev.harrel.jsonschema.JsonNodeFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.*;

public final class OrgJsonNode extends SimpleJsonNode {

    private OrgJsonNode(Object node, String jsonPointer) {
        super(Objects.requireNonNull(node), jsonPointer);
    }

    public OrgJsonNode(Object node) {
        this(node, "");
    }

    @Override
    public boolean asBoolean() {
        return (Boolean) node;
    }

    @Override
    public String asString() {
        return node.toString();
    }

    @Override
    List<JsonNode> createArray() {
        JSONArray jsonArray = (JSONArray) node;
        List<JsonNode> elements = new ArrayList<>(jsonArray.length());
        for (Object o : jsonArray) {
            elements.add(new OrgJsonNode(o, jsonPointer + "/" + elements.size()));
        }
        return elements;
    }

    @Override
    Map<String, JsonNode> createObject() {
        JSONObject jsonObject = (JSONObject) node;
        Map<String, JsonNode> map = MapUtil.newHashMap(jsonObject.length());
        for (String key : jsonObject.keySet()) {
            map.put(key, new OrgJsonNode(jsonObject.get(key), jsonPointer + "/" + JsonNode.encodeJsonPointer(key)));
        }
        return map;
    }

    @Override
    boolean isNull(Object node) {
        return JSONObject.NULL.equals(node);
    }

    @Override
    boolean isArray(Object node) {
        return node instanceof JSONArray;
    }

    @Override
    boolean isObject(Object node) {
        return node instanceof JSONObject;
    }

    public static final class Factory implements JsonNodeFactory {
        @Override
        public JsonNode wrap(Object node) {
            if (node instanceof OrgJsonNode) {
                return (OrgJsonNode) node;
            } else {
                return new OrgJsonNode(node);
            }
        }

        @Override
        public JsonNode create(String rawJson) {
            return new OrgJsonNode(new JSONTokener(rawJson).nextValue());
        }
    }
}
