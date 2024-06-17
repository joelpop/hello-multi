package it.ui.view.greetinghistory;

import com.vaadin.flow.component.button.testbench.ButtonElement;
import com.vaadin.flow.component.confirmdialog.testbench.ConfirmDialogElement;
import com.vaadin.flow.component.html.testbench.AnchorElement;
import com.vaadin.flow.component.html.testbench.DivElement;
import com.vaadin.flow.component.html.testbench.SpanElement;
import com.vaadin.flow.component.orderedlayout.testbench.VerticalLayoutElement;
import com.vaadin.testbench.HasElementQuery;
import com.vaadin.testbench.annotations.Attribute;
import it.element.VirtualListPlusElement;
import it.ui.view.hello.HelloViewElement;
import org.joelpop.hellomulti.ui.view.history.GreetingHistoryView;
import org.joelpop.hellomulti.uimodel.model.Greeting;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * The page model object for GreetingHistoryView.
 */
@Attribute(name = "id", value = GreetingHistoryView.ID)
public class GreetingHistoryViewElement extends VerticalLayoutElement {

    /**
     * Class method to find and return the GreetingHistoryViewElement.
     *
     * @param hasElementQuery the context of the query
     * @return the GreetingHistoryViewElement
     */
    public static GreetingHistoryViewElement get(HasElementQuery hasElementQuery) {
        return hasElementQuery.$(GreetingHistoryViewElement.class)
                .onPage()
                .first();
    }

    public HelloViewElement goBack() {
        // click the back link
        $backLink().click();

        // return the HelloView
        return HelloViewElement.get(this);
    }

    /**
     * Refresh the greeting history.
     *
     * @return the greeting history view
     */
    public GreetingHistoryViewElement refresh() {
        // click the refresh button
        $refreshButton().click();

        // return the current view
        return this;
    }

    /**
     * Potentially clear the greeting history.
     *
     * @return the resultant clear greeting history confirmation dialog
     */
    public ConfirmDialogElement confirmClearGreetingHistory() {
        // click the clear button
        $clearButton().click();

        // return the clear greeting history confirmation dialog
        return ClearHistoryConfirmDialogElement.$(this);
    }

    /**
     * Potentially delete a greeting.
     *
     * @param index the index of the greeting displayed
     * @return the resultant delete greeting confirmation dialog
     */
    public ConfirmDialogElement confirmDeleteGreeting(int index) {
        // find the item
        var $item = $greetingHistoryVirtualListRow(index);

        // click the item's delete button
        $item.click();

        // return the delete greeting confirmation dialog
        return DeleteGreetingConfirmDialogElement.$(this);
    }

    public boolean isGreetingHistoryEmpty() {
        return $(VerticalLayoutElement.class)
                .withTextContaining("There are no greetings in the history log.")
                .single()
                .isDisplayed();
    }

    public List<Greeting> getGreetings() {
        var $greetingHistoryVirtualList = $greetingHistoryVirtualList();

        var rowCount = $greetingHistoryVirtualList.getRowCount();
        if (rowCount == 0) {
            return List.of();
        }

        return $greetingHistoryVirtualList.getRows(0, rowCount - 1).stream()
                .map(this::toGreeting)
                .toList();
    }

    public Greeting getGreeting(int index) {
        return toGreeting($greetingHistoryVirtualList().getRow(index));
    }

    private Greeting toGreeting(DivElement $div) {
        var $greeting = $div.$(DivElement.class).single();

        var timestamp = $greeting.$(SpanElement.class).get(0).getText();
        var name = $greeting.$(SpanElement.class).get(1).getText();
        var message = $greeting.$(SpanElement.class).get(2).getText();

        var greeting = new Greeting();
        greeting.setTimestamp(parseTimestamp(timestamp));
        greeting.setName(name);
        greeting.setMessage(message);
        return greeting;
    }

    private Instant parseTimestamp(String formattedTimestamp) {
        // parse the string to LocalDateTime using the client's time zone
        var localDateTime = LocalDateTime.parse(formattedTimestamp, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        // convert LocalDateTime to Instant in "UTC"
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant();
    }

    /**
     * Find the back link element.
     *
     * @return the back link element
     */
    private AnchorElement $backLink() {
        return $(AnchorElement.class)
                .withText("← Back")
                .single();
    }

    /**
     * Find the refresh button element.
     *
     * @return the refresh button element
     */
    private ButtonElement $refreshButton() {
        return $(ButtonElement.class)
                .withText("Refresh")
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

    /**
     * Find the greeting history virtual list element.
     *
     * @return the greeting history virtual list element
     */
    private VirtualListPlusElement $greetingHistoryVirtualList() {
        return $(VirtualListPlusElement.class)
                .single();
    }

    /**
     * Find a row in the greeting history virtual list element.
     *
     * @param index the requested row
     * @return the indicated row element from the greeting history virtual list element
     */
    private DivElement $greetingHistoryVirtualListRow(int index) {
        var $greetingHistoryVirtualList = $greetingHistoryVirtualList();
        if (!$greetingHistoryVirtualList.isRowInView(index)) {
            $greetingHistoryVirtualList.scrollToRow(index);
        }
        return $greetingHistoryVirtualList.getRow(index);
    }
}
