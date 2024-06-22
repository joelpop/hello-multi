package it.ui.view.hello;

import com.vaadin.flow.component.button.testbench.ButtonElement;
import com.vaadin.flow.component.html.testbench.AnchorElement;
import com.vaadin.flow.component.notification.testbench.NotificationElement;
import com.vaadin.flow.component.orderedlayout.testbench.HorizontalLayoutElement;
import com.vaadin.flow.component.textfield.testbench.TextFieldElement;
import com.vaadin.testbench.HasElementQuery;
import com.vaadin.testbench.annotations.Attribute;
import it.ui.view.greetinghistory.GreetingHistoryViewElement;
import org.joelpop.hellomulti.ui.view.hello.HelloView;

/**
 * The page model object for HelloView.
 */
@Attribute(name = "id", value = HelloView.ID)
public class HelloViewElement extends HorizontalLayoutElement {

    /**
     * Enter the supplied name value, click the greet button, and return the notification.
     *
     * @param name the value to enter into the text field; use {@code null} or an empty String to clear it
     * @return the resultant pop-up notification
     */
    public NotificationElement greetWithName(String name) {
        // set the value of the name text field
        $nameTextField().setValue(name);

        // click the button
        $greetButton().click();

        // return the resultant pop-up notification
        return $notification();
    }

    /**
     * Navigate to the greeting history view.
     *
     * @return the resultant greeting history view
     */
    public GreetingHistoryViewElement viewHistory() {
        // click the link
        $historyLink().click();

        // return the resultant greeting history view
        return $greetingHistoryView();
    }

    /**
     * Class method to find and return the HelloViewElement.
     *
     * @param hasElementQuery the context of the query
     * @return the HelloViewElement
     */
    public static HelloViewElement $find(HasElementQuery hasElementQuery) {
        return hasElementQuery.$(HelloViewElement.class)
                .onPage()
                .first();
    }

    /**
     * Find the name text field element.
     *
     * @return the name text field element
     */
    private TextFieldElement $nameTextField() {
        return $(TextFieldElement.class)
                .withLabel("Name")
                .withPropertyValue(TextFieldElement::getPlaceholder, "your name")
                .single();
    }

    /**
     * Find the greet button element.
     *
     * @return the greet button element
     */
    private ButtonElement $greetButton() {
        return $(ButtonElement.class)
                .withText("Greet")
                .single();
    }

    /**
     * Find the history link element.
     *
     * @return the history link element
     */
    private AnchorElement $historyLink() {
        return $(AnchorElement.class)
                .withText("See previous visitors →")
                .single();
    }

    /**
     * Find the first notification element.
     *
     * @return the first notification element
     */
    private NotificationElement $notification() {
        return $(NotificationElement.class)
                .onPage()
                .first();
    }

    private GreetingHistoryViewElement $greetingHistoryView() {
        return GreetingHistoryViewElement.$find(this);
    }
}
