
package main

import (
	"github.com/jponge/vertx-go-tcp-eventbus-bridge/eventbus"
	"log"
	"time"
)

func main() {

	goaddress := "eventbus.test.go"
	eventBus, err := eventbus.NewEventBus("verxbackend:8088")

	if err != nil {
		log.Fatal("Connection to the Vert.x bridge failed: ", err)
	}
	
	for {
		<-time.After(time.Second * 1)
		eventBus.Publish(goaddress, nil, map[string]string{
			"message" : "Hello from Go!!",
			"type" : "GO",
		})
	}
}