package parcial.espinel;

public class MatrizNumerosEnteros {

    private int totalFilas;
    private int totalColumnas;
    private int[][] datos;

    public MatrizNumerosEnteros() {
        this.totalFilas = 0;
        this.totalColumnas = 0;
    }

    public MatrizNumerosEnteros(int totalFilas, int totalColumnas) {
        this.totalFilas = totalFilas;
        this.totalColumnas = totalColumnas;
        this.datos = new int[totalFilas][totalColumnas];
    }

    public int getTotalFilas() {
        return totalFilas;
    }

    public int getTotalColumnas() {
        return totalColumnas;
    }

    public int[][] getDatos() {
        return datos;
    }

    public int getValorPosicionMatriz(int posFila, int posColumna) {
        return datos[posFila][posColumna];
    }

    public void setValorPosicionMatriz(int posFila, int posColumna, int valor) {
        datos[posFila][posColumna] = valor;
    }

    public void llenarMatrizNumerosAleatorios(int p_limite) {

        for (int subIndiceFila = 0; subIndiceFila < this.totalFilas; subIndiceFila++) {
            for (int subIndiceColumna = 0; subIndiceColumna < this.totalColumnas; subIndiceColumna++) {
                int numero = (int) Math.floor(Math.random() * p_limite + 1);
                this.datos[subIndiceFila][subIndiceColumna] = numero;
            }
        }

    }

    public boolean compararMatrices(MatrizNumerosEnteros matrizB) {

        boolean sonIguales = true;

        if (this != null && matrizB != null
                && this.totalFilas == matrizB.totalFilas
                && this.totalColumnas == matrizB.totalColumnas) {
            for (int i = 0; i < this.totalFilas && sonIguales; i++) {
                for (int j = 0; j < this.totalColumnas && sonIguales; j++) {
                    sonIguales = (this.getValorPosicionMatriz(i, j) == matrizB.getValorPosicionMatriz(i, j));
                }
            }
        } else {
            sonIguales = false;
        }

        return sonIguales;

    }

    public MatrizNumerosEnteros sumarMatrices(MatrizNumerosEnteros otraMatriz) {

        MatrizNumerosEnteros matrizResultado = new MatrizNumerosEnteros(this.totalFilas, this.totalColumnas);
        int valorA;
        int valorB;
        int valorR;

        for (int subIndiceFila = 0; subIndiceFila < this.totalFilas; subIndiceFila++) {
            for (int subIndiceColumna = 0; subIndiceColumna < this.totalColumnas; subIndiceColumna++) {

                valorA = this.getValorPosicionMatriz(subIndiceFila, subIndiceColumna);
                valorB = otraMatriz.getValorPosicionMatriz(subIndiceFila, subIndiceColumna);
                valorR = valorA + valorB;

                matrizResultado.setValorPosicionMatriz(subIndiceFila, subIndiceColumna, valorR);
            }
        }

        return matrizResultado;
    }

    public String datosMatrizNumeros() {

        String salida = null;
        StringBuilder contenidoMatriz = new StringBuilder();

        for (int subIndiceFila = 0; subIndiceFila < this.totalFilas; subIndiceFila++) {
            for (int subIndiceColumna = 0; subIndiceColumna < this.totalColumnas; subIndiceColumna++) {
                contenidoMatriz.append(this.datos[subIndiceFila][subIndiceColumna]);
                contenidoMatriz.append("     ");
            }
            contenidoMatriz.append("\n");
        }

        salida = contenidoMatriz.toString();
        return salida;
    }

    public int sumarDiagonalPrincipal() {
        int suma = 0;
        for (int i = 0; i < this.totalFilas; i++) {
            for (int j = 0; j < this.totalColumnas; j++) {
                suma += this.getValorPosicionMatriz(j, j);
            }
            break;
        }
        return suma;
    }

    public void productoEscalarMatriz(int escalar) {
        for (int i = 0; i < this.totalFilas; i++) {
            for (int j = 0; j < this.totalColumnas; j++) {
                datos[i][j] = escalar * getValorPosicionMatriz(i, j);
            }
        }
    }

    public MatrizNumerosEnteros multiplicarMatrices(MatrizNumerosEnteros matrizB) {
        if (this.totalColumnas == matrizB.totalFilas) {
            MatrizNumerosEnteros matrizC = new MatrizNumerosEnteros(this.totalFilas, matrizB.totalColumnas);
            for (int i = 0; i < this.totalFilas; ++i) {
                for (int j = 0; j < matrizB.totalColumnas; ++j) {
                    int valor = 0;
                    for (int k = 0; k < this.totalColumnas; ++k) {
                        valor += this.getValorPosicionMatriz(i, k) * matrizB.getValorPosicionMatriz(k, j);
                    }
                    matrizC.setValorPosicionMatriz(i, j, valor);
                }
            }
            return matrizC;
        }
        return null;
    }

}
