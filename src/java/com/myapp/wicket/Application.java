/*
 * Application.java
 *
 * Created on 7 Июнь 2012 г., 15:03
 */
 
package com.myapp.wicket;           

import com.myapp.pages.HomePage;
import org.apache.wicket.protocol.http.WebApplication;
import com.myapp.other.ContactsDatabase;
/** 
 *
 * @author Posudevskiy
 * @version 1.0.0
 */

public class Application extends WebApplication {
private final ContactsDatabase contactsDB = new ContactsDatabase(50);
    public Application() {
    }
public ContactsDatabase getContactsDB()
    {
        return contactsDB;
    }
    public Class getHomePage() {
        return HomePage.class;
    }

}
