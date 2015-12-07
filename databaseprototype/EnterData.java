/*
 * EnterData.java
 * 
 * Version 1
 *
 * 12/6/2015
 * 
 * Copyright notice
 */

package databaseprototype;

import java.util.*;
import com.amazonaws.*;

public class EnterData extends LoadData
{
    /*
     * The enterOfficerInfo method contains the process of collecting user
     * input of officer's information.
     */
    public void enterOfficerInfo()
    {
        Menus menu = new Menus();
        System.out.println("\n    Add Officer To Database");
        do
        {
            if (enterOfficerName() || enterOfficerGender() || enterOfficerDepartment() || enterOfficerSchedule())
            {
                return;
            }
            try
            {
                loadOfficersManual();
            } catch (AmazonServiceException ase)
            {
                System.err.println("Data load script failed.");
            }
        } while (menu.addOfficerMenu());
    }
    /*
     * The enterOfficerName method contains the process of collecting user
     * input of an officer's name.
     */
    public boolean enterOfficerName()
    {
        String userSelection; //The user's selection
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
     * input of an officer's gender.
     */
    public boolean enterOfficerGender()
    {
        int userSelection; //The user's selection
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
     * input of an officer's department.
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
     * The enterOfficersSchedule method contains the process of collecting user
     * input of an officer's schedule.
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
        return false;
    }
}
