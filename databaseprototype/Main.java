/*
 * Main.java
 * 
 * Version 1
 *
 * 12/6/2015
 * 
 * Copyright notice
 */

package databaseprototype;

public class Main
{
	/*
	 * The main method contains the process of collecting user
	 * input to make selections to the main menu.
 	 */
    public static void main(String[] args) throws Exception
    {
        boolean flag = true; //The condition of the while loop
        while (flag)
        {
            Menus menu = new Menus();
            int userSelection = menu.mainMenu(); //The user's selection
            if (userSelection == 1)
            {
                EnterData data = new EnterData();
                data.enterOfficerInfo();
            }
            else if (userSelection == 2)
            {
                new SearchOfficer();
            }
            else if (userSelection == 3)
            {
                new DeleteOfficer();
            }
            else if (userSelection == 4)
            {
                new EditOfficer();
            }
            else if (userSelection == 5)
            {
                DisplayDatabase db = new DisplayDatabase();
                db.display();
            }
            else
            {
                System.out.println("GoodBye!\n");
                return;
            }
        }
    }
}
