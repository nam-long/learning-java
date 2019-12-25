package learning.swing.DemoApp;

import java.awt.*;

public abstract class Pane implements PaneListener {

    private Component component;

    private boolean active;

    public Component getComponent() {
        return this.component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public void onPaneResume() {
    }

    @Override
    public void onPanePaused() {
    }
}
