package org.wordly;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.lang.reflect.Method;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    private User user = new User(true);
    @ParameterizedTest
    @MethodSource("processTest")
    public void processTest(String command, String expected) {
        try {
            user.setName("Вася");
            Method method = User.class.getDeclaredMethod("process", String.class);
            method.setAccessible(true);
            assertEquals(expected, method.invoke(user, command));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    static Stream<Arguments> processTest() {
        return Stream.of(Arguments.of("/help", "/sayHello\t\t\tприветсвует Вас\n/help\t\t\tвыводит справочную информацию\n/changeName\t\tменяет ваше имя в программе\n"), Arguments.of("/sayHello", "Привет, Вася!\n"), Arguments.of("/heeelp", "/heeelp\t\tне является внутренней или внешней командой\n"));
    }
}