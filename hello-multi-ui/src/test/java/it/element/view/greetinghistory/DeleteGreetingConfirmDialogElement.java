package it.element.view.greetinghistory;

import com.vaadin.flow.component.button.testbench.ButtonElement;
import com.vaadin.flow.component.confirmdialog.testbench.ConfirmDialogElement;
import com.vaadin.testbench.HasElementQuery;

/**
 * The page model object for DeleteGreetingConfirmDialog.
 */
public class DeleteGreetingConfirmDialogElement extends ConfirmDialogElement {

    /**
     * Keep the greeting.
     */
    public void keepGreeting() {
        // click the keep button
        $keepButton().click();
    }

    /**
     * Clear the greeting.
     */
    public void deleteGreeting() {
        // click the delete button
        $deleteButton().click();
    }

    /**
     * Class method to find and return the DeleteGreetingConfirmDialogElement.
     *
     * @param hasElementQuery the context of the query
     * @return the DeleteGreetingConfirmDialogElement
     */
    public static DeleteGreetingConfirmDialogElement $find(HasElementQuery hasElementQuery) {
        return hasElementQuery.$(DeleteGreetingConfirmDialogElement.class)
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
