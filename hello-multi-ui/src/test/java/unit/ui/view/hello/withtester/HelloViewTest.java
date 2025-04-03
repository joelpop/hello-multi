package unit.ui.view.hello.withtester;

import com.vaadin.testbench.unit.SpringUIUnitTest;
import com.vaadin.testbench.unit.ViewPackages;
import org.joelpop.hellomulti.Application;
import org.joelpop.hellomulti.ui.view.hello.HelloView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import unit.ui.view.hello.HelloViewTester;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Application.class)
@ViewPackages(packages = { "org.joelpop.hellomulti.ui.view" })
class HelloViewTest extends SpringUIUnitTest {

    private HelloViewTester $helloView;

    @BeforeEach
    void navigateToHelloView() {
        // navigate to the view and get its tester
        var helloView = navigate(HelloView.class);
        $helloView = test(HelloViewTester.class, helloView);
    }

    @Test
    void greetWithNameEmpty() {
        // clear the name
        $helloView.$nameTextField().clear();

        // click the button
        $helloView.$greetButton().click();

        // verify the greeting
        assertThat($helloView.$notification().getText())
                .isEqualTo("Hello, World!");
    }

    @Test
    void greetWithNameWorld() {
        // set the name
        $helloView.$nameTextField().setValue("World");

        // click the button
        $helloView.$greetButton().click();

        // verify the greeting
        assertThat($helloView.$notification().getText())
                .isEqualTo("Hello, World!");
    }

    @Test
    void greetWithNameWaldo() {
        // set the name
        $helloView.$nameTextField().setValue("Waldo");

        // click the button
        $helloView.$greetButton().click();

        // verify the greeting
        assertThat($helloView.$notification().getText())
                .isEqualTo("Hello, Waldo!");
    }

    @Test
    void greetWithClearedName() {
        // set the name
        $helloView.$nameTextField().setValue("Waldo");
        // clear the name
        $helloView.$nameTextField().clear();

        // click the button
        $helloView.$greetButton().click();

        // verify the greeting
        assertThat($helloView.$notification().getText())
                .isEqualTo("Hello, World!");
    }

    @Test
    void greetWithChangedName() {
        // set the name
        $helloView.$nameTextField().setValue("Waldo");
        // reset the name
        $helloView.$nameTextField().setValue("Wilbur");

        // click the button
        $helloView.$greetButton().click();

        // verify the greeting
        assertThat($helloView.$notification().getText())
                .isEqualTo("Hello, Wilbur!");
    }
}
