package by.bsu.finalproject.dao.impl;

import by.bsu.finalproject.connectionpool.ConnectionPool;
import by.bsu.finalproject.dao.DietDao;
import by.bsu.finalproject.dao.Query;
import by.bsu.finalproject.dao.TablesColumnName;
import by.bsu.finalproject.entity.Diet;
import by.bsu.finalproject.exception.ConnectionPoolException;
import by.bsu.finalproject.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Data access object for Diet.
 * @author A. Kuzmik
 */

public class DietDaoImpl implements DietDao<Integer, Diet> {

    private static final Logger logger = LogManager.getLogger(DietDaoImpl.class);

    @Override
    public Map<Integer, Diet> findAllDiets() throws DaoException {

        Map<Integer, Diet> diets = new HashMap<>();

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.SQL_SELECT_ALL_USERS_DIET);
             ResultSet resultSet = statement.executeQuery()){

            while (resultSet.next()) {

                Diet diet = new Diet();
                diet.setUsername(resultSet.getInt(TablesColumnName.DIET_ID));
                diet.setDietType(resultSet.getString(TablesColumnName.DIET_TYPE));
                diet.setFats(resultSet.getInt(TablesColumnName.FATS));
                diet.setFats(resultSet.getInt(TablesColumnName.PROTEINS));
                diet.setFats(resultSet.getInt(TablesColumnName.CARBOHYDRATES));
                diets.put(diet.getUsername(),diet);
            }
        }catch (ConnectionPoolException | SQLException e){
            throw new DaoException(e);
        }
        return diets;
    }


    public boolean isUsersDietExist(Integer userId) throws DaoException {

        try (Connection cn = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement st = cn.prepareStatement(Query.SQL_SELECT_USERS_DIET)){

            st.setInt(1, userId);

            try(ResultSet resultSet = st.executeQuery()){
                return resultSet.first();
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    public Diet findUsersDiet(Integer userId) throws DaoException {

        Diet diet = new Diet();

        try (Connection cn = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement st = cn.prepareStatement(Query.SQL_SELECT_USERS_DIET)){

            st.setInt(1, userId);

            try(ResultSet resultSet = st.executeQuery()){

                if(resultSet.first()){
                    diet.setUsername(resultSet.getInt(TablesColumnName.DIET_ID));
                    diet.setStatus(resultSet.getString(TablesColumnName.STATUS));
                    diet.setDietType(resultSet.getString(TablesColumnName.DIET_TYPE));
                    diet.setFats(resultSet.getInt(TablesColumnName.FATS));
                    diet.setProteins(resultSet.getInt(TablesColumnName.PROTEINS));
                    diet.setCarbohydrates(resultSet.getInt(TablesColumnName.CARBOHYDRATES));
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return diet;
    }

    @Override
    public boolean create(Diet entity,Integer userId) throws DaoException {

      int dietId ;

      try (Connection connection = ConnectionPool.INSTANCE.getConnection()){

          connection.setAutoCommit(false);

          try{

              try(PreparedStatement statement = connection.prepareStatement(Query.SQL_INSERT_DIET, Statement.RETURN_GENERATED_KEYS)){

                  statement.setString(1,entity.getDietType());
                  statement.setInt(2,entity.getFats());
                  statement.setInt(3,entity.getProteins());
                  statement.setInt(4,entity.getCarbohydrates());
                  statement.executeUpdate();

                  logger.info("Created diet");

                  try (ResultSet resultSet = statement.getGeneratedKeys()){
                      if(resultSet.next()){
                          dietId = resultSet.getInt(1);
                      } else{
                          connection.rollback();
                          return false;
                      }
                  }
              }
              try(PreparedStatement statement = connection.prepareStatement(Query.SQL_INSERT_USER_DIET)) {

                  statement.setInt(1, userId);
                  statement.setInt(2, dietId);
                  statement.executeUpdate();
              }
              connection.commit();

          }catch (SQLException e){
              logger.error("some diet exception", e);
              connection.rollback();
              return false;
          }
      } catch (SQLException | ConnectionPoolException e) {
          throw new DaoException(e);
      }
      logger.info("Created users_training");
      return true;
  }

    public boolean update(Integer userId, Diet entity) throws DaoException {

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement  statement = connection.prepareStatement(Query.SQL_UPDATE_DIET)) {

                statement.setInt(5,userId);
                statement.setString(1, entity.getDietType());
                statement.setInt(2, entity.getProteins());
                statement.setInt(3,entity.getFats());
                statement.setInt(4,entity.getCarbohydrates());
                int updatedRow = statement.executeUpdate();
                logger.info("updated diet");
                return updatedRow > 0;

        }catch (ConnectionPoolException | SQLException e){
            throw new DaoException(e);
        }
    }
}
