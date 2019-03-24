package shapes;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Point;
import org.junit.Before;
import org.junit.Test;
/**
 * Circle Test Case.
 * @author phamngovinhphuc
 * @version 1
 */
public class CircleTest {
    /** 
     * A tolerance used when comparing double values of equality. 
     * */
    private static final double TOLERANCE = .000001;
    
    /** 
     * Default circle to use in the test. 
     * */
    private Circle myCircle;
    
    /** 
     * A new circle to use in the test. 
     * */
    private Circle myCircleTest;
    

    /**
     * A method to initialize the test fixture before each test.
     */
    @Before
    public void setUp() {
        //Radius = 1.0; Center = (0, 0); Color = Black 
        myCircle = new Circle();
        
        //Radius = 9.0; Center = (1, 2); Color = Red
        myCircleTest = new Circle(9.0, new Point(1, 2), Color.RED);
    }

    /**
     * The test method for the circle constructor with no parameter.
     * The method purpose is to check if initializing a default circle is successful.
     */
    @Test
    public void testCircle() {
        //Check Default Circle
        assertEquals("Checking center of default circle", 
                     new Point(0, 0), myCircle.getCenter());
        assertEquals("Checking radius of default circle", 
                     Circle.DEFAULT_RADIUS, myCircle.getRadius(), TOLERANCE);
        assertEquals("Checking color of default circle", 
                     Circle.DEFAULT_COLOR, myCircle.getColor());
    }
    
    /**
     * The test method for a circle constructor with 3 parameter.
     * The first parameter is the radius of the circle.
     * The second parameter is the center of the circle.
     * The third parameter is the color of the circle.
     * 
     * There are some exceptions for this circle constructor.
     * Case 1: The radius is a negative value.
     * Case 2: The radius is zero.
     * Case 3: The point is null. 
     * Case 4: Assign for the Point constructor is null.
     * Case 5: The color is null.
     */
    @Test
    public void testCircleConstructor() {
        //Check Default Circle
        assertEquals("Checking center of a new circle", new Point(1, 2), 
                     myCircleTest.getCenter());
        assertEquals("Checking radius of a new circle", 9.0, myCircleTest.getRadius(), 
                     TOLERANCE);
        assertEquals("Checking color of a new circle", Color.RED, myCircleTest.getColor());
    }
    
    /**
     * Case 1: The radius of the circle is negative.
     * @exception   IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCircleConstructorNegRadius() {
        new Circle(-9.0, new Point(1, 2), Color.RED);
    }
    
    /**
     * Case 2: The radius of the circle is zero.
     * @exception   IllegalArgumentException 
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCircleConstructorZeroRadius() {
        new Circle(0.0, new Point(1, 2), Color.RED);
    }
    
    /**
     * Case 3: The point is null.
     * @exception NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void testCircleConstructorNullPoint() {
        new Circle(9.0, null, Color.RED);
    }
    
    /**
     * Case 4: Assign for the point constructor is null.
     * @exception NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void testCircleConstructorNewPointNull() {
        new Circle(9.0, new Point(null), Color.RED);
    }
    
    /**
     * Case 5: The color is null.
     * @exception NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void testCircleContructorNullColor() {
        new Circle(9.0, new Point(1, 2), null);
    }

    /**
     * Test setting the radius of the circle.
     * 
     * There are some exceptions for setting radius.
     * Case 1: The radius is negative.
     * Case 2: The radius is zero.
     */
    @Test
    public void testSetRadius() {
        assertEquals("PRE-SetRadius", 1.0, myCircle.getRadius(), TOLERANCE);
        myCircle.setRadius(9.0);
        assertEquals("POST-SetRadius", 9.0, myCircle.getRadius(), TOLERANCE);
    }
    
    /**
     * Case 1: The radius is negative.
     * @exception IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetRadiusNeg() {
        myCircle.setRadius(-9.0);
    }
    
    /**
     * Case 2: The radius is zero.
     * @exception IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetRadiusZero() {
        myCircle.setRadius(0.0);
    }

    /**
     * Test setting the center of the circle.
     * 
     * There are some exceptions for setting center.
     * Case 1: The center of the circle is null.
     * Case 2: Assign for the Point constructor is null. 
     */
    @Test
    public void testSetCenter() {
        assertEquals("PRE-SetCenter", new Point(0, 0), myCircle.getCenter());
        myCircle.setCenter(new Point(1, 2));
        assertEquals("POST-SetCenter",  new Point(1, 2), myCircle.getCenter());
    }
    
