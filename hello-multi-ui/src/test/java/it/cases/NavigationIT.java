package it.cases;

import com.vaadin.testbench.BrowserTest;
import it.ui.BrowserDriverTestBase;
import it.ui.view.hello.HelloViewElement;
import org.junit.jupiter.api.BeforeEach;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * E2E tests for the navigation use case.
 */
public class NavigationIT extends BrowserDriverTestBase {

    private HelloViewElement $helloView;

    @BeforeEach
    public void initAtHelloView() {
        $helloView = HelloViewElement.$find(this);
    }

    /*
     * Navigate from the hello view to the greeting history view and then back.
     */
    @BrowserTest
    void navToHistoryAndBack() {
        // click "history" link to nav to greeting history view
        var $greetingHistoryView = $helloView.viewHistory();

        // verify the navigation was successful
        assertThat($greetingHistoryView.isDisplayed())
                .isTrue();

        // click "back" link to nav to hello view
        var $backHelloView = $greetingHistoryView.goBack();

        // verify the return navigation was successful
        assertThat($backHelloView.isDisplayed())
                .isTrue();
    }
}
