package org.wordly;

import java.util.Scanner;

public class User {
    private String name;

    public User() {
        setName();
        sayHello();
        help();
        requests();
    }
    public void requests(){
        Scanner in = new Scanner(System.in);
        String input = "";
        boolean flag = true;
        while (flag) {
            input = in.nextLine();
            switch (input){
                case "/help": {
                    help();
                    break;
                }
                case "/sayHello": {
                    sayHello();
                    break;
                }
                case "break": {
                    flag = false;
                    break;
                }
                case "/changeName": {
                    setName();
                    break;
                }
                default: {
                    uncorrectData(input);
                    break;
                }
            }
        }
    }
    private void sayHello() {
        System.out.println("Привет, " + this.name + "!\n");
    }

    private void help() {
        System.out.println("/sayHello\t\t\tприветсвует Вас");
        System.out.println("/help\t\t\tвыводит справочную информацию");
        System.out.println("/changeName\t\tменяет ваше имя в программе\n");
    }
    private void setName() {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите ваше имя...");
        this.name = in.nextLine();
        System.out.println();
    }

    private void uncorrectData(String data) {
        System.out.println(data + "\t\tне является внутренней или внешней командой\n");
    }


}
