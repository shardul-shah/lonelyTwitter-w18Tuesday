package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by Shardul Shah on 1/18/2018.
 */

public abstract class Mood
{
    private Date date;
    private String mood;

   public Mood(Date date)
   {
       this.date = date;
   }

    public Mood()
    {
        date = new Date();
    }

    public String getMood()
    {
        return mood;
    }

    public void setMood(String mood)
    {
        this.mood = mood;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }



}
