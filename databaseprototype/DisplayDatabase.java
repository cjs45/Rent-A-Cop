/*
 * DisplayDatabase.java
 * 
 * Version 1
 *
 * 12/6/2015
 * 
 * Copyright notice
 */

package databaseprototype;

import java.util.*;
import com.amazonaws.services.dynamodbv2.document.*;

public class DisplayDatabase extends GetOfficer
{   
    /*
     * The display method contains the process of displaying
     * the contents of the database to the console.
     */
    public void display()
    {
        int total = 0; //Number of items within the database
        matches = new TreeSet();
        String tableName = officerTableName;
        Table table = dynamoDB.getTable(tableName);
        ItemCollection<ScanOutcome> items = table.scan(
                null,
                null, 
                null,
                null);
        Iterator<Item> iterator = items.iterator();
        TreeSet<Officers> tree = new TreeSet<>();
        while (iterator.hasNext())
        {  
            tree.add(createOfficerObject(iterator.next().toJSONPretty()));   
            total++;
        }
        Iterator<Officers> it = tree.iterator();
        Officers temp;
        System.out.println();
        while (it.hasNext())
        {
            temp = it.next();
            if (temp != null)
            {
            System.out.println(temp);
            }
        }
        System.out.println("Database has " + total + " item(s).\n");
    }
}
