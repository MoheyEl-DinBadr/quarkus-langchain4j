package io.quarkiverse.langchain4j.mongodb.deployment;

import java.util.Map;

import io.smallrye.config.PropertiesConfigSource;
import io.smallrye.config.SmallRyeConfigBuilder;
import io.smallrye.config.SmallRyeConfigBuilderCustomizer;

/**
 * Ensure we set the Mongo stack image name so users don't have to
 */
public class DevServicesConfigBuilderCustomizer implements SmallRyeConfigBuilderCustomizer {
    @Override
    public void configBuilder(final SmallRyeConfigBuilder builder) {
        builder.withSources(
                new PropertiesConfigSource(
                        Map.of("quarkus.mongodb.devservices.image-name",
                                "moheyeldinbadr/mongodb-community-search:8.2.6-rc0-ubuntu2204-slim-20260318T063436Z"),
                        "quarkus-langchain4j-mongodb", 2000));
    }
}
