/*
 * EditOfficer.java
 * 
 * Version 1
 *
 * 12/6/2015
 * 
 * Copyright notice
 */

package databaseprototype;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.document.*;
import java.util.*;
import org.json.simple.*;

public class EditOfficer extends LoadData
{
    Table table = dynamoDB.getTable("Officers");
    static TreeSet<Officers> matches;
    static Scanner kb = new Scanner(System.in);
    /*
     * The EditOfficer method contains the process of editing
     * an officer within the database.
     */
    public EditOfficer()
    {
        boolean found = false; 
        System.out.println("\n  Edit Officer By Id");
        String offId = getOfficerId();
        if (offId.charAt(0) != '%')
        {
            try
            {
                found = validateOfficerById(offId);
            } catch (Exception e)
            {
                System.err.println(e.getMessage());
            }
        }
        if (found)
        {
            if (enterOfficerName() || enterOfficerGender() || enterOfficerDepartment() || enterOfficerSchedule())
            {
                return;
            }
            try
            {
                String daysAvailable = "{";
                for (int i = 0; i < daysOfWeek.length; i++)
                {
                    daysAvailable = daysAvailable.concat("\"" + daysOfWeek[i] + "\" : [" + "{\"Start_Time\" : \"" + officerSchedule[i].getStartTime() + "\"," + "\"End_Time\" : \"" + officerSchedule[i].getEndTime() + "\"}],");
                }
                daysAvailable = daysAvailable.concat("}");
                editDept(offId, officerDepartment);
                editFirst(offId, officerFirstName);
                editLast(offId, officerLastName);
                editGender(offId, officerGender);
                editSched(offId , daysAvailable);
                System.out.println(offId + " successfully updated.\n");
            } catch (AmazonServiceException ase)
            {
                System.err.println("Data load script failed.");
            }
        }
    }
    /*
     * The getOfficerId method contains the accessor method
     * to get an officer's ID.
     */
    public String getOfficerId()
    {
        String id; //Officer's ID
        System.out.print("Enter Officer's Id (or % to exit): ");
        id = kb.next();
        if (id.charAt(0) == '%')
        {
            System.out.println("GoodBye!\n");
            return id;
        }
        return id;
    }
    /*
     * The validateOfficerByID method contains the process of validating
     * the officer's ID.
     */
    public boolean validateOfficerById(String theId)
    {
        matches = new TreeSet();
        ItemCollection<ScanOutcome> items = table.scan(
                null,
                null,
                null,
                null);
        Iterator<Item> iterator = items.iterator();
        TreeSet<Officers> tree = new TreeSet<>();

        while (iterator.hasNext())
        {
            Officers tmp = createOfficerObject(iterator.next().toJSONPretty());
            if (tmp != null)
            {
                tree.add(tmp);
            }
        }
        Iterator<Officers> it = tree.iterator();
        Officers temp;
        while (it.hasNext())
        {
            temp = it.next();
            if (temp.getId() != null)
            {
                if (temp.getId().equals(theId))
                {
                    matches.add(temp);
                }
            }
        }
        if (matches.size() > 0)
        {
            return true;
        }
        else
        {
            System.out.println(theId + " is not in the database.\n");
            return false;
        }
    }
    /*
     * The EditDept method contains the process of editing
     * the officer's department within the database.
     */
    public void editDept(String theId, String theDept)
    {
        Map<String, String> expressionAttributeNames = new HashMap<>();
        expressionAttributeNames.put("#D", "Department");
        Map<String, Object> expressionAttributeValues = new HashMap<>();
        expressionAttributeValues.put(":dep2", theDept);

        table.updateItem(
                "Id",
                theId,
                "set #D = :dep2",
                expressionAttributeNames,
                expressionAttributeValues
        );
    }
    /*
     * The editFirst method contains the process of editing
     * the officer's first name within the database.
     */
    public void editFirst(String theId, String theFirst)
    {
        Map<String, String> expressionAttributeNames = new HashMap<>();
        expressionAttributeNames.put("#F", "FirstName");
        Map<String, Object> expressionAttributeValues = new HashMap<>();
        expressionAttributeValues.put(":first2", theFirst);

        table.updateItem(
                "Id",
                theId,
                "set #F = :first2",
                expressionAttributeNames,
                expressionAttributeValues
        );
    }
    /*
     * The editLast method contains the process of editing
     * the officer's last name within the database.
     */
    public void editLast(String theId, String theLast)
    {
        Map<String, String> expressionAttributeNames = new HashMap<>();
        expressionAttributeNames.put("#L", "LastName");

        Map<String, Object> expressionAttributeValues = new HashMap<>();
        expressionAttributeValues.put(":last2", theLast);

        table.updateItem(
                "Id",
                theId,
                "set #L = :last2",
                expressionAttributeNames,
                expressionAttributeValues
        );
    }
    /*
     * The editGender method contains the process of editing
     * the officer's gender within the database.
     */
    public void editGender(String theId, String theGen)
    {
        Map<String, String> expressionAttributeNames = new HashMap<>();
        expressionAttributeNames.put("#G", "Gender");
        Map<String, Object> expressionAttributeValues = new HashMap<>();
        expressionAttributeValues.put(":gen2", theGen);

        table.updateItem(
                "Id",
                theId,
                "set #G = :gen2",
                expressionAttributeNames,
                expressionAttributeValues
        );
    }
    /*
     * The editSched method contains the process of editing
     * the officer's schedule within the database.
     */
    public void editSched(String theId, String theSched)
    {
        Map<String, String> expressionAttributeNames = new HashMap<>();
        expressionAttributeNames.put("#S", "Schedule");
        Map<String, Object> expressionAttributeValues = new HashMap<>();
        expressionAttributeValues.put(":sched2", theSched);

        table.updateItem(
                "Id",
                theId,
                "set #S = :sched2",
                expressionAttributeNames,
                expressionAttributeValues
        );
    }
    /*
     * The createOfficerObject method contains the process of creating
     * an officer object.
     */
    public Officers createOfficerObject(String s)
    {
        Object obj1 = JSONValue.parse(s);
        JSONObject test1 = (JSONObject) obj1;
        Officers officer = null;
        try
        {
            officer = new Officers((String) test1.get("Id"), (String) test1.get("FirstName"),
                    (String) test1.get("LastName"), (String) test1.get("Gender"),
                    (String) test1.get("Department"), (String) test1.get("Schedule"));
        } catch (Exception e)
        {
        }
        return officer;
    }
    /*
     * The enterOfficerName method contains the process of collecting user
     * input to get the officer's name.
     */
    public boolean enterOfficerName()
    {
        String userSelection;
        System.out.print("Enter Officer's Last Name (or % to exit): ");
        userSelection = kb.next();
        if (userSelection.charAt(0) == '%')
        {
            System.out.println("GoodBye!\n");
            return true;
        }
        officerLastName = userSelection;
        System.out.print("Enter Officer's First Name (or % to exit): ");
        userSelection = kb.next();
        if (userSelection.charAt(0) == '%')
        {
            System.out.println("GoodBye!\n");
            return true;
        }
        officerFirstName = userSelection;
        return false;
    }
    /*
     * The enterOfficerGender method contains the process of collecting user
     * input to get the officer's gender.
     */
    public boolean enterOfficerGender()
    {
        int userSelection;
        while (true)
        {
            try
            {
                System.out.println("\nEnter Officer's Gender");
                System.out.println("1. Female");
                System.out.println("2. Male");
                System.out.println("3. Exit");
                System.out.print("Select An Option: ");
                userSelection = kb.nextInt();
                if (userSelection < 1 || userSelection > 3)
                {
                    System.out.println("Incorrect Selection!");
                    System.out.println("Please Enter 1, 2, or 3");
                }
                else if (userSelection == 3)
                {
                    System.out.println("GoodBye!\n");
                    return true;
                }
                else
                {
                    if (userSelection == 1)
                    {
                        officerGender = "Female";
                    }
                    else if (userSelection == 2)
                    {
                        officerGender = "Male";
                    }
                    return false;
                }
            } catch (InputMismatchException e)
            {
                kb.nextLine();
                System.out.println("Incorrect Selection!");
                System.out.println("Please Enter 1, 2, or 3");
            }
        }
    }
    /*
     * The enterOfficerDepartment method contains the process of collecting user
     * input to get the officer's department
     */
    public boolean enterOfficerDepartment()
    {
        while (true)
        {
            try
            {
                System.out.println("\nEnter Officer's Department");
                System.out.println("1. State ");
                System.out.println("2. County ");
                System.out.println("3. Local ");
                System.out.println("4. Exit ");
                System.out.print("Select An Option: ");
                int userSelection = kb.nextInt();
                if (userSelection < 1 || userSelection > 4)
                {
                    System.out.println("Incorrect Selection!");
                    System.out.println("Please Enter 1, 2, 3, or 4\n");
                }
                else if (userSelection == 4)
                {
                    System.out.println("GoodBye!\n");
                    return true;
                }
                else
                {
                    if (userSelection == 1)
                    {
                        officerDepartment = "State";
                    }
                    else if (userSelection == 2)
                    {
                        officerDepartment = "County";
                    }
                    else
                    {
                        officerDepartment = "Local";
                    }
                    return false;
                }

            } catch (InputMismatchException e)
            {
                kb.nextLine();
                System.out.println("Incorrect Selection!");
                System.out.println("Please Enter 1, 2, 3, or 4\n");
            }
        }
    }
    /*
     * The enterOfficerSchedule method contains the process of collecting user
     * input to get the officer's schedule.
     */
    public boolean enterOfficerSchedule()
    {
        int startTime = 0, endTime = 0;
        System.out.println("\nEnter Officer’s Schedule (24-Hour Format)");
        System.out.println("E.g. 10:35pm = 2235 or 9:23am = 0923");

        for (int i = 0; i < daysOfWeek.length; i++)
        {
            System.out.println(daysOfWeek[i] + ": ");
            boolean flag = true;
            while (flag)
            {
                try
                {
                    System.out.print("Start Time (or -1 to exit): ");
                    startTime = kb.nextInt();
                    if (startTime == -1)
                    {
                        System.out.println("GoodBye!\n");
                        return true;
                    }
                    else if (startTime < 0 || startTime > 2400)
                    {
                        System.out.println("Incorrect Selection!");
                        System.out.println("Time Has To Be Between 0000 and 2400");
                    }
                    else
                    {
                        flag = false;
                    }
                } catch (InputMismatchException e)
                {
                    kb.nextLine();
                    System.out.println("Incorrect Selection!");
                    System.out.println("Time Has To Be Between 0000 and 2400");
                }
            }

            flag = true;
            while (flag)
            {
                try
                {
                    System.out.print("End Time (or -1 to exit): ");
                    endTime = kb.nextInt();
                    if (endTime == -1)
                    {
                        System.out.println();
                        return true;
                    }
                    else if (endTime < 0 || endTime > 2400)
                    {
                        System.out.println("Incorrect Selection!");
                        System.out.println("Time Has To Be Between 0000 and 2400");
                    }
                    else
                    {
                        flag = false;
                    }
                } catch (InputMismatchException e)
                {
                    kb.nextLine();
                    System.out.println("Incorrect Selection!");
                    System.out.println("Time Has To Be Between 0000 and 2400");
                }
            }
            OfficerTimes tmp = new OfficerTimes(startTime, endTime);
            officerSchedule[i] = tmp;
        }
        /*
         System.out.println("contents of officers schedule:");
         for (int i = 0; i < officerSchedule.length; i++)
         {
         System.out.println(daysOfWeek[i] + officerSchedule[i] + "\n");
         }
         */
        return false;
    }
}
