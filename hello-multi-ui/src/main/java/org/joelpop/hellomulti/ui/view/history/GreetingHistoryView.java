package org.joelpop.hellomulti.ui.view.history;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog.ConfirmEvent;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.ExtendedClientDetails;
import com.vaadin.flow.component.virtuallist.VirtualList;
import com.vaadin.flow.data.renderer.LitRenderer;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;
import elemental.json.JsonArray;
import org.joelpop.hellomulti.ui.util.PageUtil;
import org.joelpop.hellomulti.ui.view.hello.HelloView;
import org.joelpop.hellomulti.uimodel.model.Greeting;
import org.joelpop.hellomulti.uimodel.service.GreetingService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * A history view for listing and maintaining greetings.
 *
 * <pre>
 * +-content(VerticalLayout)---------------------------------------+
 * | +-buttonBar(HorizontalLayout)-------------------------------+ |
 * | | +-backRouterLink-+      +-refreshButton-+ +-clearButton-+ | |
 * | | |    <- Back     |      |    Refresh    | |    Clear    | | |
 * | | +----------------+      +---------------+ +-------------+ | |
 * | +-----------------------------------------------------------+ |
 * | +-greetingHistoryPlaceholder(VerticalLayout)----------------+ |
 * | |        There are no greetings in the history log.         | |
 * | |        Press the ← Back link above and create one.        | |
 * | +-----------------------------------------------------------+ |
 * | +-greetingHistoryVirtualList(VirtualList)-------------------+ |
 * | | +-(GreetingRenderer)------------------------------------+ | |
 * | | | ${item.timestamp} ${item.name} ${item.message}    (X) | | |
 * | | +-------------------------------------------------------+ | |
 * | |                             .                             | |
 * | |                             .                             | |
 * | |                             .                             | |
 * | +-----------------------------------------------------------+ |
 * +---------------------------------------------------------------+
 * </pre>
 */
@Route(GreetingHistoryView.ROUTE)
@PageTitle(GreetingHistoryView.PAGE_TITLE)
public class GreetingHistoryView extends Composite<VerticalLayout> implements AfterNavigationObserver {
    public static final String ID = "greeting-history-view";
    public static final String ROUTE = "greeting-history";
    public static final String PAGE_TITLE = "Greeting History";
    public static final String MAX_WIDTH = "50em";

    private final transient GreetingService greetingService;
    private final VerticalLayout greetingHistoryPlaceholder;
    private final VirtualList<Greeting> greetingHistoryVirtualList;

    private ZoneId clientZoneId;

    public GreetingHistoryView(GreetingService greetingService) {
        this.greetingService = greetingService;

        setId(ID);

        // back router link
        var backRouterLink = new RouterLink("← Back", HelloView.class);

        // refresh button
        var refreshButton = new Button("Refresh");
        refreshButton.addClickListener(this::onRefreshFromHistoryClick);

        // clear button
        var clearButton = new Button("Clear");
        clearButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        clearButton.addClickListener(this::onClearHistoryClick);

        // button bar
        var buttonBar = new HorizontalLayout();
        buttonBar.setWidthFull();
        buttonBar.setMaxWidth(MAX_WIDTH);
        buttonBar.setAlignItems(FlexComponent.Alignment.BASELINE);
        buttonBar.add(backRouterLink);
        buttonBar.addAndExpand(new Span());
        buttonBar.add(refreshButton);
        buttonBar.add(clearButton);

        // greeting history placeholder
        var message = new Span("There are no greetings in the history log.");
        var instructions = new Span("Press the ← Back link above and create one.");

        greetingHistoryPlaceholder = new VerticalLayout();
        greetingHistoryPlaceholder.setWidthFull();
        greetingHistoryPlaceholder.setMaxWidth(MAX_WIDTH);
        greetingHistoryPlaceholder.setSpacing(false);
        greetingHistoryPlaceholder.setAlignItems(FlexComponent.Alignment.CENTER);
        greetingHistoryPlaceholder.add(message);
        greetingHistoryPlaceholder.add(instructions);

        // greeting history virtual list
        greetingHistoryVirtualList = new VirtualList<>();
        greetingHistoryVirtualList.setSizeFull();
        greetingHistoryVirtualList.setMaxWidth(MAX_WIDTH);
        greetingHistoryVirtualList.addClassNames(LumoUtility.AlignSelf.AUTO);
        greetingHistoryVirtualList.setRenderer(greetingRenderer());

        var content = getContent();
        content.setSizeFull();
        content.setAlignItems(FlexComponent.Alignment.CENTER);
        content.add(buttonBar);
        content.add(greetingHistoryPlaceholder);
        content.add(greetingHistoryVirtualList);
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        PageUtil.withExtendedClientDetails(this::setClientZoneId);

        refreshGreetingHistoryVirtualList();
    }

