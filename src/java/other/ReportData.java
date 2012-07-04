/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package other;

import org.apache.wicket.IClusterable;

/**
 *
 * @author Posudevskiy
 */
public class ReportData implements IClusterable{
    private long id;

    private String firstName;

    private String lastName;

    private String homePhone;

    private String cellPhone;

    public ReportData(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public ReportData()
    {

    }

    public String toString()
    {
        return "[Contact id=" + id + " firstName=" + firstName + " lastName=" + lastName +
                " homePhone=" + homePhone + " cellPhone=" + cellPhone + "]";
    }

    public boolean equals(Object obj)
    {
        if (obj == this)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (obj instanceof ReportData)
        {
            ReportData other = (ReportData)obj;
            return other.getFirstName().equals(getFirstName()) &&
                    other.getLastName().equals(getLastName()) &&
                    other.getHomePhone().equals(getHomePhone()) &&
                    other.getCellPhone().equals(getCellPhone());

        }
        else
        {
            return false;
        }
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public long getId()
    {
        return id;
    }

    public String getCellPhone()
    {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone)
    {
        this.cellPhone = cellPhone;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getHomePhone()
    {
        return homePhone;
    }

    public void setHomePhone(String homePhone)
    {
        this.homePhone = homePhone;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
}
