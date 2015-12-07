/*
 * Officers.java
 * 
 * Version 1
 *
 * 12/6/2015
 * 
 * Copyright notice
 */
package databaseprototype;

import org.json.simple.parser.*;
import org.json.simple.*;
/*
 * The Officers method contains the process of collecting user
 * input of the officer and setting the officer's attributes
 * (accessors/mutators).
 */
public class Officers implements Comparable
{

    private String fName; //Officer's first name
    private String lName; //Officer's last name
    private String gender; //Officer's gender
    private String Id; //Officer's ID
    private String department; //Officer's department
    private String scheduleJson; //JSON schedule
    private String schedLegible = ""; //Concatinated schedule
    private final String[] daysAvailable = new String[7]; //An array of days available
    private static final String[] daysOfWeek =
    {
        "Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
        "Saturday", "Sunday"
    }; //An array of days of the week
    /*
     * The constructor of the Officers class to set the
     * officer's attributes.
     */
    public Officers(String id, String first, String last, String gender, String theDepartment, String theSchedule)
    {
        this.Id = id;
        this.fName = first;
        this.lName = last;
        this.gender = gender;
        this.department = theDepartment;
        this.scheduleJson = theSchedule;
    }
    /*
     * The getfName method is the accessor method to get
     * the officer's first name.
     */
    public String getfName()
    {
        return fName;
    }
    /*
     * The setfName method is the mutator method to set
     * the officer's first name.
     */
    public void setfName(String afName)
    {
        fName = afName;
    }
    /*
     * The getlName method is the accessor method to get
     * the officer's last name.
     */
    public String getlName()
    {
        return lName;
    }
    /*
     * The setlName method is the mutator method to set
     * the officer's last name.
     */
    public void setlName(String alName)
    {
        lName = alName;
    }
    /*
     * The getGender method is the accessor method to get
     * the officer's gender.
     */
    public String getGender()
    {
        return gender;
    }
    /*
     * The setGender method is the mutator method to set
     * the officer's gender.
     */
    public void setGender(String aGender)
    {
        gender = aGender;
    }
    /*
     * The getId method is the accessor method to get
     * the officer's ID.
     */
    public String getId()
    {
        return Id;
    }
    /*
     * The setId method is the mutator method to set
     * the officer's ID.
     */
    public void setId(String aId)
    {
        Id = aId;
    }

    @Override
    public String toString()
    {
        if (scheduleJson != null)
        {
            JSONParser parser = new JSONParser();
            try
            {
                Object obj1 = parser.parse(scheduleJson);
                JSONObject accessor = (JSONObject) obj1;
                for (String daysOfWeek1 : daysOfWeek)
                {
                    JSONArray dayOfTheWeek = (JSONArray) accessor.get(daysOfWeek1);
                    if (dayOfTheWeek != null)
                    {
                        schedLegible = schedLegible.concat("\nDay Of The Week: " + daysOfWeek1 
                                         + "\nStart Time: " + ((JSONObject) dayOfTheWeek.get(0)).get("Start_Time") 
                                         + "\nEnd Time: " + ((JSONObject) dayOfTheWeek.get(0)).get("End_Time"));
                    }
                }
            } catch (Exception e)
            {

            }
        }
        return "Last name: " + lName
                    + "\nFirst Name: " + fName
                    + "\nGender: " + gender
                    + "\nDepartment: " + department
                    + "\nId: " + Id
                    + "\nSchedule: " + schedLegible
                    + "\n";
    }

    @Override
    public int compareTo(Object o)
    {
        try
        {
            Officers compared = ((Officers) o);
            if (this.getlName().equals(compared.getlName()))
            {
                return this.getfName().compareTo(compared.getfName());
            }
            return this.getlName().compareTo(compared.getlName());
        } catch (Exception e)
        {
        }
        return -1;
    }
    /*
     * The getDepartment method is the accessor method to get
     * the officer's department.
     */
    public String getDepartment()
    {
        return department;
    }
    /*
     * The setDepartment method is the mutator method to set
     * the officer's department.
     */
    public void setDepartment(String department)
    {
        this.department = department;
    }
    /*
     * The getScheduleJson method is the accessor method to get
     * the officer's schedule.
     */
    public String getScheduleJson()
    {
        return scheduleJson;
    }
    /*
     * The setScheduleJson method is the mutator method to set
     * the officer's schedule.
     */
    public void setScheduleJson(String scheduleJson)
    {
        this.scheduleJson = scheduleJson;
    }
}
