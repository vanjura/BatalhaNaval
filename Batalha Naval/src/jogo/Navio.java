/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import javax.swing.JOptionPane;

/**
 *
 * @author Vanjura
 */
public class Navio {

    protected int tamC;
    protected int tamA;
    protected int energia;
    protected String nome;
    protected String inicial;
    protected String papel;
    protected Habilidade[] habilidade;
    protected int status = 1; //0 = inativo, 1 = ativo

    public Navio(int tamC, int tamA, String nome, String papel, String inicial) {
        this.energia = 100;
        this.habilidade = new Habilidade[2];
        this.tamC = tamC;
        this.tamA = tamA;
        this.nome = nome;
        this.papel = papel;
        this.inicial = inicial;
    }

    public String getInicial() {
        return inicial;
    }

    public void setInicial(String inicial) {
        this.inicial = inicial;
    }

    public void adicionarHabilidade(Habilidade h0, Habilidade h1) {
        this.habilidade[0] = h0;
        this.habilidade[1] = h1;
    }

    public void consumirEnergia(Navio navio, int quantidade) {
        navio.energia -= quantidade;
    }

    public void ganharEnergia(Navio navio, int quantidade) {
        navio.energia += quantidade;
    }

    public void usarPulsoEletromagnetico(Jogador inimigo) {
        inimigo.getTabuleiro().setSituacao(2);
        JOptionPane.showMessageDialog(null, "Pulso Eletromagnético usado com sucesso em " 
                + inimigo.getApelido() 
                + " que deverá ficar " 
                + inimigo.getTabuleiro().getSituacao() 
                + " rodadas sem jogar.");
    }

    public void usarBombardeioLinear(Jogador player, Jogador inimigo) {
        int opcaoI = 0;
        int valor;
        int escudo = 0;
        int acerto = 0;
        String opcaoS;

        do {
            opcaoS = JOptionPane.showInputDialog("Opções"
                    + "\n 1 - linha"
                    + "\n 2 - coluna"
                    + "\n\nInforme qual será o tipo do bombardeio:");
            try {
                opcaoI = Integer.parseInt(opcaoS);
                if (opcaoI < 1 || opcaoI > 2) {
                    opcaoI = 0;
                    JOptionPane.showMessageDialog(null, "Essa opção não existe.");
                }
            } catch (NumberFormatException e) {
                if (opcaoS == null) {
                    System.exit(0);
                } else {
                    JOptionPane.showMessageDialog(null, "Informe somente números.");
                }
            }
        } while (opcaoI == 0);
        if (opcaoI == 1) {
            valor = pegarCoordenada(1, inimigo.getNavio()[5].habilidade[0], "linha");
            for (int i = 0; i < 14; i++) {
                if (inimigo.getTabuleiro().getPosMatV(valor, i) == 0) {
                    if (!"-".equals(inimigo.getTabuleiro().getPosMatN(valor, i)) && !"X".equals(inimigo.getTabuleiro().getPosMatN(valor, i))) {
                        acerto = 1;
                        inimigo.getTabuleiro().setPosMatN(valor, i, "X");
                    } else {
                        inimigo.getTabuleiro().setPosMatN(valor, i, "X");
                    }
                } else {
                    escudo = 1;
                    inimigo.getTabuleiro().setPosMatV(valor, i, inimigo.getTabuleiro().getPosMatV(valor, i) - 1);
                }
            }
        } else if (opcaoI == 2) {
            valor = pegarCoordenada(1, inimigo.getNavio()[5].habilidade[0], "coluna");
            for (int i = 0; i < 14; i++) {
                if (inimigo.getTabuleiro().getPosMatV(i, valor) == 0) {
                    if (!"-".equals(inimigo.getTabuleiro().getPosMatN(i, valor)) && !"X".equals(inimigo.getTabuleiro().getPosMatN(i, valor))) {
                        acerto = 1;
                        inimigo.getTabuleiro().setPosMatN(i, valor, "X");
                    } else {
                        inimigo.getTabuleiro().setPosMatN(valor, i, "X");
                    }
                } else {
                    escudo = 1;
                    inimigo.getTabuleiro().setPosMatV(i, valor, inimigo.getTabuleiro().getPosMatV(i, valor) - 1);
                }
            }
        }
        if (acerto == 1) {
            JOptionPane.showMessageDialog(null, "Você acertou um navio inimigo e ganhou +100 de energia em " 
                    + player.getNavio()[5].nome 
                    + ".");
            this.ganharEnergia(this, 100);
        } else if (escudo == 1) {
            JOptionPane.showMessageDialog(null, "Você acertou um escudo inimigo.");
        } else {
            inimigo.jogadorErrou();
        }
    }

