package com.vertx.eventbus.vertxeventbus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.ext.bridge.PermittedOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.sockjs.SockJSBridgeOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;

public class WebSocketEventBusVerticle extends AbstractVerticle {

  private final String JAVASCRIPT_CLIENT_ADDRESS = "eventbus.ui.javascript";
  private final String WEBSOCKET_SERVER_ADDRESS = "websocket_server_address";

  @Override
  public void start() {
    EventBus eb = vertx.eventBus();
    Router router = Router.router(vertx);

    SockJSBridgeOptions options = new SockJSBridgeOptions()
      .addOutboundPermitted(new PermittedOptions().setAddressRegex(JAVASCRIPT_CLIENT_ADDRESS))
      .addInboundPermitted(new PermittedOptions().setAddressRegex((JAVASCRIPT_CLIENT_ADDRESS)));

    SockJSHandler sockJSHandler = SockJSHandler.create(vertx);
    router.mountSubRouter("/", sockJSHandler.bridge(options));

    // Start the web server and tell it to use the router to handle requests.
    vertx.createHttpServer()
      .requestHandler(router)
      .listen(8999);

    eb.consumer(WEBSOCKET_SERVER_ADDRESS, l -> {
      eb.send(JAVASCRIPT_CLIENT_ADDRESS, l.body());
    });
  }
}
