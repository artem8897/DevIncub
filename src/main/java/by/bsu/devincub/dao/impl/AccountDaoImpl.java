package by.bsu.devincub.dao.impl;

import by.bsu.devincub.dao.AccountDao;
import by.bsu.devincub.entity.Account;
import by.bsu.devincub.exception.ConnectionPoolException;
import by.bsu.devincub.exception.DaoException;
import by.bsu.devincub.connectionpool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoImpl implements AccountDao {

    private static final Logger logger = LogManager.getLogger(AccountDaoImpl.class);
    private static final String SQL_CALCULATE_AMOUNT_OF_USERS = "SELECT COUNT(account.account_id) AS count FROM account";
    private static final String SQL_SELECT_ALL_ACCOUNTS = "SELECT account_id,user_id,account FROM account";
    private static final String USER_ID = "user_id";
    private static final String ACCOUNT = "account";
    private static final String ACCOUNT_ID = "account_id";
    private static final String COUNT = "count";

    @Override
    public int findNumberOfRows() throws DaoException {
        int number = 0;

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CALCULATE_AMOUNT_OF_USERS);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                number = resultSet.getInt(COUNT);
            }
        } catch (ConnectionPoolException | SQLException e) {
            logger.catching(e);
            throw new DaoException(e);
        }
        return number;
    }

    @Override
    public List<Account> findAllAccounts() throws DaoException {
        List<Account> accounts = new ArrayList<>();

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement st = connection.prepareStatement(SQL_SELECT_ALL_ACCOUNTS)) {
            try (ResultSet resultSet = st.executeQuery()) {
                while (resultSet.next()) {
                    Account account = new Account();
                    account.setUserId(resultSet.getInt(USER_ID));
                    account.setAccount(resultSet.getInt(ACCOUNT));
                    account.setAccountId(resultSet.getInt(ACCOUNT_ID));
                    accounts.add(account);
                }
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        }
        return accounts;
    }

}
