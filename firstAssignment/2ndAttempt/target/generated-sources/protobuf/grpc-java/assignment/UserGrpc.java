package assignment;

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
    comments = "Source: cars.proto")
public final class UserGrpc {

  private UserGrpc() {}

  public static final String SERVICE_NAME = "User";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<assignment.CarandOwners.Owner,
      assignment.CarandOwners.CarList> getPedidoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "pedido",
      requestType = assignment.CarandOwners.Owner.class,
      responseType = assignment.CarandOwners.CarList.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<assignment.CarandOwners.Owner,
      assignment.CarandOwners.CarList> getPedidoMethod() {
    io.grpc.MethodDescriptor<assignment.CarandOwners.Owner, assignment.CarandOwners.CarList> getPedidoMethod;
    if ((getPedidoMethod = UserGrpc.getPedidoMethod) == null) {
      synchronized (UserGrpc.class) {
        if ((getPedidoMethod = UserGrpc.getPedidoMethod) == null) {
          UserGrpc.getPedidoMethod = getPedidoMethod =
              io.grpc.MethodDescriptor.<assignment.CarandOwners.Owner, assignment.CarandOwners.CarList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "pedido"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  assignment.CarandOwners.Owner.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  assignment.CarandOwners.CarList.getDefaultInstance()))
              .setSchemaDescriptor(new UserMethodDescriptorSupplier("pedido"))
              .build();
        }
      }
    }
    return getPedidoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<assignment.CarandOwners.OwnerList,
      assignment.CarandOwners.CarList> getRequestMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "request",
      requestType = assignment.CarandOwners.OwnerList.class,
      responseType = assignment.CarandOwners.CarList.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<assignment.CarandOwners.OwnerList,
      assignment.CarandOwners.CarList> getRequestMethod() {
    io.grpc.MethodDescriptor<assignment.CarandOwners.OwnerList, assignment.CarandOwners.CarList> getRequestMethod;
    if ((getRequestMethod = UserGrpc.getRequestMethod) == null) {
      synchronized (UserGrpc.class) {
        if ((getRequestMethod = UserGrpc.getRequestMethod) == null) {
          UserGrpc.getRequestMethod = getRequestMethod =
              io.grpc.MethodDescriptor.<assignment.CarandOwners.OwnerList, assignment.CarandOwners.CarList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "request"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  assignment.CarandOwners.OwnerList.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  assignment.CarandOwners.CarList.getDefaultInstance()))
              .setSchemaDescriptor(new UserMethodDescriptorSupplier("request"))
              .build();
        }
      }
    }
    return getRequestMethod;
  }

  private static volatile io.grpc.MethodDescriptor<assignment.CarandOwners.CapsulaXML,
      assignment.CarandOwners.CapsulaXML> getRequestXMLMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "requestXML",
      requestType = assignment.CarandOwners.CapsulaXML.class,
      responseType = assignment.CarandOwners.CapsulaXML.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<assignment.CarandOwners.CapsulaXML,
      assignment.CarandOwners.CapsulaXML> getRequestXMLMethod() {
    io.grpc.MethodDescriptor<assignment.CarandOwners.CapsulaXML, assignment.CarandOwners.CapsulaXML> getRequestXMLMethod;
    if ((getRequestXMLMethod = UserGrpc.getRequestXMLMethod) == null) {
      synchronized (UserGrpc.class) {
        if ((getRequestXMLMethod = UserGrpc.getRequestXMLMethod) == null) {
          UserGrpc.getRequestXMLMethod = getRequestXMLMethod =
              io.grpc.MethodDescriptor.<assignment.CarandOwners.CapsulaXML, assignment.CarandOwners.CapsulaXML>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "requestXML"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  assignment.CarandOwners.CapsulaXML.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  assignment.CarandOwners.CapsulaXML.getDefaultInstance()))
              .setSchemaDescriptor(new UserMethodDescriptorSupplier("requestXML"))
              .build();
        }
      }
    }
    return getRequestXMLMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static UserStub newStub(io.grpc.Channel channel) {
    return new UserStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static UserBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new UserBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static UserFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new UserFutureStub(channel);
  }

  /**
   */
  public static abstract class UserImplBase implements io.grpc.BindableService {

    /**
     */
    public void pedido(assignment.CarandOwners.Owner request,
        io.grpc.stub.StreamObserver<assignment.CarandOwners.CarList> responseObserver) {
      asyncUnimplementedUnaryCall(getPedidoMethod(), responseObserver);
    }

    /**
     */
    public void request(assignment.CarandOwners.OwnerList request,
        io.grpc.stub.StreamObserver<assignment.CarandOwners.CarList> responseObserver) {
      asyncUnimplementedUnaryCall(getRequestMethod(), responseObserver);
    }

    /**
     */
    public void requestXML(assignment.CarandOwners.CapsulaXML request,
        io.grpc.stub.StreamObserver<assignment.CarandOwners.CapsulaXML> responseObserver) {
      asyncUnimplementedUnaryCall(getRequestXMLMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getPedidoMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                assignment.CarandOwners.Owner,
                assignment.CarandOwners.CarList>(
                  this, METHODID_PEDIDO)))
          .addMethod(
            getRequestMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                assignment.CarandOwners.OwnerList,
                assignment.CarandOwners.CarList>(
                  this, METHODID_REQUEST)))
          .addMethod(
            getRequestXMLMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                assignment.CarandOwners.CapsulaXML,
                assignment.CarandOwners.CapsulaXML>(
                  this, METHODID_REQUEST_XML)))
          .build();
    }
  }

  /**
   */
  public static final class UserStub extends io.grpc.stub.AbstractStub<UserStub> {
    private UserStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UserStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserStub(channel, callOptions);
    }

    /**
     */
    public void pedido(assignment.CarandOwners.Owner request,
        io.grpc.stub.StreamObserver<assignment.CarandOwners.CarList> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPedidoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void request(assignment.CarandOwners.OwnerList request,
        io.grpc.stub.StreamObserver<assignment.CarandOwners.CarList> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRequestMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void requestXML(assignment.CarandOwners.CapsulaXML request,
        io.grpc.stub.StreamObserver<assignment.CarandOwners.CapsulaXML> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRequestXMLMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class UserBlockingStub extends io.grpc.stub.AbstractStub<UserBlockingStub> {
    private UserBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UserBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserBlockingStub(channel, callOptions);
    }

    /**
     */
    public assignment.CarandOwners.CarList pedido(assignment.CarandOwners.Owner request) {
      return blockingUnaryCall(
          getChannel(), getPedidoMethod(), getCallOptions(), request);
    }

    /**
     */
    public assignment.CarandOwners.CarList request(assignment.CarandOwners.OwnerList request) {
      return blockingUnaryCall(
          getChannel(), getRequestMethod(), getCallOptions(), request);
    }

    /**
     */
    public assignment.CarandOwners.CapsulaXML requestXML(assignment.CarandOwners.CapsulaXML request) {
      return blockingUnaryCall(
          getChannel(), getRequestXMLMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class UserFutureStub extends io.grpc.stub.AbstractStub<UserFutureStub> {
    private UserFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UserFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<assignment.CarandOwners.CarList> pedido(
        assignment.CarandOwners.Owner request) {
      return futureUnaryCall(
          getChannel().newCall(getPedidoMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<assignment.CarandOwners.CarList> request(
        assignment.CarandOwners.OwnerList request) {
      return futureUnaryCall(
          getChannel().newCall(getRequestMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<assignment.CarandOwners.CapsulaXML> requestXML(
        assignment.CarandOwners.CapsulaXML request) {
      return futureUnaryCall(
          getChannel().newCall(getRequestXMLMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PEDIDO = 0;
  private static final int METHODID_REQUEST = 1;
  private static final int METHODID_REQUEST_XML = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final UserImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(UserImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_PEDIDO:
          serviceImpl.pedido((assignment.CarandOwners.Owner) request,
              (io.grpc.stub.StreamObserver<assignment.CarandOwners.CarList>) responseObserver);
          break;
        case METHODID_REQUEST:
          serviceImpl.request((assignment.CarandOwners.OwnerList) request,
              (io.grpc.stub.StreamObserver<assignment.CarandOwners.CarList>) responseObserver);
          break;
        case METHODID_REQUEST_XML:
          serviceImpl.requestXML((assignment.CarandOwners.CapsulaXML) request,
              (io.grpc.stub.StreamObserver<assignment.CarandOwners.CapsulaXML>) responseObserver);
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

  private static abstract class UserBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    UserBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return assignment.CarandOwners.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("User");
    }
  }

  private static final class UserFileDescriptorSupplier
      extends UserBaseDescriptorSupplier {
    UserFileDescriptorSupplier() {}
  }

  private static final class UserMethodDescriptorSupplier
      extends UserBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    UserMethodDescriptorSupplier(String methodName) {
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
      synchronized (UserGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new UserFileDescriptorSupplier())
              .addMethod(getPedidoMethod())
              .addMethod(getRequestMethod())
              .addMethod(getRequestXMLMethod())
              .build();
        }
      }
    }
    return result;
  }
}
