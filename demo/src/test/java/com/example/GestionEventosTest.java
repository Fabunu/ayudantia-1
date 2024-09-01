package com.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GestionEventosTest {

    private static String[][] matriz;

    @BeforeAll
    public static void setUpBeforeClass() {
        // Inicializar la matriz con datos de prueba
        matriz = new String[10][5];
        // datos:
        matriz[0] = new String[]{"Juanito", "25", "VIP", "1", "False"};
        matriz[1] = new String[]{"Dino", "17", "General", "0", "False"};
        matriz[2] = new String[]{"Alicia", "30", "VIP", "2", "False"};
        matriz[3] = new String[]{"Bimbo", "22", "General", "3", "False"};
        
    }
//pondre algunos apuntes por que me enredo que hace cada cosa tot
    @BeforeEach
    public void setUp() {
        // Inicializa o restablece el estado antes de cada prueba u.u
    }

    @AfterEach
    public void tearDown() {
        // Limpia o restablece el estado despues de cada prueba u.u
    }

    @AfterAll
    public static void tearDownAfterClass() {
        // Limpia o libera recursos despues de todas las pruebas u.u
    }

    @Test
    public void testVerificarEdad() {
        // verificarEdad devuelve true si la edad es mayor o igual a 18 
        assertTrue(GestionEventos.verificarEdad(matriz, 0));
        assertFalse(GestionEventos.verificarEdad(matriz, 1));
    }

    @Test
    public void testVerificarBoleto() {
        // verificarBoleto devuelve el tipo de boleto
        assertEquals("VIP", GestionEventos.verificarBoleto(matriz, 0));
        assertEquals("General", GestionEventos.verificarBoleto(matriz, 1));
    }

    @Test
    public void testValidarInvitados() {
        // validarInvitados devuelve true si el numero de invitados es valido
        assertTrue(GestionEventos.validarInvitados(matriz, 0));
        assertFalse(GestionEventos.validarInvitados(matriz, 1));
    }

    @Test
    public void testAforoDisponible() {
        // aforoDisponible devuelve el espacio disponible
        assertEquals(30, GestionEventos.aforoDisponible(matriz, "VIP", 30, 30)); // Asegurate de usar el aforo correcto
        assertEquals(30, GestionEventos.aforoDisponible(matriz, "General", 30, 30));
    }

    @Test
    public void testIngresarPersona() {
        // ingresarPersona devuelve true si se puede ingresar la persona
        assertTrue(GestionEventos.ingresarPersona(matriz, 1));
        assertTrue(GestionEventos.ingresarPersona(matriz, 0)); // Ya esta marcado como "True"
    }

    @Test
    public void testPermitirEntrada() {
        // Aforo VIP = 30, Aforo General = 30
        assertTrue(GestionEventos.permitirEntrada(matriz, 0, 30, 30));
        assertFalse(GestionEventos.permitirEntrada(matriz, 1, 30, 30)); // Edad insuficiente
        assertTrue(GestionEventos.permitirEntrada(matriz, 3, 30, 30)); // Exceso de invitados
    }

    @Test
    public void testRemoverPersona() {
        // Asume que ingresarPersona marca como "True"
        GestionEventos.ingresarPersona(matriz, 0);
        GestionEventos.removerPersona(matriz, 0);
        assertEquals("False", matriz[0][4]);
    }
}
