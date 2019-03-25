/**
 * 
 */
package utility;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Item;

/**
 * Read File.
 * @author phamngovinhphuc
 * @version 1
 */
public final class FileLoader {
    /**
     * The file doesn't exit.
     */
    public static final String FILE_NOT_FOUND = "The file doesn't exits.";
    
    /**
     * A private constructor.
     */
    private FileLoader() { }
    
    /**
     * Read data of items_order.txt in the files directory.
     * @param theFileName the file directory.
     * @return a list of item.
     * @throws IOException throws exception if the file doesn't exist.
     */
    public static List<Item> readData(final String theFileName) throws IOException {
        final List<Item> items = new ArrayList<Item>();
        
        /**
         * Checking whether the file exists.
         */
        final File file = new File(theFileName);
        if (!Helper.checkFileExists(file)) {
            throw new IOException(FILE_NOT_FOUND);
        }
        
        final Scanner input = new Scanner(file);
        while (input.hasNextLine()) {
            /**
             * There are two ways to read data.
             */            
            final Scanner line = new Scanner(input.nextLine());
            line.useDelimiter(";");            
            final String itemName = line.next();
            final BigDecimal itemPrice = line.nextBigDecimal();
            if (line.hasNext()) {
                final int itemBulk = line.nextInt();
                final BigDecimal itemBulkPrice = line.nextBigDecimal();
                items.add(new Item(itemName, itemPrice, itemBulk, itemBulkPrice));
            } else {
                items.add(new Item(itemName, itemPrice));
            }
            line.close();
            
            //OR
//            final String[] line = input.nextLine().split(";");
//            if (line.length == 4) {
//                final String itemName = line[0];
//                final BigDecimal itemPrice = new BigDecimal(line[1]);
//                final int itemBulk = Integer.parseInt(line[2]);
//                final BigDecimal itemBulkPrice = new BigDecimal(line[3]);
//                items.add(new Item(itemName, itemPrice, itemBulk, itemBulkPrice));
//            } else {
//                final String itemName = line[0];
//                final BigDecimal itemPrice = new BigDecimal(line[1]);
//                items.add(new Item(itemName, itemPrice));
//            }
        }
        input.close();
        return items;
    }
    
}
