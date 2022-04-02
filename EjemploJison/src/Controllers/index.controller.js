var fs = require('fs'); 
var parser = require('../Interpreter/gramatica');
var interprete = require('../Interpreter/Interprete')

exports.index = async(req, res) => {
    let {entrada}=req.body
    parser.parse(entrada.toString());
    res.send({error: false, salida: interprete.instruccionesAPI.getOperations()})
    /*fs.readFile('./src/Public/entrada.txt', (err, data) => {
        if (err) res.send({state: false, err: err});

        
    });*/
}