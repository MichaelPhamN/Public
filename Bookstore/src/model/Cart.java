/**
 * 
 */
package model;

import static java.math.BigDecimal.ROUND_HALF_UP;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * @author phamngovinhphuc
 * @version 1
 */
public class Cart implements Comparable<ItemOrder> {
    /**
     * A List Item Order.
     */
    private final List<ItemOrder> myCart;
    
    /**
     * Membership.
     */
    private boolean myMembership;
    
    /**
     * Constructor that creates an empty shopping cart.
     */
    public Cart() {
        myCart = new ArrayList<ItemOrder>();
    }
    
    /**
     * Add the ordered item into cart.
     * @param theItemOrder the item order.
     */
    public void add(final ItemOrder theItemOrder) {
        myCart.add(theItemOrder);
    }
    
    /**
     * set membership.
     * @param theMembership the membership.
     */
    public void setMembership(final boolean theMembership) {
        myMembership = theMembership;
    }
    
    /**
     * Clear the cart.
     */
    public void clear() {
        myCart.clear();
    }
    
    /**
     * Calculate the total.
     * @return the total cost.
     */
    public BigDecimal calculateTotal() {
        BigDecimal total = new BigDecimal(0);
        total = total.setScale(2, RoundingMode.HALF_EVEN);
        
        
        
        return total;
    }
    

    @Override
    public int compareTo(ItemOrder o) {
        // TODO Auto-generated method stub
        return 0;
    }
    
    
}
