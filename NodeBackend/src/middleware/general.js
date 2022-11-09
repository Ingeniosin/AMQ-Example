import production from "../config/production.js";

export default {
    error404: async (req, res, next) => {
        res.status(404).send('404 Not Found');
    },
    error500: async (err, req, res, next) => {
        if(!err) return next();
        res.locals = {
            message: err.message,
            error: req.app.get('env') === 'development' ? err : {}
        }
        res.status(err.status || 500).send('500 Internal Server Error');
    },
    timeout: async (req, res, next) => {
        next();
        setTimeout(() => {
            if (res.headersSent) return;
            req.timedout = true;
            res.status(408).send('408 Request Timeout');
        }, production.timer.timeout)
    }
}
