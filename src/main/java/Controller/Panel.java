package Controller;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Panel {

    public JFrame mainFrame;
    public JLabel controlLabel3, statusLabel3;
    public JPanel pol1, pol2;
    public JTextField polinom, polinom2, valoare;
    public JButton plus, minus, times, divide,integr,deriv;
    public int x; // valoarea lui x

    public Panel(){
        prepareGUI();
    }

    private void prepareGUI(){
        mainFrame = new JFrame("Poli.Polynomial Calculator");
        mainFrame.setSize(700,200);
        mainFrame.setLayout(new GridLayout(1,1));
        JPanel firstColumn = new JPanel();
        mainFrame.add(firstColumn);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });


        pol1 = new JPanel();
        pol1.setLayout(new FlowLayout());
        pol2 = new JPanel();
        pol2.setLayout(new FlowLayout());

        polinom = new JTextField(20);
        polinom2 = new JTextField(20);

        pol1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pol1.add(new JLabel("Polinom1: ", JLabel.LEFT));
        pol1.add(polinom);

        pol2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pol2.add(new JLabel("Polinom2: ", JLabel.LEFT));
        pol2.add(polinom2);
        controlLabel3 = new JLabel("", JLabel.LEFT);
        controlLabel3.setPreferredSize(new Dimension(350,20));
        controlLabel3.setText("Result:");
        statusLabel3 = new JLabel("",JLabel.LEFT);
        statusLabel3.setVerticalTextPosition(JLabel.TOP);
        statusLabel3.setPreferredSize(new Dimension(350,20));
        JPanel info8 = new JPanel();
        info8.setLayout(new GridLayout(2,1));
        info8.add(controlLabel3);
        info8.add(statusLabel3);
        info8.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // butoane cu operatiile elementare
        JPanel operatori = new JPanel();
        GridLayout op = new GridLayout(1,4);
        op.setHgap(20);
        operatori.setLayout(op);
        plus = Panel.createSimpleButton("+");
        minus = Panel.createSimpleButton("-");
        times = Panel.createSimpleButton("x");
        divide = Panel.createSimpleButton("/");
        integr = Panel.createSimpleButton("integr");
        deriv = Panel.createSimpleButton("deriv");
        operatori.add(plus);
        operatori.add(minus);
        operatori.add(times);
        operatori.add(divide);
        operatori.add(integr);
        operatori.add(deriv);

        firstColumn.add(pol1);
        firstColumn.add(pol2);
        firstColumn.add(operatori);
        firstColumn.add(info8);
        firstColumn.setVisible(true);

    }

    private static JButton createSimpleButton(String text) {
        JButton button = new JButton(text);
        button.setForeground(Color.BLACK);
        button.setBackground(Color.lightGray);
        Border line = BorderFactory.createLineBorder(Color.BLACK);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
        button.setBorder(compound);
        return button;
    }

    public void showEventDemo(){
        plus.setActionCommand("plus");
        minus.setActionCommand("minus");
        times.setActionCommand("times");
        divide.setActionCommand("divide");
        integr.setActionCommand("integr");
        deriv.setActionCommand("deriv");
        // instanta  obiect cate PolynomialInterpreter
        plus.addActionListener(new PolynomialInterpreter(this));
        minus.addActionListener(new PolynomialInterpreter(this));
        times.addActionListener(new PolynomialInterpreter(this));
        divide.addActionListener(new PolynomialInterpreter(this));
        integr.addActionListener(new PolynomialInterpreter(this));
        deriv.addActionListener(new PolynomialInterpreter(this));

        mainFrame.setVisible(true);
    }

}