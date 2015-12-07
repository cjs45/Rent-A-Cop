/*
 * Menus.java
 * 
 * Version 1
 *
 * 12/6/2015
 * 
 * Copyright notice
 */

package databaseprototype;

import java.util.*;

public class Menus
{
    static Scanner kb = new Scanner(System.in);
    /*
     * The mainMenu method contains the menu for the user
     * to select options for the database.
     */
    public int mainMenu()
    {
        int userSelection = 0; //The user's selection
        while (true)
        {
            try
            {
                System.out.println("       Main Menu");
                System.out.println("1: Add officer");
                System.out.println("2: Get officer's information");
                System.out.println("3. Delete Officer");
                System.out.println("4. Edit Officer");
                System.out.println("5. Display database");
                System.out.println("6. Exit");
                System.out.print("Select An Option: ");
                userSelection = kb.nextInt();
                if (userSelection < 1 || userSelection > 6) //Error detection
                {
                    System.out.println("Incorrect Selection!");
                    System.out.println("Please Enter 1, 2, 3, 4, 5, or 6\n");
                }
                else
                {
                    return userSelection;
                }

            } catch (InputMismatchException e)
            {
                kb.nextLine();
                System.out.println("Incorrect Selection!");
                System.out.println("Please Enter 1, 2, 3, 4, 5, or 6\n");
            }
        }
    }
    /*
     * The searchMenu method contains the Search menu to allow the user
     * to search the database.
     */
    public int searchMenu()
    {
        int userSelection = 0; //The user's selection
        while (true)
        {
            try
            {
                System.out.println("\n       Search Options");
                System.out.println("1: Search Officer By Last Name");
                System.out.println("2: Search Officer By Gender");
                System.out.println("3: Search Officer By Department");
                System.out.println("4. Search Officer By Day Of The Week");
                System.out.println("5. Search Officer By Time Frame");
                System.out.println("6. Search Officer By Multiple");
                System.out.println("7. Exit");
                System.out.print("Select An Option: ");
                userSelection = kb.nextInt();
                if (userSelection < 1 || userSelection > 7) //Error detection
                {
                    System.out.println("Invalid Selection!");
                    System.out.println("Please Enter 1, 2, 3, 4, 5, 6, or 7");
                }
                else
                {
                    return userSelection;
                }
            } catch (InputMismatchException e)
            {
                kb.nextLine();
                System.out.println("Incorrect Selection!");
                System.out.println("Please Enter 1, 2, 3, 4, 5, 6, or 7");
            }
        }
    }
    /*
     * The addOfficerMenu method contains the Add Officer menu to allow the user
     * to add an officer the database.
     */
    public boolean addOfficerMenu()
    {
        int userSelection = 0; //The user's selection
        while (true)
        {
            try
            {
                System.out.println("\n\tAdd Another Officer");
                System.out.println("1. Yes");
                System.out.println("2. No");
                System.out.print("Would you like to add another officer: ");
                userSelection = kb.nextInt();
                if (userSelection < 1 || userSelection > 2) //Error detection
                {
                    System.out.println("Incorrect Selection!");
                    System.out.println("Please Enter 1 or 2\n");
                }
                else if (userSelection == 1)
                {
                    System.out.println("");
                    return true;
                }
                else
                {
                    System.out.println("");
                    return false;
                }
            } catch (InputMismatchException e)
            {
                kb.nextLine();
                System.out.println("Incorrect Selection!");
                System.out.println("Please Enter 1 or 2\n");
            }
        }
    }
}
