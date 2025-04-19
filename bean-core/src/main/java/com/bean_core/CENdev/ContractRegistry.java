package com.bean_core.CENdev;

import java.util.HashMap;
import java.util.Map;

public class ContractRegistry {
    private static final Map<String, BaseContract> contracts = new HashMap<>();

    public static void register(String name, BaseContract contract) {
        contracts.put(name, contract);
    }

    public static BaseContract get(String name) {
        return contracts.get(name);
    }

    public static boolean exists(String name) {
        return contracts.containsKey(name);
    }

    // **USE**  ContractRegistry.register("EXAMPLEContract", new EXAMPLEContract());
}
