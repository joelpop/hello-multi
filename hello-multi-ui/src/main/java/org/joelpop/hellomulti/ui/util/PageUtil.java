package org.joelpop.hellomulti.ui.util;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.page.Page.ExtendedClientDetailsReceiver;
import com.vaadin.flow.router.AfterNavigationEvent;
import org.joelpop.hellomulti.shared.util.LibraryClass;

import java.util.Optional;

/**
 * Utility methods for the {@link com.vaadin.flow.component.page.Page} object.
 */
public final class PageUtil extends LibraryClass {

    private PageUtil() throws IllegalAccessException {
        // library class - not intended for instantiation
    }

    /**
     * Retrieves extended client details from browser page via a callback.
     * There must be a UI current when this is called,
     * so an appropriate place to call this from is a view's
     * {@link com.vaadin.flow.router.AfterNavigationObserver#afterNavigation(AfterNavigationEvent)}.
     *
     * @param extendedClientDetailsReceiver a receiver to accept the extended client details
     */
    public static void withExtendedClientDetails(ExtendedClientDetailsReceiver extendedClientDetailsReceiver) {
        Optional.ofNullable(UI.getCurrent())
                .map(UI::getPage)
                .ifPresent(page -> page.retrieveExtendedClientDetails(extendedClientDetailsReceiver));
    }
}
