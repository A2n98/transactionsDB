package date.base;

import classes.Transaction;
import models.Task2Model;
import models.TransactionSumModel;
import org.sqlite.JDBC;
import java.sql.*;
import java.util.ArrayList;

public class DBworker {

    // Константа, в которой хранится адрес подключения
    private static final String CON_STR = "jdbc:sqlite:trans.sqlite";

    // Используем шаблон одиночка, чтобы не плодить множество
    // экземпляров класса DbHandler
    private static DBworker instance = null;

    public static synchronized DBworker getInstance() throws SQLException {
        if (instance == null)
            instance = new DBworker();
        return instance;
    }

    // Объект, в котором будет храниться соединение с БД
    private Connection connection;

    private DBworker() throws SQLException {
        // Регистрируем драйвер, с которым будем работать
        // в нашем случае Sqlite
        DriverManager.registerDriver(new JDBC());
        // Выполняем подключение к базе данных
        this.connection = DriverManager.getConnection(CON_STR);
    }

    public void addTransaction(Transaction transaction){
        try (var statement = this.connection.prepareStatement(
                "INSERT INTO 'Transactions'('Series_reference','Period','Data_value','Suppressed','STATUS','UNITS'," +
                        "'Magnitude','Subject','Group','Series_title_1','Series_title_2','Series_title_3','Series_title_4','Series_title_5') " +
                        "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)")) {
            statement.setObject(1, transaction.serieRef);
            statement.setObject(2, transaction.period);
            statement.setObject(3, transaction.dataValue);
            statement.setObject(4, transaction.information.suppressed);
            statement.setObject(5, transaction.information.status);
            statement.setObject(6, transaction.information.units);
            statement.setObject(7, transaction.information.magnitude);
            statement.setObject(8, transaction.information.subject);
            statement.setObject(9, transaction.information.group);
            statement.setObject(10, transaction.serieTitles.title1);
            statement.setObject(11, transaction.serieTitles.title2);
            statement.setObject(12, transaction.serieTitles.title3);
            statement.setObject(13, transaction.serieTitles.title4);
            statement.setObject(14, transaction.serieTitles.title5);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<TransactionSumModel> getTransactionSumModels(){
        var models = new ArrayList<TransactionSumModel>();
        try (var statement = this.connection.createStatement()){
            var resultSet = statement.executeQuery("select sum(Value) as 'sum', Period " +
                    "from transactions " +
                    "where Unit = 'Dollars' and Period like '2020.%' and Value is not null " +
                    "group by Period");
            while(resultSet.next()){
                var model = new TransactionSumModel();
                model.sum = Float.parseFloat(resultSet.getString("sum"));
                model.month = resultSet.getString("Period");
                models.add(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return models;
    }

    public void printTask2(){
        var models = new ArrayList<Task2Model>();
        try (var statement = this.connection.createStatement()){
            var resultSet = statement.executeQuery("select distinct avg(Value) as 'Среднее значение', count(Period) as 'Количество', Period\n" +
                    "from transactions " +
                    "where Unit = 'Dollars' and  Value is not null\n" +
                    "group by Period");
            while(resultSet.next()){
                var model = new Task2Model();
                model.avg = Float.parseFloat(resultSet.getString("Среднее значение"));
                model.count = Integer.parseInt(resultSet.getString("Количество"));
                model.period = resultSet.getString("Period");
                models.add(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for(var model: models){
            System.out.println(model.toString());
        }
    }
}
