package test.ui.view.greetinghistory;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonTester;
import com.vaadin.flow.component.confirmdialog.ConfirmDialogTester;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.html.testbench.DivTester;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.virtuallist.VirtualList;
import com.vaadin.flow.component.virtuallist.VirtualListTester;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.testbench.unit.ComponentTester;
import com.vaadin.testbench.unit.TesterWrappers;
import com.vaadin.testbench.unit.Tests;
import org.joelpop.hellomulti.ui.view.history.GreetingHistoryView;
import org.joelpop.hellomulti.uimodel.model.Greeting;
import test.tester.RouterLinkTester;
import test.ui.view.hello.HelloViewTester;

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

    public static <T extends Component> GreetingHistoryViewTester get(ComponentTester<T> componentTester) {
        return new GreetingHistoryViewTester(componentTester.find(GreetingHistoryView.class)
                .from(null)
                .single());
    }

    public HelloViewTester goBack() {
        // click the back link
        $backLink().click();

        // return the HelloView
        return HelloViewTester.get(this);
    }

    /**
     * Refresh the greeting history.
     *
     * @return the greeting history view
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
    public ConfirmDialogTester confirmClearGreetingHistory() {
        // click the clear button
        $clearButton().click();

        // return the clear greeting history confirmation dialog
        return ClearHistoryConfirmDialogTester.$(this);
    }

    /**
     * Potentially delete a greeting.
     *
     * @param index the index of the greeting displayed
     * @return the resultant delete greeting confirmation dialog
     */
    public ConfirmDialogTester confirmDeleteGreeting(int index) {
        // find the item
        var $item = $greetingHistoryVirtualListRow(index);

        // click the item's delete button
        $item.click();

        // return the delete greeting confirmation dialog
        return DeleteGreetingConfirmDialogTester.$(this);
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
     * Find the back link tester.
     *
     * @return the back link tester.
     */
    private RouterLinkTester<RouterLink> $backLink() {
        return new RouterLinkTester<>(find(RouterLink.class)
                .withText("← Back")
                .single());
    }

    /**
     * Find the refresh button tester.
     *
     * @return the refresh button tester.
     */
    private ButtonTester<Button> $refreshButton() {
        return test(find(Button.class)
                .withText("Refresh")
                .single());
    }

    /**
     * Find the clear button tester.
     *
     * @return the clear button tester.
     */
    private ButtonTester<Button> $clearButton() {
        return test(find(Button.class)
                .withText("Clear")
                .single());
    }

    /**
     * Find the greeting history virtual list tester.
     *
     * @return the greeting history virtual list tester
     */
    @SuppressWarnings("unchecked")
    private VirtualListTester<VirtualList<Greeting>, Greeting> $greetingHistoryVirtualList() {
        return test((VirtualList<Greeting>) find(VirtualList.class)
                .single());
    }

    /**
     * Find a row in the greeting history virtual list tester.
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
