package com.example.koverj.demo;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;

public class TimeCoderTests {

    @Test
    void testLogin() {
        Selenide.open("http://localhost:8087/login");
        $("#usernameOrEmail").setValue("demo");
        $("#password").setValue("123456");
        $("body > app-root > app-layout > div > app-login > div > div > form > div:nth-child(3) > button").click();
    }
}
