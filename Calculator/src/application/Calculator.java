package application;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/**
 * The Calculator Application.
 * @author phamngovinhphuc
 * @version 1
 */
public class Calculator {
    /** A ToolKit. */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();
    
    /** The Dimension of the screen. */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();
    
    /** The Dimension of the calculator application. */
    private static final Dimension DIM = new Dimension(391, 235); 
    
    /** The output font size. */
    private static final int OUTPUT_SIZE = 25; 
    
    /** The screen x position. */ 
    private static final int SCREEN_POSITION_X = 6;
    
    /** The column button 1. */
    private static final int COLUMN_1 = 3;
    
    /** The column button 2. */
    private static final int COLUMN_2 = 67;    
    
    /** The column button 3. */
    private static final int COLUMN_3 = 131;
    
    /** The column button 4. */
    private static final int COLUMN_4 = 195;
    
    /** The column button 5. */
    private static final int COLUMN_5 = 259;
    
    /** The column button 6. */
    private static final int COLUMN_6 = 323;
    
    /** The width button. */
    private static final int WIDTH = 65;
    
    /** The height button. */
    private static final int HEIGHT = 35;
    
    /** The calculator myFrame. */
    private JFrame myFrame;
    
    /** The formula output. */
    private JTextField myFormula;
    
    /** The result output. */
    private JTextField myResult;    
    
    /** The formula string. */
    private String myFormulaString;
    
    /**
     * Create the application.
     */
    public Calculator() {
        myFormulaString = "";
        initialize();
    }

    /**
     * Initialize the contents of the myFrame.
     */
    private void initialize() {
        myFrame = new JFrame();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(DIM);
        myFrame.getContentPane().setLayout(null);
        
        calculatorScreen();
        calculatorButtons();
        
        myFrame.setResizable(false);
        myFrame.setLocation(SCREEN_SIZE.width / 2 - myFrame.getWidth() / 2,
                            SCREEN_SIZE.height / 2 - myFrame.getHeight() / 2);
        myFrame.setVisible(true);
    }
    
    /**
     * The calculator screen.
     */
    private void calculatorScreen() {
        final int widthOutput = 379;
        myResult = new JTextField();
        myResult.setBorder(null);
        myResult.setEditable(false);
        myResult.setFont(new Font("Lucida Grande", Font.PLAIN, OUTPUT_SIZE));
        myResult.setHorizontalAlignment(SwingConstants.RIGHT);
        myResult.setText(Integer.toString(0));
        final int screenPositionY = 28;
        final int heightOutputResult = 43;
        myResult.setBounds(SCREEN_POSITION_X, screenPositionY, widthOutput, 
                           heightOutputResult);
        myFrame.add(myResult);
                
        myFormula = new JTextField();
        myFormula.setBorder(null);
        myFormula.setEditable(false);
        final int heightOutputFomula = 26;
        myFormula.setBounds(SCREEN_POSITION_X, SCREEN_POSITION_X, widthOutput, 
                            heightOutputFomula);
        myFrame.add(myFormula);
        
        final JEditorPane editorPane = new JEditorPane();
        editorPane.setBorder(new LineBorder(new Color(0, 0, 0)));
        final int heightOutputScreen = 26;
        editorPane.setBounds(SCREEN_POSITION_X, SCREEN_POSITION_X, widthOutput, 
                             heightOutputScreen);
        myFrame.add(editorPane);
    }
    
    /**
     * The calculator buttons.
     */
    private void calculatorButtons() {
        firstRow();
        secondRow();
        thirdRow();
        fourthRow();
    }
    
    /**
     * The first row buttons.
     */
    private void firstRow() {
        final int firstRowCoordiationY = 75;
        final JButton buttonSeven = new JButton("7");
        buttonSeven.setBounds(COLUMN_1, firstRowCoordiationY, WIDTH, HEIGHT);
        buttonSeven.addActionListener(new ActionListener() {            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myFormulaString = myFormulaString + buttonSeven.getText();
                outputFormula();
            }
        });
        myFrame.add(buttonSeven);
        
