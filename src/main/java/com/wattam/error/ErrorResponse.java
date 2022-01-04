package com.wattam.error;

import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "error")
public class ErrorResponse {

    private String message;
    private String[] details;

    public ErrorResponse(String message, String[] details) {
        super();
        this.message = message;
        this.details = details;
    }
}
