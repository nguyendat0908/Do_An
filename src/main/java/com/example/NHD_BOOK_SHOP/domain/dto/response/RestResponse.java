package com.example.NHD_BOOK_SHOP.domain.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestResponse<T> {

    private int statusCode;
    private String error;

    // Message có thể là String hoặc Array
    private Object message;
    private T data;
}
