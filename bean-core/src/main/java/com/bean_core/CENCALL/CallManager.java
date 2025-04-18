package com.bean_core.CENCALL;

public class CallManager {
    private String contractName;;
    private String method;
    private String params;

    public CallManager(String contractName, String method, String params) {
        this.contractName = contractName;
        this.method = method;
        this.params = params;
    }


    public String getContractName() {
        return contractName;
    }

    public String getMethod() {
        return method;
    }

    public String getParams() {
        return params;
    }
    
}
