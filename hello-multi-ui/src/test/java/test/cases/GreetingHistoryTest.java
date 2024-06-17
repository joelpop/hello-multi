package test.cases;

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
import test.ui.view.hello.HelloViewTester;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test cases for greeting history.
 */
@SpringBootTest(classes = Application.class)
@ViewPackages(packages = { "org.joelpop.hellomulti.ui.view" })
class GreetingHistoryTest extends SpringUIUnitTest {

    private final GreetingService greetingService;
    private HelloViewTester $helloView;

    public GreetingHistoryTest(@Autowired GreetingService greetingService) {
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
     * Say hello and verify greeting is correctly logged.
     */
    @Test
    void sayHelloOnceAndVerifyGreetingHistory() {
        // generate a name
        var name = capitalize(Instancio.of(String.class)
                .create());

        sayHelloAndVerifyGreetingHistory(name);
    }

    /*
     * Say hello multiple times and verify greetings are correctly logged.
     */
    @Test
    void sayHelloALotAndVerifyGreetingHistory() {
        // generate names
        var names = Instancio.stream(String.class)
                .limit(100)
                .map(GreetingHistoryTest::capitalize)
                .toArray(String[]::new);

        sayHelloAndVerifyGreetingHistory(names);
    }

    private void sayHelloAndVerifyGreetingHistory(String... names) {
        var nameList = Arrays.asList(names);

        // issue a greeting for each name
        nameList.forEach($helloView::sayHello);

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
