package unit.ui.view.hello;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonTester;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationTester;
import com.vaadin.flow.component.routerlink.RouterLinkTester;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldBase;
import com.vaadin.flow.component.textfield.TextFieldTester;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.testbench.unit.ComponentQuery;
import com.vaadin.testbench.unit.ComponentTester;
import com.vaadin.testbench.unit.TesterWrappers;
import com.vaadin.testbench.unit.Tests;
import org.joelpop.hellomulti.ui.view.hello.HelloView;
import unit.ui.view.greetinghistory.GreetingHistoryViewTester;

@Tests(HelloView.class)
public class HelloViewTester extends ComponentTester<HelloView>
        implements TesterWrappers {

    /**
     * Wrap given component for testing.
     *
     * @param helloView target component
     */
    public HelloViewTester(HelloView helloView) {
        super(helloView);
        ensureComponentIsUsable();
    }

    /**
     * Enter the supplied name value, click the greet button, and return the notification.
     *
     * @param name the value to enter into the text field; use {@code null} or an empty String to clear it
     * @return the resultant pop-up notification
     */
    public NotificationTester<Notification> greetWithName(String name) {
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
    public GreetingHistoryViewTester viewHistory() {
        // click the link
        $historyLink().click();

        // return the resultant greeting history view
        return GreetingHistoryViewTester.$find(this);
    }

    /**
     * Find the hello view and return its tester.
     *
     * @param componentTester the current view's tester
     * @return the greeting history view tester
     * @param <T> the type of the {@link ComponentTester}
     */
    public static <T extends Component> HelloViewTester $find(ComponentTester<T> componentTester) {
        var helloView = componentTester.find(HelloView.class)
                .from(null)
                .single();
        return new HelloViewTester(helloView);
    }

    /**
     * Find the name text field and get its tester.
     *
     * @return the name text field tester.
     */
    public TextFieldTester<TextField, String> $nameTextField() {
        var nameTextField = find(TextField.class)
                .withCaption("Name")
                .withPropertyValue(TextFieldBase::getPlaceholder, "your name")
                .single();
        return test(nameTextField);
    }

    /**
     * Find the greet button and get its tester.
     *
     * @return the greet button tester.
     */
    public ButtonTester<Button> $greetButton() {
        var greetButton = find(Button.class)
                .withCaption("Greet")
                .single();
        return test(greetButton);
    }

    /**
     * Find the history link and get its tester.
     *
     * @return the history link tester.
     */
    public RouterLinkTester<RouterLink> $historyLink() {
        var historyLink = find(RouterLink.class)
                .withText("See previous visitors →")
                .single();
        return new RouterLinkTester<>(historyLink);
    }

    /**
     * Find the first notification and get its tester.
     *
     * @return the first notification's tester.
     */
    public NotificationTester<Notification> $notification() {
        var notification = new ComponentQuery<>(Notification.class)
                .first();
        return test(notification);
    }

}
