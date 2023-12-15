package ru.netology.sachko.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import ru.netology.sachko.data.DbUtils.CreditRequestEntity;
import ru.netology.sachko.data.DbUtils.OrderEntity;
import ru.netology.sachko.data.DbUtils.PaymentEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNull;

public class DbHelper {

    private static String url = System.getProperty("db.url");
    private static String user = System.getProperty("db.user");
    private static String password = System.getProperty("db.password");

    private static Connection connection;

    public DbHelper() {
    }

    @SneakyThrows
    private static void getConnection() {
        connection = DriverManager.getConnection(url, user, password);
    }

    @SneakyThrows
    public static void cleanData() {
        QueryRunner runner = new QueryRunner();
        String cleanCreditRequest = "DELETE FROM credit_request_entity;";
        String cleanPayment = "DELETE FROM payment_entity;";
        String cleanOrder = "DELETE FROM order_entity;";
        getConnection();
        runner.update(connection, cleanCreditRequest);
        runner.update(connection, cleanPayment);
        runner.update(connection, cleanOrder);
    }

    @SneakyThrows
    public static PaymentEntity payData() {
        QueryRunner runner = new QueryRunner();
        String reqStatus = "SELECT * FROM payment_entity ORDER BY created DESC LIMIT 1;";

        PaymentEntity payData = new PaymentEntity();
        getConnection();
        payData = runner.query(connection, reqStatus, new BeanHandler<>(PaymentEntity.class));
        return payData;
    }


    @SneakyThrows
    public static CreditRequestEntity creditData() {
        QueryRunner runner = new QueryRunner();
        String selectStatus = "SELECT * FROM credit_request_entity ORDER BY created DESC LIMIT 1;";

        CreditRequestEntity creditData = new CreditRequestEntity();
        getConnection();
        creditData = runner.query(connection, selectStatus, new BeanHandler<>(CreditRequestEntity.class));
        return creditData;
    }

    @SneakyThrows
    public static OrderEntity orderData() {
        var runner = new QueryRunner();
        var selectStatus = "SELECT * FROM order_entity ORDER BY created DESC LIMIT 1;";

        OrderEntity orderData = new OrderEntity();
        getConnection();
        orderData = runner.query(connection, selectStatus, new BeanHandler<>(OrderEntity.class));
        return orderData;
    }

    @SneakyThrows
    public static void checkEmptyOrderEntity() {
        var runner = new QueryRunner();
        String orderRequest = "SELECT * FROM order_entity;";

        getConnection();
        var orderBlock = runner.query(connection, orderRequest, new BeanHandler<>(OrderEntity.class));
        assertNull(orderBlock);
    }


    @SneakyThrows
    public static void checkEmptyPaymentEntity() {
        var runner = new QueryRunner();
        var orderRequest = "SELECT * FROM payment_entity";

        getConnection();
        var paymentBlock = runner.query(connection, orderRequest, new BeanHandler<>(PaymentEntity.class));
        assertNull(paymentBlock);
    }

    @SneakyThrows
    public static void checkEmptyCreditEntity() {
        var runner = new QueryRunner();
        var orderRequest = "SELECT * FROM credit_request_entity;";

        CreditRequestEntity creditBlock = new CreditRequestEntity();
        getConnection();
        creditBlock = runner.query(connection, orderRequest, new BeanHandler<>(CreditRequestEntity.class));
        assertNull(creditBlock);
    }
}
