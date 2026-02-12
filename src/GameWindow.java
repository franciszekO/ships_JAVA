import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    private JButton[][] buttons = new JButton[5][5];
    private GameLogic logic = new GameLogic();
   
    private JLabel statusLabel = new JLabel("Strzały: 0 / 8", SwingConstants.CENTER);

    public GameWindow() {
        setTitle("Zatapianie statków by Franciszek :)");
        setSize(500, 550); 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout()); 

        // licnzik
        statusLabel.setFont(new Font("Arial", Font.BOLD, 18));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(statusLabel, BorderLayout.NORTH);


        JPanel gridPanel = new JPanel(new GridLayout(5, 5));
        initializeButtons(gridPanel);
        add(gridPanel, BorderLayout.CENTER);
    }

    private void initializeButtons(JPanel panel) {
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                buttons[r][c] = new JButton("?");
                buttons[r][c].setFont(new Font("Arial", Font.PLAIN, 20));
                int finalR = r;
                int finalC = c;

                buttons[r][c].addActionListener(e -> {
                    String result = logic.shoot(finalR, finalC);
                    buttons[finalR][finalC].setEnabled(false);

                    if (result.equals("HIT")) {
                        buttons[finalR][finalC].setText("💥");
                        buttons[finalR][finalC].setBackground(Color.RED);
                    } else {
                        buttons[finalR][finalC].setText("🌊");
                        buttons[finalR][finalC].setBackground(Color.BLUE);
                    }


                    updateStatus();

                    if (logic.isGameOver()) {
                        JOptionPane.showMessageDialog(this, "Dorwaliśmy ich!!!");
                        resetGame();
                    } else if (logic.isPlayerLost()) {
                        JOptionPane.showMessageDialog(this, "Słabizna :(");
                        resetGame();
                    }
                });
                panel.add(buttons[r][c]);
            }
        }
    }

    private void updateStatus() {

        statusLabel.setText("Strzały: " + logic.getRemainingShots());
    }

    private void resetGame() {
        logic = new GameLogic();
        statusLabel.setText("Strzały: 8"); // reset
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                buttons[i][j].setEnabled(true);
                buttons[i][j].setText("?");
                buttons[i][j].setBackground(null);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameWindow().setVisible(true));
    }
}
