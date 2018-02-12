package org.eshop.eshopping.controller;

public class Scrapclass {
    public static void main(String args[]) {
        System.out.println(multiplyString("Rohan\n", 5));
    }
    
    private static String multiplyString(String input, int count) {
        return new String(new char[count]).replace("\0", input);
    }
}