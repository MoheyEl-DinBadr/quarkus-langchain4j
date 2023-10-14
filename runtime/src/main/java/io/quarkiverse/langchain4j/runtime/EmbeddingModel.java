package io.quarkiverse.langchain4j.runtime;

class EmbeddingModel {

    private ModelProvider provider;
    private OpenAi openAi;
    private HuggingFace huggingFace;
    private LocalAi localAi;

    public ModelProvider getProvider() {
        return provider;
    }

    public void setProvider(ModelProvider provider) {
        this.provider = provider;
    }

    public OpenAi getOpenAi() {
        return openAi;
    }

    public void setOpenAi(OpenAi openAi) {
        this.openAi = openAi;
    }

    public HuggingFace getHuggingFace() {
        return huggingFace;
    }

    public void setHuggingFace(HuggingFace huggingFace) {
        this.huggingFace = huggingFace;
    }

    public LocalAi getLocalAi() {
        return localAi;
    }

    public void setLocalAi(LocalAi localAi) {
        this.localAi = localAi;
    }
}