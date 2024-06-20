package test.ui.view.hello.robust;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonTester;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationTester;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldTester;
import com.vaadin.testbench.unit.ComponentQuery;
import com.vaadin.testbench.unit.ComponentTester;
import com.vaadin.testbench.unit.SpringUIUnitTest;
import com.vaadin.testbench.unit.ViewPackages;
import org.joelpop.hellomulti.Application;
import org.joelpop.hellomulti.ui.view.hello.HelloView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Application.class)
@ViewPackages(packages = { "org.joelpop.hellomulti.ui.view" })
class HelloViewTest extends SpringUIUnitTest {

    private ComponentTester<HelloView> $helloView;

    @BeforeEach
    void navigateToHelloView() {
        // navigate to the view and get its tester
        var helloView = navigate(HelloView.class);
        $helloView = test(helloView);
    }

    @Test
    void greetWithNameEmpty() {
        // clear the name
        $nameTextField().clear();

        // click the button
        $greetButton().click();

        // verify the anonymous greeting uses "World"
        assertThat($notification().getText())
                .isEqualTo("Hello, World!");
    }

    @Test
    void greetWithNameWorld() {
        // set the name
        $nameTextField().setValue("World");

        // click the button
        $greetButton().click();

        // verify the greeting
        assertThat($notification().getText())
                .isEqualTo("Hello, World!");
    }

    @Test
    void greetWithNameWaldo() {
        // set the name
        $nameTextField().setValue("Waldo");

        // click the button
        $greetButton().click();

        // verify the greeting
        assertThat($notification().getText())
                .isEqualTo("Hello, Waldo!");
    }

    @Test
    void greetWithClearedName() {
        // set the name
        $nameTextField().setValue("Waldo");
        // clear the name
        $nameTextField().clear();

        // click the button
        $greetButton().click();

        // verify the greeting
        assertThat($notification().getText())
                .isEqualTo("Hello, World!");
    }

    @Test
    void greetWithChangedName() {
        // set the name
        $nameTextField().setValue("Waldo");
        // reset the name
        $nameTextField().setValue("Wilbur");

        // click the button
        $greetButton().click();

        // verify the greeting
        assertThat($notification().getText())
                .isEqualTo("Hello, Wilbur!");
    }

    // query methods

    private TextFieldTester<TextField, String> $nameTextField() {
        // find the text field and get its tester
        var nameTextField = $helloView.find(TextField.class)
                .withCaption("Name")
                .withPropertyValue(TextField::getPlaceholder, "your name")
                .single();
        return test(nameTextField);
    }

    private ButtonTester<Button> $greetButton() {
        // find the button and get its tester
        var greetButton = $helloView.find(Button.class)
                .withCaption("Greet")
                .single();
        return test(greetButton);
    }

    private NotificationTester<Notification> $notification() {
        // find the pop-up notification and get its tester
        var notification = new ComponentQuery<>(Notification.class)
                .single();
        return test(notification);
    }
}
