package com.vertx.eventbus.vertxeventbus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.EventBus;
import io.vertx.ext.bridge.BridgeOptions;
import io.vertx.ext.bridge.PermittedOptions;
import io.vertx.ext.eventbus.bridge.tcp.TcpEventBusBridge;

import java.util.function.Consumer;

public class TcpEventBusVerticle extends AbstractVerticle {

  private final String GO_CLIENT_ADDRESS = "eventbus.test.go";
  private final String PYTHON_CLIENT_ADDRESS = "eventbus.test.python";
  private final String NODEJS_CLIENT_ADDRESS = "eventbus.test.nodejs";
  private final String SOCKET_SERVER_ADDRESS = "websocket_server_address";

  @Override
  public void start(Promise<Void> startPromise) {
    EventBus eb = vertx.eventBus();
    vertx.deployVerticle(new WebSocketEventBusVerticle());

    // Allow events for the Python, Go and Nodejs addresses as inbound
    // Allow websocket server address as Outbound to publish the events
    TcpEventBusBridge tcpEventBusBridge = TcpEventBusBridge.create(vertx, new BridgeOptions()
      .addInboundPermitted(new PermittedOptions().setAddress(GO_CLIENT_ADDRESS))
      .addInboundPermitted(new PermittedOptions().setAddress(NODEJS_CLIENT_ADDRESS))
      .addInboundPermitted(new PermittedOptions().setAddress(PYTHON_CLIENT_ADDRESS))
      .addOutboundPermitted(new PermittedOptions().setAddress(SOCKET_SERVER_ADDRESS)));

    tcpEventBusBridge.listen(8088);

    Consumer<Object> publisher = (message) -> eb.publish(SOCKET_SERVER_ADDRESS, message);

    eb.consumer(NODEJS_CLIENT_ADDRESS, callback -> {
      publisher.accept(callback.body());
    });

    eb.consumer(GO_CLIENT_ADDRESS, lambda -> {
      publisher.accept(lambda.body());
    });

    eb.consumer(PYTHON_CLIENT_ADDRESS, lambda -> {
      publisher.accept(lambda.body());
    });
  }
}
