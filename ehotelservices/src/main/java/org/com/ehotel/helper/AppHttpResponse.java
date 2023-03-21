package org.com.ehotel.helper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 2023-03-21, Tuesday
 **/

@Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class AppHttpResponse {
    private Boolean success;
    private String message;
    private Map<String, Object> data;
    private HttpStatus status;
    private LocalDateTime timestamp;
}
