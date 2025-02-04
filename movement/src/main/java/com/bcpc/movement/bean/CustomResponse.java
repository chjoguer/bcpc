package com.bcpc.movement.bean;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Custom response wrapper for standardized API responses
 * @param <T> The type of data to be included in the response
 */
public class CustomResponse<T> {
    private String msg;
    private T data;
    private String code;

    // Private constructor to enforce use of builder
    private CustomResponse() {}

    // Getters
    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public String getCode() {
        return code;
    }

    /**
     * Builder for creating CustomResponse instances
     */
    public static class Builder<T> {
        private final CustomResponse<T> response;

        public Builder() {
            response = new CustomResponse<>();
        }

        public Builder<T> msg(String msg) {
            response.msg = msg;
            return this;
        }

        public Builder<T> data(T data) {
            response.data = data;
            return this;
        }

        public Builder<T> code(String code) {
            response.code = code;
            return this;
        }

        public CustomResponse<T> build() {
            return response;
        }

        /**
         * Convenience method to create a ResponseEntity with this CustomResponse
         * @param httpStatus HTTP status to return
         * @return ResponseEntity with CustomResponse
         */
        public ResponseEntity<CustomResponse<T>> toResponseEntity(HttpStatus httpStatus) {
            return new ResponseEntity<>(response, httpStatus);
        }
    }

    /**
     * Static method to create a new Builder
     * @param <T> The type of data
     * @return A new Builder instance
     */
    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    /**
     * Convenience method for creating success responses
     *
     * @param data The data to be returned
     * @param <T>  The type of data
     * @return A CustomResponse with success message and HTTP 200 OK status
     */
    public static <T> ResponseEntity<CustomResponse<Object>> success(T data) {
        return builder()
                .msg("Success")
                .data(data)
                .code("200")
                .toResponseEntity(HttpStatus.OK);
    }

    /**
     * Convenience method for creating error responses
     *
     * @param msg  Error message
     * @param code Error code
     * @param <T>  The type of data (typically Void or null)
     * @return A CustomResponse with error details and appropriate HTTP status
     */
    public static <T> ResponseEntity<CustomResponse<Object>> error(String msg, String code) {
        return builder()
                .msg(msg)
                .data(null)
                .code(code)
                .toResponseEntity(HttpStatus.BAD_REQUEST);
    }
}