/**
 * 
 */
package application;
import view.Bookstore;

/**
 * Run the application.
 * @author phamngovinhphuc
 * @version 1
 */
public final class Main {
    /**
     * Private empty constructor to avoid accidental instantiation. 
     */
    private Main() { }
    
    /**
     * Run the program.
     * 
     * @param theArgs Command line arguments, ignored.
     */
    public static void main(final String... theArgs) {      
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Bookstore.createAndShowGUI();
            }
        });
    }
}
