package org.harrel.jsonschema;

import com.fasterxml.jackson.databind.JsonNode;
import org.harrel.jsonschema.providers.JacksonNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

@SuppressWarnings("unused")
class SpecificationTest {

    private final Logger logger = Logger.getLogger("SpecificationTest");

    @SuiteTest("/draft2020-12/boolean_schema.json")
    void booleanSchemaTest(String bundle, String name, JsonNode schema, JsonNode json, boolean valid) {
        testValidation(bundle, name, schema, json, valid);
    }

    @SuiteTest("/draft2020-12/default.json")
    void defaultTest(String bundle, String name, JsonNode schema, JsonNode json, boolean valid) {
        testValidation(bundle, name, schema, json, valid);
    }

    @SuiteTest("/draft2020-12/format.json")
    void formatTest(String bundle, String name, JsonNode schema, JsonNode json, boolean valid) {
        testValidation(bundle, name, schema, json, valid);
    }

    @SuiteTest("/draft2020-12/content.json")
    void contentTest(String bundle, String name, JsonNode schema, JsonNode json, boolean valid) {
        testValidation(bundle, name, schema, json, valid);
    }

    @SuiteTest("/draft2020-12/defs.json")
    void defsTest(String bundle, String name, JsonNode schema, JsonNode json, boolean valid) {
        testValidation(bundle, name, schema, json, valid);
    }

    @Disabled("$id in all places are supported")
    @SuiteTest("/draft2020-12/unknownKeyword.json")
    void unknownKeywordTest(String bundle, String name, JsonNode schema, JsonNode json, boolean valid) {
        testValidation(bundle, name, schema, json, valid);
    }

    @Disabled("Vocabularies are not supported yet")
    @SuiteTest("/draft2020-12/vocabulary.json")
    void vocabularyTest(String bundle, String name, JsonNode schema, JsonNode json, boolean valid) {
        testValidation(bundle, name, schema, json, valid);
    }

    @SuiteTest("/draft2020-12/id.json")
    void idTest(String bundle, String name, JsonNode schema, JsonNode json, boolean valid) {
        testValidation(bundle, name, schema, json, valid);
    }

    @SuiteTest("/draft2020-12/infinite-loop-detection.json")
    void infiniteLoopDetectionTest(String bundle, String name, JsonNode schema, JsonNode json, boolean valid) {
        testValidation(bundle, name, schema, json, valid);
    }

    @SuiteTest("/draft2020-12/if-then-else.json")
    void ifThenElseTest(String bundle, String name, JsonNode schema, JsonNode json, boolean valid) {
        testValidation(bundle, name, schema, json, valid);
    }

    @SuiteTest("/draft2020-12/anyOf.json")
    void anyOfTest(String bundle, String name, JsonNode schema, JsonNode json, boolean valid) {
        testValidation(bundle, name, schema, json, valid);
    }

    @SuiteTest("/draft2020-12/allOf.json")
    void allOfTest(String bundle, String name, JsonNode schema, JsonNode json, boolean valid) {
        testValidation(bundle, name, schema, json, valid);
    }

    @SuiteTest("/draft2020-12/oneOf.json")
    void oneOfTest(String bundle, String name, JsonNode schema, JsonNode json, boolean valid) {
        testValidation(bundle, name, schema, json, valid);
    }

    @SuiteTest("/draft2020-12/not.json")
    void notTest(String bundle, String name, JsonNode schema, JsonNode json, boolean valid) {
        testValidation(bundle, name, schema, json, valid);
    }

    @SuiteTest("/draft2020-12/type.json")
    void typeTest(String bundle, String name, JsonNode schema, JsonNode json, boolean valid) {
        testValidation(bundle, name, schema, json, valid);
    }

    @SuiteTest("/draft2020-12/const.json")
    void constTest(String bundle, String name, JsonNode schema, JsonNode json, boolean valid) {
        testValidation(bundle, name, schema, json, valid);
    }

    @SuiteTest("/draft2020-12/enum.json")
    void enumTest(String bundle, String name, JsonNode schema, JsonNode json, boolean valid) {
        testValidation(bundle, name, schema, json, valid);
    }

    @SuiteTest("/draft2020-12/items.json")
    void itemsTest(String bundle, String name, JsonNode schema, JsonNode json, boolean valid) {
        testValidation(bundle, name, schema, json, valid);
    }

    @SuiteTest("/draft2020-12/prefixItems.json")
    void prefixItemsTest(String bundle, String name, JsonNode schema, JsonNode json, boolean valid) {
        testValidation(bundle, name, schema, json, valid);
    }

    @SuiteTest("/draft2020-12/maxItems.json")
    void maxItemsTest(String bundle, String name, JsonNode schema, JsonNode json, boolean valid) {
        testValidation(bundle, name, schema, json, valid);
    }

