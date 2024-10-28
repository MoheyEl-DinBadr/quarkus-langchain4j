package io.quarkiverse.langchain4j.openai;

import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.model.openai.spi.OpenAiStreamingChatModelBuilderFactory;
import io.quarkiverse.langchain4j.openai.common.runtime.AdditionalPropertiesHack;

public class QuarkusOpenAiStreamingChatModelBuilderFactory implements OpenAiStreamingChatModelBuilderFactory {

    @Override
    public OpenAiStreamingChatModel.OpenAiStreamingChatModelBuilder get() {
        return new Builder();
    }

    public static class Builder extends OpenAiStreamingChatModel.OpenAiStreamingChatModelBuilder {

        private String tlsConfigurationName;

        public Builder tlsConfigurationName(String tlsConfigurationName) {
            this.tlsConfigurationName = tlsConfigurationName;
            return this;
        }

        @Override
        public OpenAiStreamingChatModel build() {
            AdditionalPropertiesHack.setTlsConfigurationName(tlsConfigurationName);
            return super.build();
        }
    }
}