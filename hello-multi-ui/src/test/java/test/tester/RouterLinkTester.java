package test.tester;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.testbench.unit.ComponentTester;
import com.vaadin.testbench.unit.Tests;

/**
 *
 * Tester for RouterLink components.
 *
 * @param <T>
 *            component type
 */
@Tests(RouterLink.class)
public class RouterLinkTester<T extends RouterLink> extends ComponentTester<T> {

    /**
     * Wrap given component for testing.
     *
     * @param component target component
     */
    public RouterLinkTester(T component) {
        super(component);
    }

    /**
     * Click the router-link for navigation.
     *
     * @return navigated view
     */
    public Component click() {
        ensureComponentIsUsable();

        return RouteConfiguration.forSessionScope()
                .getRoute(getComponent().getHref())
                .map(navigationTarget -> UI.getCurrent().navigate(navigationTarget).orElseThrow(IllegalStateException::new))
                .orElseThrow(IllegalStateException::new);
    }
}