    @SuiteTest("/draft2020-12/minItems.json")
    void minItemsTest(String bundle, String name, JsonNode schema, JsonNode json, boolean valid) {
        testValidation(bundle, name, schema, json, valid);
    }

    @SuiteTest("/draft2020-12/uniqueItems.json")
    void uniqueItemsTest(String bundle, String name, JsonNode schema, JsonNode json, boolean valid) {
        testValidation(bundle, name, schema, json, valid);
    }

    @SuiteTest("/draft2020-12/contains.json")
    void containsTest(String bundle, String name, JsonNode schema, JsonNode json, boolean valid) {
        testValidation(bundle, name, schema, json, valid);
    }

    @SuiteTest("/draft2020-12/maxContains.json")
    void maxContainsTest(String bundle, String name, JsonNode schema, JsonNode json, boolean valid) {
        testValidation(bundle, name, schema, json, valid);
    }

    @SuiteTest("/draft2020-12/minContains.json")
    void minContainsTest(String bundle, String name, JsonNode schema, JsonNode json, boolean valid) {
        testValidation(bundle, name, schema, json, valid);
    }

    @SuiteTest("/draft2020-12/unevaluatedItems.json")
    void unevaluatedItemsTest(String bundle, String name, JsonNode schema, JsonNode json, boolean valid) {
        testValidation(bundle, name, schema, json, valid);
    }

    @SuiteTest("/draft2020-12/properties.json")
    void propertiesTest(String bundle, String name, JsonNode schema, JsonNode json, boolean valid) {
        testValidation(bundle, name, schema, json, valid);
    }

    @SuiteTest("/draft2020-12/maxProperties.json")
    void maxPropertiesTest(String bundle, String name, JsonNode schema, JsonNode json, boolean valid) {
        testValidation(bundle, name, schema, json, valid);
    }

    @SuiteTest("/draft2020-12/additionalProperties.json")
    void additionalPropertiesTest(String bundle, String name, JsonNode schema, JsonNode json, boolean valid) {
        testValidation(bundle, name, schema, json, valid);
    }

    @SuiteTest("/draft2020-12/patternProperties.json")
    void patternPropertiesTest(String bundle, String name, JsonNode schema, JsonNode json, boolean valid) {
        testValidation(bundle, name, schema, json, valid);
    }

    @SuiteTest("/draft2020-12/propertyNames.json")
    void propertyNamesTest(String bundle, String name, JsonNode schema, JsonNode json, boolean valid) {
        testValidation(bundle, name, schema, json, valid);
    }

    @SuiteTest("/draft2020-12/unevaluatedProperties.json")
    void unevaluatedPropertiesTest(String bundle, String name, JsonNode schema, JsonNode json, boolean valid) {
        testValidation(bundle, name, schema, json, valid);
    }

    @SuiteTest("/draft2020-12/minProperties.json")
    void minPropertiesTest(String bundle, String name, JsonNode schema, JsonNode json, boolean valid) {
        testValidation(bundle, name, schema, json, valid);
    }

    @SuiteTest("/draft2020-12/required.json")
    void requiredTest(String bundle, String name, JsonNode schema, JsonNode json, boolean valid) {
        testValidation(bundle, name, schema, json, valid);
    }

    @SuiteTest("/draft2020-12/dependentRequired.json")
    void dependentRequiredTest(String bundle, String name, JsonNode schema, JsonNode json, boolean valid) {
        testValidation(bundle, name, schema, json, valid);
    }

    @SuiteTest("/draft2020-12/dependentSchemas.json")
    void dependentSchemasTest(String bundle, String name, JsonNode schema, JsonNode json, boolean valid) {
        testValidation(bundle, name, schema, json, valid);
    }

    @SuiteTest("/draft2020-12/ref.json")
    void refTest(String bundle, String name, JsonNode schema, JsonNode json, boolean valid) {
        testValidation(bundle, name, schema, json, valid);
    }

    @SuiteTest("/draft2020-12/refRemote.json")
    void refRemoteTest(String bundle, String name, JsonNode schema, JsonNode json, boolean valid) {
        testValidation(bundle, name, schema, json, valid);
    }

    @SuiteTest("/draft2020-12/anchor.json")
    void anchorTest(String bundle, String name, JsonNode schema, JsonNode json, boolean valid) {
        testValidation(bundle, name, schema, json, valid);
    }

    @SuiteTest("/draft2020-12/dynamicRef.json")
    void dynamicRefTest(String bundle, String name, JsonNode schema, JsonNode json, boolean valid) {
        testValidation(bundle, name, schema, json, valid);
    }

    @SuiteTest("/draft2020-12/pattern.json")
    void patternTest(String bundle, String name, JsonNode schema, JsonNode json, boolean valid) {
        testValidation(bundle, name, schema, json, valid);
    }

