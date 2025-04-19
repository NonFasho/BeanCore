package com.bean_core.Utils;

import java.util.ArrayList;
import java.util.List;

import com.bean_core.TXs.TX;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TXSorter {
    private List<TX> transferTX = new ArrayList<>();
    private List<TX> mintTX = new ArrayList<>();
    private List<TX> tokenTX = new ArrayList<>();
    private List<TX> tokenCENTX = new ArrayList<>();
    private List<TX> stakeTX = new ArrayList<>();

    public void sort(List<TX> memPool) throws Exception {
        for (TX tx : memPool) {
            String type = tx.getType();
            if (type == null) continue;

            switch (type) {
                case "transfer":
                    transferTX.add(tx);
                    break;
                case "mint":
                    mintTX.add(tx);
                    break;
                case "token":
                    JsonNode metaNode = getMetaNode(tx);
                    if (metaNode.has("isCEN") && metaNode.get("isCEN").asBoolean()) {
                        tokenCENTX.add(tx);
                    } else {
                        tokenTX.add(tx);
                    }
                    break;
                case "stake":
                    stakeTX.add(tx);
                    break;
                default:
                    System.out.println("ERROR: Unrecognized TX type for hash: " + tx.getTxHash());
                    break;
            }
        }
    }

    private JsonNode getMetaNode(TX tx) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(tx.getMeta());
    }

    public List<TX> getTransferTX() { return transferTX; }
    public List<TX> getMintTX() { return mintTX; }
    public List<TX> getTokenTX() { return tokenTX; }
    public List<TX> getTokenCENTX() { return tokenCENTX; }
    public List<TX> getStakeTX() { return stakeTX; }
}
