package com.bean_core.Models;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Layer2Wallet {
    private String address;
    private int l2Nonce;
    private Map<String, Integer> tokenBalances;

    // Required no-arg constructor for Jackson
    public Layer2Wallet() {
        this.tokenBalances = new HashMap<>();
    }

    public Layer2Wallet(String address) {
        this.address = address;
        this.l2Nonce = 0;
        this.tokenBalances = new HashMap<>();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getL2Nonce() {
        return l2Nonce;
    }

    public void setL2Nonce(int l2Nonce) {
        this.l2Nonce = l2Nonce;
    }

    public Map<String, Integer> getTokenBalances() {
        return tokenBalances;
    }

    public void setTokenBalances(Map<String, Integer> tokenBalances) {
        this.tokenBalances = tokenBalances;
    }

    public void incrementNonce() {
        this.l2Nonce++;
    }

    public int getBalance(String tokenHash) {
        return tokenBalances.getOrDefault(tokenHash, 0);
    }

    public void setBalance(String tokenHash, int amount) {
        tokenBalances.put(tokenHash, amount);
    }

    public void adjustBalance(String tokenHash, int delta) {
        int current = getBalance(tokenHash);
        tokenBalances.put(tokenHash, current + delta);
    }
}

