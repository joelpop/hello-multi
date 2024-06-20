package it.cases;

import com.vaadin.testbench.BrowserTest;
import it.ui.BrowserDriverTestBase;
import it.ui.view.hello.HelloViewElement;
import org.instancio.Instancio;
import org.joelpop.hellomulti.uimodel.model.Greeting;
import org.junit.jupiter.api.BeforeEach;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * E2E tester for greeting history.
 */
class GreetingHistoryIT extends BrowserDriverTestBase {

    private HelloViewElement $helloView;

    @BeforeEach
    public void initAtHelloView() {
        $helloView = HelloViewElement.get(this);
    }

    /*
     * Verify no greetings have been logged.
     */
    @BrowserTest
    void noGreetings_verifyGreetingHistory() {
        // click "history" link to nav to greeting history view
        var $greetingHistoryView = $helloView.viewHistory();

        // verify the names each have a greeting
        assertThat($greetingHistoryView.isGreetingHistoryEmpty())
                .isTrue();
    }

    /*
     * Verify greetings are logged.
     */
    @BrowserTest
    void issueGreetings_verifyGreetingHistory() {
        // create names
        var names = Instancio.stream(String.class)
                .limit(6)
                .map(GreetingHistoryIT::capitalize)
                .toList();

        // issue a greeting for each name
        names.forEach($helloView::greetWithName);

        // click "history" link to nav to greeting history view
        var $greetingHistoryView = $helloView.viewHistory();

        // extract greetings from display
        var greetings = $greetingHistoryView.getGreetings();

        // verify the names each have a greeting
        assertThat(greetings)
                .map(Greeting::getName)
                .containsExactly(names.toArray(new String[0]));
    }

    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return Character.toUpperCase(str.charAt(0)) + str.substring(1).toLowerCase();
    }
}
