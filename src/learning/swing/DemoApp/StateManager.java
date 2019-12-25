package learning.swing.DemoApp;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class StateManager {

    private static class Singleton {
        private static final StateManager INSTANCE = new StateManager();
    }

    private Container parent;

    private CardLayout layoutManager;

    private Map<State, Pane> panes = new HashMap<>();

    private StateManager() {
    }

    public static StateManager getInstance() {
        return Singleton.INSTANCE;
    }

    public void initiate(Container parent) {

        this.parent = parent;
        this.layoutManager = (CardLayout) parent.getLayout();
    }

    public void add(State state, Pane pane) {

        parent.add(state.name(), pane.getComponent());
        panes.put(state, pane);
    }

    public void show(State state) {

        Pane current = panes.get(state);
        if (current != null) {

            layoutManager.show(parent, state.name());

            for (Pane pane : panes.values()) {
                if (pane.isActive()) {
                    pane.setActive(false);
                    pane.onPaneClosed();
                }
            }
            current.onPaneOpened();
            current.setActive(true);
        }
    }
}
