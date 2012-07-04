/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package other;

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
