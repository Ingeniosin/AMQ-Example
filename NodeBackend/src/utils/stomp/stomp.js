import production from "../../config/production.js";
import Stomp from "stomp-client";

const {host, port, username, password} = production.amq;
const client = new Stomp(host, port, username, password, '1.0', null, {}, true);

export const connect = () => {
    return new Promise((resolve, reject) => {
        client.connect((sessionId) => {
            console.log('Connected to AMQ');
            resolve(sessionId);
        });
    });
}
export const getClient = () => {
    return client;
};