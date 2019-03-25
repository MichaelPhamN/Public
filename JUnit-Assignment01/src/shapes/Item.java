package shapes;


import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

/**
 * Handling data before storing into Item database.
 * @author phamngovinhphuc
 * @version 25 Jan 2019
 */
public class Item { 
    
    /**
     * Comma character.
     */
    private static final String COMMA = ", ";
    
    /**
     * The items having discount offer.
     */
    private boolean myBulk;
    
    /** 
     * Item name. 
     */
    private final String myItemName;
    
    /**
     * Item price.
     */
    private final BigDecimal myItemPrice;
    
    
    /**
     * If the customer buy more than this quantity, they will get discount
     * since the membership checkbox is checked.
     */
    private int myBulkQuantity;
    
    /**
     * The item price after getting discount.
     */
    private BigDecimal myBuckPrice;
    
    /**
     * The constructor of item with two parameters.
     * @param theName item name.
     * @param thePrice item price.
     */
    public Item(final String theName, final BigDecimal thePrice) {  
        if (theName.length() <= 0) {
            throw new IllegalArgumentException("The input string is empty.");
        }
        
        if (theName.trim().length() <= 0) {
            throw new IllegalArgumentException("The input string is all white spaces.");
        }
        
        myItemName = Objects.requireNonNull(theName);
        myItemPrice = Objects.requireNonNull(thePrice);
        
        if (myItemPrice.compareTo(BigDecimal.ZERO) == -1) {
            throw new IllegalArgumentException("the Item price must be "
                            + "greater than or equal to 0.");
        }
    }
    
    /**
     * The constructor of item with two parameters.
     * @param theName item name.
     * @param thePrice item price.
     * @param theBulk the bulk item.
     */
    public Item(final String theName, final BigDecimal thePrice, final boolean theBulk) {  
        this(theName, thePrice);
        myBulk = theBulk;
    }

    /**
     * The constructor of item with four parameters.
     * @param theName item name.
     * @param thePrice item price.
     * @param theBulk the bulk item.
     * @param theBulkQuantity bulk quantity.
     * @param theBulkPrice bulk price.
     */
    public Item(final String theName, final BigDecimal thePrice, final boolean theBulk, 
                final int theBulkQuantity, final BigDecimal theBulkPrice) {
        this(theName, thePrice);
        
        if (theBulkQuantity < 0) {
            throw new  IllegalArgumentException("The Bulk Quantity must be greater"
                            + " than or equal to 0.");
        }
        
        if (theBulkQuantity != Math.floor(theBulkQuantity)) {
            throw new IllegalArgumentException("The Bulk Quantity must be an integer.");
        }
        
        myBulkQuantity = theBulkQuantity;
        myBuckPrice = Objects.requireNonNull(theBulkPrice);
        
        if (myBuckPrice.compareTo(BigDecimal.ZERO) == -1) {
            throw new IllegalArgumentException("the bulk price should not a negative number.");
        }        
        
        myBulk = theBulk;
    }

    /**
     * @return Item Name String.
     */
    public String getName() {
        return myItemName;
    }

    /**
     * @return Item Price.
     */
    public BigDecimal getPrice() {
        return myItemPrice;
    }
    
    /**
     * @return the quantity of Bulk.
     */
    public int getBulkQuantity() {
        return myBulkQuantity;
    }
    
    /**
     * 
     * @return the price of Bulk.
     */
    public BigDecimal getBulkPrice() {
        return myBuckPrice;
    }
    
    /**
     * 
     * @return true if checked, and false otherwise.
     */
    public boolean isBulk() {
        return myBulk;
    }

    /**
     * @return a string including item name, item price, bulk quantity or bulk price.
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(128);
        final NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        if (this.isBulk()) {
            builder.append(this.getName());
            builder.append(COMMA);
            builder.append(nf.format(this.getPrice()));
            builder.append(" (");
            builder.append(this.getBulkQuantity());
            builder.append(" for ");
            builder.append(nf.format(this.getBulkPrice()));
            builder.append(") ");
        } else {
            builder.append(this.getName());
            builder.append(COMMA);
            builder.append(nf.format(this.getPrice()));
        }
        return builder.toString();
    }

    /**
     * Checking two items.
     * @param theOther the item wants to check.
     * @return true if two items is the same.
     *         false if two items is different.
     */
    @Override
    public boolean equals(final Object theOther) { 
        boolean checkEquivalentItem = false;
        //Checking the other item is not null and the other object must be an item.
        if (theOther != null && (this.getClass() == theOther.getClass())) {
            //Casting Object -> Item.
            final Item checkItem = (Item) theOther;
            //In situation the Item offers discount.
            if (this.isBulk()) {
                if (myItemName.equalsIgnoreCase(checkItem.getName())
                                && myItemPrice.equals(checkItem.getPrice())
                                && myBulkQuantity == checkItem.getBulkQuantity()
                                && myBuckPrice.equals(checkItem.getBulkPrice())) {
                    checkEquivalentItem = true;
                }
            } else { // the item does not offer discount.
                if (myItemName.equalsIgnoreCase(checkItem.getName())
                                && myItemPrice.equals(checkItem.getPrice())) {
                    checkEquivalentItem = true;
                }
                
            }
        }
        return checkEquivalentItem;
    }

    /**
     * Create a hashCode. 
     */
    @Override
    public int hashCode() {
        final int codeItem;
        if (this.isBulk()) {
            codeItem = Objects.hash(this.getName(), this.getPrice());
        } else {
            codeItem = Objects.hash(this.getName(), this.getPrice(), 
                                    this.getBulkQuantity(), this.getBulkPrice());
        }
        return codeItem;
    }
}
