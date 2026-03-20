package com.ebaytest;

public class Main {
    public static void main(String[] args) {
       BrowserCommands browserCommands = new BrowserCommands();

       try{
        browserCommands.openBrowser();
       }
       catch(Exception e){
        System.out.println("An error occurred: " + e.getMessage());

       }finally {
           browserCommands.closeBrowser();
       }
       

    }
}