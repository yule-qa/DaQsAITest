package com.dongao.DaQsAiTest.FileDto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @Author: yule
 * @Description:
 * @Date: create in 2021/1/21 11:05 上午
 */
public class ResponseTextDtoDeserializer extends JsonDeserializer<ResponseTextDto> {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public ResponseTextDto deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return mapper.readValue(jsonParser.getText(), ResponseTextDto.class);
    }
}
