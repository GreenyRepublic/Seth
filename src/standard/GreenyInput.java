package standard;
import java.util.Scanner;
/**
 * Created by Ben Clark on 20/11/2015.
 * Ben's very own input class! Time to break free of the sample one that UCL gave us. <3
 * Contains functions for getting inputs of various types, complete with error handling if things go wrong.
 */

public class GreenyInput
        {

            //Returns user input if it is an Int.
            int getIntInput(String prompt)
            {
        int output;
        System.out.println(prompt);
        Scanner input = new Scanner(System.in);
        try
        {
            output = Integer.parseInt(input.next());
            return output;
        }
        catch (NumberFormatException n) {
            System.out.println("Invalid input!");
            return getIntInput("");
        }
    }

    //Returns user input if it is an Int within the defined limits (min and max).
    int limitIntInput(String prompt, int min, int max)
    {
        int output;
        System.out.println(prompt);
        Scanner input = new Scanner(System.in);
        try
        {
            output = Integer.parseInt(input.next());
            while (output < min || output > max)
            {
                System.out.println("Invalid input! (outside range)");
                output = Integer.parseInt(input.next());
            }
            return output;
        }
        catch (NumberFormatException n) {
            System.out.println("Invalid input!");
            return limitIntInput("", min, max);
        }
    }

    //Returns user input as a String. 'acceptempty' determines whether an empty string will be accepted or not.
    String getStringInput(String prompt, boolean acceptempty)
    {
        String output;
        System.out.println(prompt);
        Scanner input = new Scanner(System.in);
        output = input.next();
        if(acceptempty)
        {
            return output;
        }
        else
        {
            if (output.length() == 0)
            {
                System.out.print("Invalid input!");
                return getStringInput(prompt, acceptempty);
            }
            else
            {
                return output;
            }
        }
    }
}
