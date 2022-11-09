import Model from "./model.js";
import {sendAndReceive} from "../../utils/amq/general.js";
import production from "../../config/production.js";

export const get = async (req, res) => {
    const {service_to_back, back_to_service} = production.amq.queues;

    const response = await sendAndReceive(service_to_back, back_to_service, {
        command: "get",
    });

    if (req.timedout) return;
    return res.status(200).json(JSON.parse(response));
}

export class create {
}