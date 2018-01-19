package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by Shardul Shah on 1/18/2018.
 */

public class Sad extends Mood
{
    public Sad(Date date)
    {
        super(date);
    }

    public Sad()
    {
        super();
    }

    public String sadMsg()
    {
        return "I feel like crying right now... :(";
    }

    //super.
}
