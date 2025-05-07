package com.bean_core.CENdev;

import com.bean_core.TXs.CENCALL;

/**
 * The {@code BaseContract} interface defines the standard structure for all 
 * Java-based smart contracts executed by a Contract Execution Node (CEN) 
 * in the BeanChain ecosystem.
 * 
 * <p>All Java contracts must implement this interface and override the 
 * {@link #execute(CENCALL)} method, which is called by the CEN runtime when a user 
 * or system triggers the contract via a {@code CENCALL}.
 * 
 * <h3>Usage Example:</h3>
 * <pre>{@code
 * public class MyContract implements BaseContract {
 *     @Override
 *     public void execute(CENCALL call) {
 *         String method = call.getMethod();
 *         if ("mintToken".equals(method)) {
 *             // Contract logic here
 *             System.out.println("Token minted by " + call.getCaller());
 *         }
 *     }
 * }
 * }</pre>
 * 
 * <p><strong>Note:</strong> Java smart contracts on BeanChain are executed 
 * by a Contract Execution Node (CEN). Contracts may use dynamic logic 
 * and external inputs, but must ensure that any resulting transactions are 
 * properly formed and valid as BeanChain transaction classes (e.g., {@code TX}, {@code MintTX}, {@code TokenTX}).
 * 
 * <p>While these transactions may follow a different verification path than direct 
 * user-submitted transactions, they are still subject to full validation by the network 
 * before inclusion in a block.
 */
public interface BaseContract {

    void execute(CENCALL call);
    
}
