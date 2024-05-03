package it.ui.view.hello;

import com.vaadin.testbench.BrowserTest;
import it.ui.BrowserDriverTestBase;
import org.junit.jupiter.api.BeforeEach;

import static org.assertj.core.api.Assertions.assertThat;


class HelloViewIT extends BrowserDriverTestBase {

    @BeforeEach
    public void setup()  {
        super.setUp();
    }

    /*
     * logic of tests
     */
    void setName_clickButton_notificationIsShown(String name, String expectedName) {
        var helloViewElement = $(HelloViewElement.class).onPage().first();

        // issue greeting
        var notificationElement = helloViewElement.sayHello(name);

        // extract the notificationElement minus the timestamp
        var greetingWithoutTimestamp = notificationElement.getText()
                .replaceFirst("\\[\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{1,6}] ", "[...] ");

        // verify the greeting
        assertThat(greetingWithoutTimestamp)
                .isEqualTo("[...] Hello, %s!", expectedName);
    }


    /*
     * Test with empty name.
     */
    @BrowserTest
    void setNameAnonymous_clickButton_notificationIsShown() {
        setName_clickButton_notificationIsShown("", "Anonymous");
    }

    /*
     * Test with supplied name.
     */
    @BrowserTest
    void setNameTest_clickButton_notificationIsShown() {
        setName_clickButton_notificationIsShown("Test", "Test");
    }
}
