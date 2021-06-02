import Eventbus as Eventbus
import time

eb = Eventbus.Eventbus('verxbackend', 8088)
body = {
	"message" : "Hello from Python!!",
	"type" : "PYTHON"
}

while True:
	eb.publish('eventbus.test.python', body) 
	time.sleep(1)