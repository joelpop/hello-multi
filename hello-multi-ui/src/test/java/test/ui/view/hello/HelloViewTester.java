package test.ui.view.hello;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonTester;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationTester;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldBase;
import com.vaadin.flow.component.textfield.TextFieldTester;
import com.vaadin.testbench.unit.ComponentQuery;
import com.vaadin.testbench.unit.ComponentTester;
import com.vaadin.testbench.unit.TesterWrappers;
import com.vaadin.testbench.unit.Tests;
import org.joelpop.hellomulti.ui.view.hello.HelloView;

@Tests(HelloView.class)
public class HelloViewTester extends ComponentTester<HelloView>
        implements TesterWrappers {

    public HelloViewTester(HelloView helloView) {
        super(helloView);
        ensureComponentIsUsable();
    }

    /**
     * Get the name text field tester.
     *
     * @return the name text field tester.
     */
    public TextFieldTester<TextField, String> $nameTextField() {
        return test(find(TextField.class)
                .withPropertyValue(TextFieldBase::getPlaceholder, "your name")
                .single());
    }

    /**
     * Get the hello button tester.
     *
     * @return the hello button tester.
     */
    public ButtonTester<Button> $helloButton() {
        return test(find(Button.class)
                .withCaption("Hello")
                .single());
    }

    /**
     * Get the notification tester.
     *
     * @return the notification tester.
     */
    public NotificationTester<Notification> $notification() {
        return test(new ComponentQuery<>(Notification.class)
                .single());
    }
}
