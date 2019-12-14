package by.bsu.finalproject.dao;

public class Query {

    public static final String SQL_SELECT_ALL_USERS = "SELECT id,username,password,email,usertype,status FROM user ORDER BY username LIMIT ?,?";
    public static final String SQL_CALCULATE_TRAINING_COUNT = "SELECT COUNT(user_training.training_id) AS count FROM user_training JOIN training ON user_training.training_id = training.training_id WHERE date > CURRENT_DATE AND user_training.user_id = ? ;";
    public static final String SQL_SELECT_ALL_STATUS = "SELECT discounts.discount_id, discounts.type_of_student FROM discounts";
    public static final String SQL_UPDATE_STATUS_PAY = "UPDATE personal_information SET status_id = ? WHERE information_id = ?";
    public static final String SQL_CALCULATE_AMOUNT_OF_TRAINERS = "SELECT COUNT(trainer_information.trainer_id) AS 'count' FROM trainer_information;";
    public static final String SQL_SELECT_USER_BY_EMAIL = "SELECT id,password,username,email,usertype,status FROM mydb.user WHERE email = ? ;";
    public static final String SQL_CALCULATE_STUDENTS_COUNT = "SELECT COUNT(personal_information.information_id) AS 'count' FROM personal_information;";
    public static final String SQL_CALCULATE_STUDENTS_COUNT_WITH_PAID_TRAINERS = "SELECT COUNT(personal_information.information_id) AS 'count' FROM personal_information JOIN user_trainer ON user_trainer.user_id = personal_information.information_id  WHERE PAID_TRAINING > 0 AND user_trainer.trainer_id = ?";
    public static final String SQL_CALCULATE_STUDENTS_COUNT_WITH_NO_DIET = "SELECT COUNT(personal_information.information_id) AS 'count' FROM personal_information LEFT JOIN  user_diet ON user_diet.user_id = personal_information.information_id JOIN user_trainer ON personal_information.information_id = user_trainer.user_id WHERE trainer_id = ? AND diet_id IS NULL";
    public static final String SQL_SELECT_USER_BY_EMAIL_AND_USERNAME = "SELECT id,password,email,usertype,status FROM mydb.user WHERE email = ? OR username = ? ;";
    public static final String SQL_INSERT_USER = "INSERT mydb.user(username,password,email,usertype) VALUES (?,?,?,?)";
    public static final String SQL_DELETE_USER = "DELETE FROM user WHERE user.id = ?";
    public static final String SQL_CHANGE_STATUS_USER = "UPDATE user SET status = ?, usertype = ? WHERE id = ?";
    public static final String SQL_SELECT_USERS_REVIEW = "SELECT review,review.review_id,rate FROM review JOIN user_review ON user_review.review_id = review.review_id JOIN personal_information ON user_review.user_id = personal_information.information_id WHERE personal_information.information_id = ?";
    public static final String SQL_SELECT_TRAINERS_REVIEW = "SELECT review,review.review_id,rate FROM review JOIN user_review ON user_review.review_id = review.review_id JOIN trainer_information ON user_review.trainer_id = trainer_information.trainer_id WHERE trainer_information.trainer_id= ?";


    public static final String SQL_DELETE_TRAINING = "UPDATE training SET status = 'deleted' WHERE training_id = ?";
    public static final String SQL_UPDATE_TRAINING_PAID = "UPDATE user_trainer SET paid_training = paid_training - 1 WHERE user_trainer.user_id = ? ";
    public static final String SQL_INSERT_REVIEW = "INSERT mydb.review(review,rate) VALUES (?,?)";
    public static final String SQL_INSERT_USERS_REVIEW = "INSERT mydb.user_review(user_id,trainer_id,review_id) VALUES (?,?,?)";
    public static final String SQL_SELECT_ALL_TRAINERS = "SELECT work_experience,name,training_type,trainer_id FROM trainer_information";
    public static final String SQL_SELECT_LIMIT_TRAINERS = "SELECT work_experience,name,training_type,trainer_id FROM trainer_information JOIN user on trainer_information.trainer_id = user.id WHERE user.status NOT LIKE 'deleted' AND user.usertype LIKE 'trainer' LIMIT ?,?";
    public static final String SQL_INSERT_TRAINER = "INSERT mydb.trainer_information(training_type,name,work_experience,trainer_id) VALUES (?,?,?,?)";
    public static final String SQL_SELECT_TRAINER_INFORMATION = "SELECT training_type,name,work_experience FROM trainer_information WHERE trainer_id = ?";
    public static final String SQL_SELECT_USERNAME = "SELECT username";
    public static final String SQL_CALCULATE_AMOUNT_OF_USERS = "SELECT COUNT(user.id) AS count FROM user";

