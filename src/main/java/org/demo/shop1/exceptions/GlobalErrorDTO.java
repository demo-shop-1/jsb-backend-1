package org.demo.shop1.exceptions;

import lombok.Data;

@Data
public class GlobalErrorDTO {
    private String messageRaw;
    private String messageCode;
    private String status;
    private int statusCode;
    private String date;

}
