package br.com.carv.grpc.resources;

import br.com.carv.grpc.HelloRequest;
import br.com.carv.grpc.HelloResponse;
import br.com.carv.grpc.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class HelloWorldResource extends HelloServiceGrpc.HelloServiceImplBase  {

    @Override
    public void hello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        HelloResponse response = HelloResponse.newBuilder().
                setMessage(request.getMessage())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
