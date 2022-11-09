import {getClient} from "../stomp/stomp.js";

const client = getClient()

export const send = (queue, message) => {
    client.publish('/queue/' + queue, JSON.stringify(message));
}