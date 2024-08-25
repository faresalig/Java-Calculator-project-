package Calculator;

import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI {
    private JFrame frame;
    private JTextField textField;
    private double num1 = 0;
    private String operator = "";

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                CalculatorGUI window = new CalculatorGUI();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public CalculatorGUI() {
        frame = new JFrame("Calculator");
        frame.setBounds(100, 100, 400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.BOLD, 24));
        frame.getContentPane().add(textField, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.PLAIN, 18));
            button.addActionListener(new ButtonClickListener());
            panel.add(button);
        }
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            String command = source.getText();

            switch (command) {
                case "=":
                    double num2 = Double.parseDouble(textField.getText());
                    double result;
                    switch (operator) {
                        case "+":
                            result = num1 + num2;
                            break;
                        case "-":
                            result = num1 - num2;
                            break;
                        case "*":
                            result = num1 * num2;
                            break;
                        case "/":
                            if (num2 != 0) {
                                result = num1 / num2;
                            } else {
                                textField.setText("Error");
                                return;
                            }
                            break;
                        default:
                            result = 0;
                            break;
                    }
                    textField.setText(String.valueOf(result));
                    operator = "";
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                    num1 = Double.parseDouble(textField.getText());
                    operator = command;
                    textField.setText("");
                    break;
                default:
                    textField.setText(textField.getText() + command);
                    break;
            }
        }
    }
}
