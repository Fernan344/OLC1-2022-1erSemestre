const express = require("express")
const router = express.Router();
const control = require("../Controllers/index.controller")

router.get("/", control.index)

module.exports = router;