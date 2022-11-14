package org.example;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    @BeforeAll
    public static void printMessage() {
        System.out.println("Test Message!");
    }

}
