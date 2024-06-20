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
        return GreetingHistoryViewTester.$find(this);
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
        return GreetingHistoryViewTester.$find(this);
    }

    /**
     * Find and return the DeleteGreetingConfirmDialogTester.
     *
     * @param componentTester the context of the query
     * @return the DeleteGreetingConfirmDialogTester
     * @param <T> the type of the {@link ComponentTester}
     */
    public static <T extends Component> DeleteGreetingConfirmDialogTester $find(ComponentTester<T> componentTester) {
        var deleteGreetingConfirmDialog = componentTester.find(DeleteGreetingConfirmDialog.class)
                .withTextContaining("Delete Greeting")
                .single();
        return new DeleteGreetingConfirmDialogTester(deleteGreetingConfirmDialog);
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
     * Find the delete button and get its tester.
     *
     * @return the delete button tester
     */
    private ButtonTester<Button> $deleteButton() {
        var deleteButton = find(Button.class)
                .withText("Delete")
                .single();
        return test(deleteButton);
    }
}