    public static final String SQL_SELECT_LIMIT_INFORMATION_BY_TRAINER = "SELECT name,second_name,sex,weight,height,information_id FROM personal_information JOIN user ON personal_information.information_id = user.id JOIN user_trainer ON personal_information.information_id = user_trainer.user_id WHERE trainer_id = ? AND user.status NOT LIKE 'deleted' ORDER BY NAME  LIMIT ?,?";
    public static final String SQL_SELECT_LIMIT_PERSONAL_INFORMATION = "SELECT name,second_name,sex,weight,height,information_id FROM personal_information ORDER BY NAME LIMIT ?,?";
    public static final String SQL_SELECT_TRAINING_BY_ID = "SELECT personality,training_type, training_id,date FROM training  WHERE training_id = ? ;";
    public static final String SQL_SELECT_ALL_PERSONAL_INFORMATION = "SELECT name,second_name,sex,weight,height,information_id FROM personal_information ORDER BY NAME";
    public static final String SQL_SELECT_ALL_USER_PERSONAL_INFORMATION = "SELECT name,second_name,sex,weight,height,information_id FROM personal_information WHERE information_id = ?";
    public static final String SQL_SELECT_USER_BY_TRAINER = "SELECT NAME,SECOND_NAME,SEX,WEIGHT,HEIGHT,INFORMATION_ID FROM PERSONAL_INFORMATION JOIN USER_TRAINER ON PERSONAL_INFORMATION.INFORMATION_ID = USER_TRAINER.USER_ID WHERE USER_TRAINER.TRAINER_ID = ? ORDER BY NAME";
    public static final String SQL_INSERT_USER_INFORMATION = "INSERT personal_information(second_name,name,sex,weight,height,information_id) VALUES (?,?,?,?,?,?)";
    public static final String SELECT_TRAINER_INFORMATION = "SELECT * FROM trainer_information INNER JOIN users_review ON users_review.trainer_id = trainer_information.trainer_id WHERE users_review.trainer_id = ?";
    public static final String SELECT_TRAINER_REVIEWS = "SELECT * FROM users_review WHERE users_review.trainer_id = ?";
    public static final String SQL_SELECT_STUDENT_WITH_PAID_TRAININGS = "SELECT name,second_name,paid_training,sex,weight,height,personal_information.information_id FROM personal_information JOIN user_trainer ON user_trainer.user_id = personal_information.information_id  WHERE PAID_TRAINING > 0 AND user_trainer.trainer_id = ?  ORDER BY NAME  LIMIT ?,?";
    public static final String SQL_SELECT_STUDENTS_WITH_NO_DIET = "SELECT name,second_name,sex,weight,height,personal_information.information_id FROM personal_information LEFT JOIN  user_diet ON user_diet.user_id = personal_information.information_id JOIN user_trainer ON personal_information.information_id = user_trainer.user_id WHERE trainer_id = ? AND diet_id IS NULL  ORDER BY NAME  LIMIT ?,?;";

    public static final String SQL_UPDATE_MONEY_CARD = "UPDATE money_card SET money_amount = money_amount - ? WHERE account_number = ?";
    public static final String SQL_FIND_USERS_TRAINER = "SELECT key_id FROM mydb.user_trainer WHERE user_id = ? AND trainer_id = ?";
    public static final String SQL_UPDATE_PAID_TRAINING = " UPDATE user_trainer SET paid_training = paid_training + ? WHERE key_id = ?";
    public static final String SQL_CREATE_USERS_TRAINER_WITH_PAID_TRAININGS = " INSERT user_trainer (paid_training,user_Id,trainer_Id) VALUES (?,?,?);";
    public static final String SQL_CREATE_BANK_TRANSACTION_INFORMATION = "INSERT center_bank(sum, date_transaction, center_bank.card_id) VALUES (?,CURDATE(),?)";


