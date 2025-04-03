package unit.cases;

import com.vaadin.testbench.unit.SpringUIUnitTest;
import com.vaadin.testbench.unit.ViewPackages;
import org.joelpop.hellomulti.Application;
import org.joelpop.hellomulti.ui.view.hello.HelloView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import unit.ui.view.hello.HelloViewTester;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for the navigation use case.
 */
@SpringBootTest(classes = Application.class)
@ViewPackages(packages = { "org.joelpop.hellomulti.ui.view" })
class NavigationUseCaseTest extends SpringUIUnitTest {

    private HelloViewTester $helloView;

    @BeforeEach
    void navigateToHelloView() {
        // navigate to the view and get its tester
        var helloView = navigate(HelloView.class);
        $helloView = test(HelloViewTester.class, helloView);
    }

    /*
     * Navigate from the hello view to the greeting history view and then back.
     */
    @Test
    void navToHistoryAndBack() {
        // click "history" link to nav to greeting history view
        var $greetingHistoryView = $helloView.viewHistory();

        // verify the navigation was successful
        assertThat($greetingHistoryView.isUsable())
                .isTrue();

        // click "back" link to nav to hello view
        var $backHelloView = $greetingHistoryView.goBack();

        // verify the return navigation was successful
        assertThat($backHelloView.isUsable())
                .isTrue();
    }
}
