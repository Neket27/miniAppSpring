package ru.demo.numbers;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.24.0)",
    comments = "Source: common/NumbersService.proto")
public final class NumbersServiceGrpc {

  private NumbersServiceGrpc() {}

  public static final String SERVICE_NAME = "NumbersService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ru.demo.numbers.NumberRequest,
      ru.demo.numbers.NumberResponse> getGetNumberMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getNumber",
      requestType = ru.demo.numbers.NumberRequest.class,
      responseType = ru.demo.numbers.NumberResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<ru.demo.numbers.NumberRequest,
      ru.demo.numbers.NumberResponse> getGetNumberMethod() {
    io.grpc.MethodDescriptor<ru.demo.numbers.NumberRequest, ru.demo.numbers.NumberResponse> getGetNumberMethod;
    if ((getGetNumberMethod = NumbersServiceGrpc.getGetNumberMethod) == null) {
      synchronized (NumbersServiceGrpc.class) {
        if ((getGetNumberMethod = NumbersServiceGrpc.getGetNumberMethod) == null) {
          NumbersServiceGrpc.getGetNumberMethod = getGetNumberMethod =
              io.grpc.MethodDescriptor.<ru.demo.numbers.NumberRequest, ru.demo.numbers.NumberResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getNumber"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ru.demo.numbers.NumberRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ru.demo.numbers.NumberResponse.getDefaultInstance()))
              .setSchemaDescriptor(new NumbersServiceMethodDescriptorSupplier("getNumber"))
              .build();
        }
      }
    }
    return getGetNumberMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static NumbersServiceStub newStub(io.grpc.Channel channel) {
    return new NumbersServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static NumbersServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new NumbersServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static NumbersServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new NumbersServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class NumbersServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getNumber(ru.demo.numbers.NumberRequest request,
        io.grpc.stub.StreamObserver<ru.demo.numbers.NumberResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetNumberMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetNumberMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                ru.demo.numbers.NumberRequest,
                ru.demo.numbers.NumberResponse>(
                  this, METHODID_GET_NUMBER)))
          .build();
    }
  }

  /**
   */
  public static final class NumbersServiceStub extends io.grpc.stub.AbstractStub<NumbersServiceStub> {
    private NumbersServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private NumbersServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NumbersServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new NumbersServiceStub(channel, callOptions);
    }

    /**
     */
    public void getNumber(ru.demo.numbers.NumberRequest request,
        io.grpc.stub.StreamObserver<ru.demo.numbers.NumberResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getGetNumberMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class NumbersServiceBlockingStub extends io.grpc.stub.AbstractStub<NumbersServiceBlockingStub> {
    private NumbersServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private NumbersServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NumbersServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new NumbersServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<ru.demo.numbers.NumberResponse> getNumber(
        ru.demo.numbers.NumberRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getGetNumberMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class NumbersServiceFutureStub extends io.grpc.stub.AbstractStub<NumbersServiceFutureStub> {
    private NumbersServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private NumbersServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NumbersServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new NumbersServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_GET_NUMBER = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final NumbersServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(NumbersServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_NUMBER:
          serviceImpl.getNumber((ru.demo.numbers.NumberRequest) request,
              (io.grpc.stub.StreamObserver<ru.demo.numbers.NumberResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class NumbersServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    NumbersServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ru.demo.numbers.NumbersServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("NumbersService");
    }
  }

  private static final class NumbersServiceFileDescriptorSupplier
      extends NumbersServiceBaseDescriptorSupplier {
    NumbersServiceFileDescriptorSupplier() {}
  }

  private static final class NumbersServiceMethodDescriptorSupplier
      extends NumbersServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    NumbersServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (NumbersServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new NumbersServiceFileDescriptorSupplier())
              .addMethod(getGetNumberMethod())
              .build();
        }
      }
    }
    return result;
  }
}
