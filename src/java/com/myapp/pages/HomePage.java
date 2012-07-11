package com.myapp.pages;


import com.myapp.panels.GeneratedPanel;
import com.myapp.panels.SettingsPanel;
import com.myapp.panels.HeaderPanel;
import org.apache.wicket.markup.html.WebPage;

/**
 *
 * @author Posudevskiy
 */
public final class HomePage extends WebPage {

    public HomePage() {
        super();
        add(new HeaderPanel("headerpanel")); 
        add(new SettingsPanel("settingspanel"));
        add(new GeneratedPanel("generatedpanel"));
        
    }
    

}
