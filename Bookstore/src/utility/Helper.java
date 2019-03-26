package utility;
/**
 * 
 */

import java.io.File;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Helper class.
 * @author phamngovinhphuc
 * @version 1
 */
public final class Helper {

    /**
     * The constructor.
     */
    private Helper() { }
    
    /**
     * Checking an exist file.
     * @param theFile the file should be checked.
     * @return true if it is correct.
     */
    public static boolean checkFileExists(final File theFile) {
        boolean check = true;
        if (theFile.isDirectory()) {
            check = false;
        } else if (!theFile.exists() || !theFile.isFile()) {
            check = false;
        }
        return check;
    }
    
    /**
     * Convert the U.S. currency.
     * @param theTotal the total BigDecimal.
     * @return the converted money.
     */
    public static String convertCurrency(final BigDecimal theTotal) {
        final NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        final double total = theTotal.doubleValue();
        return nf.format(total);
    }
}
