FROM golang:1.16
WORKDIR /usr/app/go
RUN go mod init go
COPY ./test.go .
RUN go get github.com/jponge/vertx-go-tcp-eventbus-bridge/eventbus
CMD ["go","run", "test.go"]