import {send} from "./producer.js";
import {getAsync} from "./consumer.js";

export const sendAndReceive = async (sendQueue, getQueue, message) => {
    send(sendQueue, message);
    return await getAsync(getQueue);
}