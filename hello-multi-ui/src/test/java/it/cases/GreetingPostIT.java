package it.cases;

import com.vaadin.testbench.BrowserTest;
import it.ui.BrowserDriverTestBase;
import it.ui.view.hello.HelloViewElement;
import org.junit.jupiter.api.BeforeEach;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * E2E tests for the greeting post use case.
 */
class GreetingPostIT extends BrowserDriverTestBase {

    private HelloViewElement $helloView;

    @BeforeEach
    public void initAtHelloView() {
        $helloView = HelloViewElement.$find(this);
    }

    /*
     * Test with empty name.
     */
    @BrowserTest
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
    @BrowserTest
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
    @BrowserTest
    void greetWithNameWaldo() {
        // issue greeting
        var $notification = $helloView.greetWithName("Waldo");

        // verify the greeting
        assertThat($notification.getText())
                .isEqualTo("Hello, %s!", "Waldo");
    }
}
