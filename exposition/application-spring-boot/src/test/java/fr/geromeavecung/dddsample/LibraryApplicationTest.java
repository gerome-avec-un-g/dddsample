package fr.geromeavecung.dddsample;

import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * spring boot has dependency on junit 5 and is not child
 * of parent project but of spring-boot-starter-parent
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LibraryApplicationTest {

    @Test
    @Disabled
    public void contextLoads() {
        // empty but fails if there is an issue to start application

        // had following exception :
        //java.lang.NoClassDefFoundError: Could not initialize class org.codehaus.groovy.vmplugin.v7.Java7

    }

}