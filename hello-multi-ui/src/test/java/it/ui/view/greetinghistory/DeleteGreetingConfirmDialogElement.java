package it.ui.view.greetinghistory;

import com.vaadin.flow.component.button.testbench.ButtonElement;
import com.vaadin.flow.component.confirmdialog.testbench.ConfirmDialogElement;
import com.vaadin.testbench.HasElementQuery;

/**
 * The page model object for DeleteGreetingConfirmDialog.
 */
public class DeleteGreetingConfirmDialogElement extends ConfirmDialogElement {

    /**
     * Factory method to find and return the DeleteGreetingConfirmDialogElement.
     *
     * @param hasElementQuery the context of the query
     * @return the DeleteGreetingConfirmDialogElement
     */
    public static DeleteGreetingConfirmDialogElement $(HasElementQuery hasElementQuery) {
        return hasElementQuery.$(DeleteGreetingConfirmDialogElement.class)
                .onPage()
                .withTextContaining("Delete Greeting")
                .single();
    }

    /**
     * Keep the greeting.
     *
     * @return the greeting history view
     */
    public GreetingHistoryViewElement keepGreeting() {
        // click the keep button
        $keepButton().click();

        // return the current view
        return GreetingHistoryViewElement.get(this);
    }

    /**
     * Clear the greeting.
     *
     * @return the greeting history view
     */
    public GreetingHistoryViewElement deleteGreeting() {
        // click the delete button
        $deleteButton().click();

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
     * Find the delete button element.
     *
     * @return the delete button element
     */
    private ButtonElement $deleteButton() {
        return $(ButtonElement.class)
                .withText("Delete")
                .single();
    }
}
