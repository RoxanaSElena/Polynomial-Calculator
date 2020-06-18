package Controller;
import Poli.Polynomial;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PolynomialInterpreter implements ActionListener {

    Panel panel;

    // Suntem dependenti de valorile existente pe panou
    public PolynomialInterpreter(Panel panel) {
        this.panel = panel;
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equals("plus")) {
            String buffer1 = panel.polinom.getText();
            String buffer2 = panel.polinom2.getText();
            Polynomial poli3 = this.add(buffer1, buffer2);
            panel.statusLabel3.setText("Poli.Polynomial 3: " + poli3.display() + " ");
        } else if(command.equals("minus")) {
            String buffer1 = panel.polinom.getText();
            String buffer2 = panel.polinom2.getText();
            Polynomial poli3 = this.sub(buffer1, buffer2);
            panel.statusLabel3.setText("Poli.Polynomial 3: " + poli3.display() + " ");
        } else if(command.equals("times")) {
            String buffer1 = panel.polinom.getText();
            String buffer2 = panel.polinom2.getText();
            Polynomial poli3 = this.times(buffer1, buffer2);
            panel.statusLabel3.setText("Poli.Polynomial 3:" + poli3.display() + " ");
        } else if(command.equals("divide")) {
            String buffer1 = panel.polinom.getText();
            String buffer2 = panel.polinom2.getText();
            ArrayList<Polynomial> polis = new ArrayList<Polynomial>();
            polis = this.divide(buffer1, buffer2);
            panel.statusLabel3.setText("Poli.Polynomial 3:" + polis.get(0).display() +/* "  |   " + polis.get(1).display() +*/ " ");
        }else if(command.equals("deriv")) {
            String buffer1= panel.polinom.getText();
            String actualBuffer = (buffer1.contains("-")) ? buffer1.replace("-", "+-") : buffer1;
            String finalBuffer=(actualBuffer.charAt(0) == '+') ? actualBuffer.substring(1) : actualBuffer;
            String[] monoame = finalBuffer.split("\\+");
            Polynomial poli = new Polynomial(monoame);
            poli.deriv();
            String buffer2= panel.polinom2.getText();
            String actualBuffer1 = (buffer2.contains("-")) ? buffer2.replace("-", "+-") : buffer2;
            String finalBuffer1=(actualBuffer1.charAt(0) == '+') ? actualBuffer1.substring(1) : actualBuffer1;
            String[] monoame1 = finalBuffer1.split("\\+");
            Polynomial poli1 = new Polynomial(monoame1);
            poli1.deriv();
            panel.statusLabel3.setText("Poli.Polynomial 1:" + poli.display() + " "+ "Poli.Polynomial 2: "+ poli1.display());
        }else if(command.equals("integr")) {
            String buffer1= panel.polinom.getText();
            String actualBuffer = (buffer1.contains("-")) ? buffer1.replace("-", "+-") : buffer1;
            String finalBuffer = (actualBuffer.charAt(0) == '+') ? actualBuffer.substring(1) : actualBuffer;
            String[] monoame = finalBuffer.split("\\+");
            Polynomial poli = new Polynomial(monoame);
            poli.integr();
            String buffer2= panel.polinom2.getText();
            String actualBuffer1 = (buffer2.contains("-")) ? buffer2.replace("-", "+-") : buffer2;
            String finalBuffer1 = (actualBuffer1.charAt(0) == '+') ? actualBuffer1.substring(1) : actualBuffer1;
            String[] monoame1 = finalBuffer1.split("\\+");
            Polynomial poli1= new Polynomial(monoame1);
            poli1.integr();
            panel.statusLabel3.setText("Poli.Polynomial 1:" + poli.display() + " "+"Poli.Polynomial 2:" + poli1.display()+" ");
        }
    }

    public  Polynomial createPoli(String buffer) {
        String monoame[];
        String finalBuffer;
        String actualBuffer = (buffer.contains("-")) ? buffer.replace("-", "+-") : buffer;
        finalBuffer = (actualBuffer.charAt(0) == '+') ? actualBuffer.substring(1) : actualBuffer;
        monoame = finalBuffer.split("\\+");
        Polynomial poli = new Polynomial(monoame);
        return poli;

    }

    private Polynomial add(String buffer1, String buffer2) {
        Polynomial poli1, poli2, poli3;
        poli1 = createPoli(buffer1);
        poli2 = createPoli(buffer2);
        poli3 = poli1.addition(poli2);
        return poli3;
    }

    private Polynomial sub(String buffer1, String buffer2) {
        Polynomial poli1, poli2, poli3;
        poli1 = createPoli(buffer1);
        poli2 = createPoli(buffer2);
        poli3 = poli1.substraction(poli2);
        return poli3;
    }

    private Polynomial times(String buffer1, String buffer2) {
        Polynomial poli1, poli2, poli3;
        poli1 = createPoli(buffer1);
        poli2 = createPoli(buffer2);
        poli3 = poli1.times(poli2);
        return poli3;
    }

    private ArrayList<Polynomial> divide(String buffer1, String buffer2) {
        Polynomial poli1, poli2;
        ArrayList<Polynomial> polis = new ArrayList<Polynomial>();
        poli1 = createPoli(buffer1);
        poli2 = createPoli(buffer2);
        polis = poli1.divide(poli2);
        return polis;
    }


}