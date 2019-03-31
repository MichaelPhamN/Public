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
     * The total cost.
     */
    private BigDecimal myTotal;
    
    /**
     * Constructor that creates an empty shopping cart.
     */
    public Cart() {
        myCart = new ArrayList<ItemOrder>();
        myTotal = new BigDecimal("0.00");
    }
    
    /**
     * @return get list items in cart.
     */
    public List<ItemOrder> getMyCart() {
        return myCart;
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
        myTotal = myTotal.setScale(2, RoundingMode.HALF_EVEN);        
        for (final ItemOrder items : myCart) {
            if (this.isMembership() && items.getMyItem().isMyIsBulk()) {
                final int divQuantity = items.getMyQuantity() 
                                / items.getMyItem().getMyBulkQuantity();
                final int modQuantity = items.getMyQuantity() 
                                % items.getMyItem().getMyBulkQuantity();
                if (divQuantity > 0) {
                    myTotal = myTotal.add(items.getMyItem().getMyBulkPrice().multiply(
                                  BigDecimal.valueOf(divQuantity)));
                } 
                myTotal = myTotal.add(items.getMyItem().getMyItemPrice().multiply(
                                  BigDecimal.valueOf(modQuantity)));
            } else {
                myTotal = myTotal.add(items.getMyItem().getMyItemPrice().multiply(
                              BigDecimal.valueOf(items.getMyQuantity())));
            }                                    
        }
        return myTotal;
    }
    
    /**
     * a string including all item name and its quantity in a cart. 
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(128);
        builder.append("Items in the cart: ");
        if (this.getMyCart() == null) {
            builder.append(" no item");
        } else {
            this.getMyCart().forEach(item -> {
                builder.append(item.getMyItem().getMyItemName());
                builder.append(", ");
                builder.append(item.getMyQuantity());
            });     
        }
        return builder.toString();
    }
}
