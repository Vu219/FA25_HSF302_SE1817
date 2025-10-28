package javafx.demojavafxs3.service;

import javafx.demojavafxs3.entity.SonyAccounts;
import org.springframework.stereotype.Component;

@Component
public class UserSession {

    private SonyAccounts loggedInAccount;

    public void setLoggedInAccount(SonyAccounts account) {
        this.loggedInAccount = account;
    }

    public SonyAccounts getLoggedInAccount() {
        return loggedInAccount;
    }

    public void clearSession() {
        loggedInAccount = null;
    }

    public boolean isLoggedIn() {
        return loggedInAccount != null;
    }

    public int getRoleID() {
        return (loggedInAccount != null) ? loggedInAccount.getRoleID() : -1;
    }
}