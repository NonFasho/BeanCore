package com.bean_core.CENdev;

import com.bean_core.TXs.CENCALL;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BaseContractTest {

    static class DummyContract implements BaseContract {
        boolean called = false;
        String methodReceived = null;

        @Override
        public void execute(CENCALL call) {
            called = true;
            methodReceived = call.getMethod();
        }
    }

    @Test
    public void testExecuteMethodIsCalled() {
        DummyContract contract = new DummyContract();

        // Create a dummy CENCALL
        CENCALL call = new CENCALL();
        call.setMethod("testMethod");

        contract.execute(call);

        // Assertions
        assertTrue(contract.called, "execute(...) should have been called.");
        assertEquals("testMethod", contract.methodReceived, "Method name should match the test input.");
    }
}
