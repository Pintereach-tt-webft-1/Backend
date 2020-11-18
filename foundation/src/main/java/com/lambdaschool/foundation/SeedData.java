package com.lambdaschool.foundation;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.lambdaschool.foundation.models.Role;
import com.lambdaschool.foundation.models.User;
import com.lambdaschool.foundation.models.UserRoles;
import com.lambdaschool.foundation.models.Userarticle;
import com.lambdaschool.foundation.services.RoleService;
import com.lambdaschool.foundation.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.util.Locale;

/**
 * SeedData puts both known and random data into the database. It implements CommandLineRunner.
 * <p>
 * CoomandLineRunner: Spring Boot automatically runs the run method once and only once
 * after the application context has been loaded.
 */
@Transactional
@Component
public class SeedData
    implements CommandLineRunner
{
    /**
     * Connects the Role Service to this process
     */
    @Autowired
    RoleService roleService;

    /**
     * Connects the user service to this process
     */
    @Autowired
    UserService userService;

    //Connection connection = dataSource.getConnection();

    /**
     * Generates test, seed data for our application
     * First a set of known data is seeded into our database.
     * Second a random set of data using Java Faker is seeded into our database.
     * Note this process does not remove data from the database. So if data exists in the database
     * prior to running this process, that data remains in the database.
     *
     * @param args The parameter is required by the parent interface but is not used in this process.
     */
    @Transactional
    @Override
    public void run(String[] args) throws
                                   Exception
    {
        userService.deleteAll();
        roleService.deleteAll();
        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("data");

        r1 = roleService.save(r1);
        r2 = roleService.save(r2);
        r3 = roleService.save(r3);

        // admin, data, user
        User u1 = new User("admin",
            "password",
            "admin@lambdaschool.local");
        u1.getRoles()
            .add(new UserRoles(u1,
                r1));
        u1.getRoles()
            .add(new UserRoles(u1,
                r2));
        u1.getRoles()
            .add(new UserRoles(u1,
                r3));
        u1.getUserarticles()
            .add(new Userarticle(u1,
                "Gaming the World: How Sports Are Reshaping Global Politics and Culture",
                    "sports",
                    1));
        u1.getUserarticles()
            .add(new Userarticle(u1,
                "Robert Hatake the lost twin brother of Kakashi",
                    "anime",
                    2));

        userService.save(u1);

        // data, user
        User u2 = new User("cinnamon",
            "password",
            "cinnamon@lambdaschool.local");
        u2.getRoles()
            .add(new UserRoles(u2,
                r2));
        u2.getRoles()
            .add(new UserRoles(u2,
                r3));
        u2.getUserarticles()
            .add(new Userarticle(u2,
                    "Why Television Is Trouncing Film at Major Media Companies",
                    "media",
                    1));
        u2.getUserarticles()
            .add(new Userarticle(u2,
                    "Reel Women: Pioneers of the Cinema, 1896 to the Present",
                    "media",
                    2));
        u2.getUserarticles()
            .add(new Userarticle(u2,
                    "Supermarkets and the Changing Cultures of Consumption",
                    "food",
                    3));
        userService.save(u2);

        // user
        User u3 = new User("barnbarn",
            "password",
            "barnbarn@lambdaschool.local");
        u3.getRoles()
            .add(new UserRoles(u3,
                r2));
        u3.getUserarticles()
            .add(new Userarticle(u3,
                    "Garfield Named World's Most Syndicated Comic Strip",
                    "comic",
                    1));
        userService.save(u3);

        User u4 = new User("puttat",
            "password",
            "puttat@school.lambda");
        u4.getRoles()
            .add(new UserRoles(u4,
                r2));
        userService.save(u4);

        User u5 = new User("misskitty",
            "password",
            "misskitty@school.lambda");
        u5.getRoles()
            .add(new UserRoles(u5,
                r2));
        userService.save(u5);

        if (false)
        {
            // using JavaFaker create a bunch of regular users
            // https://www.baeldung.com/java-faker
            // https://www.baeldung.com/regular-expressions-java

            FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-US"),
                new RandomService());
            Faker nameFaker = new Faker(new Locale("en-US"));

            for (int i = 0; i < 25; i++)
            {
                new User();
                User fakeUser;

                fakeUser = new User(nameFaker.name()
                    .username(),
                    "password",
                    nameFaker.internet()
                        .emailAddress());
                fakeUser.getRoles()
                    .add(new UserRoles(fakeUser,
                        r2));
                fakeUser.getUserarticles()
                    .add(new Userarticle(fakeUser,
                        fakeValuesService.bothify("Article"),
                            "misc",
                            1000));
                userService.save(fakeUser);
            }
        }
    }
}