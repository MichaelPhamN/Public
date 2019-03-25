/**
 * 
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.IOException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import model.Cart;
import model.Item;
import model.ItemOrder;
import utility.FileLoader;

/**
 * The Bookstore Panel.
 * @author phamngovinhphuc
 * @version 1
 */
public class Bookstore {
    /** Padding. */
    private static final int PADDING = 20;
    
    /** The color for some elements in the GUI. */
    private static final Color COLOR_1 = new Color(199, 153, 0); // Gold

    /** The color for some elements in the GUI. */
    private static final Color COLOR_2 = new Color(57, 39, 91); // Purple
    
    /** The dimension total text field. */
    private static final Dimension TEXTFIELD_SIZE = new Dimension(150, 20);
    
    /** The text field length. */
    private static final int TEXTFIELD_LENGTH = 3;
    
    /**
     * The filename of items.
     */
    private static final String FILE_ITEMS = "files/items_order.txt";
    
    /**
     * The JFrame.
     */
    private static JFrame myFrame;
    
    /**
     * The order total.
     */
    private JTextField myOutputTotal;
    
    /**
     * The printing button.
     */
    private JButton myPrintingButton;
    
    /**
     * The clear button.
     */
    private JButton myClearButton;
    
    /**
     * The checkbox membership.
     */
    private JCheckBox myCheckBox;
    
    /**
     * The Item Order List.
     */
    private final Cart myCart; 
    
    /**
     * The bookstore panel constructor.
     */
    public Bookstore() { 
        myCart = new Cart();
    }
        
    /**
     * Create Total Panel.
     * @return a JPanel.
     */
    private JPanel createTopPanel() {
        /**
         * Create total panel and set background.
         */
        final JPanel total = new JPanel();
        total.setBackground(COLOR_2);
        
        /**
         * Create order total label. Set color for the text label.
         */
        final JLabel orderTotal = new JLabel("order total");
        orderTotal.setForeground(Color.WHITE);
        
        /**
         * Set size for text field and cannot edit.
         */
        myOutputTotal = new JTextField("$0.00");
        myOutputTotal.setPreferredSize(TEXTFIELD_SIZE);
        myOutputTotal.setEditable(false);
                        
        total.add(orderTotal);
        total.add(myOutputTotal);
        
        return total;
    }
    
    /**
     * Create Products Panel.
     * @return a JPanel.
     */
    private JPanel createCenterPanel() {
        final JPanel itemsOrder = new JPanel();
        try {
            final List<Item> items = FileLoader.readData(FILE_ITEMS);
            final JPanel panel = new JPanel(new GridLayout(items.size(), 1));            
            for (final Item item : items) {
                final JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                itemPanel.setBackground(COLOR_1);
                final JTextField input = new JTextField(TEXTFIELD_LENGTH);
                input.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(final ActionEvent theEvent) {
                        input.transferFocus();
                    }
                });
                input.addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusLost(final FocusEvent theEvent) {
                        updateItem(item, input);
                    }
                });
                itemPanel.add(input);
                itemPanel.add(new JLabel(item.toString()));
                panel.add(itemPanel);
            }
            itemsOrder.add(panel);
        } catch (final IOException event) {
            JOptionPane.showMessageDialog(null, event.getMessage(), 
                                  "WARNING", JOptionPane.ERROR_MESSAGE);
        }
        return itemsOrder;
    }
    
    /**
     * Updates the set of items by changing the quantity of the specified
     * product to the specified quantity.
     * 
     * @param theItem The product to update.
     * @param theQuantity The new quantity.
     */
    private void updateItem(final Item theItem, final JTextField theQuantity) {
        final String text = theQuantity.getText().trim();
        int number;
        try {
            number = Integer.parseInt(text);
            if (number < 0) {
                throw new NumberFormatException();
            }
        } catch (final NumberFormatException e) {
            number = 0;
            theQuantity.setText("");
        }
        myCart.add(new ItemOrder(theItem, number));
    }
    
    /**
     * Create Clear Panel.
     * @return a clear panel
     */
    private JPanel createBottomPanel() {
        final JPanel buttonPanel = new JPanel(new BorderLayout());
        
        
        final JPanel clearPanel = new JPanel();   
        clearPanel.setBackground(COLOR_2);
        clearPanel.setBorder(new EmptyBorder(0, PADDING, 0, PADDING));        
        myClearButton = new JButton("Clear");        
        final JPanel customerPanel = new JPanel(new BorderLayout());
        customerPanel.setBackground(COLOR_2);
        myCheckBox = new JCheckBox();
        final JLabel memebership = new JLabel("customer has store membership");
        memebership.setForeground(Color.WHITE);
        customerPanel.add(myCheckBox, BorderLayout.WEST);
        customerPanel.add(memebership, BorderLayout.CENTER);       
        clearPanel.add(myClearButton);       
        clearPanel.add(customerPanel);
        
        final JPanel printingPanel = new JPanel();   
        printingPanel.setBackground(COLOR_2);
        myPrintingButton = new JButton("Printing Receipt");
        printingPanel.add(myPrintingButton);
        
        buttonPanel.add(clearPanel, BorderLayout.NORTH);
        buttonPanel.add(printingPanel, BorderLayout.SOUTH);
        return buttonPanel;
    }
    
    /**
     * Create GUI.
     */
    public static void createAndShowGUI() {
        myFrame = new JFrame("UW Bookstore");
        
        final Bookstore bookstore = new Bookstore();

        myFrame.add(bookstore.createTopPanel(), BorderLayout.NORTH);
        myFrame.add(bookstore.createCenterPanel(), BorderLayout.CENTER);
        myFrame.add(bookstore.createBottomPanel(), BorderLayout.SOUTH);
        
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setLocationRelativeTo(null);
        myFrame.setResizable(false);
        myFrame.pack();
        myFrame.setVisible(true);
        
    }

}
