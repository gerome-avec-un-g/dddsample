package fr.geromeavecung.businessdomain.books;

import fr.geromeavecung.businessdomain.shared.FieldMaximumLength;
import fr.geromeavecung.businessdomain.shared.FieldMinimumLength;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BookTest {

    @Test
    @DisplayName("Given an empty title when we create a book then we have an error message")
    void minimumTitleLength() {
        assertThatThrownBy(() -> Book.create(""))
                .isInstanceOf(FieldMinimumLength.class)
                .hasMessage("[title, , 1]");
    }

    @Test
    @DisplayName("Given a long title when we create a book then we have an error message")
    void maximumTitleLength() {
        assertThatThrownBy(() -> Book.create("012345678901234567890"))
                .isInstanceOf(FieldMaximumLength.class)
                .hasMessage("[title, 012345678901234567890, 20]");
    }

    @ParameterizedTest(name = "#{index} - title is {0}")
    @ValueSource(strings = {"0", "01234567890123456789"})
    @DisplayName("Given a valid title when we create a book then we have an error message")
    void okTitle(String title) {
        assertThatCode(() -> Book.create(title)).doesNotThrowAnyException();
    }

}