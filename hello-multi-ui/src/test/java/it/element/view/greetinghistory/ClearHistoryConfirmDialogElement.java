package it.element.view.greetinghistory;

import com.vaadin.flow.component.button.testbench.ButtonElement;
import com.vaadin.flow.component.confirmdialog.testbench.ConfirmDialogElement;
import com.vaadin.testbench.HasElementQuery;

/**
 * The page model object for ClearHistoryConfirmDialog.
 */
public class ClearHistoryConfirmDialogElement extends ConfirmDialogElement {

    /**
     * Keep the greeting history.
     */
    public void keepHistory() {
        // click the keep button
        $keepButton().click();
    }

    /**
     * Clear the greeting history.
     */
    public void clearHistory() {
        // click the clear button
        $clearButton().click();
    }

    /**
     * Class method to find and return the ClearHistoryConfirmDialogElement.
     *
     * @param hasElementQuery the context of the query
     * @return the ClearHistoryConfirmDialogElement
     */
    public static ClearHistoryConfirmDialogElement $find(HasElementQuery hasElementQuery) {
        return hasElementQuery.$(ClearHistoryConfirmDialogElement.class)
                .onPage()
                .single();
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
