package calculator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Calculator {
    private JFrame calFrame;
    private JTabbedPane tab;
    private JTextArea textArea;
    // store buttons
    ArrayList<JButton> bList;

    /**
     * set calculate containers and components
     */
    public Calculator(){
        bList = new ArrayList<>();
        // set frame
        calFrame = new JFrame("calculator");
        calFrame.setLocation(100,100);
        calFrame.setSize(270,400);
        calFrame.setLayout(null);
        calFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // set tab
        tab = new JTabbedPane();
        tab.setBounds(0,0,250,350);
        //set panel
        JPanel panel = new JPanel(new BorderLayout(5,5));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        //set text area
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.BOLD,14));
//        int labelWidth = textArea.getPreferredSize().width ;
//        int labelHeight = textArea.getPreferredSize().height + 10;
//        textArea.setPreferredSize(new Dimension(labelWidth, labelHeight));
        
        //add text area to scroll  
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(200, 80));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // add buttons to panel
        JPanel keyboardPanel = new JPanel(new GridLayout(5,3));
        for (int i = 9; i > 0; i--) {
            JButton btn = new JButton();
            btn.setText("" + i);
            int buttonWidth = btn.getPreferredSize().width;
            int buttonHeight = btn.getPreferredSize().height + 20;
            btn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
            keyboardPanel.add(btn);
            bList.add(btn);
        }
        JButton sumBtn = new JButton();
        sumBtn.setText("+");
        bList.add(sumBtn);
        keyboardPanel.add(sumBtn);
        JButton zeroBtn = new JButton();
        zeroBtn.setText("0");
        bList.add(zeroBtn);
        keyboardPanel.add(zeroBtn);
        JButton minus = new JButton();
        minus.setText("-");
        keyboardPanel.add(minus);
        bList.add(minus);
        JButton divide = new JButton();
        divide.setText("/");
        keyboardPanel.add(divide);
        bList.add(divide);
        JButton multiple = new JButton();
        multiple.setText("*");
        keyboardPanel.add(multiple);
        bList.add(multiple);
        JButton result = new JButton();
        result.setText("=");
        keyboardPanel.add(result);
        bList.add(result);
        
        // add handler to ActionListener list of buttons 
        ButtonHandler handler = new ButtonHandler();
        for(JButton i : bList){
            i.addActionListener(handler);
        }
        panel.add(keyboardPanel,BorderLayout.SOUTH);
        panel.add(scrollPane,BorderLayout.NORTH);
        // add tab to panel 
        
        tab.add("normal",panel);
        calFrame.add(tab);

        calFrame.setVisible(true);
    }

    /**
     * class to handle buttons
     */
    
    private class ButtonHandler implements ActionListener {
        String num1,operator,num2;
        
        /**
         * num1 store the first number and num2 store the second number
         */
        public ButtonHandler(){
             num1 = "";
             operator = "";
             num2 = "";
        }

        /**
         * handle buttons
         * @param e button which pressed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            String s = e.getActionCommand();
            if (s.charAt(0)>='0' && s.charAt(0)<='9') {
                textArea.append(e.getActionCommand());
                if (!operator.equals("")){
                    num1 += s;
                }
                else{
                    num2 += s;
                }
            }
            else if(s.equals("=")){
                int n1 = 0, n2 = 0;
                if(!(num1.equals("") || num2.equals(""))) {
                    n1 = Integer.parseInt(num1);
                    n2 = Integer.parseInt(num2);
                }
                float res=0;
                switch (operator) {
                    case "+":
                        res = n1 + n2;
                        break;
                    case "-":
                        res = n2 - n1;
                        break;
                    case "*":
                        res = n1 * n2;
                        break;
                    case "/":
                        res = (float)n2 / n1;

                        break;
                    case "":
                        textArea.append("\"=\" is not allowed here\n");
                        return;
                }
                textArea.append(" = " + res + "\n");
                num1 = num2 = operator = "";
            }
            else {
                textArea.append(String.format(" %s ",s));
                switch (s){
                    case "+":
                        operator = "+";
                        break;
                    case "-":
                        operator = "-";
                        break;
                    case "*":
                        operator = "*";
                        break;
                    case "/":
                        operator = "/";
                        break;
                }
            }
        }
    }
}
