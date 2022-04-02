const express = require("express")
const router = express.Router();
const control = require("../Controllers/index.controller")

router.post("/root", control.index)

module.exports = router;