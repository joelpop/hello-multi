package org.joelpop.hellomulti.test.ui.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.testbench.unit.SpringUIUnitTest;
import com.vaadin.testbench.unit.ViewPackages;
import org.joelpop.hellomulti.ui.view.HelloView;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@ViewPackages(packages = { "org.joelpop.hellomulti.ui.view" })
class HelloViewTest extends SpringUIUnitTest {

    @BeforeTestClass
    void navigateToHelloView() {
        /* final var helloView = */ navigate(HelloView.class);
    }

    void setName_clickButton_notificationIsShown(String name, String expectedName) {
        // get the text field element and set its value
        $view(TextField.class).id(HelloView.NAME_TEXT_FIELD_ID).setValue(name);

        // get the button element and click it
        $view(Button.class).id(HelloView.HELLO_BUTTON_ID).click();

        // find the resultant pop-up notification
        var notification = $(Notification.class).first();

        // extract the notification minus the timestamp
        var greetingWithoutTimestamp = test(notification).getText()
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
