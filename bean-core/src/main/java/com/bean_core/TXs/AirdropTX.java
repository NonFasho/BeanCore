package com.bean_core.TXs;

import com.bean_core.Utils.ParamBuilder;

public class AirdropTX extends TX {
    private ParamBuilder paramBuilder = new ParamBuilder();

    // For BEAN airdrops (native)
    public AirdropTX(String from, String pubKey, String to, double amount, int nonce, long gasFee) {
        this.setFrom(from);
        this.setPublicKeyHex(pubKey);
        this.setTo(to);
        this.setAmount(amount);
        this.setNonce(nonce);
        this.setTimeStamp(System.currentTimeMillis());
        this.setType("airdrop");
        this.setGasFee(gasFee);

        this.paramBuilder.add("isToken", false);
        this.setMeta(this.paramBuilder.build());
        this.setTxHash(this.generateHash());
    }

    // For token airdrops (delegated via RN logic)
    public AirdropTX(String from, String pubKey, String to, String tokenHash, double tokenAmount, int nonce, long gasFee) {
        this.setFrom(from);
        this.setPublicKeyHex(pubKey);
        this.setTo(to);
        this.setAmount(0); // no BEAN 
        this.setNonce(nonce);
        this.setTimeStamp(System.currentTimeMillis());
        this.setType("airdrop");
        this.setGasFee(gasFee);

        this.paramBuilder
            .add("isToken", true)
            .add("tokenHash", tokenHash)
            .add("tokenAmount", tokenAmount);

        this.setMeta(this.paramBuilder.build());
        this.setTxHash(this.generateHash());
    }
}
