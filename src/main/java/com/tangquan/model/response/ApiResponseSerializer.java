package com.tangquan.model.response;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class ApiResponseSerializer extends JsonSerializer<ApiResponse> {

    @Override
    public void serialize(ApiResponse value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException, JsonProcessingException {
        if (value.getStatus().equals(ApiResponse.STATUS_OK)) {
            gen.writeObject(value.getData());
        } else {
            gen.writeStartObject();
            gen.writeStringField("key", value.getErrorCode());
            gen.writeStringField("msg", value.getMessage());
            gen.writeEndObject();
        }
    }
}
