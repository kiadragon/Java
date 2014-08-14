import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.math.*;

public class CaculatorView  extends  JFrame{


//  you can save Caculator as a single cacultor.java
    private static  final int TWO = 2;
    private static  final int FIVE = 5;
    public class Caculator{
        private static final int DEF_DIV_SCALE = 5;

          public String plus(String op1, String op2){
              BigDecimal v1 = new BigDecimal(op1);
              BigDecimal v2 = new BigDecimal(op2);
              return String.valueOf(v1.add(v2));
          }
      
          public String subtract(String op1, String op2){
              BigDecimal v1 = new BigDecimal(op1);
              BigDecimal v2 = new BigDecimal(op2);
              return String.valueOf(v1.subtract(v2));
          }
      
          public String multiply(String op1, String op2){
              BigDecimal v1 = new BigDecimal(op1);
              BigDecimal v2 = new BigDecimal(op2);
              return String.valueOf(v1.multiply(v2));
          }
      
          public String divide(String op1, String op2){
              BigDecimal v1 = new BigDecimal(op1);
              BigDecimal v2 = new BigDecimal(op2);
              return String.valueOf(v1.divide(v2, DEF_DIV_SCALE,BigDecimal.ROUND_HALF_UP));
          }
      }   




    private static int winWidth = 700,
    winHeight = 200;
    private Font genralFont = new Font("微软雅黑",Font.BOLD, 20);
    // set component
    private JTextField op1JTextField = new JTextField("", 15);
    private JLabel operator = new JLabel("", JLabel.CENTER);

    private JTextField op2JTextField = new JTextField("", 15);

    private JLabel equal = new JLabel("=", JLabel.CENTER);
    private JLabel answer = new JLabel("Answer", JLabel.CENTER);
    
    private JButton plus = new JButton("+");
    private JButton subtract = new JButton("-");
    private JButton multiply = new JButton("*");
    private JButton divide = new JButton("/");
    private JButton caculate = new JButton( "Caculate!");
    private Caculator caculatorLogic = new Caculator();

    public static void setWinMidLocation(JFrame f) {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();  // get screen width and height.

        int width = Integer.valueOf(d.width),
        height = Integer.valueOf(d.height);
        
        f.setLocation( (width - winWidth) / TWO, (height - winHeight) / TWO);
    }


    public void setUI(Container c) {
        //  SET UI
        c.setFont(genralFont);
        op1JTextField.setHorizontalAlignment(JTextField.CENTER);
        op2JTextField.setHorizontalAlignment(JTextField.CENTER);
        operator.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        equal.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        answer.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        // add Component
        c.setLayout( new GridLayout(TWO, FIVE, FIVE, FIVE) );
        add(op1JTextField);
        add(operator);
        add(op2JTextField);
        add(equal);
        add(answer);
        add(plus);
        add(subtract);
        add(multiply);
        add(divide);
        add(caculate);

    }

    CaculatorView () {
        super("My first Caculator");
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setSize(winWidth, winHeight);

        // set KeyListener
        op1JTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                int keyChar = e.getKeyChar();
                if (keyChar < KeyEvent.VK_0 && keyChar > KeyEvent.VK_9){
                    e.consume(); // Skip Input
                }

            }
        });

        op2JTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                int keyChar = e.getKeyChar();
                if (keyChar < KeyEvent.VK_0 && keyChar > KeyEvent.VK_9){
                    e.consume(); // Skip Input
                }
            }
        });

        Container c = this.getContentPane();
        //  SET Layout
        setUI(c);

        //set ActionListener
        plus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                operator.setText("+");
            }
        });
        subtract.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                operator.setText("-");
            }
        });
        multiply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                operator.setText("*");
            }
        });
        divide.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                operator.setText("/");
            }
        });
        caculate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (operator.getText().equals("+")) {
                    answer.setText(caculatorLogic.plus(op1JTextField.getText(), op2JTextField.getText()));
                }
                if (operator.getText().equals("-")) {
                    answer.setText(caculatorLogic.subtract(op1JTextField.getText(), op2JTextField.getText()));
                }
                if (operator.getText().equals("*")) {
                    answer.setText(caculatorLogic.multiply(op1JTextField.getText(), op2JTextField.getText()));
                }
                if (operator.getText().equals("/")) {
                    answer.setText(caculatorLogic.divide(op1JTextField.getText(), op2JTextField.getText()));
                }
            }
        });

        setWinMidLocation(this);
        this.setVisible(true);

    }



    public static void main(String[] args) {
        new CaculatorView();
    }
}