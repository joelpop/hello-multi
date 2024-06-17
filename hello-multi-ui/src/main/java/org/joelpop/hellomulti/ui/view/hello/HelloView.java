package org.joelpop.hellomulti.ui.view.hello;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.router.RouterLink;
import org.joelpop.hellomulti.ui.view.history.GreetingHistoryView;
import org.joelpop.hellomulti.uimodel.service.GreetingService;

/**
 * A Hello World view that uses a service to log greetings.
 *
 * <pre>
 * +-content(HorizontalLayout)---------------------------------------------+
 * | Name                                                                  |
 * | +-nameTextField--------+ +-greetButton-+ +-historyRouterLink--------+ |
 * | | your name            | |    Greet    | | See previous visitors -> | |
 * | +----------------------+ +-------------+ +--------------------------+ |
 * +-----------------------------------------------------------------------+
 * </pre>
 */
@Route(HelloView.ROUTE)
@RouteAlias("")
@PageTitle(HelloView.PAGE_TITLE)
public class HelloView extends Composite<HorizontalLayout> {
    public static final String ID = "hello-view";
    public static final String ROUTE = "hello";
    public static final String PAGE_TITLE = "Hello";

    private final transient GreetingService greetingService;

    private final TextField nameTextField;

    public HelloView(GreetingService greetingService) {
        this.greetingService = greetingService;

        setId(ID);

        // name text field
        nameTextField = new TextField("Name");
        nameTextField.setPlaceholder("your name");
        nameTextField.setClearButtonVisible(true);

        // greet button
        var greetButton = new Button("Greet");
        greetButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        greetButton.addClickShortcut(Key.ENTER);
        greetButton.addClickListener(this::onButtonClick);

        // history router link
        var historyRouterLink = new RouterLink("See previous visitors →",
                GreetingHistoryView.class);

        // content
        var content = getContent();
        content.setAlignItems(FlexComponent.Alignment.BASELINE);
        content.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        content.add(nameTextField);
        content.add(greetButton);
        content.add(historyRouterLink);
    }

    private void onButtonClick(ClickEvent<Button> event) {
        // generate greeting message and log greeting into history
        var greeting = greetingService.generateAndLog(nameTextField.getValue().trim());

        // display greeting message in notification
        var greetingNotification = new Notification(greeting.getMessage(),
                5000, Notification.Position.MIDDLE);
        greetingNotification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        greetingNotification.open();
    }
}
