package com.example;

public class GestionEventos {

    public static void main(String[] args) {
        // Inicializa la matriz
        String[][] matriz = new String[10][5];
        // No se ocupara como tal ya que en el testeo hare los ejemplos con matriz
        //0 Nombre
        //1 Edad
        //2 Entrada
        //3 Invitados
        //4 Ingresado
    }

    public static boolean verificarEdad(String[][] matriz, int fila) {
        if (matriz[fila][1] != null) { //distinto de "vacio"
            int edad = Integer.parseInt(matriz[fila][1]); //Convierte de String a int
            return edad >= 18;
        }
        return false; //si no esta disponible (null) 
    }

    public static String verificarBoleto(String[][] matriz, int fila) {
        return matriz[fila][2];//solo devuelve el tipo de entrada
    }

    public static boolean validarInvitados(String[][] matriz, int fila) {
        if (matriz[fila][3] != null && matriz[fila][2] != null) {
            int invitados = Integer.parseInt(matriz[fila][3]);
            String boleto = matriz[fila][2];
            return boleto.equals("VIP") && invitados <= 2;//Tiene que ser VIP y los invitados menor o igual a 2 
        }
        return false;
    }

    public static int aforoDisponible(String[][] matriz, String sala, int aforoVIP, int aforoGeneral) {
        int aforo = sala.equals("VIP") ? aforoVIP : aforoGeneral;//Decide cual se usa (VIP o gral)(es como un if-else)
        int ocupados = 0;

        for (int i = 0; i < matriz.length; i++) {
            if ("True".equals(matriz[i][4]) && sala.equals(matriz[i][2])) {
                ocupados++;//Verifica si ingreso y si pertenece a la sala (gral)
                if (sala.equals("VIP")) {
                    ocupados += Integer.parseInt(matriz[i][3]);
                } //La sala es VIP, se añade las personas invitadas
            }
        }
        return aforo - ocupados;
    }

    public static boolean ingresarPersona(String[][] matriz, int fila) {
        if ("False".equals(matriz[fila][4])) {
            matriz[fila][4] = "True";
            return true;//Si no ha ingresado se cambia a a True
        }
        return false;
    }

    public static boolean permitirEntrada(String[][] matriz, int fila, int aforoVIP, int aforoGeneral) {
        if (!verificarEdad(matriz, fila)) return false;//debe cumplir ocn el requisito de la edad

        String entrada = verificarBoleto(matriz, fila);
        if (entrada.equals("False")) return false;//ve si tiene una entrada valida

        int aforoRestante = aforoDisponible(matriz, entrada, aforoVIP, aforoGeneral);
        int totalPersonas = 1 + (entrada.equals("VIP") ? Integer.parseInt(matriz[fila][3]) : 0);
        //Si es vip suma a los invitados, sino, solo sumara 1(la persona que tiene la entrada)
        if (aforoRestante < totalPersonas) return false;
        return ingresarPersona(matriz, fila);
    }

    public static void removerPersona(String[][] matriz, int fila) {
        if (matriz[fila][2].equals("VIP")) { // Si es VIP
            int invitados = Integer.parseInt(matriz[fila][3]);
            for (int i = 0; i <= invitados; i++) { //remueve los invitados
                if (fila + i < matriz.length) {
                    matriz[fila + i][4] = "False"; // Cambiar el estado 
                }
            }
        } else {
            // Si no es VIP, solo se cambia estado
            matriz[fila][4] = "False";
        }
    }
}