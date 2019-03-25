package utility;
/**
 * 
 */

import java.io.File;

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
}
