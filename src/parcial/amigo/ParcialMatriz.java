package parcial.amigo;

import javax.swing.JOptionPane;

public class ParcialMatriz {

    static final int MAXIMO_FILAS = 5;
    static final int MAXIMO_COLUMNAS = 5;

    public static void main(String[] args) {

        int opcion = 1;
        String datoentrada = null;
        boolean datosok = false;

        String menu = "Seleccionar Opción \n\n"
                + "1. Comparar 2 Matrices" + "\n"
                + "2. Sumar Diagonal Principal Matriz \n"
                + "3. Producto por un escalar \n"
                + "4. Sumar Matrices \n"
                + "5. Multiplicar Matrices \n"
                + "6. Traspuesta Matriz \n\n"
                + "10. Salir";

        do {

            datoentrada = inputDialog(menu, "Menú Opciones", 3);

            if (datoentrada != null) {
                opcion = Integer.parseInt(datoentrada);

                if ((opcion > 0) && (opcion <= 9)) {
                    datosok = true;
                } else {
                    if (opcion != 10) {
                        messageDialog("Opción Errada", "Mensajes Sistema", 0);
                    } else {
                        datosok = true;
                    }
                }
            } else {
                break;
            }

        } while (!datosok);

        System.out.println("Opcion = " + opcion);
        if (opcion != 10) {

            switch (opcion) {
                case 1:
                    compararMatrices();
                    break;
                case 2:
                    sumarDiagonalPrincipal();
                    break;
                case 3:
                    productoEscalarMatriz();
                    break;
                case 4:
                    sumarMatrices();
                    break;
                case 5:
                    multiplicarMatrices();
                    break;
                case 6:
                    traspuestaMatriz();
                    break;
            }
        }

    }

    /*
        El producto de una matriz A por un escalar k se define como

            k * MatrizA = k * A (i,j)
    
        es decir, el escalar k multiplica todas las entradas de la matriz A.
     */
    static void productoEscalarMatriz() {

        System.out.println("PRODUCTO ESCALAR X MATRIZ \n");

        String valor = inputDialog("Valor", "Limite", JOptionPane.INFORMATION_MESSAGE);
        int maxRandom = Integer.parseInt(valor);
        MatrizNumerosEnteros matrizA = crearMatriz("Cree la primera matriz");
        matrizA.llenarMatrizNumerosAleatorios(maxRandom);
        messageDialog(matrizA.datosMatrizNumeros(), "Primera Matriz", 3);

        String numero = inputDialog("Inserte un escalar", "Escalar", JOptionPane.INFORMATION_MESSAGE);
        int escalar = Integer.parseInt(numero);
        for (int i = 0; i < matrizA.getTotalFilas(); i++) {
            for (int j = 0; j < matrizA.getTotalColumnas(); j++) {
                int resultado = escalar * matrizA.getValorPosicionMatriz(i, j);
                matrizA.setValorPosicionMatriz(i, j, resultado);
            }
        }
        messageDialog("Resultado: \n" + matrizA.datosMatrizNumeros(), "Producto escalar", 3);

    }

    /**
     * Metodo encargado de verificar si 2 matrices de igual dimension tienen el
     * mismo contenido Este metododo usa el metodo compararMatrices de la clase
     * MatrizNumerosEnteros
     */
    static void compararMatrices() {
        System.out.println("COMPARAR MATRIZ CUADRADA \n");

        String valor = inputDialog("Valor", "Limite", JOptionPane.INFORMATION_MESSAGE);
        int maxRandom = Integer.parseInt(valor);
        MatrizNumerosEnteros matrizA = crearMatriz("Cree la primera matriz");
        matrizA.llenarMatrizNumerosAleatorios(maxRandom);
        messageDialog(matrizA.datosMatrizNumeros(), "Primera Matriz", 3);

        MatrizNumerosEnteros matrizB = crearMatriz("Cree la segunda matriz");
        matrizB.llenarMatrizNumerosAleatorios(maxRandom);
        messageDialog(matrizB.datosMatrizNumeros(), "Segunda Matriz", 3);

        boolean iguales = matrizA.compararMatrices(matrizB);

        if (iguales) {
            messageDialog("Iguales", "Comparacion", 3);
        } else {
            messageDialog("Diferentes", "Comparacion", 3);
        }
    }

    static void sumarDiagonalPrincipal() {
        System.out.println("SUMAR DIAGONAL PRINCIPAL MATRIZ \n");

        String valor = inputDialog("Valor", "Limite", JOptionPane.INFORMATION_MESSAGE);
        int maxRandom = Integer.parseInt(valor);
        MatrizNumerosEnteros matrizA = crearMatriz("Cree la primera matriz");
        matrizA.llenarMatrizNumerosAleatorios(maxRandom);
        messageDialog(matrizA.datosMatrizNumeros(), "Primera Matriz", 3);

        int suma = 0;
        boolean terminar = false;
        for (int i = 0; i < matrizA.getTotalFilas() && !terminar; i++) {
            for (int j = 0; j < matrizA.getTotalColumnas(); j++) {
                suma += matrizA.getValorPosicionMatriz(j, j);
            }
            terminar = true;
        }

        messageDialog("Resultado: " + suma, "Suma diagonal", 3);
    }

