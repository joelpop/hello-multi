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
 * Unit test cases for posting greetings.
 */
@SpringBootTest(classes = Application.class)
@ViewPackages(packages = { "org.joelpop.hellomulti.ui.view" })
class SayHelloTest extends SpringUIUnitTest {

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
        greetWithName("", "World");
    }

    /*
     * Test with name "World".
     */
    @Test
    void greetWithNameWorld() {
        greetWithName("World", "World");
    }

    /*
     * Test with name "Waldo".
     */
    @Test
    void greetWithNameWaldo() {
        greetWithName("Waldo", "Waldo");
    }

    /*
     * use case flow
     */
    private void greetWithName(String name, String expectedName) {
        // issue greeting
        var $notification = $helloView.sayHello(name);

        // verify the greeting
        assertThat($notification.getText())
                .isEqualTo("Hello, %s!", expectedName);
    }
}
