package it.cases;

import com.vaadin.testbench.BrowserTest;
import it.ui.BrowserDriverTestBase;
import it.ui.view.hello.HelloViewElement;
import org.junit.jupiter.api.BeforeEach;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * E2E test cases for posting greetings.
 */
class SayHelloIT extends BrowserDriverTestBase {

    private HelloViewElement $helloView;

    @BeforeEach
    public void initAtHelloView() {
        $helloView = HelloViewElement.get(this);
    }

    /*
     * Test with empty name.
     */
    @BrowserTest
    void greetWithNameEmpty() {
        greetWithName("", "World");
    }

    /*
     * Test with name "World".
     */
    @BrowserTest
    void greetWithNameWorld() {
        greetWithName("World", "World");
    }

    /*
     * Test with name "Waldo".
     */
    @BrowserTest
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
