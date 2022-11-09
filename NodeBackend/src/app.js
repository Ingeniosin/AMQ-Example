import express, {json} from 'express';
import configuration from './config/production.js';
import routes from './routes/index.js';
import generalMiddlewares from "./middleware/general.js";
import {connect} from "./utils/stomp/stomp.js";

const {timeout, error404, error500} = generalMiddlewares;
const app = express();

app.use(json());
app.use(timeout);
app.use(routes);
/*app.use(error404);
app.use(error500);*/

connect().then(() => {
    app.listen(configuration.port, () => {
        console.log(`Server running on port ${configuration.port}`);
    });
});