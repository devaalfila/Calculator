import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {

    private JTextField displayField;
    private JButton[] numberButtons;
    private JButton addButton, subtractButton, multiplyButton, divideButton, equalsButton, clearButton;

    private double currentValue = 0;
    private String currentOperator = "";
    private boolean newInput = true;

    public Calculator() {
        setTitle("Simple Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        displayField = new JTextField();
        displayField.setEditable(false);
        add(displayField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4));

        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            buttonPanel.add(numberButtons[i]);
            int finalI = i;
            numberButtons[i].addActionListener(e -> numberButtonClicked(finalI));
        }

        addButton = new JButton("+");
        subtractButton = new JButton("-");
        multiplyButton = new JButton("*");
        divideButton = new JButton("/");
        equalsButton = new JButton("=");
        clearButton = new JButton("C");

        addButton.addActionListener(e -> operatorButtonClicked("+"));
        subtractButton.addActionListener(e -> operatorButtonClicked("-"));
        multiplyButton.addActionListener(e -> operatorButtonClicked("*"));
        divideButton.addActionListener(e -> operatorButtonClicked("/"));
        equalsButton.addActionListener(e -> equalsButtonClicked());
        clearButton.addActionListener(e -> clearButtonClicked());

        buttonPanel.add(addButton);
        buttonPanel.add(subtractButton);
        buttonPanel.add(multiplyButton);
        buttonPanel.add(divideButton);
        buttonPanel.add(equalsButton);
        buttonPanel.add(clearButton);

        add(buttonPanel, BorderLayout.CENTER);
    }

    private void numberButtonClicked(int number) {
        if (newInput) {
            displayField.setText(String.valueOf(number));
            newInput = false;
        } else {
            displayField.setText(displayField.getText() + number);
        }
    }

    private void operatorButtonClicked(String operator) {
        if (!newInput) {
            currentValue = Double.parseDouble(displayField.getText());
            currentOperator = operator;
            newInput = true;
        }
    }

    private void equalsButtonClicked() {
        if (!newInput) {
            double newValue = Double.parseDouble(displayField.getText());
            switch (currentOperator) {
                case "+":
                    currentValue += newValue;
                    break;
                case "-":
                    currentValue -= newValue;
                    break;
                case "*":
                    currentValue *= newValue;
                    break;
                case "/":
                    if (newValue != 0) {
                        currentValue /= newValue;
                    } else {
                        displayField.setText("Error");
                        newInput = true;
                        return;
                    }
                    break;
            }
            displayField.setText(String.valueOf(currentValue));
            newInput = true;
        }
    }

    private void clearButtonClicked() {
        displayField.setText("");
        currentValue = 0;
        currentOperator = "";
        newInput = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculator calculator = new Calculator();
            calculator.setVisible(true);
        });
    }
}
