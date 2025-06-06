package unit.tester.view.greetinghistory;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonTester;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.html.testbench.DivTester;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.routerlink.RouterLinkTester;
import com.vaadin.flow.component.virtuallist.VirtualList;
import com.vaadin.flow.component.virtuallist.VirtualListTester;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.testbench.unit.ComponentTester;
import com.vaadin.testbench.unit.TesterWrappers;
import com.vaadin.testbench.unit.Tests;
import org.joelpop.hellomulti.ui.view.history.GreetingHistoryView;
import org.joelpop.hellomulti.uimodel.model.Greeting;
import unit.tester.view.hello.HelloViewTester;

import java.util.List;
import java.util.stream.IntStream;

@Tests(GreetingHistoryView.class)
public class GreetingHistoryViewTester extends ComponentTester<GreetingHistoryView>
        implements TesterWrappers {

    /**
     * Wrap given component for testing.
     *
     * @param greetingHistoryView target component
     */
    public GreetingHistoryViewTester(GreetingHistoryView greetingHistoryView) {
        super(greetingHistoryView);
        ensureComponentIsUsable();
    }

    public HelloViewTester goBack() {
        // click the back link
        $backLink().click();

        // return the HelloView tester
        return HelloViewTester.$find(this);
    }

    /**
     * Refresh the greeting history.
     *
     * @return the greeting history view tester
     */
    public GreetingHistoryViewTester refresh() {
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
    public ClearHistoryConfirmDialogTester confirmClearGreetingHistory() {
        // click the clear button
        $clearButton().click();

        // return the clear greeting history confirmation dialog
        return ClearHistoryConfirmDialogTester.$find(this);
    }

    /**
     * Potentially delete a greeting.
     *
     * @param index the index of the greeting displayed
     * @return the resultant delete greeting confirmation dialog
     */
    public DeleteGreetingConfirmDialogTester confirmDeleteGreeting(int index) {
        // find the item
        var $item = $greetingHistoryVirtualListRow(index);

        // click the item's delete button
        $item.click();

        // return the delete greeting confirmation dialog
        return DeleteGreetingConfirmDialogTester.$find(this);
    }

    public boolean isGreetingHistoryEmpty() {
        try {
            return find(VerticalLayout.class)
                    .thenOnFirst(Span.class)
                    .withTextContaining("There are no greetings in the history log.")
                    .single()
                    .isVisible();
        } catch (Exception ignored) {
            return false;
        }
    }

    public List<Greeting> getGreetings() {
        var $greetingHistoryVirtualList = $greetingHistoryVirtualList();

        var rowCount = $greetingHistoryVirtualList.size();
        if (rowCount == 0) {
            return List.of();
        }

        return IntStream.range(0, rowCount)
                .mapToObj($greetingHistoryVirtualList::getItem)
                .toList();
    }

    public Greeting getGreeting(int index) {
        return $greetingHistoryVirtualList().getItem(index);
    }

    /**
     * Find the greeting history view and return its tester.
     *
     * @param componentTester the current view's tester
     * @return the greeting history view tester
     * @param <T> the type of the {@link ComponentTester}
     */
    public static <T extends Component> GreetingHistoryViewTester $find(ComponentTester<T> componentTester) {
        var greetingHistoryView = componentTester.find(GreetingHistoryView.class)
                .from(null)
                .single();
        return new GreetingHistoryViewTester(greetingHistoryView);
    }

    /**
     * Find the back link and get its tester.
     *
     * @return the back link tester.
     */
    private RouterLinkTester<RouterLink> $backLink() {
        var backLink = find(RouterLink.class)
                .withText("← Back")
                .single();
        return new RouterLinkTester<>(backLink);
    }

    /**
     * Find the refresh button and get its tester.
     *
     * @return the refresh button tester.
     */
    private ButtonTester<Button> $refreshButton() {
        var refreshButton = find(Button.class)
                .withText("Refresh")
                .single();
        return test(refreshButton);
    }

    /**
     * Find the clear button and get its tester.
     *
     * @return the clear button tester.
     */
    private ButtonTester<Button> $clearButton() {
        var clearButton = find(Button.class)
                .withText("Clear")
                .single();
        return test(clearButton);
    }

    /**
     * Find the greeting history virtual list and get its tester.
     *
     * @return the greeting history virtual list tester
     */
    private VirtualListTester<VirtualList<Greeting>, Greeting> $greetingHistoryVirtualList() {
        var greetingHistoryVirtualList = find(VirtualList.class)
                .single();
        return test(greetingHistoryVirtualList, Greeting.class);
    }

    /**
     * Find a row in the greeting history virtual list and get its tester.
     *
     * @param index the requested row
     * @return the indicated row tester from the greeting history virtual list tester
     * @throws IllegalArgumentException
     *             when row doesn't exist or its component is not a Div
     */
    private DivTester $greetingHistoryVirtualListRow(int index) {
        if (!($greetingHistoryVirtualList().getItemComponent(index) instanceof Div div)) {
            throw new IllegalArgumentException("Row component is not a div");
        }

        return test(div);
    }
}
