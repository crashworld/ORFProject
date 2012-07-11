package com.myapp.panels;

import java.util.ArrayList;
import org.apache.wicket.markup.html.panel.Panel;

/**
 *
 * @author Posudevskiy
 */
public final class GeneratedPanel extends Panel {

    private  ArrayList list1 = new ArrayList();
    char character;
    public GeneratedPanel(String id) {
        super(id);
        add(new MapPanel("mappanel"));
        add(new IntegralTablePanel("integraltable"));
        add(new ChartPanel("chartpanel"));
        add(new DifferentialTablePanel("differentaltable"));
    }
}
