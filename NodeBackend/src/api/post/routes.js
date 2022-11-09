import express from "express";
import {get} from "./controller.js";

const router = express.Router();

router.get('/', get);

export default router;