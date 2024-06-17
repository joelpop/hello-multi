package it.ui.view.greetinghistory;

import com.vaadin.flow.component.button.testbench.ButtonElement;
import com.vaadin.flow.component.confirmdialog.testbench.ConfirmDialogElement;
import com.vaadin.testbench.HasElementQuery;

/**
 * The page model object for ClearHistoryConfirmDialog.
 */
public class ClearHistoryConfirmDialogElement extends ConfirmDialogElement {

    /**
     * Factory method to find and return the ClearHistoryConfirmDialogElement.
     *
     * @param hasElementQuery the context of the query
     * @return the ClearHistoryConfirmDialogElement
     */
    public static ClearHistoryConfirmDialogElement $(HasElementQuery hasElementQuery) {
        return hasElementQuery.$(ClearHistoryConfirmDialogElement.class)
                .onPage()
                .withTextContaining("Clear Greeting History")
                .single();
    }

    /**
     * Keep the greeting history.
     *
     * @return the greeting history view
     */
    public GreetingHistoryViewElement keepHistory() {
        // click the keep button
        $keepButton().click();

        // return the current view
        return GreetingHistoryViewElement.get(this);
    }

    /**
     * Clear the greeting history.
     *
     * @return the greeting history view
     */
    public GreetingHistoryViewElement clearHistory() {
        // click the clear button
        $clearButton().click();

        // return the current view
        return GreetingHistoryViewElement.get(this);
    }

    /**
     * Find the keep button element.
     *
     * @return the keep button element
     */
    private ButtonElement $keepButton() {
        return $(ButtonElement.class)
                .withText("Keep")
                .single();
    }

    /**
     * Find the clear button element.
     *
     * @return the clear button element
     */
    private ButtonElement $clearButton() {
        return $(ButtonElement.class)
                .withText("Clear")
                .single();
    }
}
