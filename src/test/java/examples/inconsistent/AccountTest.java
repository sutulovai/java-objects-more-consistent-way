package examples.inconsistent;

import org.junit.jupiter.api.Test;

import static examples.AccountStatus.ACTIVE;
import static examples.AccountStatus.CREATED;
import static org.assertj.core.api.Assertions.assertThat;

class AccountTest {

    @Test
    void should_successfully_instantiate_and_validate_nothing() {
        // given
        var result = new Account(null, null, null);

        // when //then
        assertThat(result.getId()).isNull();
        assertThat(result.getEmail()).isNull();
        assertThat(result.getStatus()).isNull();
    }

    @Test
    void should_allow_to_set_any_state_and_any_email() {
        // given
        var account = new Account("example-id", CREATED, "");

        // when
        account.setStatus(ACTIVE);
        account.setEmail(null); // Any part of code in this project can change the class as it wants to. No consistency

        // then
        assertThat(account.getStatus()).isEqualTo(ACTIVE);
        assertThat(account.getEmail()).isBlank();
    }

}