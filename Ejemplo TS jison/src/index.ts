import express, { Application } from 'express';
import morgan from 'morgan';
import cors from 'cors';
import indexRoutes from './routes/indexRoutes';
class server {
  public app: Application;
  constructor() {
    this.app = express();
    this.config();
    this.routes();
  }

  config(): void {
    this.app.set('port', 5000); //el proceso que toma una variable de entorno o el puerto 3000
    //el set es como si se hubiera declarado el app como una variable
    this.app.use(morgan('dev')); //dev, es lo que ve la peticion del cliente
    this.app.use(cors()); //pide datos al server
    this.app.use(express.json()); //para que se entienda el formato json y lo guarde asi
    this.app.use(express.urlencoded({ extended: false })); //si se usa formato html
  }

  routes(): void {
    this.app.use('/', indexRoutes);
  }
  start(): void {
    this.app.listen(this.app.get('port'), () => {
      console.log('Servidor en puerto', this.app.get('port'));
    });
  }
}
export const servirdor = new server();
servirdor.start();
