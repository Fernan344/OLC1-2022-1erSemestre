let operations = []

const instruccionesAPI = {	
	addOperation: function(valor) {
		operations.push({valor: valor});
	},

    getOperations: function() {
		return operations;
	}
}

module.exports.instruccionesAPI = instruccionesAPI;