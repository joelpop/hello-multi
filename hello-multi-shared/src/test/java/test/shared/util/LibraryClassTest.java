package test.shared.util;

import org.joelpop.hellomulti.shared.util.LibraryClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LibraryClassTest {

    @Test
    void instantiationPreventionTest() {
        assertThatThrownBy(() -> LibraryClass.class.getDeclaredConstructor().newInstance())
                .isInstanceOf(IllegalAccessException.class);
    }
}
