package org.joelpop.hellomulti.ui.view;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.joelpop.hellomulti.service.GreetingService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Route("")
@PageTitle("Hello Multi")
public class HelloView extends Composite<HorizontalLayout> {
    public static final String ID = "hello-view";

    private final transient GreetingService greetingService;
    private final TextField nameTextField;

    private ZoneId clientZoneId;


    public HelloView(GreetingService greetingService) {
        this.greetingService = greetingService;

        setId(ID);

        nameTextField = new TextField();
        nameTextField.setPlaceholder("your name");
        nameTextField.setClearButtonVisible(true);

        var helloButton = new Button("Hello");
        helloButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        helloButton.addClickShortcut(Key.ENTER);
        helloButton.addClickListener(this::onButtonClick);

        var content = getContent();
        content.setPadding(true);
        content.add(nameTextField);
        content.add(helloButton);

        clientZoneId = ZoneId.systemDefault();
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);

        // obtain user's time zone from browser
        Page.ExtendedClientDetailsReceiver initClientZoneId =
                extendedClientDetails -> clientZoneId = ZoneId.of(extendedClientDetails.getTimeZoneId());
        getUI()
                .map(UI::getPage)
                .ifPresent(page -> page.retrieveExtendedClientDetails(initClientZoneId));

    }

    private void onButtonClick(ClickEvent<Button> event) {
        // generate greeting response via service
        var greeting = greetingService.getGreeting(nameTextField.getValue());

        // convert service's Instance timestamp to LocalDateTime using client's time zone
        var localDateTime = LocalDateTime.ofInstant(greeting.timestamp(), clientZoneId);

        // display greeting in notification
        Notification.show("[%s] %s, %s!".formatted(
                        localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                        greeting.salutation(),
                        greeting.name()),
                3000,
                Notification.Position.MIDDLE);
    }
}
