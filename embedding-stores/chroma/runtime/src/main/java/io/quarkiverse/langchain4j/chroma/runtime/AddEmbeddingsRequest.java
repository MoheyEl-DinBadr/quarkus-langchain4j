package io.quarkiverse.langchain4j.chroma.runtime;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class AddEmbeddingsRequest {

    private final List<String> ids;
    private final List<float[]> embeddings;
    private final List<String> documents;
    private final List<Map<String, Object>> metadatas;

    public AddEmbeddingsRequest(Builder builder) {
        this.ids = builder.ids;
        this.embeddings = builder.embeddings;
        this.documents = builder.documents;
        this.metadatas = builder.metadatas;
    }

    public List<String> getIds() {
        return ids;
    }

    public List<float[]> getEmbeddings() {
        return embeddings;
    }

    public List<String> getDocuments() {
        return documents;
    }

    public List<Map<String, Object>> getMetadatas() {
        return metadatas;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private List<String> ids = new ArrayList<>();
        private List<float[]> embeddings = new ArrayList<>();
        private List<String> documents = new ArrayList<>();
        private List<Map<String, Object>> metadatas = new ArrayList<>();

        public Builder ids(List<String> ids) {
            if (ids != null) {
                this.ids = ids;
            }
            return this;
        }

        public Builder embeddings(List<float[]> embeddings) {
            if (embeddings != null) {
                this.embeddings = embeddings;
            }
            return this;
        }

        public Builder documents(List<String> documents) {
            if (documents != null) {
                this.documents = documents;
            }
            return this;
        }

        public Builder metadatas(List<Map<String, Object>> metadatas) {
            if (metadatas != null) {
                this.metadatas = metadatas;
            }
            return this;
        }

        public AddEmbeddingsRequest build() {
            return new AddEmbeddingsRequest(this);
        }
    }
}
