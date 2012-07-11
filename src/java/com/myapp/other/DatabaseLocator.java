package com.myapp.other;

import com.myapp.wicket.Application;

/**
 *
 * @author Posudevskiy
 */
public class DatabaseLocator
{
    /**
     * @return contacts database
     */
    private final static ContactsDatabase contactsDB = new ContactsDatabase(50);
    public static ContactsDatabase getDatabase()
    {        
        Application app = (Application)Application.get();
        return app.getContactsDB();
    }
}
