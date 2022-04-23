const indexRouter = require("./Routes/index.router")
const express = require('express');
const morgan = require('morgan');
const cors = require('cors');

const app = express();

app.set('port', process.env.PORT || 3500);

app.use(morgan('dev'));
app.use(express.urlencoded({extended: false}));
app.use(express.json());
app.use(express.json({limit: '50mb'}));
app.use(express.urlencoded({limit: '50mb'}));
app.use(cors());

app.listen(app.get('port'), () => {
    console.log('Server On Port ', app.get('port'))
});

app.use("/", indexRouter)

/*app.get('/', (req, res) => {
    res.send({"Saludo": "Hola OLC1"});
});*/