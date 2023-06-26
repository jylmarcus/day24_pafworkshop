package paf.visa.day24_pafworkshop.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
    private int statusCode;

    private Date timeStamp;

    private String message;

    private String description;
}
