package learning.swing.DemoApp;

import learning.swing.DemoApp.state.action.ActionState;
import learning.swing.DemoApp.state.intro.IntroState;
import learning.swing.DemoApp.state.main.MainState;

import javax.swing.*;

public class MainFrame extends JFrame {

    private JPanel rootPanel;
    private JPanel contentPanel;

    private final StateManager stateManager;

    public MainFrame() {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(rootPanel);

        stateManager = StateManager.getInstance();
        stateManager.initiate(contentPanel);

        initComponents();

        open(State.INTRO);
    }

    private void initComponents() {

        // Màn hình giới thiệu (có thể là Logo, Slash,...)
        stateManager.add(State.INTRO, new IntroState());
        // Màn hình menu chính của ứng dụng
        stateManager.add(State.MAIN, new MainState());
        // Đối với game, đây là màn hình chơi game
        stateManager.add(State.ACTION, new ActionState());
    }

    public void open(final State state) {
        stateManager.show(state);
    }
}
