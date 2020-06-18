package Poli;

import java.util.ArrayList;

public class Polynomial {
    String[] monomStr;
    ArrayList<Monomial> monom = new ArrayList<Monomial>();

    public Polynomial (String[] monomStr)
    {
        this.monomStr = monomStr;
        int i=0;
        while(i<monomStr.length)
        {
            System.out.println(monomStr[i]);
            monom.add(new Monomial(monomStr[i]));
            i++;
        }
        while(i<monom.size())
        {
            Monomial temporary = monom.get(i);
            temporary.print();
            i++;
        }
    }
    private Polynomial()
    {

    }
    private  Polynomial(Monomial m)
    {
        this.monom.add(m);
    }
    public Polynomial deriv()
    {
        int i=0;
        while(i<this.monom.size())
        {
            this.monom.get(i).deriv();
            i++;
        }
        return null;
    }
    public Polynomial integr()
    {
        int i=0;
        while(i<this.monom.size())
        {
            this.monom.get(i).integration();
            i++;
        }
        return null;
    }
    private int getIndexDegree(int  degree)
    {
        int i=0;
        while(i<monom.size())
        {
            Monomial m = monom.get(i);
            if(degree == m.getDegree())
                return i;
            i++;
        }
        return -1;
    }

    private void rem() {
        int i=0;
        while(i<monom.size())
        {
            if(monom.get(i).coeff.intValue() == 0) monom.remove(i);
            i++;
        }
    }

    public Polynomial addition(Polynomial P2)
    {
        Polynomial P3 = new Polynomial();
        int i=0;
        while(i<this.monom.size())
        {
            int d1 = this.monom.get(i).getDegree();
            Number c1 = this.monom.get(i).getCoeff();

            int i_degree = P2.getIndexDegree(d1);
            if(i_degree == -1)
            {
                //haven't been found => add new monomial
                P3.monom.add(new Monomial(c1, d1));
            }else{
                //foud => eff the sum
                int d3 = P2.monom.get(i_degree).getDegree();
                Number c3 = P2.monom.get(i_degree).getCoeff();
                P3.monom.add(new Monomial(c1.intValue()+c3.intValue(), d3));
                //get rid of what we do have
                P2.monom.remove(i_degree);
            }i++;
        }
        //add what we also have in p2
        int j=0;
        while(j<P2.monom.size())
        {
            int d2 = P2.monom.get(j).getDegree();
            Number c2 = P2.monom.get(j).getCoeff();
            P3.monom.add(new Monomial(c2,d2));
            P3.rem();
            j++;
        }return P3; }

    public Polynomial substraction (Polynomial P2)
    {
        Polynomial P3;
        int i=0;
        while(i<P2.monom.size())
        { P2.monom.get(i).coeff = P2.monom.get(i).coeff.intValue() * -1;
            i++;}
        P3 = this.addition(P2);
        return P3;
    }

    public Polynomial times(Polynomial P2)
    {
        Polynomial P3 = new Polynomial();
        int i=0;
        while(i<this.monom.size())
        {
            Monomial m1 = this.monom.get(i);
            int j=0;
            while(j<P2.monom.size())
            {
                Monomial m2= P2.monom.get(j);
                Monomial m3 = m1.times(m2);
                P3.monom.add(m3);
                j++;
            }i++;
        }
        return P3;
    }

    public int getDegree()
    {
        int c=-1; //no neg degree for polynomials
        int i=0;
        while(i<monom.size())
        {
            if(c < monom.get(i).getDegree()&& monom.get(i).coeff.intValue() !=0)
                c = monom.get(i).getDegree();
            i++;
        }
        return c;
    }

    private Monomial findMax(Polynomial p)
    {
        int d = p.getDegree();
        int i_degree = p.getIndexDegree(d);
        return p.monom.get(i_degree);
    }
    public ArrayList<Polynomial>divide(Polynomial impartitor)
    {
        ArrayList<Polynomial> p = new ArrayList<Polynomial>();
        Polynomial cat = new Polynomial();
        Polynomial rest = new Polynomial();
        rest = this;
        if(rest.getDegree() < impartitor.getDegree())
        {
            cat.monom.add(new Monomial(0,0));
        }else{
            while(rest.getDegree() >= impartitor.getDegree() /*&& rest.value()!=0*/)
            {
                Monomial dm = findMax(rest);
                Monomial im = findMax(impartitor);
                Monomial m = dm.devide(im);
                Polynomial pm= new Polynomial(m);
                cat.monom.add(m);
                rest = rest.substraction(impartitor.times(pm));
            }
        }
        if(rest.monom.isEmpty())
            rest.monom.add(new Monomial(0,0));
        p.add(cat);
        p.add(rest);
        return p;
    }

    public String display()
    {
        String display_pol = new String("");
        int i=0;
        while(i<monom.size())
        {
            if(monom.get(i).coeff.intValue()<0)
            {
                display_pol += monom.get(i).getMonom();
            }
            else
            {
                display_pol+=(0 == i ) ? monom.get(i).getMonom() : "+" + monom.get(i).getMonom();
            }
            i++;
        }
        return display_pol;
    }

    public static Polynomial createPolinomial(String buffer) {
        String monoame[];
        String finalBuffer;
        String actualBuffer = (buffer.contains("-")) ? buffer.replace("-", "+-") : buffer;
        finalBuffer = (actualBuffer.charAt(0) == '+') ? actualBuffer.substring(1) : actualBuffer;
        monoame = finalBuffer.split("\\+");
        Polynomial poli = new Polynomial(monoame);
        return poli;

    }

}
