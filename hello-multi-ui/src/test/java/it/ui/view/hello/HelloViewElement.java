package it.ui.view.hello;

import com.vaadin.flow.component.button.testbench.ButtonElement;
import com.vaadin.flow.component.notification.testbench.NotificationElement;
import com.vaadin.flow.component.orderedlayout.testbench.HorizontalLayoutElement;
import com.vaadin.flow.component.textfield.testbench.TextFieldElement;
import com.vaadin.testbench.annotations.Attribute;
import org.joelpop.hellomulti.ui.view.hello.HelloView;
import org.openqa.selenium.NoSuchElementException;

@Attribute(name = "id", value = HelloView.ROUTE)
public class HelloViewElement extends HorizontalLayoutElement {

    /**
     * Enter the supplied name value, click the hello button, and return the notification.
     *
     * @param name the value to enter into the text field
     * @return the resultant pop-up notification
     */
    public NotificationElement sayHello(String name) {
        // get the name text field element and set its value
        $nameTextField().setValue(name);

        // get the button element and click it
        $nameButton().click();

        // return the resultant pop-up notification element
        return $notification();
    }

    /**
     * Find the name text field element.
     *
     * @return the name text field element
     */
    private TextFieldElement $nameTextField() {
        return $(TextFieldElement.class)
                .attribute("placeholder", "your name")
                .first();
    }

    /**
     * Find the hello button element.
     *
     * @return the hello button element
     */
    private ButtonElement $nameButton() {
        return $(ButtonElement.class).all().stream()
                .filter(buttonElement -> buttonElement.getText().equals("Hello"))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No element with tag <vaadin-button> found with text \"Hello\"."));
    }

    /**
     * Find the notification element.
     *
     * @return the notification element
     */
    private NotificationElement $notification() {
        return $(NotificationElement.class)
                .onPage()
                .first();
    }
}
