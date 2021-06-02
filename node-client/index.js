// Node JS Client
const EventBus = require('./vertx');

const eventBus = new EventBus('verxbackend', 8088);
const nodeClientAddress = "eventbus.test.nodejs";

eventBus.onopen = () => {

	setInterval(() =>
		eventBus.publish( nodeClientAddress,
			{
				type: 'NODEJS',
				message: 'Hello from NodeJS !!'
			}),
		1000
	);
};
