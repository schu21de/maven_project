package de.hfu;

import java.util.Locale;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Geben Sie einen Text ein:");
        String text = sc.nextLine();
        System.out.println(text.toUpperCase());
    }
}
