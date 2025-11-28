public class Medida {

    private double valor;
    private String unidad; 

    public Medida(double valor, String unidad) throws ConversionInvalidaException {
        if (!esUnidadValida(unidad)) {
            throw new ConversionInvalidaException("Unidad no válida: " + unidad);
        }
        this.valor = valor;
        this.unidad = unidad;
    }

    public double getValor() {
        return valor;
    }

    public String getUnidad() {
        return unidad;
    }

    private boolean esUnidadValida(String u) {
        return u.equals("cm") || u.equals("m") || u.equals("in") || u.equals("ft");
    }

    public Medida convertir(String nuevaUnidad) throws ConversionInvalidaException {

        if (!esUnidadValida(nuevaUnidad)) {
            throw new ConversionInvalidaException("No se puede convertir a una unidad inválida: " + nuevaUnidad);
        }

        double metros = pasarAMetros(valor, unidad);
        double nuevoValor = pasarDesdeMetros(metros, nuevaUnidad);

        return new Medida(nuevoValor, nuevaUnidad);
    }

    public Medida convertir(Unidad nuevaUnidadEnum) throws ConversionInvalidaException {
        String nuevaUnidad = "";

        switch (nuevaUnidadEnum) {
            case CM: nuevaUnidad = "cm"; break;
            case M:  nuevaUnidad = "m";  break;
            case IN: nuevaUnidad = "in"; break;
            case FT: nuevaUnidad = "ft"; break;
        }

        return convertir(nuevaUnidad);
    }

    private double pasarAMetros(double v, String u) {
        switch (u) {
            case "cm": return v / 100;
            case "m":  return v;
            case "in": return v * 0.0254;
            case "ft": return v * 0.3048;
        }
        return 0;
    }

    private double pasarDesdeMetros(double v, String u) {
        switch (u) {
            case "cm": return v * 100;
            case "m":  return v;
            case "in": return v / 0.0254;
            case "ft": return v / 0.3048;
        }
        return 0;
    }
}
