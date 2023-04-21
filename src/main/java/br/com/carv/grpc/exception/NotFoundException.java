package br.com.carv.grpc.exception;

import io.grpc.Status;

public class NotFoundException extends BaseBusinessException {

    private static final String ERROR_MESSAGE = "Product not found. Id: %s";
    private final Long id;

    public NotFoundException(Long id) {
        super(String.format(ERROR_MESSAGE, id));
        this.id = id;
    }

    @Override
    public Status getStatusCode() {
        return Status.NOT_FOUND;
    }

    @Override
    public String getErrorMessage() {
        return String.format(this.ERROR_MESSAGE, this.id);
    }
}
