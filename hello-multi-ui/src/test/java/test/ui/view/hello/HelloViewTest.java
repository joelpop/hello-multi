package test.ui.view.hello;

import com.vaadin.testbench.unit.SpringUIUnitTest;
import com.vaadin.testbench.unit.ViewPackages;
import org.joelpop.hellomulti.ui.view.hello.HelloView;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@ViewPackages(packages = { "org.joelpop.hellomulti.ui.view" })
class HelloViewTest extends SpringUIUnitTest {

    private HelloViewTester $helloView;

    @BeforeTestClass
    void navigateToHelloView() {
        $helloView = test(HelloViewTester.class, navigate(HelloView.class));
    }

    @Test
    void greetAnonymously() {
        greet("", "Anonymous");
    }

    @Test
    void greetWithName() {
        greet("Test", "Test");
    }

    void greet(String name, String expectedName) {
        // set the text field's value
        $helloView.$nameTextField().setValue(name);

        // click the button
        $helloView.$helloButton().click();

        // extract the notification minus the timestamp
        var greetingWithoutTimestamp = $helloView.$notification().getText()
                .replaceFirst("\\[\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{1,6}] ", "[...] ");

        // verify the greeting
        assertEquals("[...] Hello, %s!".formatted(expectedName), greetingWithoutTimestamp);
    }
}
