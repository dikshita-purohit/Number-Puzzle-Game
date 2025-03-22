package puzzle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Puzzle {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                // Create the frame and content panel
                JFrame frame = new JFrame("Puzzle Game");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setBounds(100, 100, 450, 502);
                JPanel contentPane = new JPanel();
                contentPane.setBackground(new Color(204, 255, 255));
                frame.setContentPane(contentPane);
                contentPane.setLayout(null);

                // Create the buttons
                JButton[] buttons = new JButton[9];
                String[] labels = {"1", "3", "4", "7", "", "2", "8", "6", "5"};
                for (int i = 0; i < buttons.length; i++) {
                    buttons[i] = createButton(labels[i], 29 + (i % 3) * 146, 61 + (i / 3) * 115, 85, 84, e -> handleTileMovement((JButton) e.getSource(), buttons));
                    contentPane.add(buttons[i]);
                }

                // Create the submit button
                JButton submitBtn = createButton("SUBMIT", 158, 422, 127, 33, e -> submitAction(buttons));
                contentPane.add(submitBtn);

                // Create the shuffle button
                JButton shuffleBtn = createButton("SHUFFLE", 158, 380, 127, 33, e -> shufflePuzzle(buttons));
                contentPane.add(shuffleBtn);

                // Set the frame visible
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // Method to create a button
    private static JButton createButton(String text, int x, int y, int width, int height, ActionListener action) {
        JButton button = new JButton(text);
        button.setBackground(new Color(128, 255, 255));
        button.setFont(new Font("Tahoma", Font.PLAIN, 20));
        button.setBounds(x, y, width, height);
        button.addActionListener(action);
        return button;
    }

    // Handle tile movement
    private static void handleTileMovement(JButton button, JButton[] buttons) {
        String buttonText = button.getText();
        JButton[] adjacentButtons = getAdjacentButtons(button, buttons);

        for (JButton adjacent : adjacentButtons) {
            if (adjacent.getText().equals("")) {
                adjacent.setText(buttonText);
                button.setText("");
                break; // Move only once
            }
        }
        checkWinCondition(buttons);
    }

    // Get adjacent buttons
    private static JButton[] getAdjacentButtons(JButton button, JButton[] buttons) {
        if (button == buttons[0]) return new JButton[]{buttons[1], buttons[3]};
        if (button == buttons[1]) return new JButton[]{buttons[0], buttons[2], buttons[4]};
        if (button == buttons[2]) return new JButton[]{buttons[1], buttons[5]};
        if (button == buttons[3]) return new JButton[]{buttons[0], buttons[4], buttons[6]};
        if (button == buttons[4]) return new JButton[]{buttons[1], buttons[3], buttons[5], buttons[7]};
        if (button == buttons[5]) return new JButton[]{buttons[2], buttons[4], buttons[8]};
        if (button == buttons[6]) return new JButton[]{buttons[3], buttons[7]};
        if (button == buttons[7]) return new JButton[]{buttons[4], buttons[6], buttons[8]};
        if (button == buttons[8]) return new JButton[]{buttons[5], buttons[7]};
        return new JButton[]{}; // No adjacent buttons
    }

    // Check win condition
    private static void checkWinCondition(JButton[] buttons) {
        if (buttons[0].getText().equals("1") && buttons[1].getText().equals("2") && buttons[2].getText().equals("3") &&
            buttons[3].getText().equals("4") && buttons[4].getText().equals("5") && buttons[5].getText().equals("6") &&
            buttons[6].getText().equals("7") && buttons[7].getText().equals("8") && buttons[8].getText().equals("")) {
            submitAction(buttons);
        }
    }

    // Shuffle the puzzle
    private static void shufflePuzzle(JButton[] buttons) {
        List<String> labels = new ArrayList<>();
        for (int i = 0; i < buttons.length; i++) {
            labels.add(buttons[i].getText());
        }
        
        // Shuffle the list of labels
        Collections.shuffle(labels);

        // Apply the shuffled labels back to the buttons
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setText(labels.get(i));
        }
    }

    // Submit action (called when the player clicks submit)
    private static void submitAction(JButton[] buttons) {
        // Check if the puzzle is solved correctly
        if (buttons[0].getText().equals("1") && buttons[1].getText().equals("2") && buttons[2].getText().equals("3") &&
            buttons[3].getText().equals("4") && buttons[4].getText().equals("5") && buttons[5].getText().equals("6") &&
            buttons[6].getText().equals("7") && buttons[7].getText().equals("8") && buttons[8].getText().equals("")) {
            JOptionPane.showMessageDialog(null, "--- You won ---");
        } else {
            JOptionPane.showMessageDialog(null, "--- You lost ---");
        }
    }
}
