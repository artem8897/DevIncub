package by.bsu.devincub.entity;

import java.util.Objects;

public class Account {

    private int account;
    private int accountId;
    private int userId;

    public Account() {
    }

    public Account(int account, int accountId, int userId) {
        this.account = account;
        this.accountId = accountId;
        this.userId = userId;
    }

    public int getAccount() {
        return account;
    }

    public int getAccountId() {
        return accountId;
    }

    public int getUserId() {
        return userId;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Account{" +
                "account=" + account +
                ", accountId=" + accountId +
                ", userId=" + userId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account1 = (Account) o;
        return account == account1.account &&
                accountId == account1.accountId &&
                userId == account1.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(account, accountId, userId);
    }


}