    public static final String SQL_SELECT_ALL_TRAININGS = "SELECT training.training_id,date,personality,training_type FROM training JOIN user_training ON user_training.training_id = training.training_id WHERE date > CURRENT_DATE AND user_training.user_id = ?";
    public static final String SQL_SELECT_TRAININGS_BY_USERNAME = "SELECT * FROM mydb.training WHERE username = ? ;";
    public static final String SQL_INSERT_TRAINING = "INSERT mydb.training(date,training_type,personality) VALUES (?,?,?)";
    public static final String SQL_SELECT_LIMIT_TRAININGS = "SELECT training.training_id,date,personality,training_type FROM training JOIN user_training ON user_training.training_id = training.training_id WHERE date > CURRENT_DATE AND user_training.user_id = ? AND training.status = 'active' LIMIT ?,?";

    public static final String SQL_SELECT_ALL_USERS_DIET = "SELECT * FROM diet";
    public static final String SQL_INSERT_DIET = "INSERT diet(diet_type,fats,proteins,carbohydrates) VALUES (?,?,?,?)";
    public static final String SQL_SELECT_USERS_DIET = "SELECT diet_type,proteins,fats,carbohydrates,status,diet.diet_id FROM user_diet JOIN diet ON user_diet.diet_id = diet.diet_id WHERE user_id = ?";

    public static final String SQL_SELECT_ALL_USER_INFORMATION_DIET_TRAINING = "SELECT name,second_name,sex,weight,height,diet_type,proteins,fats,carbohydrates,date,personality,training_type FROM mydb.personal_information LEFT OUTER  JOIN mydb.user_diet ON  mydb.personal_information.information_id = mydb.user_diet.user_id LEFT OUTER JOIN mydb.diet ON mydb.diet.diet_id = mydb.user_diet.diet_id LEFT OUTER JOIN mydb.user_training ON  mydb.personal_information.information_id = mydb.user_training.user_id LEFT OUTER JOIN mydb.training ON mydb.training.training_id = mydb.user_training.training_id WHERE mydb.personal_information.information_id = ?";
    public static final String SQL_INSERT_USER_TRAINING = "INSERT mydb.user_training(user_id,training_id) VALUES (?,?)";
    public static final String SQL_INSERT_USER_DIET = "INSERT mydb.user_diet(user_id,diet_id) VALUES (?,?)";
    public static final String SQL_INSERT_USER_PERSONAL_INFORMATION = "INSERT mydb.user_information(user_id,information_id) VALUES (?,?)";
    public static final String SQL_SELECT_USER_IN_FORMATION_BY_ID = "SELECT * FROM mydb.personal_information WHERE  information_id = ? ;";
    public static final String SQL_SELECT_TRAINER_BY_ID = "SELECT * FROM mydb.trainer_information WHERE  trainer_id = ? ";


    public static final String SQL_UPDATE_USER_PASSWORD =  "UPDATE user SET password = ? WHERE id = ?";
    public static final String SQL_UPDATE_USERNAME =  "UPDATE user SET username = ? WHERE id = ?";
    public static final String SQL_UPDATE_TRAINING =    "UPDATE training SET date = ?, personality = ?,training_type = ? WHERE training_id = ?";
    public static final String SQL_UPDATE_TRAINER =       "UPDATE trainer_information SET  name = ?, work_experience = ?, training_type = ? WHERE trainer_id = ?";
    public static final String SQL_UPDATE_PERSONAL_INFORMATION =        "UPDATE mydb.personal_information SET name = ?, second_name = ?, sex = ?, weight = ?, height = ? WHERE information_id = ?";
    public static final String SQL_UPDATE_DIET =      "UPDATE diet  JOIN user_diet ON user_diet.diet_id = diet.diet_id  SET diet_type= ?, proteins=?, fats=?,carbohydrates=? WHERE user_diet.user_id = ?";
    public static final String SQL_INSERT_DISCOUNT = "INSERT mydb.discount_date(date, discount_amount) VALUES (?, ?)";

    public static final String SQL_CALCULATE_PRICE_FOR_TRAININGS = "SELECT (price * discounts.discount_value * if(discount_amount IS NOT NULL, 100 - discount_date.discount_amount, 100))/10000  FROM discounts JOIN personal_information ON discounts.discount_id = personal_information.status_id, centerces_price LEFT JOIN discount_date ON CURRENT_DATE = discount_date.date WHERE personal_information.information_id = ? AND training_amount = ?";
    public static final String SQL_SELECT_MONEY_FROM_ACCOUNT = "SELECT money_amount FROM money_card WHERE account_number = ?";
}

