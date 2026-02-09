import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    private JButton[][] buttons = new JButton[5][5];
    private GameLogic logic = new GameLogic();

    public GameWindow() {
        setTitle("Zatapianie statków by Franciszek :)");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 5));

        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                buttons[r][c] = new JButton("?");
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

                    if (logic.isGameOver()) {
                        JOptionPane.showMessageDialog(this, "Victory! All ships sunk!");
                    }
                });
                add(buttons[r][c]);
            }
        }
    }

    public static void main(String[] args) {
        new GameWindow().setVisible(true);
    }
}
