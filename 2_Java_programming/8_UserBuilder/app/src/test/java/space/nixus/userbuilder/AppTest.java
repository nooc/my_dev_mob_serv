/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package space.nixus.userbuilder;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    @Test void appHasAGreeting() {
        App classUnderTest = new App();
        assertNotNull(classUnderTest.getUser(), "app should have a greeting");
    }
}
