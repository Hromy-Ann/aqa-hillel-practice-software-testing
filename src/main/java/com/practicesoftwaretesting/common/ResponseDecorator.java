package com.practicesoftwaretesting.common;

import io.restassured.response.Response;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ResponseDecorator<T> {
    private final Response targetResponse;
    private final Class<T> targetClass;

    public Response get() {
        return targetResponse;
    }

    public T as() {
        return targetResponse.as(targetClass);
    }

    public ResponseDecorator<T> assertStatusCode(int expectedStatusCode) {
        targetResponse.then().statusCode(expectedStatusCode);
        return this;
    }
}