    public void usarArtilharia(Jogador player, Jogador inimigo) {
        int linha = -1;
        int coluna = -1;
        int escudo = 0;
        int acerto = 0;
        String opcao;

        do {
            opcao = JOptionPane.showInputDialog("Informe a linha para usar a Artilharia:");
            try {
                linha = Integer.parseInt(opcao) - 1;
                if (linha < 0 || linha > 12) {
                    linha = -1;
                    JOptionPane.showMessageDialog(null, "Essa habilidade não pode ser usada nesse local ou esse local não existe no tabuleiro."
                            + "\nTamanho da habilidade: 2x2");
                }
            } catch (NumberFormatException e) {
                if (opcao == null) {
                    System.exit(0);
                } else {
                    JOptionPane.showMessageDialog(null, "Informe somente números.");
                }
            }
        } while (linha == -1);
        do {
            opcao = JOptionPane.showInputDialog("Informe a coluna para usar a Artilharia:");
            try {
                coluna = Integer.parseInt(opcao) - 1;
                if (coluna < 0 || coluna > 12) {
                    coluna = -1;
                    JOptionPane.showMessageDialog(null, "Essa habilidade não pode ser usada nesse local ou esse local não existe no tabuleiro."
                            + "\nTamanho da habilidade: 2x2");
                }
            } catch (NumberFormatException e) {
                if (opcao == null) {
                    System.exit(0);
                } else {
                    JOptionPane.showMessageDialog(null, "Informe somente números.");
                }
            }
        } while (linha == -1);
        for (int i = linha; i < linha + 2; i++) {
            for (int j = coluna; j < coluna + 2; j++) {
                if (inimigo.getTabuleiro().getPosMatV(linha, coluna) == 0) {
                    if (!"-".equals(inimigo.getTabuleiro().getPosMatN(i, j)) || !"X".equals(inimigo.getTabuleiro().getPosMatN(i, j))) {
                        inimigo.getTabuleiro().setPosMatN(i, j, "X");
                        acerto = 1;
                    } else {
                        inimigo.getTabuleiro().setPosMatN(i, j, "X");
                    }
                } else {
                    escudo = 1;
                    inimigo.getTabuleiro().setPosMatV(linha, coluna, inimigo.getTabuleiro().getPosMatV(linha, coluna) - 1);
                }
            }
        }
        if (acerto == 1) {
            JOptionPane.showMessageDialog(null, "Você acertou um navio inimigo e ganhou +100 de energia em " + player.getNavio()[5].nome + ".");
            this.ganharEnergia(this, 100);
        } else if (escudo == 1) {
            JOptionPane.showMessageDialog(null, "Você acertou um escudo inimigo.");
        } else {
            JOptionPane.showMessageDialog(null, "Você errou e " + inimigo.getApelido() + "ganhou +30 em cada navio.");
            inimigo.jogadorErrou();
        }
    }

    public void usarArtilhariaPesada(Jogador player, Jogador inimigo) {
        int linha;
        int coluna;
        int escudo = 0;
        int acerto = 0;

        linha = pegarCoordenada(3, inimigo.getNavio()[4].habilidade[1], "linha");
        coluna = pegarCoordenada(3, inimigo.getNavio()[4].habilidade[1], "coluna");
        for (int i = linha - 1; i < linha + 2; i++) {
            for (int j = coluna - 1; j < coluna + 2; j++) {
                if (inimigo.getTabuleiro().getPosMatV(linha, coluna) == 0) {
                    if (!"-".equals(inimigo.getTabuleiro().getPosMatN(i, j)) || !"X".equals(inimigo.getTabuleiro().getPosMatN(i, j))) {
                        inimigo.getTabuleiro().setPosMatN(i, j, "X");
                        acerto = 1;
                    } else {
                        inimigo.getTabuleiro().setPosMatN(i, j, "X");
                    }
                } else {
                    escudo = 1;
                    inimigo.getTabuleiro().setPosMatV(linha, coluna, inimigo.getTabuleiro().getPosMatV(linha, coluna) - 1);
                }
            }
        }
        if (acerto == 1) {
            JOptionPane.showMessageDialog(null, "Você acertou um navio inimigo e ganhou +100 de energia em " + player.getNavio()[5].nome + ".");
            this.ganharEnergia(this, 100);
        } else if (escudo == 1) {
            JOptionPane.showMessageDialog(null, "Você acertou um escudo inimigo.");
        } else {
            JOptionPane.showMessageDialog(null, "Você errou e " + inimigo.getApelido() + "ganhou +30 em cada navio.");
            inimigo.jogadorErrou();
        }
    }

