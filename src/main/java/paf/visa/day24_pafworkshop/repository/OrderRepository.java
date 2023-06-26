package paf.visa.day24_pafworkshop.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import paf.visa.day24_pafworkshop.model.Order;

@Repository
public class OrderRepository {

    @Autowired private JdbcTemplate jdbcTemplate;

    private final String insertOrderSql = "insert into orders(order_date, customer_name, ship_address, notes, tax) values (?, ?, ?, ?, ?)";

    private final String insertOrderDetailsSql = "insert into order_details(product, unit_price, discount, quantity) values (?, ?, ?, ?)";

    public boolean createOrder(Order order) {
        //Integer iResult = jdbcTemplate.update(insertOrderSql, order.getOrderDate(), order.getCustomerName(), order.getShipAddress(), order.getNotes(), order.getTax());

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

                PreparedStatement ps = con.prepareStatement(insertOrderSql);
                ps.setDate(0, order.getOrderDate());
                ps.setString(1, order.getCustomerName());
                ps.setString(2, order.getShipAddress());
                ps.setString(3, order.getNotes());
                ps.setFloat(4, order.getTax());

                return ps;
            }
        };

        Integer iResult = jdbcTemplate.update(psc);

        return iResult > 0;
    }

    public boolean createOrderDetails(Order order) {
        //Integer iResult = jdbcTemplate.update(insertOrderDetailsSql, order.getProductName(), order.getUnitPrice(), order.getDiscount(), order.getQuantity());

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

                PreparedStatement ps = con.prepareStatement(insertOrderDetailsSql);
                ps.setString(0, order.getProductName());
                ps.setFloat(1, order.getUnitPrice());
                ps.setFloat(2, order.getDiscount());
                ps.setInt(3, order.getQuantity());

                return ps;
            }
        };

        Integer iResult = jdbcTemplate.update(psc);

        return iResult > 0;
    }
}
