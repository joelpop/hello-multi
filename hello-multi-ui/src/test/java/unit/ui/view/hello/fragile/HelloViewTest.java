package unit.ui.view.hello.fragile;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.testbench.unit.ComponentQuery;
import com.vaadin.testbench.unit.SpringUIUnitTest;
import com.vaadin.testbench.unit.ViewPackages;
import org.joelpop.hellomulti.Application;
import org.joelpop.hellomulti.ui.view.hello.HelloView;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Application.class)
@ViewPackages(packages = { "org.joelpop.hellomulti.ui.view" })
class HelloViewTest extends SpringUIUnitTest {

    @Test
    void greetWithNameEmpty() {
        // navigate to the view and get its tester
        var helloView = navigate(HelloView.class);
        var $helloView = test(helloView);

        // find the text field and get its tester
        var nameTextField = $helloView.find(TextField.class)
                .withCaption("Name")
                .withPropertyValue(TextField::getPlaceholder, "your name")
                .single();
        var $nameTextField = test(nameTextField);
        // clear the name
        $nameTextField.clear();

        // find the button and get its tester
        var greetButton = $helloView.find(Button.class)
                .withCaption("Greet")
                .single();
        var $greetButton = test(greetButton);
        // click the button
        $greetButton.click();

        // find the pop-up notification and get its tester
        var notification = new ComponentQuery<>(Notification.class)
                .single();
        var $notification = test(notification);

        // verify the anonymous greeting uses "World"
        assertThat($notification.getText())
                .isEqualTo("Hello, World!");
    }

    @Test
    void greetWithNameWorld() {
        // navigate to the view and get its tester
        var helloView = navigate(HelloView.class);
        var $helloView = test(helloView);

        // find the text field and get its tester
        var nameTextField = $helloView.find(TextField.class)
                .withCaption("Name")
                .withPropertyValue(TextField::getPlaceholder, "your name")
                .single();
        var $nameTextField = test(nameTextField);
        // set the name
        $nameTextField.setValue("World");

        // find the button and get its tester
        var greetButton = $helloView.find(Button.class)
                .withCaption("Greet")
                .single();
        var $greetButton = test(greetButton);
        // click the button
        $greetButton.click();

        // find the pop-up notification and get its tester
        var notification = new ComponentQuery<>(Notification.class)
                .single();
        var $notification = test(notification);

        // verify the greeting
        assertThat($notification.getText())
                .isEqualTo("Hello, World!");
    }

    @Test
    void greetWithNameWaldo() {
        // navigate to the view and get its tester
        var helloView = navigate(HelloView.class);
        var $helloView = test(helloView);

        // find the text field and get its tester
        var nameTextField = $helloView.find(TextField.class)
                .withCaption("Name")
                .withPropertyValue(TextField::getPlaceholder, "your name")
                .single();
        var $nameTextField = test(nameTextField);
        // set the name
        $nameTextField.setValue("Waldo");

        // find the button and get its tester
        var greetButton = $helloView.find(Button.class)
                .withCaption("Greet")
                .single();
        var $greetButton = test(greetButton);
        // click the button
        $greetButton.click();

        // find the pop-up notification and get its tester
        var notification = new ComponentQuery<>(Notification.class)
                .single();
        var $notification = test(notification);

        // verify the greeting
        assertThat($notification.getText())
                .isEqualTo("Hello, Waldo!");
    }

    @Test
    void greetWithClearedName() {
        // navigate to the view and get its tester
        var helloView = navigate(HelloView.class);
        var $helloView = test(helloView);

        // find the text field and get its tester
        var nameTextField = $helloView.find(TextField.class)
                .withCaption("Name")
                .withPropertyValue(TextField::getPlaceholder, "your name")
                .single();
        var $nameTextField = test(nameTextField);
        // set the name
        $nameTextField.setValue("Waldo");
        // clear the name
        $nameTextField.clear();

        // find the button and get its tester
        var greetButton = $helloView.find(Button.class)
                .withCaption("Greet")
                .single();
        var $greetButton = test(greetButton);
        // click the button
        $greetButton.click();

        // find the pop-up notification and get its tester
        var notification = new ComponentQuery<>(Notification.class)
                .single();
        var $notification = test(notification);

        // verify the greeting
        assertThat($notification.getText())
                .isEqualTo("Hello, World!");
    }

    @Test
    void greetWithChangedName() {
        // navigate to the view and get its tester
        var helloView = navigate(HelloView.class);
        var $helloView = test(helloView);

        // find the text field and get its tester
        var nameTextField = $helloView.find(TextField.class)
                .withCaption("Name")
                .withPropertyValue(TextField::getPlaceholder, "your name")
                .single();
        var $nameTextField = test(nameTextField);
        // set the name
        $nameTextField.setValue("Waldo");
        // reset the name
        $nameTextField.setValue("Wilbur");

        // find the button and get its tester
        var greetButton = $helloView.find(Button.class)
                .withCaption("Greet")
                .single();
        var $greetButton = test(greetButton);
        // click the button
        $greetButton.click();

        // find the pop-up notification and get its tester
        var notification = new ComponentQuery<>(Notification.class)
                .single();
        var $notification = test(notification);

        // verify the greeting
        assertThat($notification.getText())
                .isEqualTo("Hello, Wilbur!");
    }
}
