package examples.consistent;

import org.junit.jupiter.api.Test;

import static examples.AccountStatus.*;
import static java.lang.String.format;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AccountTest {

    @Test
    void should_successfully_instantiate() {
        // given
        var id = "example-id";
        var email = "example@example.com";
        var status = ACTIVE;

        // when
        var result = new Account(id, status, of(email));

        // then
        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getEmail().get()).isEqualTo(email);
        assertThat(result.getStatus()).isEqualTo(status);
    }

    @Test
    void should_validate_parameters_on_instantiating() {
        assertThatThrownBy(() -> new Account("", CREATED, empty())).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Account("example-id", null, empty())).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> new Account("example-id", ACTIVE, empty()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(format("Email must be filled when status %s", ACTIVE));
    }

    @Test
    void should_verify_and_validate() {
        // given
        var email = "example@example.com";
        var account = new Account("example-id", CREATED, empty());

        // when
        account.verify(of(email)); // Account controls its state's consistency and won't be with the wrong data

        // then
        assertThat(account.getStatus()).isEqualTo(VERIFIED);
        assertThat(account.getEmail().get()).isEqualTo(email);
        assertThatThrownBy(
                () -> account.verify(empty()) // It's impossible to verify account without an email
        )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(format("Email must be filled when status %s", VERIFIED));

    }

    @Test
    void should_fail_when_set_empty_email_for_activated_account() {
        // given
        var activatedAccount = new Account("example-id", ACTIVE, of("example@example.com"));

        // when // then
        assertThatThrownBy(() -> activatedAccount.setEmail(empty()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(format("Email must be filled when status %s", ACTIVE));
    }

    @Test
    void should_fail_when_set_empty_id() {
        // given
        var account = new Account("example-id", CREATED, empty());

        // when // then
        assertThatThrownBy(() -> account.setId("")).isInstanceOf(IllegalArgumentException.class);
    }

}
