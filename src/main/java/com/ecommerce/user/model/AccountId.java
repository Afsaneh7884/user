package com.ecommerce.user.model;

public class AccountId {
    private String accountNumber;
    private String accountType;

    public AccountId(String accountNumber, String accountType) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
    }

    public AccountId() {
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountId accountId = (AccountId) o;

        if (getAccountNumber() != null ? !getAccountNumber().equals(accountId.getAccountNumber()) : accountId.getAccountNumber() != null)
            return false;
        return getAccountType() != null ? getAccountType().equals(accountId.getAccountType()) : accountId.getAccountType() == null;
    }

    @Override
    public int hashCode() {
        int result = getAccountNumber() != null ? getAccountNumber().hashCode() : 0;
        result = 31 * result + (getAccountType() != null ? getAccountType().hashCode() : 0);
        return result;
    }
}
