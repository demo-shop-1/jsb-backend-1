package org.demo.shop1.exceptions;

import java.util.Calendar;
import java.util.Map;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@Order(-1)
public class GlobalErrorAttributes extends DefaultErrorAttributes {

  @Override
  public Map<String, Object> getErrorAttributes(ServerRequest request,
      ErrorAttributeOptions options) {

    GlobalErrorDTO global = new GlobalErrorDTO();
    Throwable error = getError(request);
    Map<String, Object> map = super.getErrorAttributes(
        request, options);

    global.setMessageRaw(error.getMessage());

    if (error.getCause() != null) {
      global.setMessageCode(error.getCause().getMessage());
    }

    global.setStatus(map.get("error").toString());
    global.setStatusCode(Integer.parseInt(map.get("status").toString()));
    global.setDate(Calendar.getInstance().getTime().toString());

    return new ObjectMapper().convertValue(global,
        new TypeReference<Map<String, Object>>() {
        });
  }

}
