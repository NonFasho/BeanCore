package com.bean_core.Block;

public class BlockHeader {
    private String validator;
    private int height;
    private String previousHash;
    private double gasFeeReward;

    // Getters and setters
    public String getValidator() { return validator; }
    public void setValidator(String validator) { this.validator = validator; }

    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }

    public String getPreviousHash() { return previousHash; }
    public void setPreviousHash(String previousHash) { this.previousHash = previousHash; }

    public double getGasFeeReward() { return gasFeeReward; }
    public void setGasFeeReward(double gasFeeReward) { this.gasFeeReward = gasFeeReward; }
}

