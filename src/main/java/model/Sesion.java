package model;

public class Sesion {
    private static Cliente clienteLogiado;

    public static Cliente getClienteLogiado() {
        return clienteLogiado;
    }

    public static void setClienteLogiado(Cliente clienteLogiado) {
        Sesion.clienteLogiado = clienteLogiado;
    }

    public static void cerrarSesion(){
        clienteLogiado = null;
    }
}