    public void usarRadar(Jogador inimigo) {
        int linha;
        int coluna;
        int detectou = 0;
        
        linha = pegarCoordenada(5, inimigo.getNavio()[3].habilidade[1], "linha");
        coluna = pegarCoordenada(5, inimigo.getNavio()[3].habilidade[1], "coluna");
        for (int i = linha - 2; i < linha + 3; i++) {
            for (int j = coluna - 2; j < coluna + 3; j++) {
                if ("S".equals(inimigo.getTabuleiro().getPosMatN(i, j))) {
                    inimigo.getTabuleiro().setPosMatH(i, j, "N");
                    detectou = 1;
                }
            }
        }
        
        if (detectou == 1) {
            JOptionPane.showMessageDialog(null, "Seu radar detectou um navio.");
        }else{
            JOptionPane.showMessageDialog(null, "Nenhum navio detectado.");
        }
    }

    public void usarCargasDeProfundidade(Jogador player, Jogador inimigo) {
        int linha;
        int coluna;
        int acerto = 0;
        int escudo = 0;
        
        linha = pegarCoordenada(5, inimigo.getNavio()[3].habilidade[0], "linha");
        coluna = pegarCoordenada(5, inimigo.getNavio()[3].habilidade[0], "coluna");
        for (int i = linha - 2; i < linha + 3; i++) {
            for (int j = coluna - 2; j < coluna + 3; j++) {
                if (inimigo.getTabuleiro().getPosMatV(linha, coluna) == 0) {
                    if ("S".equals(inimigo.getTabuleiro().getPosMatN(i, j))) {
                        inimigo.getTabuleiro().setPosMatN(i, j, "X");
                        acerto = 1;
                    }
                } else {
                    escudo = 1;
                    inimigo.getTabuleiro().setPosMatV(linha, coluna, inimigo.getTabuleiro().getPosMatV(linha, coluna) - 1);
                }
            }
        }
        
        if (acerto == 1) {
            JOptionPane.showMessageDialog(null, "Você acertou um submarino inimigo e ganhou +100 de energia em " + player.getNavio()[5].nome + ".");
            this.ganharEnergia(this, 100);
        } else if (escudo == 1) {
            JOptionPane.showMessageDialog(null, "Você acertou um escudo inimigo.");
        } else {
            JOptionPane.showMessageDialog(null, "Você errou e " + inimigo.getApelido() + "ganhou +30 em cada navio.");
            inimigo.jogadorErrou();
        }
    }

