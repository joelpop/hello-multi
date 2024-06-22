package test.ui.view.greetinghistory;

import com.vaadin.flow.component.Component;
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
     */
    public void keepHistory() {
        // click the keep button
        cancel();
    }

    /**
     * Clear the greeting history.
     */
    public void clearHistory() {
        // click the clear button
        confirm();
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
                .from(null)
                .single();
        return new ClearHistoryConfirmDialogTester(clearHistoryConfirmDialog);
    }
}
