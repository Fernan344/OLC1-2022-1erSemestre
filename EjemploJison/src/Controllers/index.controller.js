var fs = require('fs'); 
var parser = require('../Interpreter/gramatica');
var interprete = require('../Interpreter/Interprete')

exports.index = async(req, res) => {
    fs.readFile('./src/Public/entrada.txt', (err, data) => {
        if (err) res.send({state: false, err: err});

        parser.parse(data.toString());
        res.send({valores: interprete.instruccionesAPI.getOperations()})
    });
}