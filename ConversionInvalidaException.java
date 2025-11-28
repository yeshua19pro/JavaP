public class ConversionInvalidaException extends Exception {

    public ConversionInvalidaException() {
        super("Conversión inválida.");
    }

    public ConversionInvalidaException(String mensaje) {
        super(mensaje);
    }
}
