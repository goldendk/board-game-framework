package com.goldenworkshop.boardgame.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

public class ScalingJPanel extends JPanel {
    private static final Logger logger = LoggerFactory.getLogger(ScalingJPanel.class);

    public ScalingJPanel(GridLayout gridLayout) {
        super(gridLayout);
    }

    /**
     * Override the preferred size to return the largest it can, in
     * a square shape.  Must (must, must) be added to a GridBagLayout
     * as the only component (it uses the parent as a guide to size)
     * with no GridBagConstraint (so it is centered).
     */
    @Override
    public final Dimension getPreferredSize() {
        Dimension d = super.getPreferredSize();
        Dimension prefSize = null;
        Component parentComponent = getParent();
        if (parentComponent == null) {
            prefSize = new Dimension(
                    (int) d.getWidth(), (int) d.getHeight());
        } else if (parentComponent != null &&
                parentComponent.getWidth() > d.getWidth() &&
                parentComponent.getHeight() > d.getHeight()) {
            prefSize = parentComponent.getSize();
        } else {
            prefSize = d;
        }
        int w = (int) prefSize.getWidth();
        int h = (int) prefSize.getHeight();
        // the smaller of the two sizes
        int s = Math.max(w, h);
        logger.debug("Dimensions: " + s + " _ " + s);
        return new Dimension(s, s);
    }


}
