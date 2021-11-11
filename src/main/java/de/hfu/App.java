package de.hfu;

import java.util.Locale;
import java.util.Scanner;

/**
 * App which converts input in upperspace
 *
 */
public class App 
{
    /**
     *
     * @param args
     * command line arguments
     */
    public static void main( String[] args )
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Geben Sie einen Text ein:");
        String text = sc.nextLine();
        System.out.println(text.toUpperCase());
    }
}
