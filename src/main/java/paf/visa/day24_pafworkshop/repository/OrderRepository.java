package paf.visa.day24_pafworkshop.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import paf.visa.day24_pafworkshop.model.Order;

@Repository
public class OrderRepository {

    @Autowired private JdbcTemplate jdbcTemplate;

    private final String insertOrderSql = "insert into orders(order_date, customer_name, ship_address, notes, tax) values (?, ?, ?, ?, ?)";

    private final String insertOrderDetailsSql = "insert into order_details(product, unit_price, discount, quantity, order_id) values (?, ?, ?, ?, ?)";

    private final String selectOrderId = "select order_id from orders where customer_name = ?";

    public Integer[] createOrder(Order order) {
        //Integer iResult = jdbcTemplate.update(insertOrderSql, order.getOrderDate(), order.getCustomerName(), order.getShipAddress(), order.getNotes(), order.getTax());

        KeyHolder generatedKey = new GeneratedKeyHolder();

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

                PreparedStatement ps = con.prepareStatement(insertOrderSql);
                ps.setDate(1, order.getOrderDate());
                ps.setString(2, order.getCustomerName());
                ps.setString(3, order.getShipAddress());
                ps.setString(4, order.getNotes());
                if(order.getTax()!= null) {ps.setFloat(5, order.getTax());} else {ps.setString(5, null);}
                return ps;
            }
        };

        Integer iResult = jdbcTemplate.update(psc, generatedKey);
        Integer[] createdOrder = {iResult, generatedKey.getKey().intValue()};
        return createdOrder;
    }

    public boolean createOrderDetails(Order order, Integer orderId) {
        //Integer iResult = jdbcTemplate.update(insertOrderDetailsSql, order.getProductName(), order.getUnitPrice(), order.getDiscount(), order.getQuantity());

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

                PreparedStatement ps = con.prepareStatement(insertOrderDetailsSql);
                ps.setString(1, order.getProductName());
                ps.setDouble(2, order.getUnitPrice());
                if (order.getDiscount()!= null) {ps.setFloat(3, order.getDiscount());} else {ps.setString(3, null);}
                ps.setInt(4, order.getQuantity());
                ps.setInt(5, orderId);

                return ps;
            }
        };

        Integer iResult = jdbcTemplate.update(psc);

        return iResult > 0;
    }
}
