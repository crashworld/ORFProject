/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.wicket;


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
