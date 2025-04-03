package unit.cases;

import com.vaadin.testbench.unit.SpringUIUnitTest;
import com.vaadin.testbench.unit.ViewPackages;
import org.instancio.Instancio;
import org.joelpop.hellomulti.Application;
import org.joelpop.hellomulti.ui.view.hello.HelloView;
import org.joelpop.hellomulti.uimodel.model.Greeting;
import org.joelpop.hellomulti.uimodel.service.GreetingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import unit.ui.view.hello.HelloViewTester;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for the greeting history use case.
 */
@SpringBootTest(classes = Application.class)
@ViewPackages(packages = { "org.joelpop.hellomulti.ui.view" })
class GreetingHistoryUseCaseTest extends SpringUIUnitTest {

    private final GreetingService greetingService;
    private HelloViewTester $helloView;

    public GreetingHistoryUseCaseTest(@Autowired GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @BeforeEach
    void navigateToHelloView() {
        // navigate to the view and get its tester
        var helloView = navigate(HelloView.class);
        $helloView = test(HelloViewTester.class, helloView);

        // remove previous greetings accumulated in DB
        greetingService.clear();
    }

    /*
     * Verify no greetings have been logged.
     */
    @Test
    void verifyInitialEmptyGreetingHistory() {
        // click "history" link to nav to greeting history view
        var $greetingHistoryView = $helloView.viewHistory();

        // verify there are no greetings in history (placeholder is shown)
        assertThat($greetingHistoryView.isGreetingHistoryEmpty())
                .isTrue();
    }

    /*
     * Greet and verify single greeting is correctly logged.
     */
    @Test
    void greetOnceAndVerifyGreetingHistory() {
        // generate a name
        var name = capitalize(Instancio.of(String.class)
                .create());

        greetAndVerifyGreetingHistory(name);
    }

    /*
     * Greet multiple times and verify all greetings are correctly logged.
     */
    @Test
    void greetALotAndVerifyGreetingHistory() {
        // generate names
        var names = Instancio.stream(String.class)
                .limit(100)
                .map(GreetingHistoryUseCaseTest::capitalize)
                .toArray(String[]::new);

        greetAndVerifyGreetingHistory(names);
    }

    /*
     * Verify clearing logged greetings shows placeholder.
     */
    @Test
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

        // verify there are greetings in history (placeholder is not shown)
        assertThat($greetingHistoryView.isGreetingHistoryEmpty())
                .isFalse();

        // extract greetings from display
        var greetings = $greetingHistoryView.getGreetings();
        var reversedNames = nameList.reversed();

        // verify the names each have their greeting, in correct order
        assertThat(greetings)
                .map(Greeting::getName)
                .containsExactly(reversedNames.toArray(new String[0]));

        // verify the names each have their greeting message, in correct order
        assertThat(greetings)
                .map(Greeting::getMessage)
                .containsExactly(reversedNames.stream()
                        .map(name -> name.isEmpty() ? "World" : name)
                        .map("Hello, %s!"::formatted)
                        .toArray(String[]::new));
    }

    private static String capitalize(String word) {
        if ((word == null) || word.isEmpty()) {
            return word;
        }

        return Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
    }
}
