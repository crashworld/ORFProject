package com.myapp.other;

import com.myapp.other.data.ContactGenerator;
import java.util.*;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;

/**
 *
 * @author Posudevskiy
 */

public class ContactsDatabase
{
    private final Map<Long, ReportData> map = Collections.synchronizedMap(new HashMap<Long, ReportData>());
    private final List<ReportData> fnameIdx = Collections.synchronizedList(new ArrayList<ReportData>());
    private final List<ReportData> lnameIdx = Collections.synchronizedList(new ArrayList<ReportData>());
    private final List<ReportData> fnameDescIdx = Collections.synchronizedList(new ArrayList<ReportData>());
    private final List<ReportData> lnameDescIdx = Collections.synchronizedList(new ArrayList<ReportData>());

    /**
     * Constructor
     * 
     * @param count
     *            number of contacts to generate at startup
     */
    public ContactsDatabase(int count)
    {
        for (int i = 0; i < count; i++)
        {
            add(ContactGenerator.getInstance().generate());
        }
        updateIndecies();
    }

    /**
     * find contact by id
     * 
     * @param id
     * @return contact
     */
    public ReportData get(long id)
    {
        ReportData c = map.get(id);
        if (c == null)
        {
            throw new RuntimeException("contact with id [" + id + "] not found in the database");
        }
        return c;
    }

    protected void add(final ReportData contact)
    {
        map.put(contact.getId(), contact);
        fnameIdx.add(contact);
        lnameIdx.add(contact);
        fnameDescIdx.add(contact);
        lnameDescIdx.add(contact);
    }

    /**
     * select contacts and apply sort
     * 
     * @param first
     * @param count
     * @param sort
     * @return list of contacts
     */
    public List<ReportData> find(int first, int count, SortParam sort)
    {
        return getIndex(sort).subList(first, first + count);
    }

    protected List<ReportData> getIndex(SortParam sort)
    {
        if (sort == null)
        {
            return fnameIdx;
        }

        if (sort.getProperty().equals("firstName"))
        {
            return sort.isAscending() ? fnameIdx : fnameDescIdx;
        }
        else if (sort.getProperty().equals("lastName"))
        {
            return sort.isAscending() ? lnameIdx : lnameDescIdx;
        }
        throw new RuntimeException("unknown sort option [" + sort + "]. valid fields: [firstName], [lastName]");
    }

    /**
     * @return number of contacts in the database
     */
    public int getCount()
    {
        return fnameIdx.size();
    }

    /**
     * add contact to the database
     * 
     * @param contact
     */
    public void save(final ReportData contact)
    {
        if (contact.getId() == 0)
        {
            contact.setId(ContactGenerator.getInstance().generateId());
            add(contact);
            updateIndecies();
        }
        else
        {
            throw new IllegalArgumentException("contact [" + contact.getFirstName() +
                "] is already persistent");
        }
    }

    /**
     * delete contact from the database
     * 
     * @param contact
     */
    public void delete(final ReportData contact)
    {
        map.remove(contact.getId());

        fnameIdx.remove(contact);
        lnameIdx.remove(contact);
        fnameDescIdx.remove(contact);
        lnameDescIdx.remove(contact);

        contact.setId(0);
    }

    private void updateIndecies()
    {
        Collections.sort(fnameIdx, new Comparator<ReportData>()
        {
            public int compare(ReportData arg0, ReportData arg1)
            {
                return (arg0).getFirstName().compareTo((arg1).getFirstName());
            }
        });

        Collections.sort(lnameIdx, new Comparator<ReportData>()
        {
            public int compare(ReportData arg0, ReportData arg1)
            {
                return (arg0).getLastName().compareTo((arg1).getLastName());
            }
        });

        Collections.sort(fnameDescIdx, new Comparator<ReportData>()
        {
            public int compare(ReportData arg0, ReportData arg1)
            {
                return (arg1).getFirstName().compareTo((arg0).getFirstName());
            }
        });

        Collections.sort(lnameDescIdx, new Comparator<ReportData>()
        {
            public int compare(ReportData arg0, ReportData arg1)
            {
                return (arg1).getLastName().compareTo((arg0).getLastName());
            }
        });

    }

}