    public void usarMissilBalistico(Jogador player, Jogador inimigo) {
        int linha;
        int coluna;
        int acerto = 0;
        int escudo = 0;
        
        for (int i = 0; i < player.getTabuleiro().MAX; i++) {
            for (int j = 0; j < player.getTabuleiro().MAX; j++) {
                if ("F".equals(player.getTabuleiro().getPosMatN(i, j))) {
                    player.getTabuleiro().setPosMatN(i, j, "f");
                }
            }
        }
        
        linha = pegarCoordenada(5, inimigo.getNavio()[2].habilidade[1], "linha");
        coluna = pegarCoordenada(5, inimigo.getNavio()[2].habilidade[1], "coluna");
        for (int i = linha - 2; i < linha + 3; i++) {
            for (int j = coluna - 2; j < coluna + 3; j++) {
                if (inimigo.getTabuleiro().getPosMatV(linha, coluna) == 0) {
                    if (!"-".equals(inimigo.getTabuleiro().getPosMatN(i, j)) && !"X".equals(inimigo.getTabuleiro().getPosMatN(i, j))) {
                        inimigo.getTabuleiro().setPosMatN(i, j, "X");
                        acerto = 1;
                    } else {
                        inimigo.getTabuleiro().setPosMatN(i, j, "X");
                    }
                } else {
                    escudo = 1;
                    inimigo.getTabuleiro().setPosMatV(linha, coluna, inimigo.getTabuleiro().getPosMatV(linha, coluna) - 1);
                }
            }
        }
        
        if (acerto == 1) {
            JOptionPane.showMessageDialog(null, "Você acertou um submarino inimigo e ganhou +100 de energia em " + player.getNavio()[5].nome + ".");
            this.ganharEnergia(this, 100);
        } else if (escudo == 1) {
            JOptionPane.showMessageDialog(null, "Você acertou um escudo inimigo.");
        } else {
            JOptionPane.showMessageDialog(null, "Você errou e " + inimigo.getApelido() + "ganhou +30 em cada navio.");
            inimigo.jogadorErrou();
        }
    }