    private void refreshGreetingHistoryVirtualList() {
        var greetings = greetingService.fetchAll();
        greetingHistoryVirtualList.setItems(greetings);

        greetingHistoryPlaceholder.setVisible(greetings.isEmpty());
        greetingHistoryVirtualList.setVisible(!greetings.isEmpty());
    }

    /**
     * Set client time zone ID from the client details.
     *
     * @param extendedClientDetails the client details from which to extract the time zone
     */
    private void setClientZoneId(ExtendedClientDetails extendedClientDetails) {
        clientZoneId = Optional.ofNullable(extendedClientDetails)
                .map(ExtendedClientDetails::getTimeZoneId)
                .map(ZoneId::of)
                .orElse(ZoneId.systemDefault());
    }

    private LitRenderer<Greeting> greetingRenderer() {
        return LitRenderer.<Greeting>of("""
                    <div class="${item.classes}">
                        <span>${item.timestamp}</span>
                        <span>${item.name}</span>
                        <span>${item.message}</span>
                        <vaadin-button theme="small error icon"
                         title="Delete greeting log entry from history."
                         @click=${onDeleteGreetingClick}>
                            <vaadin-icon icon="vaadin:close"></vaadin-icon>
                        </vaadin-button>
                    </div>
                """)
                .withProperty("classes", greeting -> String.join(" ",
                        LumoUtility.Display.FLEX,
                        LumoUtility.JustifyContent.BETWEEN,
                        LumoUtility.AlignItems.BASELINE,
                        LumoUtility.Padding.Horizontal.XSMALL,
                        LumoUtility.Background.CONTRAST_5))
                .withProperty("timestamp", this::formatTimestamp)
                .withProperty("name", Greeting::getName)
                .withProperty("message", Greeting::getMessage)
                .withProperty("deleteIcon", ignored ->
                        VaadinIcon.CLOSE_CIRCLE.create().getElement().getAttribute("icon"))
                .withFunction("onDeleteGreetingClick", this::onDeleteGreetingClick);
    }

    private String formatTimestamp(Greeting greeting) {
        // convert greeting's timestamp from Instance in "UTC"
        // to LocalDateTime using client's time zone
        var localDateTime = LocalDateTime.ofInstant(greeting.getTimestamp(), clientZoneId);
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    private void onRefreshFromHistoryClick(ClickEvent<Button> event) {
        refreshGreetingHistoryVirtualList();
    }

    private void onClearHistoryClick(ClickEvent<Button> event) {
        new ClearHistoryConfirmDialog(this::onConfirmClearHistory).open();
    }

    private void onConfirmClearHistory(ConfirmEvent event) {
        greetingService.clear();
        refreshGreetingHistoryVirtualList();
    }

    private void onDeleteGreetingClick(Greeting greeting, JsonArray ignored) {
        new DeleteGreetingConfirmDialog(greeting, this::onConfirmDeleteGreeting).open();
    }

    private void onConfirmDeleteGreeting(ConfirmEvent event) {
        if (event.getSource() instanceof DeleteGreetingConfirmDialog deleteGreetingConfirmDialog) {
            greetingService.delete(deleteGreetingConfirmDialog.getGreeting());
            refreshGreetingHistoryVirtualList();
        }
    }
}
