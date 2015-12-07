/*
 * LoadData.java
 * 
 * Version 1
 *
 * 12/6/2015
 * 
 * Copyright notice
 */

package databaseprototype;

import java.util.*;
import com.amazonaws.auth.profile.*;
import com.amazonaws.services.dynamodbv2.*;
import com.amazonaws.services.dynamodbv2.document.*;

public class LoadData
{
    static Scanner kb = new Scanner(System.in);
    static DynamoDB dynamoDB = new DynamoDB(new AmazonDynamoDBClient(new ProfileCredentialsProvider()));
    static String officerTableName = "Officers"; //Officer Table Name
    static String officerFirstName; //Officer's first name
    static String officerLastName; //Officer's last name
    static String officerGender; //Officer's gender
    static String officerDepartment; //Officer's department
    static OfficerTimes[] officerSchedule = new OfficerTimes[7];
    static String[] daysOfWeek =
    {
        "Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
        "Saturday", "Sunday"
    }; //An array of days of the week
    /*
     * The loadOfficerManual method contains the process of loading
     * the officer to the database.
     */
    public void loadOfficersManual()
    {   
        String daysAvailable = "{";
        for (int i = 0; i < daysOfWeek.length; i++)
        {
            daysAvailable = daysAvailable.concat("\"" + daysOfWeek[i] + "\" : [" + "{\"Start_Time\" : \"" + officerSchedule[i].getStartTime() + "\"," + "\"End_Time\" : \"" + officerSchedule[i].getEndTime() + "\"}],");
        }
        daysAvailable = daysAvailable.concat("}");
   
        Table table = dynamoDB.getTable(officerTableName);
        String theID = String.valueOf(UUID.randomUUID());
        try
        {
            Item item = new Item()
                    .withPrimaryKey("Id", theID)
                    .withString("LastName", officerLastName)
                    .withString("FirstName", officerFirstName)
                    .withString("Gender", officerGender)
                    .withString("Department", officerDepartment)
                    .withString("Schedule", daysAvailable);
            table.putItem(item);
        } catch (Exception e)
        {
            System.err.println("Failed to create item in " + officerTableName);
            System.err.println(e.getMessage());
        }
    }
}
