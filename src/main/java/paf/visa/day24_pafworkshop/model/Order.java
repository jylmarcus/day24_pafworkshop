package paf.visa.day24_pafworkshop.model;

import java.io.Serializable;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable{
    private Integer id;
    private Date orderDate;
    private String customerName;
    private String shipAddress;
    private String notes;
    private Float tax;
    private String productName;
    private Double unitPrice;
    private Float discount;
    private Integer quantity;
}
