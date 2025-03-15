import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePage extends JFrame {

    private JRadioButton size5x5;
    private JRadioButton size7x7;
    private JRadioButton size9x9;
    private JButton continueButton;
    private ButtonGroup boardSizeGroup;

    public WelcomePage() {
        setTitle("Black Hole Game");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JLabel welcomeLabel = new JLabel("Welcome to the Black Hole Game!", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel instructionLabel = new JLabel("Select your board size:", JLabel.CENTER);

        size5x5 = new JRadioButton("5 x 5", true);
        size7x7 = new JRadioButton("7 x 7");
        size9x9 = new JRadioButton("9 x 9");

        boardSizeGroup = new ButtonGroup();
        boardSizeGroup.add(size5x5);
        boardSizeGroup.add(size7x7);
        boardSizeGroup.add(size9x9);

        continueButton = new JButton("Continue");

        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (size5x5.isSelected()) {
                    System.out.println("Selected board size: 5 x 5");
                } else if (size7x7.isSelected()) {
                    System.out.println("Selected board size: 7 x 7");
                } else if (size9x9.isSelected()) {
                    System.out.println("Selected board size: 9 x 9");
                }

                int selectedSize = 5;
                if (size7x7.isSelected()) {
                    selectedSize = 7;
                } else if (size9x9.isSelected()) {
                    selectedSize = 9;
                }

                Rules rules = new Rules(selectedSize);
                dispose();
            }
        });

        setLayout(new GridLayout(5, 1));
        add(welcomeLabel);
        add(instructionLabel);

        JPanel radioPanel = new JPanel();
        radioPanel.add(size5x5);
        radioPanel.add(size7x7);
        radioPanel.add(size9x9);
        add(radioPanel);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(continueButton);
        add(buttonPanel);
    }
}
