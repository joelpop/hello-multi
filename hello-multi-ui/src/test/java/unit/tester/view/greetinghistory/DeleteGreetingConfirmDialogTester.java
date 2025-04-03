package unit.tester.view.greetinghistory;

import com.vaadin.flow.component.Component;
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
     */
    public void keepGreeting() {
        // click the keep button
        cancel();
    }

    /**
     * Clear the greeting.
     */
    public void deleteGreeting() {
        // click the delete button
        confirm();
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
                .from(null)
                .single();
        return new DeleteGreetingConfirmDialogTester(deleteGreetingConfirmDialog);
    }
}
