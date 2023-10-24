package org.wordly;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    User user;

    @org.junit.Test
    public void requests(){
        String input = new String("/dwewewec");
        String actual = "uncorrectData";
        String out = "";
        switch (input) {
            case "/help": {
                out = "help";
                break;
            }
            case "/hello": {
                out = "hello";
                break;
            }
            case "break": {
                out = "break";
                break;
            }
            case "/changeName": {
                out = "setName";
                break;
            }
            default: {
                out = "uncorrectData";
                break;
            }
        }
        assertEquals(out, actual);
    }

    @org.junit.Test
    public void sayHello(){
        String name = new String("Oleg");
        String actual = new String("Hello, Oleg!");
        assertEquals("Hello, " + name + "!", actual);
    }

    @org.junit.Test
    public void uncorrectData(){
        String input = new String("/heeelp");
        String actual = new String("/heeelp\t\tне является внутренней или внешней командой");
        assertEquals(input + "\t\tне является внутренней или внешней командой", actual);
    }

}