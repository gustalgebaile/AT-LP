package org.example.application.common;

import org.example.domain.exceptions.DeliveryException;

public class OperationResult<T> {
    private final T value;
    private final String error;
    private final boolean success;

    private OperationResult(T value, String error, boolean success) {
        this.value = value;
        this.error = error;
        this.success = success;
    }

    public static <T> OperationResult<T> success(T value) {
        return new OperationResult<>(value, null, true);
    }

    public static <T> OperationResult<T> failure(String message) {
        return new OperationResult<>(null, message, false);
    }

    public boolean isSuccess() { return success; }
    public T getValue() { return value; }
    public String getError() { return error; }

    public T getValueOrThrow() {
        if (!success) {
            throw new DeliveryException(error);
        }
        return value;
    }
}


