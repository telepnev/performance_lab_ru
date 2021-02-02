package models;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import java.util.Locale;

public class ShodowUser {
    Faker faker =  new Faker();
    FakeValuesService fakeValuesService = new FakeValuesService(
            new Locale("en-GB"), new RandomService());

    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String userEmail = fakeValuesService.bothify("?????###@mail.com");
    String userPhone = fakeValuesService.regexify("909"+"[0-9]{8}");
    String message = faker.lorem().paragraph();
    String company = faker.company().industry().toUpperCase();

    public String getMessage() {
        return message;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getCompany() {
        return company;
    }
}
