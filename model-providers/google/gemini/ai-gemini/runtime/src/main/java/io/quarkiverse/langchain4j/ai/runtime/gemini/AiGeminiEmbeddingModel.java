package io.quarkiverse.langchain4j.ai.runtime.gemini;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.jboss.resteasy.reactive.client.api.LoggingScope;

import io.quarkiverse.langchain4j.ai.runtime.gemini.AiGeminiChatLanguageModel.Builder;
import io.quarkiverse.langchain4j.gemini.common.EmbedContentRequest;
import io.quarkiverse.langchain4j.gemini.common.EmbedContentRequests;
import io.quarkiverse.langchain4j.gemini.common.EmbedContentResponse;
import io.quarkiverse.langchain4j.gemini.common.EmbedContentResponses;
import io.quarkiverse.langchain4j.gemini.common.GeminiEmbeddingModel;
import io.quarkiverse.langchain4j.gemini.common.ModelAuthProviderFilter;
import io.quarkus.rest.client.reactive.QuarkusRestClientBuilder;

public class AiGeminiEmbeddingModel extends GeminiEmbeddingModel {

    private final AiGeminiRestApi restApi;
    private final AiGeminiRestApi.ApiMetadata apiMetadata;

    public AiGeminiEmbeddingModel(Builder builder) {
        super(builder.modelId, builder.dimension, builder.taskType);
        this.apiMetadata = AiGeminiRestApi.ApiMetadata
                .builder()
                .modelId(builder.modelId)
                .key(builder.key)
                .build();

        try {
            String baseUrl = builder.baseUrl.orElse("https://generativelanguage.googleapis.com");
            var restApiBuilder = QuarkusRestClientBuilder.newBuilder()
                    .baseUri(new URI(baseUrl))
                    .connectTimeout(builder.timeout.toSeconds(), TimeUnit.SECONDS)
                    .readTimeout(builder.timeout.toSeconds(), TimeUnit.SECONDS);

            if (builder.logRequests || builder.logResponses) {
                restApiBuilder.loggingScope(LoggingScope.REQUEST_RESPONSE);
                restApiBuilder.clientLogger(new AiGeminiRestApi.AiClientLogger(builder.logRequests,
                        builder.logResponses));
            }
            if (builder.key == null) {
                restApiBuilder.register(new ModelAuthProviderFilter(builder.modelId));
            }
            restApi = restApiBuilder.build(AiGeminiRestApi.class);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected EmbedContentResponse embedContent(EmbedContentRequest embedContentRequest) {
        return restApi.embedContent(embedContentRequest, apiMetadata);
    }

    @Override
    protected EmbedContentResponses batchEmbedContents(EmbedContentRequests embedContentRequests) {
        return restApi.batchEmbedContents(embedContentRequests, apiMetadata);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String configName;
        private Optional<String> baseUrl = Optional.empty();
        private String modelId;
        private String key;
        private Integer dimension;
        private String taskType;
        private Duration timeout = Duration.ofSeconds(10);
        private Boolean logRequests = false;
        private Boolean logResponses = false;

        public Builder configName(String configName) {
            this.configName = configName;
            return this;
        }

        public Builder baseUrl(Optional<String> baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder key(String key) {
            this.key = key;
            return this;
        }

        public Builder modelId(String modelId) {
            this.modelId = modelId;
            return this;
        }

        public Builder dimension(Integer dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder taskType(String taskType) {
            this.taskType = taskType;
            return this;
        }

        public Builder timeout(Duration timeout) {
            this.timeout = timeout;
            return this;
        }

        public Builder logRequests(boolean logRequests) {
            this.logRequests = logRequests;
            return this;
        }

        public Builder logResponses(boolean logResponses) {
            this.logResponses = logResponses;
            return this;
        }

        public AiGeminiEmbeddingModel build() {
            return new AiGeminiEmbeddingModel(this);
        }
    }

}
