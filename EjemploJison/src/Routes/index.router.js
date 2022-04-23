const express = require("express")
const router = express.Router();
const control = require("../Controllers/index.controller")

router.post("/root", control.index)
router.get("/getFotos", control.photos)
router.get("/getAdmins", control.admins)
router.get("/getDevs", control.devs)
router.post("/uploadphoto", control.upFotos)

router.post("/encrypt", control.encrypts)

module.exports = router;