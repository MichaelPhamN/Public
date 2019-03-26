/**
 * 
 */
package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

/**
 * @author phamngovinhphuc
 * @version 1
 */
public final class MyPrintable implements Printable {
    /**
     * The constructor my printable class.
     */
    public MyPrintable() { }

    @Override
    public int print(final Graphics theGraphic, final PageFormat thePageFormat, 
                     final int thePageIndex) throws PrinterException {
        if (thePageIndex != 0) {
            return NO_SUCH_PAGE;
        }
        final Graphics2D g2 = (Graphics2D) theGraphic;
        g2.setFont(new Font("Serif", Font.PLAIN, 36));
        g2.setPaint(Color.black);
        g2.drawString("www.java2s.com", 50, 70);
        final Rectangle2D outline = new Rectangle2D.Double(
                 thePageFormat.getImageableX(), thePageFormat.getImageableY(), 
                 thePageFormat.getImageableWidth(), thePageFormat.getImageableHeight());
        g2.draw(outline);
        return PAGE_EXISTS;
    }

}
