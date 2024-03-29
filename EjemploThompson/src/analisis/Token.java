/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analisis;

/**
 *
 * @author teval
 */
public class Token {
    /**
     * Identificador del token.
     */
    private TokenExprReg ident; //PAREN_IZQUIERDO
   
    /**
     * Valor del token.
     */
    private String valor; //(
   
    /**
     * Constructor por defecto.
     * @param token El tipo de token que deseamos crear.
     * @throws Exception En caso de que <code>token</code> sea un tipo invalido.
     */
    public Token(TokenExprReg token) throws Exception {
        switch (token) {
            case CERRADURA_KLEENE:
                ident = TokenExprReg.CERRADURA_KLEENE;
                valor = "*";
                break;
            case CERRADURA_POSITIVA:
                ident = TokenExprReg.CERRADURA_POSITIVA;
                valor = "+";
                break;
            case OPCION:
                ident = TokenExprReg.OPCION;
                valor = "?";
                break;
            case CONCATENACION:
                ident = TokenExprReg.CONCATENACION;
                valor = "#";
                break;
            case UNION:
                ident = TokenExprReg.UNION;
                valor = "|";
                break;
            case PAREN_IZQUIERDO:
                ident = TokenExprReg.PAREN_IZQUIERDO;
                valor = "(";
                break;
            case PAREN_DERECHO:
                ident = TokenExprReg.PAREN_DERECHO;
                valor = ")";
                break;
            case FINAL:
                ident = TokenExprReg.FINAL;
                valor = "";
                break;
            default:
                throw new Exception("Token invalido");
        }
    }
    
    public Token(TokenExprReg token, String simbolo) throws Exception {
        switch (token) {
            case ALFABETO:
                ident = TokenExprReg.ALFABETO;
                valor = simbolo;
                break;
            case DESCONOCIDO:
                ident = TokenExprReg.DESCONOCIDO;
                valor = simbolo;
                break;
            default:
                throw new Exception("Token invalido");
        }
    }
    
    public TokenExprReg getIdentificador() {
        return ident;
    }
    
    /**
     * Devuelve el atributo <i>valor</i> del token.
     * @return El atributo <i>valor</i> del token.
     */
    public String getValor() {
        return valor;
    }
    
    @Override
    public String toString() {
        return valor;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
       
        if (getClass() != obj.getClass()) {
            return false;
        }
       
        final Token other = (Token) obj;
        if (this.ident != other.ident) {
            return false;
        }
       
        return true;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + (this.ident != null ? this.ident.hashCode() : 0);
        hash = 59 * hash + (this.valor != null ? this.valor.hashCode() : 0);
        return hash;
    }    
}
