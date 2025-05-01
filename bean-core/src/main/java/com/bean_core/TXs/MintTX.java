package com.bean_core.TXs;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import com.bean_core.Utils.ParamBuilder;
import com.bean_core.Utils.beantoshinomics;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MintTX extends TX {
    private static final long mintFee = 1;
    private ParamBuilder paramBuilder = new ParamBuilder();

    public MintTX() {
        this.setType("mint");
    }

    public MintTX(String from, String publicKeyHex, String to, double amount, int nonce, String token, double supply, String symbol) {
        this.setFrom(from);
        this.setPublicKeyHex(publicKeyHex);
        this.setTo(to);
        this.setAmount(amount);
        this.setNonce(nonce);
        this.setTimeStamp(System.currentTimeMillis());
        this.setType("mint");
        this.setGasFee(beantoshinomics.toBeantoshi(supply) * mintFee);
        this.setTxHash(this.generateHash());

        String tokenHash = generateHash(from, token, Double.toString(supply), symbol);

        this.paramBuilder
            .add("mode", "create")
            .add("token", token)
            .add("symbol", symbol)
            .add("tokenHash", tokenHash)
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

    //working on NFT set up 
    // public MintTX(String from, String publicKeyHex, String to, String tokenId, int nonce, String tokenHash, long gasFee, String meta) {
    //     this.setFrom(from);
    //     this.setPublicKeyHex(publicKeyHex);
    //     this.setTo(to);
    //     this.setAmount(1);
    //     this.setNonce(nonce);
    //     this.setTimeStamp(System.currentTimeMillis());
    //     this.setType("mint");
    //     this.setGasFee(gasFee);
    //     this.setTxHash(this.generateHash());

    //     this.paramBuilder
    //         .add("mode", "NFT")
    //         .add("tokenHash", tokenHash)
    //         .add("tokenId", tokenId);
    //         .add("meta",)
    // }

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

    public String generateHash(String minter, String token, String supply, String symbol){
        
        try {
            String data = minter + token + supply + symbol; //uses original supply at mint 
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();
            for(byte b: hash){
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getTokenHash() throws Exception {
        return new ObjectMapper().readTree(this.getMeta()).get("tokenHash").asText();
    }
}
