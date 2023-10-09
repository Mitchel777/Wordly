package org.wordly;

import java.util.Scanner;

public class User {
    private String name;

    public User() {
        this.getName();
        this.hello();
        this.requests();
    }
    private void requests(){
        Scanner in = new Scanner(System.in);
        String input = "";
        boolean flag = true;
        while (flag) {
            input = in.nextLine();
            switch (input){
                case "/help": {
                    System.out.println("/hello\t\tприветсвует Вас");
                    System.out.println("/help\t\tвыводит справочную информацию");
                    System.out.println("/changeName\t\tменяет ваше имя в программе");
                    break;
                }
                case "/hello": {
                    this.hello();
                    break;
                }
                case "break": {
                    flag = false;
                    break;
                }
                case "changeName": {
                    getName();
                }
                default: {
                    System.out.println(input + "\tне является внутренней или внешней командой");
                    break;
                }
            }
        }
    }
    public void hello() {
        System.out.println("Привет, " + this.name + "!");
    }
    private void getName() {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите ваше имя...");
        this.name = in.nextLine();
    }
}
