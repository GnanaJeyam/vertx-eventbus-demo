# vertx-eventbus-demo
This repo contains the implementation of Vertx TCP-Eventbus Bridge and SocketJS Eventbus Bridge. 
You will also find a demo application using TCP Eventbus client and SocketJs client.

## To build and deploy the project using docker-compose
```
  docker-compose up --build
```

The above command will build all the client and server projects and also spin up all 4 container by creating a network between them
for container-to-container communication. 

After the successful deployment, Navigate to http://localhost:8080 in the browser.
You will able to see this screen
<img src="/output/socketjsoutput.png"><img>



## To undeploy the project using docker-compose
```
  docker-compose down
```
