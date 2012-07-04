/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package other;

import org.apache.wicket.model.LoadableDetachableModel;

/**
 *
 * @author Posudevskiy
 */
public class DetachableContactModel extends LoadableDetachableModel<ReportData>
{
    private final long id;

    protected ContactsDatabase getContactsDB()
    {
        return DatabaseLocator.getDatabase();
    }

    /**
     * @param c
     */
    public DetachableContactModel(ReportData c)
    {
        this(c.getId());
    }

    /**
     * @param id
     */
    public DetachableContactModel(long id)
    {
        if (id == 0)
        {
            throw new IllegalArgumentException();
        }
        this.id = id;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        return Long.valueOf(id).hashCode();
    }

    /**
     * used for dataview with ReuseIfModelsEqualStrategy item reuse strategy
     * 
     * @see org.apache.wicket.markup.repeater.ReuseIfModelsEqualStrategy
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj)
    {
        if (obj == this)
        {
            return true;
        }
        else if (obj == null)
        {
            return false;
        }
        else if (obj instanceof DetachableContactModel)
        {
            DetachableContactModel other = (DetachableContactModel)obj;
            return other.id == id;
        }
        return false;
    }

    /**
     * @see org.apache.wicket.model.LoadableDetachableModel#load()
     */
    @Override
    protected ReportData load()
    {
        // loads contact from the database
        return getContactsDB().get(id);
    }
}
