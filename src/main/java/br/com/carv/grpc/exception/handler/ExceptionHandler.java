package br.com.carv.grpc.exception.handler;

import br.com.carv.grpc.exception.BaseBusinessException;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;

@GrpcAdvice
public class ExceptionHandler {

    @GrpcExceptionHandler(BaseBusinessException.class)
    public StatusRuntimeException handleBusinessException(BaseBusinessException exception) {
        return exception.getStatusCode()
                .withCause(exception.getCause())
                .withDescription(exception.getErrorMessage())
                .asRuntimeException();
    }
}
