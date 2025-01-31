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
    comments = "Source: common/UserService.proto")
public final class UserServerGrpc {

  private UserServerGrpc() {}

  public static final String SERVICE_NAME = "service.UserServer";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ru.demo.numbers.GRPCUsername,
      ru.demo.numbers.GRPCUser> getGetUserByUsernameMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetUserByUsername",
      requestType = ru.demo.numbers.GRPCUsername.class,
      responseType = ru.demo.numbers.GRPCUser.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<ru.demo.numbers.GRPCUsername,
      ru.demo.numbers.GRPCUser> getGetUserByUsernameMethod() {
    io.grpc.MethodDescriptor<ru.demo.numbers.GRPCUsername, ru.demo.numbers.GRPCUser> getGetUserByUsernameMethod;
    if ((getGetUserByUsernameMethod = UserServerGrpc.getGetUserByUsernameMethod) == null) {
      synchronized (UserServerGrpc.class) {
        if ((getGetUserByUsernameMethod = UserServerGrpc.getGetUserByUsernameMethod) == null) {
          UserServerGrpc.getGetUserByUsernameMethod = getGetUserByUsernameMethod =
              io.grpc.MethodDescriptor.<ru.demo.numbers.GRPCUsername, ru.demo.numbers.GRPCUser>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetUserByUsername"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ru.demo.numbers.GRPCUsername.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ru.demo.numbers.GRPCUser.getDefaultInstance()))
              .setSchemaDescriptor(new UserServerMethodDescriptorSupplier("GetUserByUsername"))
              .build();
        }
      }
    }
    return getGetUserByUsernameMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static UserServerStub newStub(io.grpc.Channel channel) {
    return new UserServerStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static UserServerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new UserServerBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static UserServerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new UserServerFutureStub(channel);
  }

  /**
   */
  public static abstract class UserServerImplBase implements io.grpc.BindableService {

    /**
     */
    public void getUserByUsername(ru.demo.numbers.GRPCUsername request,
        io.grpc.stub.StreamObserver<ru.demo.numbers.GRPCUser> responseObserver) {
      asyncUnimplementedUnaryCall(getGetUserByUsernameMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetUserByUsernameMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                ru.demo.numbers.GRPCUsername,
                ru.demo.numbers.GRPCUser>(
                  this, METHODID_GET_USER_BY_USERNAME)))
          .build();
    }
  }

  /**
   */
  public static final class UserServerStub extends io.grpc.stub.AbstractStub<UserServerStub> {
    private UserServerStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UserServerStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserServerStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserServerStub(channel, callOptions);
    }

    /**
     */
    public void getUserByUsername(ru.demo.numbers.GRPCUsername request,
        io.grpc.stub.StreamObserver<ru.demo.numbers.GRPCUser> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getGetUserByUsernameMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class UserServerBlockingStub extends io.grpc.stub.AbstractStub<UserServerBlockingStub> {
    private UserServerBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UserServerBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserServerBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserServerBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<ru.demo.numbers.GRPCUser> getUserByUsername(
        ru.demo.numbers.GRPCUsername request) {
      return blockingServerStreamingCall(
          getChannel(), getGetUserByUsernameMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class UserServerFutureStub extends io.grpc.stub.AbstractStub<UserServerFutureStub> {
    private UserServerFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UserServerFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserServerFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserServerFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_GET_USER_BY_USERNAME = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final UserServerImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(UserServerImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_USER_BY_USERNAME:
          serviceImpl.getUserByUsername((ru.demo.numbers.GRPCUsername) request,
              (io.grpc.stub.StreamObserver<ru.demo.numbers.GRPCUser>) responseObserver);
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

  private static abstract class UserServerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    UserServerBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ru.demo.numbers.UserService.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("UserServer");
    }
  }

  private static final class UserServerFileDescriptorSupplier
      extends UserServerBaseDescriptorSupplier {
    UserServerFileDescriptorSupplier() {}
  }

  private static final class UserServerMethodDescriptorSupplier
      extends UserServerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    UserServerMethodDescriptorSupplier(String methodName) {
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
      synchronized (UserServerGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new UserServerFileDescriptorSupplier())
              .addMethod(getGetUserByUsernameMethod())
              .build();
        }
      }
    }
    return result;
  }
}
