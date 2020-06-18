
import Poli.Polynomial;
import org.junit.jupiter.api.Test;
import Poli.Polynomial;
import Poli.Monomial;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.jupiter.api.Assertions.*;

public class MyTest {

    @Test
    public void testAddition()
    {
        Polynomial p_expected = Polynomial.createPolinomial("10x^2+10x+3");
        Polynomial p1 = Polynomial.createPolinomial("7x+3");
        Polynomial p2 =Polynomial.createPolinomial("10x^2+3x");

        assertNotSame(p_expected,p1.addition(p2));
    }

    @Test
    public void testSubstraction()
    {
        Polynomial p_expected = Polynomial.createPolinomial("x^6+4x^4+5x^2-1x+4");
        Polynomial p1 = Polynomial.createPolinomial("x^6+4x^4+5x^2+4");
        Polynomial p2 =Polynomial.createPolinomial("-1x");

        assertNotSame(p_expected,p1.substraction(p2));
    }

    @Test
    public void testMultiplication()
    {
        Polynomial p_expected = Polynomial.createPolinomial("5x^2");
        Polynomial p1 = Polynomial.createPolinomial("-5");
        Polynomial p2 =Polynomial.createPolinomial("-1x^2");
        Polynomial p = p1.times(p2);
        assertNotSame(p_expected,p);
    }

    @Test
    public void testIntegration()
    {
        Polynomial p_expected = Polynomial.createPolinomial("8x^2");
        Polynomial p1 = Polynomial.createPolinomial("16x");
        Polynomial p2 = p1.integr();

        assertNotSame(p_expected,p2);
    }

    @Test
    public void testDerivation()
    {
        Polynomial p_expected = Polynomial.createPolinomial("16");
        Polynomial p1 = Polynomial.createPolinomial("16x");
        Polynomial p2 = p1.deriv();

        assertNotSame(p_expected,p2);
    }

    @Test
    public void testDivide()
    {

        Polynomial p_expected = Polynomial.createPolinomial("3x");
        Polynomial p1 = Polynomial.createPolinomial("21x^2");
        Polynomial p2 = Polynomial.createPolinomial("7x");
        ArrayList<Polynomial> poli =p1.divide(p2) ;
        assertNotSame(p_expected,Polynomial.createPolinomial(poli.toString()));
    }
}
