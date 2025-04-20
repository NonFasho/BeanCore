package com.bean_core.Models;

import com.bean_core.Utils.ParamBuilder;
import com.bean_core.Utils.beantoshinomics;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TokenStorage {
    private String tokenHash;
    private String tokenData;
    @JsonIgnore
    private ParamBuilder paramBuilder = new ParamBuilder();

    public String getTokenHash() { return tokenHash;}
    public String getTokenData() { return tokenData;}

    public TokenStorage(String tokenHash, String token, String symbol, double supply, String minter, boolean mintable, boolean open) {
        this.tokenHash = tokenHash;
        long storedToshiSupply = beantoshinomics.toBeantoshi(supply);
        this.tokenData = paramBuilder
                                .add("token", token)
                                .add("symbol", symbol)
                                .add("supply", storedToshiSupply)
                                .add("minter", minter)
                                .add("mintable", mintable)
                                .add("open", open)
                                .build();

    }

    public JsonNode getTokenMetaAsJson() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(this.tokenData);
    }

}