    /*
    La traspuesta de una matriz A, es la matriz que tiene por filas a las columnas de la matriz A:
    Es decir: la columna i de la matriz A será la fila i de matriz traspuesta
     */
    static void traspuestaMatriz() {
        System.out.println("TRASPUESTA MATRIZ \n");

        String valor = inputDialog("Valor", "Limite", JOptionPane.INFORMATION_MESSAGE);
        int maxRandom = Integer.parseInt(valor);
        MatrizNumerosEnteros matrizA = crearMatriz("Cree la primera matriz");
        matrizA.llenarMatrizNumerosAleatorios(maxRandom);
        messageDialog(matrizA.datosMatrizNumeros(), "Primera Matriz", 3);

        MatrizNumerosEnteros traspuesta = new MatrizNumerosEnteros(matrizA.getTotalColumnas(), matrizA.getTotalFilas());

        for (int i = 0; i < traspuesta.getTotalFilas(); i++) {
            for (int j = 0; j < traspuesta.getTotalColumnas(); j++) {
                traspuesta.setValorPosicionMatriz(i, j, matrizA.getValorPosicionMatriz(j, i));
            }
        }
        
        messageDialog(traspuesta.datosMatrizNumeros(), "Traspuesta", 3);

    }

    /**
     * Dos matrices A y B son multiplicables si el número de columnas de A
     * coincide con el número de filas de B.
     *
     * A (m x n) * B (n x p) = M (m x p)
     *
     * El elemento M (i,j) de la matriz producto se obtiene multiplicando cada
     * elemento de la fila i de la matriz A por cada elemento de la columna j de
     * la matriz B y sumándolos.
     *
     * |2 0 1| |1 0 1| A * B = |3 0 0| X |1 2 1| = |5 1 1| |1 1 0|
     *
     * |2*1 + 0*1 + 1*1 2*0 + 0*2 + 1*1 2*1 + 0*1 + 1*0| = |3*1 + 0*1 + 0*1 3*0
     * + 0*2 + 0*1 3*1 + 0*1 + 0*0| |5*1 + 1*1 + 1*1 5*0 + 1*2 + 1*1 5*1 + 1*1 +
     * 1*0|
     *
     * |3 1 2| = |3 0 3| |7 3 6|
     */
    static void multiplicarMatrices() {

        System.out.println("MULTIPLICAR MATRIZ \n");
    }

    /**
     * Si las matrices A(i,j) y B(i,j) tienen la misma dimensión, la matriz suma
     * es:
     *
     * A+B = A(i,j) + B(i,j)
     *
     * La matriz suma se obtienen sumando los elementos de las dos matrices que
     * ocupan la misma misma posición.
     */
    static void sumarMatrices() {

        System.out.println("SUMA DE MATRICES \n");
        String salida = "";

        MatrizNumerosEnteros miMatrizA;
        MatrizNumerosEnteros miMatrizB;
        MatrizNumerosEnteros miMatrizR;

        miMatrizA = crearMatriz("Matriz A");

        if ((miMatrizA.getTotalColumnas() > 0) && (miMatrizA.getTotalFilas() > 0)) {

            miMatrizB = crearMatriz("Matriz B");

            if ((miMatrizB.getTotalColumnas() > 0) && (miMatrizB.getTotalFilas() > 0)) {

                miMatrizA.llenarMatrizNumerosAleatorios(5);
                salida = miMatrizA.datosMatrizNumeros();
                messageDialog(salida, "Datos MATRIZ A", 3);

                miMatrizB.llenarMatrizNumerosAleatorios(9);
                salida = miMatrizB.datosMatrizNumeros();
                messageDialog(salida, "Datos MATRIZ B", 3);

                miMatrizR = miMatrizA.sumarMatrices(miMatrizB);
                salida = miMatrizR.datosMatrizNumeros();
                messageDialog(salida, "Datos MATRIZ A+B", 3);
            }
        } else {
            messageDialog("Error Definir Dimensiones Matriz A", "Mensaje del Sistema", 1);
        }

    }

    /**
     * Este metodo debe capturar las dimensiones de la matriz : Número de Filas
     * y Columnas A partir de estos 2 valores se debe crear una matriz
     *
     * @param p_mensaje : Nombre de la Matriz A Crear
     * @return
     */
    static MatrizNumerosEnteros crearMatriz(String p_mensaje) {

        MatrizNumerosEnteros miMatriz = new MatrizNumerosEnteros();

        int totalFilas = 0;
        int totalColumnas = 0;
        String datoentrada;

        boolean datosok = false;

        do {
            datoentrada = inputDialog("Número Filas", p_mensaje, 3);
            if (datoentrada != null) {
                totalFilas = Integer.parseInt(datoentrada);

                if ((totalFilas > 0) && (totalFilas <= MAXIMO_FILAS)) {
                    datosok = true;
                } else {
                    messageDialog("Numero de Filas Errado", "Mensajes Sistema", 0);
                }
            } else {
                break;
            }
        } while (!datosok);

        if (datosok) {

            datosok = false;

            do {
                datoentrada = inputDialog("Número Columnas", p_mensaje, 3);
                if (datoentrada != null) {
                    totalColumnas = Integer.parseInt(datoentrada);

                    if ((totalColumnas > 0) && (totalColumnas <= MAXIMO_COLUMNAS)) {
                        datosok = true;
                    } else {
                        messageDialog("Numero de Columnas Errado", "Mensajes Sistema", 0);
                    }
                } else {
                    break;
                }
            } while (!datosok);
        }

        if (datosok) {
            miMatriz = new MatrizNumerosEnteros(totalFilas, totalColumnas);
        }

        return miMatriz;
    }

    /**
     *
     * @param p_label
     * @param p_titulo
     * @param p_tipomensaje
     * @return
     */
    private static String inputDialog(String p_label, String p_titulo, int p_tipomensaje) {

        String valor;

        valor = JOptionPane.showInputDialog(null, p_label, p_titulo, p_tipomensaje);

        return valor;
    }

    static void messageDialog(String p_label, String p_titulo, int p_tipomensaje) {

        JOptionPane.showMessageDialog(null, p_label, p_titulo, p_tipomensaje);

    }

}
