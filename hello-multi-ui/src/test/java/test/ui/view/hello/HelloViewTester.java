package test.ui.view.hello;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonTester;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationTester;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldBase;
import com.vaadin.flow.component.textfield.TextFieldTester;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.testbench.unit.ComponentQuery;
import com.vaadin.testbench.unit.ComponentTester;
import com.vaadin.testbench.unit.TesterWrappers;
import com.vaadin.testbench.unit.Tests;
import org.joelpop.hellomulti.ui.view.hello.HelloView;
import test.tester.RouterLinkTester;
import test.ui.view.greetinghistory.GreetingHistoryViewTester;

@Tests(HelloView.class)
public class HelloViewTester extends ComponentTester<HelloView>
        implements TesterWrappers {

    public static <T extends Component> HelloViewTester get(ComponentTester<T> componentTester) {
        return new HelloViewTester(componentTester.find(HelloView.class)
                .from(null)
                .single());
    }

    public HelloViewTester(HelloView helloView) {
        super(helloView);
        ensureComponentIsUsable();
    }

    /**
     * Enter the supplied name value, click the greet button, and return the notification.
     *
     * @param name the value to enter into the text field
     * @return the resultant pop-up notification
     */
    public NotificationTester<Notification> sayHello(String name) {
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
        return GreetingHistoryViewTester.get(this);
    }

    /**
     * Find the name text field tester.
     *
     * @return the name text field tester.
     */
    public TextFieldTester<TextField, String> $nameTextField() {
        return test(find(TextField.class)
                .withPropertyValue(TextFieldBase::getPlaceholder, "your name")
                .single());
    }

    /**
     * Find the greet button tester.
     *
     * @return the greet button tester.
     */
    public ButtonTester<Button> $greetButton() {
        return test(find(Button.class)
                .withCaption("Greet")
                .single());
    }

    /**
     * Find the history link tester.
     *
     * @return the history link tester.
     */
    public RouterLinkTester<RouterLink> $historyLink() {
        return new RouterLinkTester<>(find(RouterLink.class)
                .withText("See previous visitors →")
                .single());
    }

    /**
     * Find the notification tester.
     *
     * @return the notification tester.
     */
    public NotificationTester<Notification> $notification() {
        return test(new ComponentQuery<>(Notification.class)
                .first());
    }

}
