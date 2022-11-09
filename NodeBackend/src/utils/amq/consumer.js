import {getClient} from "../stomp/stomp.js";

const client = getClient()

const get = (queue, onReceive) => {
    const name = '/queue/' + queue;
    client.subscribe(name, (msg) => {
        onReceive(msg);
        client.unsubscribe(name);
    });
}

export const getAsync = (queue) => {
    return new Promise((resolve, reject) => {
        get(queue, (message) => {
            resolve(message);
        });
    })
}

