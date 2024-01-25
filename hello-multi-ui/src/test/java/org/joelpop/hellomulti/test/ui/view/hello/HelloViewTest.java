package org.joelpop.hellomulti.test.ui.view.hello;

import com.vaadin.testbench.unit.SpringUIUnitTest;
import com.vaadin.testbench.unit.ViewPackages;
import org.joelpop.hellomulti.ui.view.HelloView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@ViewPackages(packages = { "org.joelpop.hellomulti.ui.view" })
class HelloViewTest extends SpringUIUnitTest {

    private HelloViewTester<HelloView> $helloView;

    @BeforeTestClass
    void navigateToHelloView() {
        navigate(HelloView.class);
    }

    @BeforeEach
    void findHelloView() {
        $helloView = new HelloViewTester<>($view(HelloView.class).single());
    }

    void setName_clickButton_notificationIsShown(String name, String expectedName) {
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

    @Test
    void setNameAnonymous_clickButton_notificationIsShown() {
        setName_clickButton_notificationIsShown("", "Anonymous");
    }

    @Test
    void setNameTest_clickButton_notificationIsShown() {
        setName_clickButton_notificationIsShown("Test", "Test");
    }
}
