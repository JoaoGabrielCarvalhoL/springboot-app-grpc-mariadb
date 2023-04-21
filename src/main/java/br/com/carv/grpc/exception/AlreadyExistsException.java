package br.com.carv.grpc.exception;

import io.grpc.Status;

public class AlreadyExistsException extends BaseBusinessException {

    private static final String ERROR_MESSAGE = "Product: %s already registered in the system.";
    private final String name;

    public AlreadyExistsException(String name) {
        super(String.format(ERROR_MESSAGE, name));
        this.name = name;
    }

    @Override
    public Status getStatusCode() {
        return Status.ALREADY_EXISTS;
    }

    @Override
    public String getErrorMessage() {
        return String.format(this.ERROR_MESSAGE, this.name);
    }
}
