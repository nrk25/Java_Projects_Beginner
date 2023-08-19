package passwordGenerator;

import java.util.Scanner;

public class Generator {
    Alphabet alphabet;
    public static Scanner keyboard;

    public Generator(Scanner scanner) {
        keyboard = scanner;
    }

    public Generator(boolean includeUpper, boolean includeLower, boolean includeNum, boolean includeSym) {
        alphabet = new Alphabet(includeUpper, includeLower, includeNum, includeSym);
    }

    public void mainLoop() {
        System.out.println("Welcome to Password services...");
        printMenu();

        String userOption = "-1";

        while(!userOption.equals("4")) {
            userOption = keyboard.next();

            switch (userOption) {
                case "1" -> {
                    requestPassword();
                    printMenu();
                }
                case "2" -> {
                    checkPassword();
                    printMenu();
                }
                case "3" -> {
                    printUsefulInfo();
                    printMenu();
                }
                case "4" -> printQuitMessage();

                default -> {
                    System.out.println();
                    System.out.println("Kindly select one of the available commands");
                    printMenu();
                }
            }
        }

    }

    public Password generatePassword(int length) {
        final StringBuilder pass = new StringBuilder();

        final int alphabetLength = alphabet.getAlphabet().length();

        int max = alphabetLength - 1;
        int min = 0;
        int range = max - min + 1;

        for(int i=0; i< length; i++) {
            int index = (int) (Math.random() * range) + min;
            pass.append(alphabet.getAlphabet().charAt(index));
        }

        return new Password(pass.toString());
    }

    private void printUsefulInfo() {
        System.out.println();
        System.out.println("Use a minimum password length of 8 or more characters if permitted");
        System.out.println("Include lowercase and uppercase alphabetic characters, numbers and symbols if permitted");
        System.out.println("Generate passwords randomly where feasible");
        System.out.println("Avoid using the same password twice (e.g., across multiple user accounts and/or software systems");
        System.out.println("Avoid character repetition, keyboad patterns, dictionary words, letters or number sequences," + "\nusernames, relative or pet names, romantic links (current or past)" + "and biographical information (e.g., ID numbers, ancestors names or dates).");
        System.out.println("Avoid using information that the user's colleagues and/or" + "acquaintances might know to be associated with the user");
        System.out.println("Do not use passwords which consist wholly of any simple combination of the aforementioned weak components");
    }
    private void requestPassword() {
        boolean includeUpper = false;
        boolean includeLower = false;
        boolean includeNum = false;
        boolean includeSym = false;

        boolean correctParams;

        System.out.println();
        System.out.println("Hello, welcome to the Password Generator :) answer" +
                "the following questions by Yes or No \n");

        do{
            String input;
            correctParams = false;
            do {
                System.out.println("Do you want Lowercase letters \"abcd...\" to be used ? ");
                input = keyboard.next();
                passwordRequestError(input);
            } while(!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));

            if(isInclude(input)) includeLower = true;

            do{
                System.out.println("Do you want Uppercase letters \"ABCD...\" to be used ? ");
                input = keyboard.next();
                passwordRequestError(input);
            } while(!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));

            if(isInclude(input)) includeUpper = true;

            do{
                System.out.println("Do you want Numbers \"1234...\" to be used ? ");
                input = keyboard.next();
                passwordRequestError(input);
            } while(!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));

            if(isInclude(input)) includeNum = true;

            do{
                System.out.println("Do you want Symbots \"!@#$...\" to be used ? ");
                input = keyboard.next();
                passwordRequestError(input);
            } while(!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));

            if(isInclude(input)) includeSym = true;

            //No Pool Selected
            if(!includeUpper && !includeLower && !includeNum && !includeSym) {
                System.out.println("You have selected no characters to generate your password, at least one of your answers should be Yes \n");
                correctParams = true;
            }


        } while(correctParams);

        System.out.println("Great! Now enter the length of the password");
        int length1 = keyboard.nextInt();

        final Generator generator = new Generator(includeUpper, includeLower, includeNum, includeSym);
        final Password password = generator.generatePassword(length1);

        System.out.println("Your generated password : " + password);

    }

    private void passwordRequestError(String i) {
        if(!i.equalsIgnoreCase("yes") && !i.equalsIgnoreCase("no")) {
            System.out.println("You have entered somethign incorrect let's go over it again \n");
        }
    }

    private boolean isInclude(String input) {
        if(input.equalsIgnoreCase("yes")) {
            return true;
        }
        return false;
    }

    private void checkPassword() {
        String input;

        System.out.println("Enter your password: ");
        input = keyboard.next();
        final Password p = new Password(input);
        System.out.println(p.calculateScore());
    }

    private void printMenu() {
        System.out.println();
        System.out.println("Enter 1 - Password Generator");
        System.out.println("Enter 2 - Password Strength Check");
        System.out.println("Enter 3 - useful Information");
        System.out.println("Enter 4 - Quit");
        System.out.println("Choice: ");
    }

    private void printQuitMessage() {
        System.out.println("Closing the program bye bye!!");
    }
}

