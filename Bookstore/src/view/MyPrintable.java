/**
 * 
 */
package view;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import model.Cart;
import model.ItemOrder;

/**
 * @author phamngovinhphuc
 * @version 1
 */
public final class MyPrintable implements Printable {
    /** The comma. */
    private static final String COMMA = ", ";
    
    /** Font Name. */
    private static final String FONT = "Arial";
    
    /** Font Title. */
    private static final int FONT_TITLE_SIZE = 16;
    
    /** Font Body. */
    private static final int FONT_BODY_SIZE = 10;
    
    /** The title position X. */
    private static final int TITLE_X = 250;
    
    /** The title position Y. */
    private static final int TITLE_Y = 60;
    
    /** The column title. */
    private static final int COLUMN_NO = 50;
    
    /** The column 1 . */
    private static final int COLUMN_1 = 52;
    
    /** The column 2. */
    private static final int COLUMN_ITEM = 80;
    
    /** The column 3. */
    private static final int COLUMN_QUANTITY = 400;
    
    /** The column 3. */
    private static final int COLUMN_3 = 417;
    
    /** The column 4. */
    private static final int COLUMN_TOTAL = 510;
    
    /** The column total cost. */
    private static final int COLUMN_TOTAL_COST = 455;
    
    /** The header. */
    private static final int HEADER = 140;
    
    /** The order. */
    private static final int ORDEREDDAY = 90;
    
    /** The start item. */
    private static final int BEGINNING_ITEM = 160;
    
    /** The next line. */
    private static final int NEXT_LINE = 15;
    
    /** Cart of the ordered items. */
    private final Cart myCart;
    
    /** The membership. */
    private final boolean myMembership;
   
    /**
     * The constructor my printable class.
     * @param theCart the cart.
     * @param theMembership the membership.
     */
    public MyPrintable(final Cart theCart, final boolean theMembership) { 
        myCart = theCart;
        myMembership = theMembership;
    }

    @Override
    public int print(final Graphics theGraphic, final PageFormat thePageFormat, 
                     final int thePageIndex) throws PrinterException {
        
        if (thePageIndex != 0) {
            return Printable.NO_SUCH_PAGE;  //NOPMD
        }
        
        final Graphics2D g2 = (Graphics2D) theGraphic;
        
        /**
         * Title.
         */
        g2.setFont(new Font(FONT, Font.BOLD, FONT_TITLE_SIZE));
        g2.drawString("UW Bookstore Invoice", TITLE_X, TITLE_Y);
        
        final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.US);
        final Date date = new Date();
        g2.setFont(new Font(FONT, Font.BOLD, FONT_BODY_SIZE));
        g2.drawString("Order Placed: " + dateFormat.format(date), COLUMN_NO, ORDEREDDAY);
        
        g2.drawString("No.", COLUMN_NO, HEADER);
        g2.drawString("Items Ordered" , COLUMN_ITEM, HEADER);
        g2.drawString("Quantity", COLUMN_QUANTITY, HEADER);
        g2.drawString("Price" , COLUMN_TOTAL, HEADER);
        
        g2.setFont(new Font(FONT, Font.PLAIN, FONT_BODY_SIZE)); 
        int y = BEGINNING_ITEM;
        int i = 1;
        BigDecimal total = BigDecimal.ZERO;
        total = total.setScale(2, RoundingMode.HALF_EVEN);
        for (final ItemOrder items : myCart.getMyCart()) {
            g2.drawString(i + ".", COLUMN_1, y);
            final StringBuilder builder = new StringBuilder(256);
            final NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
            builder.append(items.getMyItem().getMyItemName());
            builder.append(COMMA);
            builder.append(nf.format(items.getMyItem().getMyItemPrice()));
            if (items.getMyItem().isMyIsBulk()) {
                builder.append(" (");
                builder.append(items.getMyItem().getMyBulkQuantity());
                builder.append(" for ");
                builder.append(nf.format(items.getMyItem().getMyBulkPrice()));
                builder.append(") ");
            } 
            g2.drawString(builder.toString(), COLUMN_ITEM, y);
            g2.drawString(Integer.toString(items.getMyQuantity()), COLUMN_3, y);
            
            BigDecimal itemCost = BigDecimal.ZERO;
            
            if (this.myMembership && items.getMyItem().isMyIsBulk()) {
                final int divQuantity = items.getMyQuantity() 
                                / items.getMyItem().getMyBulkQuantity();
                final int modQuantity = items.getMyQuantity() 
                                % items.getMyItem().getMyBulkQuantity();
                if (divQuantity > 0) {
                    itemCost = itemCost.add(items.getMyItem().getMyBulkPrice().multiply(
                                                       BigDecimal.valueOf(divQuantity)));
                    total = total.add(itemCost);
                } 
                itemCost = itemCost.add(items.getMyItem().getMyItemPrice().multiply(
                                               BigDecimal.valueOf(modQuantity)));
                total = total.add(items.getMyItem().getMyItemPrice().multiply(
                                  BigDecimal.valueOf(modQuantity)));                
            } else {
                itemCost = itemCost.add(items.getMyItem().getMyItemPrice().multiply(
                                            BigDecimal.valueOf(items.getMyQuantity())));
                total = total.add(items.getMyItem().getMyItemPrice().multiply(
                              BigDecimal.valueOf(items.getMyQuantity())));
            }
            g2.drawString(itemCost.toString(), COLUMN_TOTAL, y);
            i = i + 1;
            y = y + NEXT_LINE;
        }
        g2.setFont(new Font(FONT, Font.BOLD, FONT_BODY_SIZE)); 
        g2.drawString("Total Cost: " + total.toString(), COLUMN_TOTAL_COST, y);
        
        final Rectangle2D outline = new Rectangle2D.Double(
                 thePageFormat.getImageableX(), thePageFormat.getImageableY(), 
                 thePageFormat.getImageableWidth(), thePageFormat.getImageableHeight());
        g2.draw(outline);
        return PAGE_EXISTS;
    }

}