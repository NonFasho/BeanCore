package com.bean_core.CENdev;


import com.bean_core.TXs.TX;
import com.bean_core.Utils.hex;
import com.bean_core.crypto.TransactionVerifier;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CallManager {
    private String contractName;
    private String method;
    private String paramsRaw;
    private JsonNode fullCall;
    private JsonNode unpackedParams;

    public CallManager(String cencallJSON) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        this.fullCall = mapper.readTree(cencallJSON);

        this.contractName = fullCall.get("contract").asText();
        this.method = fullCall.get("method").asText();
        this.paramsRaw = fullCall.get("params").asText(); 
        this.unpackedParams = mapper.readTree(paramsRaw);
    }

    public String getContractName() {
        return contractName;
    }

    public String getMethod() {
        return method;
    }

    public String getParams() {
        return paramsRaw;
    }

    // debox params
    public JsonNode getParamsAsJson() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(paramsRaw);
    }

    // raw param 
    public String getContractHash() {
        return unpackedParams.get("contractHash").asText();
    }

    public String getCaller() {
        return unpackedParams.get("caller").asText();
    }

    public String getCallSignature() {
        return unpackedParams.get("callSignature").asText();
    }

    public String getCallHash() {
        return unpackedParams.get("callHash").asText();
    }

    public String getCallerPublicKey() {
        return unpackedParams.get("callerPublicKey").asText();
    }

    public String getCENIP() {
        return unpackedParams.get("cenIP").asText();
    }

    // sig verify 
    public boolean isSignatureValid() {
        byte[] hashBytes = hex.hexToBytes(getCallHash());
        try {
            return TransactionVerifier.verifySHA256Transaction(
                getCallerPublicKey(),
                hashBytes,
                getCallSignature()
            );
        } catch (Exception e) {
            return false;
        }
    }

    //custom param helpers 

    public boolean hasParam(String key) {
        return unpackedParams.has(key);
    }

    public JsonNode getParam(String key) {
        return unpackedParams.get(key);
    }

    // funded CENCALLs 

    public String getEmbeddedTXJson() {
        return unpackedParams.has("originalStakeTx") ? unpackedParams.get("tx").toString() : null; //updated for node correction 
    }

    public String getFundingHash() throws Exception{
        String txRaw = getEmbeddedTXJson();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode tx = mapper.readTree(txRaw);
        return tx.get("txHash").asText();
    }

    public TX getEmbeddedTX() throws Exception {
        String txJson = getEmbeddedTXJson();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(txJson, TX.class); 
    }


}
