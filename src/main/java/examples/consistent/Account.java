package examples.consistent;

import examples.AccountStatus;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

import static examples.AccountStatus.CREATED;
import static examples.AccountStatus.VERIFIED;
import static org.apache.commons.lang3.Validate.*;

public class Account {

    private String id;
    private AccountStatus status;
    private Optional<String> email;

    public Account(String id, AccountStatus status, Optional<String> email) {
        this.id = notEmpty(id);
        this.status = notNull(status);
        this.email = checkEmail(notNull(email));
    }

    public void verify(Optional<String> email) {
        this.status = VERIFIED;
        this.email = checkEmail(email);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = notEmpty(id);
    }

    public AccountStatus getStatus() {
        return status;
    }

    public Optional<String> getEmail() {
        return email;
    }

    public void setEmail(Optional<String> email) {
        this.email = checkEmail(email);
    }

    private Optional<String> checkEmail(Optional<String> email) {
        isTrue(
                email.map(StringUtils::isNotBlank).orElse(false) || this.status.equals(CREATED),
                "Email must be filled when status %s",
                this.status
        );
        return email;
    }

}