    @SuiteTest("/draft2020-12/maxLength.json")
    void maxLengthTest(String bundle, String name, JsonNode schema, JsonNode json, boolean valid) {
        testValidation(bundle, name, schema, json, valid);
    }

    @SuiteTest("/draft2020-12/minLength.json")
    void minLengthTest(String bundle, String name, JsonNode schema, JsonNode json, boolean valid) {
        testValidation(bundle, name, schema, json, valid);
    }

    @SuiteTest("/draft2020-12/maximum.json")
    void maximumTest(String bundle, String name, JsonNode schema, JsonNode json, boolean valid) {
        testValidation(bundle, name, schema, json, valid);
    }

    @SuiteTest("/draft2020-12/minimum.json")
    void minimumTest(String bundle, String name, JsonNode schema, JsonNode json, boolean valid) {
        testValidation(bundle, name, schema, json, valid);
    }

    @SuiteTest("/draft2020-12/exclusiveMaximum.json")
    void exclusiveMaximumTest(String bundle, String name, JsonNode schema, JsonNode json, boolean valid) {
        testValidation(bundle, name, schema, json, valid);
    }

    @SuiteTest("/draft2020-12/exclusiveMinimum.json")
    void exclusiveMinimumTest(String bundle, String name, JsonNode schema, JsonNode json, boolean valid) {
        testValidation(bundle, name, schema, json, valid);
    }

    @SuiteTest("/draft2020-12/multipleOf.json")
    void multipleOfTest(String bundle, String name, JsonNode schema, JsonNode json, boolean valid) {
        testValidation(bundle, name, schema, json, valid);
    }

    private static SchemaResolver resolver;

    @BeforeAll
    static void beforeAll() {
        Map<String, String> schemaMap = Map.ofEntries(
                Map.entry("https://json-schema.org/draft/2020-12/schema", readResource("/schemas/draft2020-12.json")),
                Map.entry("http://localhost:1234/different-id-ref-string.json", readResource("/schemas/different-id-ref-string.json")),
                Map.entry("http://localhost:1234/nested-absolute-ref-to-string.json", readResource("/schemas/nested-absolute-ref-to-string.json")),
                Map.entry("http://localhost:1234/urn-ref-string.json", readResource("/schemas/urn-ref-string.json")),
                Map.entry("http://localhost:1234/draft2020-12/extendible-dynamic-ref.json", readResource("/schemas/extendible-dynamic-ref.json")),
                Map.entry("http://localhost:1234/draft2020-12/integer.json", readResource("/schemas/integer.json")),
                Map.entry("http://localhost:1234/draft2020-12/locationIndependentIdentifier.json", readResource("/schemas/locationIndependentIdentifier.json")),
                Map.entry("http://localhost:1234/draft2020-12/name-defs.json", readResource("/schemas/name-defs.json")),
                Map.entry("http://localhost:1234/draft2020-12/ref-and-defs.json", readResource("/schemas/ref-and-defs.json")),
                Map.entry("http://localhost:1234/draft2020-12/subSchemas-defs.json", readResource("/schemas/subSchemas-defs.json")),
                Map.entry("http://localhost:1234/draft2020-12/tree.json", readResource("/schemas/tree.json")),
                Map.entry("http://localhost:1234/draft2020-12/baseUriChange/folderInteger.json", readResource("/schemas/baseUriChange/folderInteger.json")),
                Map.entry("http://localhost:1234/draft2020-12/baseUriChangeFolder/folderInteger.json", readResource("/schemas/baseUriChangeFolder/folderInteger.json")),
                Map.entry("http://localhost:1234/draft2020-12/baseUriChangeFolderInSubschema/folderInteger.json", readResource("/schemas/baseUriChangeFolderInSubschema/folderInteger.json")),
                Map.entry("http://localhost:1234/draft2020-12/nested/foo-ref-string.json", readResource("/schemas/nested/foo-ref-string.json")),
                Map.entry("http://localhost:1234/draft2020-12/nested/string.json", readResource("/schemas/nested/string.json"))
        );
        resolver = uri -> Optional.ofNullable(schemaMap.get(uri));
    }

    static String readResource(String resource) {
        try {
            return new String(SpecificationTest.class.getResourceAsStream(resource).readAllBytes());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private void testValidation(String bundle, String name, JsonNode schema, JsonNode json, boolean valid) {
//        Assumptions.assumeTrue(bundle.equals("oneOf with empty schema"));
//        Assumptions.assumeTrue(name.equals("both valid - invalid"));
        SchemaValidator validator = new SchemaValidator(new JacksonNode.Factory(), resolver);
        logger.info("%s: %s".formatted(bundle, name));
        logger.info(schema.toPrettyString());
        logger.info(json.toPrettyString());
        logger.info(String.valueOf(valid));
        URI uri = validator.registerSchema(schema);
        Assertions.assertEquals(valid, validator.validate(uri, json));
    }
}
