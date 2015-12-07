/*
 * OfficerTimes.java
 * 
 * Version 1
 *
 * 12/6/2015
 * 
 * Copyright notice
 */

package databaseprototype;

public class OfficerTimes
{
    private int startTime = 0; //Officer's start time
    private int endTime = 0; //Officer's end time
    /*
     * The OfficerTimes constructor to set the officer's
     * start and end time.
     */
    public OfficerTimes(int theStartTime, int theEndTime)
    {
        startTime = theStartTime;
        endTime = theEndTime;
    }
    /*
     * The getStartTime method is the accessor method to get
     * the officer's start time.
     */
    public int getStartTime()
    {
        return startTime;
    }
    /*
     * The setStartTime method is the mutator method to set
     * the officer's start time.
     */
    public void setStartTime(int startTime)
    {
        this.startTime = startTime;
    }
    /*
     * The getEndTime method is the accessor method to get
     * the officer's end time.
     */
    public int getEndTime()
    {
        return endTime;
    }
    /*
     * The setEndTime method is the mutator method to set
     * the officer's end time.
     */
    public void setEndTime(int endTime)
    {
        this.endTime = endTime;
    }
    
    @Override
    public String toString()
    {
        return "\nStart Time: " + startTime + "\nEnd Time: " + endTime;
    }
}
