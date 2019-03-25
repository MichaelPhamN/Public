/**
 * 
 */
package model;

import java.util.Objects;

/**
 * @author phamngovinhphuc
 * @version 1
 */
public class ItemOrder {
    /** The item. */
    private final Item myItem;
    
    /** The item quantity. */
    private final int myQuantity;
    /**
     * The item order constructor.
     * @param theItem the item.
     * @param theQuantity its quantity.
     */
    public ItemOrder(final Item theItem, final int theQuantity) {
        myItem = Objects.requireNonNull(theItem);
        if (theQuantity < 0) {
            throw new IllegalArgumentException("The item quantity "
                            + "cannot be a negative number.");
        }
        myQuantity = theQuantity;
    }
    
    /**
     * @return the item.
     */
    public Item getMyItem() {
        return myItem;
    }
    
    /**
     * @return the item quantity.
     */
    public int getMyQuantity() {
        return myQuantity;
    }

    @Override
    public String toString() {
        return "ItemOrder [myItem=" + myItem + ", myQuantity=" + myQuantity + "]";
    }
}
