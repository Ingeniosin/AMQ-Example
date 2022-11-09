export default {
    amq: {
        username: process.env.AMQ_USERNAME,
        password: process.env.AMQ_PASSWORD,
        host: process.env.AMQ_HOST,
        port: Number(process.env.AMQ_PORT),
        queues: {
            service_to_back: process.env.AMQ_POST_QUEUE,
            back_to_service: process.env.AMQ_RESPONSE_QUEUE
        }
    },
    timer: {
        interval: Number(process.env.TIMER_INTERVAL),
        timeout: Number(process.env.TIMER_TIMEOUT),
    },
    port: 3000
}