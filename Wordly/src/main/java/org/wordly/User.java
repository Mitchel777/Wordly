package org.wordly;

import java.util.Scanner;

public class User {
    private String name;

    public User(boolean test) {
        if (!test) {
            System.out.println(setName(inputName()));
            System.out.println(help());
        }
    }

    public void requests(){
        Scanner in = new Scanner(System.in);
        String input = "";
        boolean flag = true;
        while (flag) {
            input = in.nextLine();
            System.out.println(process(input));
        }
    }
    private String sayHello() {
        return "Привет, " + name + "!\n";
    }

    private String help() {
        return "/sayHello\t\t\tприветсвует Вас\n/help\t\t\tвыводит справочную информацию\n/changeName\t\tменяет ваше имя в программе\n";
    }

    private String inputName(){
        Scanner in = new Scanner(System.in);
        System.out.println("Введите ваше имя...");
        return in.nextLine();
    }
    public String setName(String name) {
        this.name = name;
        return sayHello();
    }

    private String uncorrectData(String data) {
        return data + "\t\tне является внутренней или внешней командой\n";
    }


    private String process(String input) {
        String message = "";
        switch (input) {
            case "/help": {
                message = help();
                break;
            }
            case "/sayHello": {
                message = sayHello();
                break;
            }
            case "/changeName": {
                message = setName(inputName());
                break;
            }
            default: {
                message = uncorrectData(input);
                break;
            }
        }
        return message;
    }

}
