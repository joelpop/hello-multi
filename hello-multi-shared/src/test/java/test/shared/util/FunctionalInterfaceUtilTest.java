package test.shared.util;

import org.joelpop.hellomulti.shared.util.FunctionalInterfaceUtil;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FunctionalInterfaceUtilTest {

    @Test
    void instantiationPreventionTest() {
        assertThatThrownBy(() -> FunctionalInterfaceUtil.class.getDeclaredConstructor().newInstance())
                .isInstanceOf(IllegalAccessException.class);
    }

    @Test
    void noOpTest() {
        // test with a null
        assertThatCode(() -> FunctionalInterfaceUtil.noOp(null))
                .doesNotThrowAnyException();

        // test with an object
        assertThatCode(() -> FunctionalInterfaceUtil.noOp(new Object()))
                .doesNotThrowAnyException();

        // test with a string
        assertThatCode(() -> FunctionalInterfaceUtil.noOp("Hello"))
                .doesNotThrowAnyException();

        // test with a number
        assertThatCode(() -> FunctionalInterfaceUtil.noOp(123))
                .doesNotThrowAnyException();
    }
}
