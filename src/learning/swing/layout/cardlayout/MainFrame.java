package learning.swing.layout.cardlayout;

import learning.swing.layout.cardlayout.cards.PanelOne;
import learning.swing.layout.cardlayout.cards.PanelThree;
import learning.swing.layout.cardlayout.cards.PanelTwo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    public static void main(String[] args) {

        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }

    private JPanel rootPanel;

    private JPanel contentPanel;

    private JButton previousButton;
    private JButton nextButton;

    MainFrame() {

        initUiComponents();
    }

    private void initUiComponents() {

        initCardLayoutLayout();

        initButtons();

        initFrame();
    }

    private void initCardLayoutLayout() {

        contentPanel.add("ONE", new PanelOne().getPanel());
        contentPanel.add("TWO", new PanelTwo().getPanel());
        contentPanel.add("THREE", new PanelThree().getPanel());
    }

    private void initButtons() {

        final CardLayout cards = (CardLayout) contentPanel.getLayout();

        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cards.previous(contentPanel);
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cards.next(contentPanel);
            }
        });
    }

    private void initFrame() {

        setTitle("CardLayout Demo");

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setPreferredSize(new Dimension(600, 400));
        setContentPane(rootPanel);
        pack();

        setLocationRelativeTo(null);
    }
}
