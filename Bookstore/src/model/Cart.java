/**
 * 
 */
package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * @author phamngovinhphuc
 * @version 1
 */
public class Cart {
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
     * set membership from bookstore frame.
     * @param theMembership the membership.
     */
    public void setMembership(final boolean theMembership) {
        myMembership = theMembership;
    }
    
    /**
     * @return get membership.
     */
    public boolean isMembership() {
        return myMembership;
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
        BigDecimal total = new BigDecimal("0.00");
        total = total.setScale(2, RoundingMode.HALF_EVEN);        
        for (final ItemOrder items : myCart) {
            if (items.getMyItem().isMyIsBulk()) {
                if (this.isMembership()) {
                    final int divQuantity = items.getMyQuantity() 
                                    / items.getMyItem().getMyBulkQuantity();
                    final int modQuantity = items.getMyQuantity() 
                                    % items.getMyItem().getMyBulkQuantity();
                    if (divQuantity > 0) {
                        total = total.add(items.getMyItem().getMyBulkPrice().multiply(
                                      BigDecimal.valueOf(divQuantity)));
                    } 
                    total = total.add(items.getMyItem().getMyItemPrice().multiply(
                                      BigDecimal.valueOf(modQuantity)));
                } else {
                    total = total.add(items.getMyItem().getMyItemPrice().multiply(
                                  BigDecimal.valueOf(items.getMyQuantity())));
                }                                    
            } else {
                total = total.add(items.getMyItem().getMyItemPrice().multiply(
                                  BigDecimal.valueOf(items.getMyQuantity())));
            }                
        }
        return total;
    }
}
