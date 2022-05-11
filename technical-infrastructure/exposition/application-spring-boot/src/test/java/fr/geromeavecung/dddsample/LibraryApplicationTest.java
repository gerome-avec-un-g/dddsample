package fr.geromeavecung.dddsample;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * spring boot has dependency on junit 5 and is not child
 * of parent project but of spring-boot-starter-parent
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class LibraryApplicationTest {

    /*
     * even without tests, still load application context
     * and fails if there is an error in context
     */

    @Test
    @Disabled("see below")
    public void contextLoads() {

        // TODO have following exception :
        // java.lang.NoClassDefFoundError: Could not initialize class org.codehaus.groovy.vmplugin.v7.Java7

    }

}