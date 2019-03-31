/**
 * 
 */
package model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

/**
 * @author phamngovinhphuc
 * @version 1
 */
public class Item implements Comparable<Item> {
    /**
     * The comma.
     */
    private static final String COMMA = ", ";
    /**
     * The Item Name.
     */
    private final String myItemName;

    /**
     * The Item Price.
     */
    private final BigDecimal myItemPrice;
    
    /**
     * The Bulk Quantity.
     */
    private int myBulkQuantity;
    
    /**
     * The Bulk Price.
     */
    private BigDecimal myBulkPrice;
    
    /**
     * The Item is bulk.
     */
    private boolean myIsBulk;
    
    /**
     * Item constructor.
     * @param theItemName the item name.
     * @param theItemPrice the item price.
     */
    public Item(final String theItemName, final BigDecimal theItemPrice) {
        myItemName = theItemName;
        
        if (theItemPrice.compareTo(BigDecimal.ZERO) == -1) {
            throw new IllegalArgumentException("The item price must be a positive number.");
        }
        myItemPrice = theItemPrice;
        
        myIsBulk = false;
    }
    
    /**
     * Item constructor.
     * @param theItemName the item name.
     * @param theItemPrice the item price.
     * @param theBulkQuantity the bulk quantity.
     * @param theBulkPrice the bulk price.
     */
    public Item(final String theItemName, final BigDecimal theItemPrice, 
                final int theBulkQuantity, final BigDecimal theBulkPrice) {
        this(theItemName, theItemPrice);
        
        if (theBulkQuantity < 0) {
            throw new IllegalArgumentException("The bulk quantity must be a positive number.");
        }
        
        myBulkQuantity = theBulkQuantity;
        
        if (theBulkPrice.compareTo(BigDecimal.ONE) == -1) {
            throw new IllegalArgumentException("The bulk price must be a positive number.");
        }
        myBulkPrice = theBulkPrice;
        
        myIsBulk = true;        
    }
    
    /**
     * @return the bulk quantity.
     */
    public int getMyBulkQuantity() {
        return myBulkQuantity;
    }

    /**
     * Set the bulk quantity.
     * @param theBulkQuantity the bulk quantity.
     */
    public void setMyBulkQuantity(final int theBulkQuantity) {
        myBulkQuantity = theBulkQuantity;
    }

    /**
     * @return the bulk price.
     */
    public BigDecimal getMyBulkPrice() {
        return myBulkPrice;
    }

    /**
     * Set the bulk price.
     * @param theBulkPrice the bulk price.
     */
    public void setMyBulkPrice(final BigDecimal theBulkPrice) {
        myBulkPrice = theBulkPrice;
    }

    /**
     * @return checking the item is bulk.
     */
    public boolean isMyIsBulk() {
        return myIsBulk;
    }

    /**
     * Set true if the item is bulk.
     * @param theIsBulk the bulk.
     */
    public void setMyIsBulk(final boolean theIsBulk) {
        myIsBulk = theIsBulk;
    }

    /**
     * @return item name.
     */
    public String getMyItemName() {
        return myItemName;
    }

    /**
     * @return item price.
     */
    public BigDecimal getMyItemPrice() {
        return myItemPrice;
    }
    
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(128);
        final NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        if (this.isMyIsBulk()) {
            builder.append(this.getMyItemName());
            builder.append(COMMA);
            builder.append(nf.format(this.getMyItemPrice()));
            builder.append(" (");
            builder.append(this.getMyBulkQuantity());
            builder.append(" for ");
            builder.append(nf.format(this.getMyBulkPrice()));
            builder.append(") ");
        } else {
            builder.append(this.getMyItemName());
            builder.append(COMMA);
            builder.append(nf.format(this.getMyItemPrice()));
        }
        return builder.toString();
    }
    
    

    @Override
    public int hashCode() {
        int hashCode = 31;
        if (this.isMyIsBulk()) {
            hashCode = hashCode + Objects.hash(this.myItemName, this.myItemPrice, 
                                           this.getMyBulkQuantity(), this.getMyBulkPrice());
        } else {
            hashCode = hashCode + Objects.hash(this.myItemName, this.myItemPrice);
        }
        return hashCode;
    }

    @Override
    public boolean equals(final Object theOther) {
        boolean checkEquivalentItem = false;
        if (theOther != null && (this.getClass() == theOther.getClass())) {
            final Item checkItem = (Item) theOther;
            if (this.isMyIsBulk()) {
                if (this.getMyItemName().equalsIgnoreCase(checkItem.getMyItemName())
                                && this.getMyItemPrice().equals(checkItem.getMyItemPrice())
                                && this.getMyBulkPrice().equals(checkItem.getMyBulkPrice())
                                && this.getMyBulkQuantity() == checkItem.getMyBulkQuantity()) {
                    checkEquivalentItem = true;
                }
            } else {
                if (this.getMyItemName().equalsIgnoreCase(checkItem.getMyItemName())
                                && this.getMyItemPrice().equals(checkItem.getMyItemPrice())) {
                    checkEquivalentItem = true;
                }
                
            }
        }
        return checkEquivalentItem;
    }

    @Override
    public int compareTo(final Item theOther) {
        return this.getMyItemPrice().compareTo(theOther.getMyItemPrice());
    }

}
