package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by Shardul Shah on 1/18/2018.
 */

public class Happy extends Mood
{
    public Happy(Date date)
    {
        super(date);
    }

    public Happy()
    {
        super();
    }

    public String happyMsg()
    {
        return "Yayyyy I am so happy!!!!";
    }
}
