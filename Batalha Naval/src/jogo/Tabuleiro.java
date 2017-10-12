/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

/**
 *
 * @author Vanjura
 */
public class Tabuleiro {

    private String[][] matN;
    private String[][] matH;
    private int[][] matV;
    final int MAX = 14;
    private int situacao;

    public Tabuleiro() {
        this.matN = new String[14][14];
        this.matH = new String[14][14];
        this.matV = new int[14][14];
        this.situacao = 0;
        this.zerar();
    }

    private void zerar() {
        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < MAX; j++) {
                this.matN[i][j] = "-";
                this.matV[i][j] = 0;
                this.matH[i][j] = "-";
            }

        }
    }

    public int getSituacao() {
        return situacao;
    }

    public String[][] getMatN() {
        return matN;
    }

    public void setMatN(String[][] matN) {
        this.matN = matN;
    }

    public String[][] getMatH() {
        return matH;
    }

    public void setMatH(String[][] matH) {
        this.matH = matH;
    }

    public int[][] getMatV() {
        return matV;
    }

    public void setMatV(int[][] matV) {
        this.matV = matV;
    }

    public void setSituacao(int situacao) {
        this.situacao = situacao;
    }

    public String getPosMatN(int linha, int coluna) {
        return this.matN[linha][coluna];
    }

    public String getPosMatH(int linha, int coluna) {
        return this.matH[linha][coluna];
    }

    public int getPosMatV(int linha, int coluna) {
        return this.matV[linha][coluna];
    }

    public void setPosMatN(int linha, int coluna, String valor) {
        this.matN[linha][coluna] = valor;
    }

    public void setPosMatH(int linha, int coluna, String valor) {
        this.matH[linha][coluna] = valor;
    }

    public void setPosMatV(int linha, int coluna, int valor) {
        this.matV[linha][coluna] = valor;
    }
}
