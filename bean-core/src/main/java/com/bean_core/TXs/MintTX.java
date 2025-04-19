package com.bean_core.TXs;

import com.bean_core.Utils.ParamBuilder;
import com.bean_core.Utils.beantoshinomics;

public class MintTX extends TX {
    private static final long mintFee = 1;
    private ParamBuilder paramBuilder = new ParamBuilder();

    public MintTX() {
        this.setType("mint");
    }

    public MintTX(String from, String publicKeyHex, String to, double amount, int nonce, String token, double supply) {
        this.setFrom(from);
        this.setPublicKeyHex(publicKeyHex);
        this.setTo(to);
        this.setAmount(amount);
        this.setNonce(nonce);
        this.setTimeStamp(System.currentTimeMillis());
        this.setType("mint");
        this.setGasFee(beantoshinomics.toBeantoshi(supply) * mintFee);
        this.setTxHash(this.generateHash());

        this.paramBuilder
            .add("mode", "create")
            .add("token", token)
            .add("supply", supply)
            .add("capped", false)
            .add("openMint", false);
    }

    public MintTX(String from, String publicKeyHex, String to, double amount, int nonce, String tokenHash) {
        this.setFrom(from);
        this.setPublicKeyHex(publicKeyHex);
        this.setTo(to);
        this.setAmount(amount);
        this.setNonce(nonce);
        this.setTimeStamp(System.currentTimeMillis());
        this.setType("mint");
        this.setGasFee(beantoshinomics.toBeantoshi(amount) * mintFee);
        this.setTxHash(this.generateHash());

        this.paramBuilder
            .add("mode", "mintMore")
            .add("tokenHash", tokenHash)
            .add("amount", amount);
    }

    public void capSupply() {
        this.paramBuilder.add("capped", true);
    }

    public void openMint() {
        this.paramBuilder.add("openMint", true);
    }

    public void finalizeParams() {
        if (this.getMeta() == null) {
            this.setMeta(this.paramBuilder.build());
        }
    }
}