        final JButton buttonEight = new JButton("8");
        buttonEight.setBounds(COLUMN_2, firstRowCoordiationY, WIDTH, HEIGHT);
        buttonEight.addActionListener(new ActionListener() {            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myFormulaString = myFormulaString + buttonEight.getText();
                outputFormula();
            }
        });
        myFrame.getContentPane().add(buttonEight);
        
        final JButton buttonNine = new JButton("9");
        buttonNine.setBounds(COLUMN_3, firstRowCoordiationY, WIDTH, HEIGHT);
        buttonNine.addActionListener(new ActionListener() {            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myFormulaString = myFormulaString + buttonNine.getText();
                outputFormula();
            }
        });
        myFrame.getContentPane().add(buttonNine);
        
        final JButton buttonDivide = new JButton("\u00F7");
        buttonDivide.setBounds(COLUMN_4, firstRowCoordiationY, WIDTH, HEIGHT);
        buttonDivide.addActionListener(new ActionListener() {            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myFormulaString = myFormulaString + buttonDivide.getText();
                outputFormula();
            }
        });
        myFrame.getContentPane().add(buttonDivide);
              
        final JButton buttonUndo = new JButton("\u232B");
        buttonUndo.setBounds(COLUMN_5, firstRowCoordiationY, WIDTH, HEIGHT);
        buttonUndo.addActionListener(new ActionListener() {            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                
            }
        });
        myFrame.getContentPane().add(buttonUndo);        
        
        final JButton buttonClear = new JButton("Clear");
        buttonClear.setBounds(COLUMN_6, firstRowCoordiationY, WIDTH, HEIGHT);
        buttonClear.addActionListener(new ActionListener() {            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {

            }
        });
        myFrame.getContentPane().add(buttonClear);
    }
    
    /**
     * The second row buttons.
     */
    private void secondRow() {
        final int secondRowCoordiationY = 108;
        final JButton buttonFour = new JButton("4");
        buttonFour.setBounds(COLUMN_1, secondRowCoordiationY, WIDTH, HEIGHT);
        buttonFour.addActionListener(new ActionListener() {            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myFormulaString = myFormulaString + buttonFour.getText();
                outputFormula();
            }
        });
        myFrame.getContentPane().add(buttonFour);
        
        final JButton buttonFive = new JButton("5");
        buttonFive.setBounds(COLUMN_2, secondRowCoordiationY, WIDTH, HEIGHT);
        buttonFive.addActionListener(new ActionListener() {            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myFormulaString = myFormulaString + buttonFive.getText();
                outputFormula();
            }
        });
        myFrame.getContentPane().add(buttonFive);
        
        final JButton buttonSix = new JButton("6");
        buttonSix.setBounds(COLUMN_3, secondRowCoordiationY, WIDTH, HEIGHT);
        buttonSix.addActionListener(new ActionListener() {            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myFormulaString = myFormulaString + buttonSix.getText();
                outputFormula();
            }
        });
        myFrame.getContentPane().add(buttonSix);
        
        final JButton buttonMultiply = new JButton("*");
        buttonMultiply.setBounds(COLUMN_4, secondRowCoordiationY, WIDTH, HEIGHT);
        buttonMultiply.addActionListener(new ActionListener() {            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myFormulaString = myFormulaString + buttonMultiply.getText();
                outputFormula();
            }
        });
        myFrame.getContentPane().add(buttonMultiply);
        
        final JButton buttonLeftParenthesis = new JButton("(");
        buttonLeftParenthesis.setBounds(COLUMN_5, secondRowCoordiationY, WIDTH, HEIGHT);
        buttonLeftParenthesis.addActionListener(new ActionListener() {            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myFormulaString = myFormulaString + buttonLeftParenthesis.getText();
                outputFormula();
            }
        });
        myFrame.getContentPane().add(buttonLeftParenthesis);
        
        final JButton buttonRightParenthesis = new JButton(")");
        buttonRightParenthesis.setBounds(COLUMN_6, secondRowCoordiationY, WIDTH, HEIGHT);
        buttonRightParenthesis.addActionListener(new ActionListener() {            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myFormulaString = myFormulaString + buttonRightParenthesis.getText();
                outputFormula();
            }
        });
        myFrame.getContentPane().add(buttonRightParenthesis);
    }
    
    /**
     * The third row buttons.
     */
    private void thirdRow() {
        final int thirdRowCoordiationY = 141;
        final JButton buttonOne = new JButton("1");
        buttonOne.setBounds(COLUMN_1, thirdRowCoordiationY, WIDTH, HEIGHT);
        buttonOne.addActionListener(new ActionListener() {            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myFormulaString = myFormulaString + buttonOne.getText();
                outputFormula();
            }
        });
        myFrame.getContentPane().add(buttonOne);
        
        final JButton buttonTwo = new JButton("2");
        buttonTwo.setBounds(COLUMN_2, thirdRowCoordiationY, WIDTH, HEIGHT);
        buttonTwo.addActionListener(new ActionListener() {            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myFormulaString = myFormulaString + buttonTwo.getText();
                outputFormula();
            }
        });
        myFrame.getContentPane().add(buttonTwo);
        
        final JButton buttonThree = new JButton("3");
        buttonThree.setBounds(COLUMN_3, thirdRowCoordiationY, WIDTH, HEIGHT);
        buttonThree.addActionListener(new ActionListener() {            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myFormulaString = myFormulaString + buttonThree.getText();
                outputFormula();
            }
        });
        myFrame.getContentPane().add(buttonThree);
        
        final JButton buttonMinus = new JButton("-");
        buttonMinus.setBounds(COLUMN_4, thirdRowCoordiationY, WIDTH, HEIGHT);
        buttonMinus.addActionListener(new ActionListener() {            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myFormulaString = myFormulaString + buttonMinus.getText();
                outputFormula();
            }
        });
        myFrame.getContentPane().add(buttonMinus);
        
        final JButton buttonSquare = new JButton("x" + "\u00B2");
        buttonSquare.setBounds(COLUMN_5, thirdRowCoordiationY, WIDTH, HEIGHT);
        buttonSquare.addActionListener(new ActionListener() {            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myFormulaString = myFormulaString + buttonSquare.getText();
                outputFormula();
            }
        });
        myFrame.getContentPane().add(buttonSquare);
        
        final JButton buttonSqrt = new JButton("\u221A");
        buttonSqrt.setBounds(COLUMN_6, thirdRowCoordiationY, WIDTH, HEIGHT);
        buttonSqrt.addActionListener(new ActionListener() {            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myFormulaString = myFormulaString + buttonSqrt.getText();
                outputFormula();
            }
        });
        myFrame.getContentPane().add(buttonSqrt);
    }
    
    /**
     * The fourth row buttons.
     */
    private void fourthRow() {
        final int fourthRowCoordiationY = 174;
        final JButton buttonZero = new JButton("0");
        buttonZero.setBounds(COLUMN_1, fourthRowCoordiationY, WIDTH, HEIGHT);
        buttonZero.addActionListener(new ActionListener() {            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myFormulaString = myFormulaString + buttonZero.getText();
                outputFormula();
            }
        });
        myFrame.getContentPane().add(buttonZero);
        
        final JButton buttonDot = new JButton(".");
        buttonDot.setBounds(COLUMN_2, fourthRowCoordiationY, WIDTH, HEIGHT);
        buttonDot.addActionListener(new ActionListener() {            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myFormulaString = myFormulaString + buttonDot.getText();
                outputFormula();
            }
        });
        myFrame.getContentPane().add(buttonDot);
        
        final JButton buttonPercent = new JButton("%");
        buttonPercent.setBounds(COLUMN_3, fourthRowCoordiationY, WIDTH, HEIGHT);
        buttonPercent.addActionListener(new ActionListener() {            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myFormulaString = myFormulaString + buttonPercent.getText();
                outputFormula();
            }
        });
        myFrame.getContentPane().add(buttonPercent);
        
        final JButton buttonSum = new JButton("+");
        buttonSum.setBounds(COLUMN_4, fourthRowCoordiationY, WIDTH, HEIGHT);
        buttonSum.addActionListener(new ActionListener() {            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myFormulaString = myFormulaString + buttonSum.getText();
                outputFormula();
            }
        });
        myFrame.getContentPane().add(buttonSum);
        
        final int equalButtonWidth = 129;
        final JButton buttonEqual = new JButton("=");
        buttonEqual.setBounds(COLUMN_5, fourthRowCoordiationY, equalButtonWidth, HEIGHT);
        buttonEqual.addActionListener(new ActionListener() {            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                outputFormula();
            }
        });
        myFrame.getContentPane().add(buttonEqual);
    }
    
    /** print the formula. */
    private void outputFormula() {
        myFormula.setText(myFormulaString);
    }
    
    /**
     * Launch the application.
     * @param theArgs the argument.
     */
    public static void main(final String... theArgs) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Calculator();
            }
        });
    }
}