    /**
     * Case 1: The center of the circle is null.
     * @exception NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void testSetCenterNull() {
        myCircle.setCenter(null);
    }
    
    /**
     * Case 2: Assign for the Point constructor is null.
     * @exception NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void testSetPointNull() {
        myCircle.setCenter(new Point(null));
    }

    /**
     * Test setting the color of the circle.
     * 
     * There is an exception for setting color.
     * The color is null.
     */
    @Test
    public void testSetColor() {
        assertEquals("PRE-SetColor", Color.BLACK, myCircle.getColor());
        myCircle.setColor(Color.RED);
        assertEquals("POST-SetColor", Color.RED, myCircle.getColor());
    }
    
    /**
     * The color is null.
     * @exception NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void testSetColorNull() {
        myCircle.setColor(null);
    }

    /**
     * Test getting the radius of the circle.
     */
    @Test
    public void testGetRadius() {
        assertEquals("PRE-GetRadius", 1.0, myCircle.getRadius(), TOLERANCE);
        myCircle.setRadius(9.0);
        assertEquals("POST-GetRadius", 9.0, myCircle.getRadius(), TOLERANCE);
    }

    /**
     * Test getting the center of the circle.
     */
    @Test
    public void testGetCenter() {
        assertEquals("PRE-GetCenter", new Point(0, 0), myCircle.getCenter());
        myCircle.setCenter(new Point(1, 2));
        assertEquals("POST-GetCenter", new Point(1, 2), myCircle.getCenter());
    }

    /**
     * Test getting the color of the circle.
     */
    @Test
    public void testGetColor() {
        assertEquals("Checking color before setting", Color.BLACK, myCircle.getColor());
        myCircle.setColor(Color.RED);
        assertEquals("Checking color after setting", Color.RED, myCircle.getColor());
    }

    /**
     * Test calculating diameter of the circle.
     * The Diameter = 2 * the radius
     */
    @Test
    public void testCalculateDiameter() {
        assertEquals("testCalculateDiameter on default fail!", 2 * myCircle.getRadius(), 
                     myCircle.calculateDiameter(), TOLERANCE);
        assertEquals("testCalculateDiameter on new fail!", 2 * myCircleTest.getRadius(), 
                     myCircleTest.calculateDiameter(), TOLERANCE);
    } 

    /**
     * Test calculating circumference of the circle.
     * The Circumference = 2 * PI * the radius
     */
    @Test
    public void testCalculateCircumference() {
        assertEquals("testCalculateCircumference on default fail!", 
                     2 * Math.PI * myCircle.getRadius(), 
                     myCircle.calculateCircumference(), TOLERANCE);
        assertEquals("testCalculateCircumference on new fail!", 
                     2 * Math.PI * myCircleTest.getRadius(), 
                     myCircleTest.calculateCircumference(), TOLERANCE);
    }

    /**
     * Test calculating area of the circle.
     * The Area = PI * the radius * the radius
     */
    @Test
    public void testCalculateArea() {
        assertEquals("testCalculateArea on defaul fail!", 
                     Math.PI * myCircle.getRadius() * myCircle.getRadius(), 
                     myCircle.calculateArea(), TOLERANCE);
        assertEquals("testCalculateArea on new fail!", 
                     Math.PI * myCircleTest.getRadius() * myCircleTest.getRadius(), 
                     myCircleTest.calculateArea(), TOLERANCE);
    }

    /**
     * Test method for {@link shapes.Circle#toString()}.
     */
    @Test
    public void testToString() {
        assertEquals("toString() produced an unexpected result!",
                     "Circle [radius=" + String.format("%.2f", myCircle.getRadius())
                     + ", center=" + myCircle.getCenter() 
                     + ", color=" + myCircle.getColor() + "]", myCircle.toString());
    }
}
