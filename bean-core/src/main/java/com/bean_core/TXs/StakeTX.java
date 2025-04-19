package com.bean_core.TXs;

import com.bean_core.Utils.ParamBuilder;

public class StakeTX extends TX {
    private ParamBuilder paramBuilder = new ParamBuilder();

    public StakeTX() {
        this.setType("stake");
    }

    public StakeTX(String from, String publicKeyHex, String to, double amount, int nonce, String mode, String tokenHash, long gasFee) {
        this.setFrom(from);
        this.setPublicKeyHex(publicKeyHex);
        this.setTo(to);
        this.setAmount(amount);
        this.setNonce(nonce);
        this.setTimeStamp(System.currentTimeMillis());
        this.setType("stake");
        this.setGasFee(gasFee);

        this.paramBuilder.add("mode", mode);

        if (tokenHash != null) {
            this.paramBuilder.add("tokenHash", tokenHash);
        }

        this.setMeta(this.paramBuilder.build());
        this.setTxHash(this.generateHash()); 
    }
}
