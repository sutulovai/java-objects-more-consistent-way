package examples.inconsistent;

import examples.AccountStatus;

public class Account {

    private String id;
    private AccountStatus status;
    private String email;

    public Account(String id, AccountStatus status, String email) {
        this.id = id;
        this.status = status;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
