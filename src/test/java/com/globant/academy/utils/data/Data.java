package com.globant.academy.utils.data;

import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;

public class Data {
    @DataProvider(name = "credentialManager")
    public Object[][] credentialManager() {
        Faker faker = new Faker();
        int numOfUsers = 5;
        Object[][] data = new Object[numOfUsers][2];

        for (int i = 0; i < numOfUsers; i++) {
            data[i] = new Object[] {
                    faker.internet().emailAddress(),
                    faker.internet().password(7, 12, true, true)
            };
        }

        return data;
    }
}
