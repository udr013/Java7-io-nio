package com.udr013;

import java.io.Console;

public class ConsoleStuff {

    public static void main(String[] args) {

        char[] pass = null;
        char[] passcheck = null;

        Console console = System.console();
        if (console != null) {
            String name = console.readLine("Tell me your %s", "name: ");
            while (pass == null || pass.length < 3) {
                pass = console.readPassword("Enter a password: ");
                if (pass.length > 3) {
                    console.format("saved\n");
                    break;
                }
            }
            while (passcheck == null || !new String(pass).equals(new String(passcheck))) { // String  accepts char[] in its constructor
                passcheck = console.readPassword("re-enter your password %s", name);

            }
            console.format("%s your password is set\n", name);
        } else {
            System.out.println("no console available");
        }
    }

}
