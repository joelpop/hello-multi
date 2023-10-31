package org.joelpop.hellomulti.it.ui.view.pageobject;

import com.vaadin.flow.component.button.testbench.ButtonElement;
import com.vaadin.flow.component.notification.testbench.NotificationElement;
import com.vaadin.flow.component.orderedlayout.testbench.HorizontalLayoutElement;
import com.vaadin.flow.component.textfield.testbench.TextFieldElement;
import com.vaadin.testbench.annotations.Attribute;
import org.joelpop.hellomulti.ui.view.HelloView;

@Attribute(name = "id", value = HelloView.ID)
public class HelloViewElement extends HorizontalLayoutElement {

    public NotificationElement sayHello(String name) {
        // get the text field element and set its value
        $(TextFieldElement.class).id(HelloView.NAME_TEXT_FIELD_ID).setValue(name);

        // get the button element and click it
        $(ButtonElement.class).id(HelloView.HELLO_BUTTON_ID).click();

        // return the resultant pop-up notification element
        return $(NotificationElement.class).onPage().first();
    }
}
