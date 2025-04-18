package com.bean_core.CENCALL;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class stakeCallManager {
    private final String contractName = "ValidatorStake";
    private CallManager manager;
    
    public stakeCallManager(String method, String staker, String stakerPublicKeyHex, long stakeInBeantoshi, String hash, String signature, long gasFee) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode params = mapper.createObjectNode();
        params.put("staker", staker);
        params.put("stakerPubKeyHex", stakerPublicKeyHex);
        params.put("stake", stakeInBeantoshi);
        params.put("hash", hash);
        params.put("signature", signature);
        params.put("gasFee", gasFee);

        String jsonParams = mapper.writeValueAsString(params);
        this.manager = new CallManager(this.contractName, method, jsonParams);
    }

    public CallManager getCallManager() {
        return manager;
    }
    
}
