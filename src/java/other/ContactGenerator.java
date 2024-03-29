/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package other;

import java.util.Collection;

/**
 *
 * @author Posudevskiy
 */
public class ContactGenerator
{
    private static ContactGenerator instance = new ContactGenerator();
    private static long nextId = 1;

    /**
     * @return static instance of generator
     */
    public static ContactGenerator getInstance()
    {
        return instance;
    }

    private final String[] firstNames = { "Jacob", "Emily", "Michael", "Sarah", "Matthew",
            "Brianna", "Nicholas", "Samantha", "Christopher", "Hailey", "Abner", "Abby", "Joshua",
            "Douglas", "Jack", "Keith", "Gerald", "Samuel", "Willie", "Larry", "Jose", "Timothy",
            "Sandra", "Kathleen", "Pamela", "Virginia", "Debra", "Maria", "Linda" };
    private final String[] lastNames = { "Smiith", "Johnson", "Williams", "Jones", "Brown",
            "Donahue", "Bailey", "Rose", "Allen", "Black", "Davis", "Clark", "Hall", "Lee",
            "Baker", "Gonzalez", "Nelson", "Moore", "Wilson", "Graham", "Fisher", "Cruz", "Ortiz",
            "Gomez", "Murray" };

    private ContactGenerator()
    {

    }

    /**
     * @return unique id
     */
    public synchronized long generateId()
    {
        return nextId++;
    }

    /**
     * generates a new contact
     * 
     * @return generated contact
     */
    public ReportData generate()
    {
        ReportData contact = new ReportData(randomString(firstNames), randomString(lastNames));
        
        contact.setId(generateId());
        contact.setHomePhone(generatePhoneNumber());
        contact.setCellPhone(generatePhoneNumber());
        return contact;
    }

    /**
     * generats <code>count</code> number contacts and puts them into <code>collection</code>
     * collection
     * 
     * @param collection
     * @param count
     */
    public void generate(Collection<ReportData> collection, int count)
    {
        for (int i = 0; i < count; i++)
        {
            collection.add(generate());
        }
    }

    private String generatePhoneNumber()
    {
        return new StringBuilder().append(rint(2, 9))
            .append(rint(0, 9))
            .append(rint(0, 9))
            .append("-555-")
            .append(rint(1, 9))
            .append(rint(0, 9))
            .append(rint(0, 9))
            .append(rint(0, 9))
            .toString();
    }

    private int rint(int min, int max)
    {
        return (int)(Math.random() * (max - min) + min);
    }

    private String randomString(String[] choices)
    {
        return choices[rint(0, choices.length)];
    }


}
