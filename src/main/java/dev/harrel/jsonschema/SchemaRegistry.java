package dev.harrel.jsonschema;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.*;

final class SchemaRegistry {
    private State state = State.empty();

    State createSnapshot() {
        return state.copy();
    }

    void restoreSnapshot(State state) {
        this.state = state;
    }

    Schema get(URI baseUri) {
        return get(new CompoundUri(baseUri, ""));
    }

    Schema get(CompoundUri compoundUri) {
        Fragments fragments = state.getFragments(compoundUri.uri);
        return fragments.schemas.getOrDefault(compoundUri.fragment, fragments.additionalSchemas.get(compoundUri.fragment));
    }

    Schema getDynamic(URI baseUri) {
        return getDynamic(new CompoundUri(baseUri, ""));
    }

    Schema getDynamic(CompoundUri compoundUri) {
        Fragments fragments = state.getFragments(compoundUri.uri);
        return fragments.dynamicSchemas.get(compoundUri.fragment);
    }

    void registerAlias(URI originalUri, URI aliasUri) {
        Fragments originalFragments = state.getFragments(originalUri);
        /* As long as registering schema under one URI multiple times is not forbidden, */
        /* aliases can cause unexpected changes - thus use of readOnly */
        state.fragments.put(aliasUri, originalFragments.readOnly());
    }

    void registerSchema(SchemaParsingContext ctx, JsonNode schemaNode, List<EvaluatorWrapper> evaluators, Set<String> activeVocabularies) {
        Schema schema = new Schema(ctx.getParentUri(), ctx.getAbsoluteUri(schemaNode), evaluators, activeVocabularies, ctx.getVocabulariesObject());
        state.getFragments(ctx.getBaseUri()).schemas.put(schemaNode.getJsonPointer(), schema);
        registerAnchorsIfPresent(ctx, schemaNode, schema);
    }

    void registerEmbeddedSchema(SchemaParsingContext ctx,
                                URI id,
                                JsonNode schemaNode,
                                List<EvaluatorWrapper> evaluators,
                                Set<String> activeVocabularies) {
        Fragments baseFragments = state.getFragments(ctx.getBaseUri());
        Fragments idFragments = state.getFragments(UriUtil.getUriWithoutFragment(id));

        baseFragments.schemas.entrySet().stream()
                .filter(e -> e.getKey().startsWith(schemaNode.getJsonPointer()))
                .forEach(e -> {
                    String newJsonPointer = e.getKey().substring(schemaNode.getJsonPointer().length());
                    idFragments.additionalSchemas.put(newJsonPointer, e.getValue());
                });
        Schema identifiableSchema = new Schema(ctx.getParentUri(), ctx.getAbsoluteUri(schemaNode), evaluators, activeVocabularies, ctx.getVocabulariesObject());
        idFragments.schemas.put("", identifiableSchema);
        baseFragments.schemas.put(schemaNode.getJsonPointer(), identifiableSchema);
        registerAnchorsIfPresent(ctx, schemaNode, identifiableSchema);
    }

    private void registerAnchorsIfPresent(SchemaParsingContext ctx, JsonNode schemaNode, Schema schema) {
        if (!schemaNode.isObject()) {
            return;
        }
        Map<String, JsonNode> objectMap = schemaNode.asObject();
        Fragments fragments = state.getFragments(ctx.getParentUri());

        JsonNodeUtil.getStringField(objectMap, Keyword.ANCHOR)
                .ifPresent(anchorString -> fragments.additionalSchemas.put(anchorString, schema));
        JsonNodeUtil.getStringField(objectMap, Keyword.DYNAMIC_ANCHOR)
                .ifPresent(anchorString -> fragments.dynamicSchemas.put(anchorString, schema));
        JsonNodeUtil.getBooleanField(objectMap, Keyword.RECURSIVE_ANCHOR)
                .filter(anchor -> anchor)
                .ifPresent(anchorString -> fragments.dynamicSchemas.put("", schema));
    }

    static final class State {
        private final Map<URI, Fragments> fragments;

        private State(Map<URI, Fragments> fragments) {
            this.fragments = fragments;
        }

        private Fragments getFragments(URI uri) {
            return fragments.computeIfAbsent(uri, key -> Fragments.empty());
        }

        private State copy() {
            Map<URI, Fragments> copiedMap = this.fragments.entrySet().stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().copy()));
            return new State(copiedMap);
        }

        private static State empty() {
            return new State(new HashMap<>());
        }
    }

    private static final class Fragments {
        private final Map<String, Schema> schemas;
        private final Map<String, Schema> additionalSchemas;
        private final Map<String, Schema> dynamicSchemas;

        private Fragments(Map<String, Schema> schemas, Map<String, Schema> additionalSchemas, Map<String, Schema> dynamicSchemas) {
            this.schemas = schemas;
            this.additionalSchemas = additionalSchemas;
            this.dynamicSchemas = dynamicSchemas;
        }

        private Fragments copy() {
            return new Fragments(new HashMap<>(this.schemas), new HashMap<>(this.additionalSchemas), new HashMap<>(this.dynamicSchemas));
        }

        private Fragments readOnly() {
            return new Fragments(unmodifiableMap(this.schemas), unmodifiableMap(this.additionalSchemas), unmodifiableMap(this.dynamicSchemas));
        }

        private static Fragments empty() {
            return new Fragments(new HashMap<>(), new HashMap<>(), new HashMap<>());
        }
    }
}
