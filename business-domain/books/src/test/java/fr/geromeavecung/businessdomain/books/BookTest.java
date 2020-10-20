package fr.geromeavecung.businessdomain.books;

import fr.geromeavecung.businessdomain.shared.FieldMinimumLength;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BookTest {

    @Test
    @DisplayName("Given an empty title when we create a book then we have an error message")
    void poorlyNamedTest() {
        assertThatThrownBy(() -> Book.create(""))
                .isInstanceOf(FieldMinimumLength.class)
                .hasMessage("[title, , 1]");
    }
}