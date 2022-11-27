package formulario.sample;

public     class Compra{
    private String ID;
    private String Name;
    private int Cantidad;
    private int Valor_Unit;
    private int Valor_Total;
    private int Disponibles;

    public Compra(String ID, String Name, int Cantidad, int Valor_Unit, int Valor_Total, int Disponibles){
        this.ID = ID;
        this.Name = Name;
        this.Cantidad = Cantidad;
        this.Valor_Unit = Valor_Unit;
        this.Valor_Total = Valor_Total;
        this.Disponibles = Disponibles;

    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public int getValor_Total() {
        return Valor_Unit*Cantidad;
    }

    public int getDisponibles() {
        return Disponibles;
    }

    public int getValor_Unit() {
        return Valor_Unit;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

    public void setValor_Total(int valor_Total) {
        Valor_Total = valor_Total;
    }
}
