package org.joelpop.hellomulti.ui.view.history;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import org.joelpop.hellomulti.uimodel.model.Greeting;
import org.joelpop.hellomulti.shared.util.FunctionalInterfaceUtil;

public class DeleteConfirmDialog extends ConfirmDialog {

    private final transient Greeting greeting;

    public DeleteConfirmDialog(Greeting greeting,
                               ComponentEventListener<ConfirmEvent> confirmListener) {
        super("Delete Greeting",
                "Delete from history greeting \"" + greeting.getMessage() +
                        "\" on " + greeting.getTimestamp() + "?",
                "Delete", confirmListener,
                "Keep", FunctionalInterfaceUtil::noOp);
        setConfirmButtonTheme(String.join(" ",
                ButtonVariant.LUMO_PRIMARY.getVariantName(),
                ButtonVariant.LUMO_ERROR.getVariantName()));
        this.greeting = greeting;
    }

    public Greeting getGreeting() {
        return greeting;
    }
}
