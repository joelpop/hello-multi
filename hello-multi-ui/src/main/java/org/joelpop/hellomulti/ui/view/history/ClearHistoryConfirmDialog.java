package org.joelpop.hellomulti.ui.view.history;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import org.joelpop.hellomulti.shared.util.FunctionalInterfaceUtil;

public class ClearHistoryConfirmDialog extends ConfirmDialog {

    public ClearHistoryConfirmDialog(ComponentEventListener<ConfirmEvent> confirmListener) {
        super("Clear Greeting History",
                "Clear all greetings from history?",
                "Clear", confirmListener,
                "Keep", FunctionalInterfaceUtil::noOp);
        setConfirmButtonTheme(String.join(" ",
                ButtonVariant.LUMO_PRIMARY.getVariantName(),
                ButtonVariant.LUMO_ERROR.getVariantName()));
    }
}
