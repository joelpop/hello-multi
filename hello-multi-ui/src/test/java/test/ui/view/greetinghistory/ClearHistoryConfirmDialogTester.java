package test.ui.view.greetinghistory;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonTester;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.confirmdialog.ConfirmDialogTester;
import com.vaadin.testbench.unit.ComponentTester;
import com.vaadin.testbench.unit.TesterWrappers;
import org.joelpop.hellomulti.ui.view.history.ClearHistoryConfirmDialog;

/**
 * The page model object for ClearHistoryConfirmDialog.
 */
public class ClearHistoryConfirmDialogTester extends ConfirmDialogTester
        implements TesterWrappers {

    /**
     * Wrap given component for testing.
     *
     * @param confirmDialog target component
     */
    public ClearHistoryConfirmDialogTester(ConfirmDialog confirmDialog) {
        super(confirmDialog);
    }

    /**
     * Keep the greeting history.
     *
     * @return the greeting history view
     */
    public GreetingHistoryViewTester keepHistory() {
        // click the keep button
        $keepButton().click();

        // return the current view
        return GreetingHistoryViewTester.$find(this);
    }

    /**
     * Clear the greeting history.
     *
     * @return the greeting history view
     */
    public GreetingHistoryViewTester clearHistory() {
        // click the clear button
        $clearButton().click();

        // return the current view
        return GreetingHistoryViewTester.$find(this);
    }

    /**
     * Find and return the ClearHistoryConfirmDialogTester.
     *
     * @param componentTester the context of the query
     * @return the ClearHistoryConfirmDialogTester
     * @param <T> the type of the {@link ComponentTester}
     */
    public static <T extends Component> ClearHistoryConfirmDialogTester $find(ComponentTester<T> componentTester) {
        var clearHistoryConfirmDialog = componentTester.find(ClearHistoryConfirmDialog.class)
                .withTextContaining("Clear Greeting History")
                .single();
        return new ClearHistoryConfirmDialogTester(clearHistoryConfirmDialog);
    }

    /**
     * Find the keep button and get its tester.
     *
     * @return the keep button tester
     */
    private ButtonTester<Button> $keepButton() {
        var keepButton = find(Button.class)
                .withText("Keep")
                .single();
        return test(keepButton);
    }

    /**
     * Find the clear button and get its tester.
     *
     * @return the clear button tester
     */
    private ButtonTester<Button> $clearButton() {
        var clearButton = find(Button.class)
                .withText("Clear")
                .single();
        return test(clearButton);
    }
}
