package com.myapp.other.providers;

import java.util.Iterator;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import com.myapp.other.ContactsDatabase;
import com.myapp.other.DatabaseLocator;
import com.myapp.other.models.DetachableContactModel;
import com.myapp.other.ReportData;

/**
 *
 * @author Posudevskiy
 */
public class SortableContactDataProvider extends SortableDataProvider<ReportData>
{
    
    public SortableContactDataProvider()    {
        
        setSort("firstName", SortOrder.ASCENDING);
    }

    protected ContactsDatabase getContactsDB()
    {
        return DatabaseLocator.getDatabase();
    }

    /**
     * @see org.apache.wicket.markup.repeater.data.IDataProvider#iterator(int, int)
     */
    public Iterator<ReportData> iterator(int first, int count)
    {
        return getContactsDB().find(first, count, getSort()).iterator();
    }

    /**
     * @see org.apache.wicket.markup.repeater.data.IDataProvider#size()
     */
    public int size()
    {
        return getContactsDB().getCount();
    }

    /**
     * @see org.apache.wicket.markup.repeater.data.IDataProvider#model(java.lang.Object)
     */
    public IModel<ReportData> model(ReportData object)
    {
        return new DetachableContactModel(object);
    }
    
}
