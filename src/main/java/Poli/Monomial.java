package Poli;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Monomial {
    public String monom;
    public int degree;
    public Number coeff;
    private String[] buffer;

    //create a function for the validation of a monomial; how a monomial should look

    private boolean validation (String monom)
    {
        //we should verify if there are any other symbol besides characters, sign  and ^
        if(!monom.matches("^[a-zA-Z0-9\\^\\*\\-]*")) return false;
        Pattern polynomFormat = Pattern.compile("\\^");
        Matcher match = polynomFormat.matcher(monom);
        String s = new String();
        while(match.find())
        {
            s=match.group();
        }
        if(s.isEmpty()) {
            //if we do not have the symbol ^
            buffer=monom.split("[a-zA-Z]");
            if (buffer.length == 0) {
                coeff=1;
                degree=1;
            } else {
                coeff=(!buffer[0].isEmpty()) ? Integer.parseInt(buffer[0]) : 1;
                degree=(buffer[0] == monom) ? 0 : 1;
            } }
        else
        {
            // has the symbol ^
            buffer = monom.split("\\^");
            try{
                String nrStr = new String();
                int i=0;
                while(i<buffer[0].length())
                {
                    char letter= buffer[0].charAt(i);
                    if(letter=='-') nrStr+=letter;
                    if(letter>='0' && letter<='9') nrStr+=letter;
                    i++;
                }
                coeff= (nrStr.isEmpty()) ?1:Integer.parseInt(nrStr);
                degree=Integer.parseInt((buffer[1]));
            }catch (NumberFormatException e)
            {
                System.out.println("Not a valid format");
            }
        }
        return true;
    }
    public String format()
    {
        String container = new String(this.coeff+"x^"+this.degree);
        return container;
    }

    // if the validation function is true-> we have the degree and the coef => update the monomial
    public Monomial(String monom)
    {
        if(validation(monom))
        {
            this.monom=monom;
        }
    }
    public Monomial(Number coeff, int degree)
    {
        this.degree=degree;
        this.coeff =coeff;
        this.monom=format();
    }
    public String getMonom()
    {
        if(degree ==0)
            return Double.toString(coeff.doubleValue());
        else
            return coeff.doubleValue()+"x^"+degree;
    }
    public int getDegree()
    {
        return this.degree;
    }
    public Number getCoeff()
    {
        return this.coeff;
    }
    public Number value (int x)
    {
        return coeff.intValue()*Math.pow(x,degree);
    }

    public void addition(int x)
    {
        this.coeff=this.coeff.intValue()+x;
    }

    public Monomial times(Monomial M1)
    {
        Monomial M3;
        Number c = this.coeff.doubleValue() * M1.coeff.doubleValue();
        int d = this.getDegree() + M1.getDegree();
        M3 = new Monomial(c,d);
        return  M3;
    }

    public Monomial devide(Monomial divisor)
    {
        Monomial M3;
        Number c = this.coeff.doubleValue() / divisor.coeff.doubleValue();
        int d = this.getDegree() -divisor.getDegree();
        M3 = new Monomial(c,d);
        return M3;
    }

    public void deriv()
    {
        if(degree!=0)
        {coeff = coeff.intValue()*degree;
            degree--;}
    }

    public void integration()
    {
        degree++;
        coeff = coeff.doubleValue() / (double)degree;
        if(coeff.intValue() == coeff.doubleValue())
            coeff = coeff.intValue();
    }

    public void print()
    {
        System.out.println("Degree: "+ degree+ "coefficient: "+ coeff + "\n");
    }

}
