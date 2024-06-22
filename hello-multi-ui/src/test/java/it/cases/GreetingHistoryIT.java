package it.cases;

import com.vaadin.testbench.BrowserTest;
import it.ui.BrowserDriverTestBase;
import it.ui.view.hello.HelloViewElement;
import org.instancio.Instancio;
import org.joelpop.hellomulti.uimodel.model.Greeting;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * E2E tests for the greeting history use case.
 */
class GreetingHistoryIT extends BrowserDriverTestBase {

    private HelloViewElement $helloView;

    @BeforeEach
    public void initAtHelloView() {
        $helloView = HelloViewElement.$find(this);
    }

    /*
     * Greet and verify single greeting is correctly logged.
     */
    @BrowserTest
    @Execution(ExecutionMode.SAME_THREAD)
    void greetOnceAndVerifyGreetingHistory() {
        // generate a name
        var name = capitalize(Instancio.of(String.class)
                .create());

        greetAndVerifyGreetingHistory(name);
    }

    /*
     * Greet multiple times and verify all greetings are correctly logged.
     */
    @BrowserTest
    @Execution(ExecutionMode.SAME_THREAD)
    void greetALotAndVerifyGreetingHistory() {
        // create names
        var names = Instancio.stream(String.class)
//                .limit(50)
                .limit(10)
                .map(GreetingHistoryIT::capitalize)
                .toArray(String[]::new);

        greetAndVerifyGreetingHistory(names);
    }

    /*
     * Verify clearing logged greetings shows placeholder.
     */
    @BrowserTest
    @Execution(ExecutionMode.SAME_THREAD)
    void clearGreetingHistoryAndVerifyEmpty() {
        // click "history" link to nav to greeting history view
        var $greetingHistoryView = $helloView.viewHistory();

        var $clearHistoryConfirmDialog = $greetingHistoryView.confirmClearGreetingHistory();
        $clearHistoryConfirmDialog.clearHistory();

        // verify there are no greetings in history (placeholder is shown)
        assertThat($greetingHistoryView.isGreetingHistoryEmpty())
                .isTrue();
    }

    private void greetAndVerifyGreetingHistory(String... names) {
        var nameList = Arrays.asList(names);

        // issue a greeting for each name
        nameList.forEach($helloView::greetWithName);

        // click "history" link to nav to greeting history view
        var $greetingHistoryView = $helloView.viewHistory();

        // extract greetings from display
        var greetings = $greetingHistoryView.getGreetings();

        var topGreetings = greetings.stream().limit(names.length).toList();
        var reversedNames = nameList.reversed();

        // verify the names each have a greeting, in correct order
        assertThat(topGreetings)
                .map(Greeting::getName)
                .containsExactly(reversedNames.toArray(new String[0]));

        // verify the names each have their greeting message, in correct order
        assertThat(topGreetings)
                .map(Greeting::getMessage)
                .containsExactly(reversedNames.stream()
                        .map(name -> name.isEmpty() ? "World" : name)
                        .map("Hello, %s!"::formatted)
                        .toArray(String[]::new));
    }

    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        return Character.toUpperCase(str.charAt(0)) + str.substring(1).toLowerCase();
    }
}
