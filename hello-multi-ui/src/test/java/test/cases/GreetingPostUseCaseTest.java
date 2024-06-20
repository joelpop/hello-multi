package test.cases;

import com.vaadin.testbench.unit.SpringUIUnitTest;
import com.vaadin.testbench.unit.ViewPackages;
import org.joelpop.hellomulti.Application;
import org.joelpop.hellomulti.ui.view.hello.HelloView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import test.ui.view.hello.HelloViewTester;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for the greeting post use case.
 */
@SpringBootTest(classes = Application.class)
@ViewPackages(packages = { "org.joelpop.hellomulti.ui.view" })
class GreetingPostUseCaseTest extends SpringUIUnitTest {

    private HelloViewTester $helloView;

    @BeforeEach
    void navigateToHelloView() {
        // navigate to the view and get its tester
        var helloView = navigate(HelloView.class);
        $helloView = test(HelloViewTester.class, helloView);
    }

    /*
     * Test with empty name.
     */
    @Test
    void greetWithNameEmpty() {
        // issue greeting
        var $notification = $helloView.greetWithName("");

        // verify the greeting
        assertThat($notification.getText())
                .isEqualTo("Hello, %s!", "World");
    }

    /*
     * Test with name "World".
     */
    @Test
    void greetWithNameWorld() {
        // issue greeting
        var $notification = $helloView.greetWithName("World");

        // verify the greeting
        assertThat($notification.getText())
                .isEqualTo("Hello, %s!", "World");
    }

    /*
     * Test with name "Waldo".
     */
    @Test
    void greetWithNameWaldo() {
        // issue greeting
        var $notification = $helloView.greetWithName("Waldo");

        // verify the greeting
        assertThat($notification.getText())
                .isEqualTo("Hello, %s!", "Waldo");
    }
}
