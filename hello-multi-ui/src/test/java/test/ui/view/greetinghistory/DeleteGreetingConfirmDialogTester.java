package test.ui.view.greetinghistory;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonTester;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.confirmdialog.ConfirmDialogTester;
import com.vaadin.testbench.unit.ComponentTester;
import com.vaadin.testbench.unit.TesterWrappers;
import org.joelpop.hellomulti.ui.view.history.DeleteGreetingConfirmDialog;

/**
 * The page model object for DeleteGreetingConfirmDialog.
 */
public class DeleteGreetingConfirmDialogTester extends ConfirmDialogTester
        implements TesterWrappers {

    /**
     * Factory method to find and return the DeleteGreetingConfirmDialogTester.
     *
     * @param componentTester the context of the query
     * @return the DeleteGreetingConfirmDialogTester
     */
    public static <T extends Component> DeleteGreetingConfirmDialogTester $(ComponentTester<T> componentTester) {
        return new DeleteGreetingConfirmDialogTester(componentTester.find(DeleteGreetingConfirmDialog.class)
                .withTextContaining("Delete Greeting")
                .single());
    }

    /**
     * Wrap given component for testing.
     *
     * @param confirmDialog target component
     */
    public DeleteGreetingConfirmDialogTester(ConfirmDialog confirmDialog) {
        super(confirmDialog);
    }

    /**
     * Keep the greeting.
     *
     * @return the greeting history view
     */
    public GreetingHistoryViewTester keepGreeting() {
        // click the keep button
        $keepButton().click();

        // return the current view
        return GreetingHistoryViewTester.get(this);
    }

    /**
     * Clear the greeting.
     *
     * @return the greeting history view
     */
    public GreetingHistoryViewTester deleteGreeting() {
        // click the delete button
        $deleteButton().click();

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
     * Find the delete button tester.
     *
     * @return the delete button tester
     */
    private ButtonTester<Button> $deleteButton() {
        return test(find(Button.class)
                .withText("Delete")
                .single());
    }
}
