package com.bean_core.TXs;

import com.bean_core.Utils.ParamBuilder;


public class TokenTX extends TX {
    private ParamBuilder paramBuilder = new ParamBuilder();

    public TokenTX() {
        this.setType("token");
    }

    public TokenTX(String from, String publicKeyHex, String to, double amount, int layer2Nonce, String tokenHash, long gasFee) {
        this.setFrom(from);
        this.setPublicKeyHex(publicKeyHex);
        this.setTo(to);
        this.setAmount(amount);
        this.setNonce(layer2Nonce);
        this.setTimeStamp(System.currentTimeMillis());
        this.setType("token");
        this.setGasFee(gasFee);

        this.paramBuilder
            .add("tokenHash", tokenHash)
            .add("amount", amount);

        this.setMeta(this.paramBuilder.build());
        this.setTxHash(this.generateHash()); 
    }

    public TokenTX(String CEN, String CENpublicKeyHex, String to, double amount, int CENnonce, String tokenHash, long gasFee, String caller, String callHash, String callerPublicKey, String callSignature, int callerLayer2Nonce) {
        this.setFrom(CEN);
        this.setPublicKeyHex(CENpublicKeyHex);
        this.setTo(to);
        this.setAmount(amount);
        this.setNonce(CENnonce);
        this.setTimeStamp(System.currentTimeMillis());
        this.setType("token");
        this.setGasFee(gasFee);

        this.paramBuilder
            .add("isCEN", true)
            .add("caller", caller)
            .add("callHash", callHash)
            .add("callerPublicKey", callerPublicKey)
            .add("callerLayer2Nonce", callerLayer2Nonce)
            .add("callSignature", callSignature)
            .add("tokenHash", tokenHash)
            .add("amount", amount);

        this.setMeta(this.paramBuilder.build());
        this.setTxHash(this.generateHash()); 
    }

    public TokenTX addParam(String key, boolean value) {
        this.paramBuilder.add(key, value);
        return this;
    }

    public void finalizeParams() {
        if (this.getMeta() == null) {
            this.setMeta(this.paramBuilder.build());
            this.setTxHash(this.generateHash()); // Refresh hash if meta was changed
        }
    }
}
