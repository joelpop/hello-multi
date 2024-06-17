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
     * Factory method to find and return the ClearHistoryConfirmDialogTester.
     *
     * @param componentTester the context of the query
     * @return the ClearHistoryConfirmDialogTester
     */
    public static <T extends Component> ClearHistoryConfirmDialogTester $(ComponentTester<T> componentTester) {
        return new ClearHistoryConfirmDialogTester(componentTester.find(ClearHistoryConfirmDialog.class)
                .withTextContaining("Clear Greeting History")
                .single());
    }

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
        return GreetingHistoryViewTester.get(this);
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
        return GreetingHistoryViewTester.get(this);
    }

    /**
     * Find the keep button tester.
     *
     * @return the keep button tester
     */
    private ButtonTester<Button> $keepButton() {
        return test(find(Button.class)
                .withText("Keep")
                .single());
    }

    /**
     * Find the clear button tester.
     *
     * @return the clear button tester
     */
    private ButtonTester<Button> $clearButton() {
        return test(find(Button.class)
                .withText("Clear")
                .single());
    }
}