    public void usarFoguetes(Jogador player, Jogador inimigo) {
        int x, y, rand, teste = 0;
        x = pegarCoordenada(5, inimigo.getNavio()[2].habilidade[0], "linha");
        y = pegarCoordenada(5, inimigo.getNavio()[2].habilidade[0], "coluna");
        for (int i = x - 2; i < x + 3; i++) {
            for (int j = y - 2; j < y + 3; j++) {
                rand = (int) (Math.random() * 2);
                if (rand == 1) {
                    if (inimigo.getTabuleiro().getPosMatV(x, y) == 0) {
                        if (!"-".equals(inimigo.getTabuleiro().getPosMatN(i, j)) && !"X".equals(inimigo.getTabuleiro().getPosMatN(i, j))) {
                            inimigo.getTabuleiro().setPosMatN(i, j, "X");
                            teste = 1;
                        } else {
                            inimigo.getTabuleiro().setPosMatN(i, j, "X");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Você acertou um escudo inimigo.");
                        inimigo.getTabuleiro().setPosMatV(x, y, inimigo.getTabuleiro().getPosMatV(x, y) - 1);
                    }
                }
            }
        }
        if (teste == 1) {
            JOptionPane.showMessageDialog(null, "Você acertou um navio inimigo e ganhou +100 de energia com " + this.nome + ".");
            this.ganharEnergia(this, 100);
        } else {
            inimigo.jogadorErrou();
        }
    }

    public void usarSonar(Jogador inimigo) {
        int x, y;
        x = pegarCoordenada(5, inimigo.getNavio()[1].habilidade[1], "linha");
        y = pegarCoordenada(5, inimigo.getNavio()[1].habilidade[1], "coluna");
        for (int i = x - 2; i < x + 3; i++) {
            for (int j = y - 2; j < y + 3; j++) {
                if (!"-".equals(inimigo.getTabuleiro().getPosMatN(i, j)) && !"X".equals(inimigo.getTabuleiro().getPosMatN(i, j))) {
                    inimigo.getTabuleiro().setPosMatH(i, j, "N");
                }
            }
        }
    }

    public void atirar(Jogador player, Jogador inimigo) {
        int x, y;
        x = pegarCoordenada(1, null, "linha");
        y = pegarCoordenada(1, null, "coluna");
        if (inimigo.getTabuleiro().getPosMatV(x, y) == 0) {
            if ("-".equals(inimigo.getTabuleiro().getPosMatN(x, y)) || "X".equals(inimigo.getTabuleiro().getPosMatN(x, y))) {
                JOptionPane.showMessageDialog(null, "Você errou e o inimigo ganhou +30 de energia em cada navio.");
                inimigo.jogadorErrou();
            } else {
                JOptionPane.showMessageDialog(null, "Você acertou um navio inimigo e ganhou +100 de energia com " + this.nome + ".");
                this.ganharEnergia(this, 100);
            }
            inimigo.getTabuleiro().setPosMatN(x, y, "X");
        } else {
            JOptionPane.showMessageDialog(null, "Você acertou um escudo inimigo.");
            inimigo.getTabuleiro().setPosMatV(x, y, inimigo.getTabuleiro().getPosMatV(x, y) - 1);
        }
    }

    public int pegarCoordenada(int tamanho, Habilidade habilidade, String orientacao) {
        int calc = tamanho / 2;
        int coordenada = -1;
        String opcao;
        if (habilidade == null) {
            do {
                opcao = JOptionPane.showInputDialog("Informe a " + orientacao + " que deseja atirar:");
                try {
                    coordenada = Integer.parseInt(opcao);
                    if (coordenada < 1 || coordenada > 14) {
                        coordenada = -1;
                        JOptionPane.showMessageDialog(null, "Esse local não existe no tabuleiro.");
                    }
                } catch (NumberFormatException e) {
                    if (opcao == null) {
                        System.exit(0);
                    } else {
                        JOptionPane.showMessageDialog(null, "Informe somente números.");
                        coordenada = -1;
                    }
                }
            } while (coordenada == -1);
        } else {
            do {
                opcao = JOptionPane.showInputDialog("Informe a " + orientacao + " que deseja usar a habilidade " + habilidade.nome + ":");
                try {
                    coordenada = Integer.parseInt(opcao);
                    if (coordenada < 1 + calc || coordenada > 14 - calc) {
                        coordenada = -1;
                        JOptionPane.showMessageDialog(null, "Essa habilidade não pode ser usada nesse local ou esse local não existe no tabuleiro."
                                + "\nTamanho da habilidade: " + tamanho + "x" + tamanho);
                    }
                } catch (NumberFormatException e) {
                    if (opcao == null) {
                        System.exit(0);
                    } else {
                        JOptionPane.showMessageDialog(null, "Informe somente números.");
                        coordenada = -1;
                    }
                }
            } while (coordenada == -1);
        }
        return coordenada - 1;
    }

    public String getMensagem() {
        return "Você ganhou o jogo e desbloqueou esta receita para um delicioso bolo de fubá:\n"
                + "INGREDIENTES\n"
                + "3 ovos inteiros\n"
                + "2 xícaras (chá) de açúcar\n"
                + "2 xícaras (chá) de fubá\n"
                + "3 colheres (sopa) de farinha de trigo\n"
                + "1/2 copo (americano) de óleo\n"
                + "1 copo (americano) de leite\n"
                + "1 colher (sopa) de fermento em pó\n\n"
                + "MODO DE PREPARO\n"
                + "1 - Em um liquidificador, adicione os ovos, o açúcar, o fubá, a farinha de trigo,\n    o óleo, o leite e o fermento, depois bata até a massa ficar lisa e homogênea\n"
                + "2 - Despeje a massa em uma forma untada e polvilhada\n"
                + "3 - Leve para assar em forno médio 180 °C (preaquecido por 40 minutos)";

    }

    public void usarTorpedo(Jogador player, Jogador inimigo) {
        int x = -1, y = -1, option = 0, teste = 0;
        String c;
        do {
            c = JOptionPane.showInputDialog("Informe a linha que deseja usar o Torpedo:");
            try {
                x = Integer.parseInt(c) - 1;
                if (x < 0 || x > 13) {
                    x = -1;
                    JOptionPane.showMessageDialog(null, "Essa habilidade não pode ser lançada nesse local.");
                }
            } catch (NumberFormatException e) {
                if (c == null) {
                    System.exit(0);
                } else {
                    JOptionPane.showMessageDialog(null, "Informe somente números.");
                }
            }
        } while (x == -1);
        do {
            c = JOptionPane.showInputDialog("Informe a coluna que deseja usar o Torpedo:");
            try {
                y = Integer.parseInt(c) - 1;
                if (y < 0 || y > 13) {
                    y = -1;
                    JOptionPane.showMessageDialog(null, "Essa habilidade não pode ser lançada nesse local.");
                }
            } catch (NumberFormatException e) {
                if (c == null) {
                    System.exit(0);
                } else {
                    JOptionPane.showMessageDialog(null, "Informe somente números.");
                }
            }
        } while (y == -1);
        do {
            c = JOptionPane.showInputDialog("Informe a direção que deseja atirar:"
                    + "\n1 - Para cima"
                    + "\n2 - Para baixo"
                    + "\n3 - Para a direita"
                    + "\n4 - Para a esquerda");
            try {
                option = Integer.parseInt(c);
                if (option < 1 || option > 4) {
                    option = 0;
                    JOptionPane.showMessageDialog(null, "Essa direção não existe.");
                }
            } catch (NumberFormatException e) {
                if (c == null) {
                    System.exit(0);
                } else {
                    JOptionPane.showMessageDialog(null, "Informe somente números.");
                }
            }
        } while (option == 0);
        JOptionPane.showMessageDialog(null, "Torpedo lançado com sucesso!");
        switch (option) {
            case 1:
                for (int i = x; i >= 0; i--) {
                    if ("-".equals(inimigo.getTabuleiro().getPosMatN(i, y))) {
                        inimigo.getTabuleiro().setPosMatN(i, y, "X");
                    } else if ("X".equals(inimigo.getTabuleiro().getPosMatN(i, y))) {
                    } else {
                        teste = 1;
                        explosaoTorpedo(i, y, inimigo);
                        break;
                    }
                }
                break;
            case 2:
                for (int i = x; i < 14; i++) {
                    if ("-".equals(inimigo.getTabuleiro().getPosMatN(i, y))) {
                        inimigo.getTabuleiro().setPosMatN(i, y, "X");
                    } else if ("X".equals(inimigo.getTabuleiro().getPosMatN(i, y))) {
                    } else {
                        teste = 1;
                        explosaoTorpedo(i, y, inimigo);
                        break;
                    }
                }
                break;
            case 3:
                for (int i = y; i < 14; i++) {
                    if ("-".equals(inimigo.getTabuleiro().getPosMatN(x, i))) {
                        inimigo.getTabuleiro().setPosMatN(x, i, "X");
                    } else if ("X".equals(inimigo.getTabuleiro().getPosMatN(x, i))) {
                    } else {
                        teste = 1;
                        explosaoTorpedo(x, i, inimigo);
                        break;
                    }
                }
                break;
            case 4:
                for (int i = y; i >= 0; i--) {
                    if ("-".equals(inimigo.getTabuleiro().getPosMatN(x, i))) {
                        inimigo.getTabuleiro().setPosMatN(x, i, "X");
                    } else if ("X".equals(inimigo.getTabuleiro().getPosMatN(x, i))) {
                    } else {
                        teste = 1;
                        explosaoTorpedo(x, i, inimigo);
                        break;
                    }
                }
                break;
            default:
                break;
        }
        if (teste == 1) {
            this.ganharEnergia(this, 100);
            JOptionPane.showMessageDialog(null, "O torpedo acertou um navio inimigo."
                    + "\nSeu submarino ganhou +100 de energia."
                    + "\nEnergia Submarino: " + this.energia);
        } else {
            JOptionPane.showMessageDialog(null, "O Torpedo não conseguiu acertar nenhum navio inimigo."
                    + "\n" + inimigo.getApelido() + " ganhou +30 de energia em todos os navios.");
            inimigo.jogadorErrou();
        }
    }

    public void explosaoTorpedo(int linha, int coluna, Jogador inimigo) {
        if (linha < 1) {
            linha++;
        } else if (linha > 12) {
            linha--;
        }
        if (coluna < 1) {
            coluna++;
        } else if (coluna > 12) {
            coluna--;
        }
        coluna--;
        linha--;
        for (int i = linha; i < (linha + 3); i++) {
            for (int j = coluna; j < (coluna + 3); j++) {
                inimigo.getTabuleiro().setPosMatN(i, j, "X");
            }
        }
    }
    
    public void mostrarInimigo(String texto, Jogador inimigo) {
        JOptionPane.showMessageDialog(null, "Tabuleiro atual de " + inimigo.getApelido()
                + "\n\n" + texto);
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public int getTamC() {
        return tamC;
    }

    public void setTamC(int tamC) {
        this.tamC = tamC;
    }

    public int getTamA() {
        return tamA;
    }

    public void setTamA(int tamA) {
        this.tamA = tamA;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }

    public Habilidade[] getHabilidade() {
        return habilidade;
    }

    public void setHabilidade(Habilidade[] habilidade) {
        this.habilidade = habilidade;
    }
}
