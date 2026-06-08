package br.ucsal.ads.lpa;

import java.util.Random;
import java.util.Scanner;

public class jogoVelha3 {

    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    static int[][] rotulos;
    static char[][] tabuleiro;
    static int tamanho;
    static int sequenciaVitoria;

    static String nomeJogador1, nomeJogador2;
    static char simboloJogador1, simboloJogador2;
    static boolean modoMaquina;

    static int vitorias1 = 0, vitorias2 = 0, empates = 0;

    public static void main(String[] args) {

        exibir("banner");
        configurarJogo();

        boolean jogarNovamente = true;

        while (jogarNovamente) {

            gerenciarTabuleiro("inicializar");
            executarPartida();
            exibir("placar");

            System.out.print("Jogar novamente? (s/n): ");

            boolean respostaValida = false;

            while (!respostaValida) {

                String resp = scanner.nextLine();

                resp = resp.toLowerCase();

                if (resp.equals("s")) {
                    jogarNovamente = true;
                    respostaValida = true;
                }
                else if (resp.equals("sim")) {
                    jogarNovamente = true;
                    respostaValida = true;
                }
                else if (resp.equals("n")) {
                    jogarNovamente = false;
                    respostaValida = true;
                }
                else if (resp.equals("nao")) {
                    jogarNovamente = false;
                    respostaValida = true;
                }
                else if (resp.equals("não")) {
                    jogarNovamente = false;
                    respostaValida = true;
                }
                else {
                    System.out.print("Digite apenas s ou n: ");
                }
            }
        }

        System.out.println("Obrigado por jogar!");
    }

    static void exibir(String tipo) {
        switch (tipo) {
            case "banner" -> {
                System.out.println("=====================================");
                System.out.println("         JOGO DA VELHA");
                System.out.println("=====================================");
                System.out.println();
            }
            case "placar" -> {
                System.out.println("------ PLACAR ------");
                System.out.printf("%-15s : %d vitorias%n", nomeJogador1, vitorias1);
                System.out.printf("%-15s : %d vitorias%n", nomeJogador2, vitorias2);
                System.out.printf("%-15s : %d%n", "Empates", empates);
                System.out.println("--------------------");
                System.out.println();
            }
        }
    }

    static void gerenciarTabuleiro(String acao) {

        if (acao.equals("inicializar")) {
            rotulos = new int[tamanho][tamanho];
            tabuleiro = new char[tamanho][tamanho];
            int num = 1;
            for (int i = 0; i < tamanho; i++) {
                for (int j = 0; j < tamanho; j++) {
                    rotulos[i][j] = num;
                    tabuleiro[i][j] = ' ';
                    num++;
                }
            }
        }

        if (acao.equals("exibir")) {
            System.out.println();
            for (int i = 0; i < tamanho; i++) {
                System.out.print("  ");
                for (int j = 0; j < tamanho; j++) {
                    if (tabuleiro[i][j] == ' ') {
                        System.out.printf(" %2d ", rotulos[i][j]);
                    } else {
                        System.out.printf("  %c ", tabuleiro[i][j]);
                    }
                    if (j < tamanho - 1) {
                        System.out.print("|");
                    }
                }
                System.out.println();
                if (i < tamanho - 1) {
                    System.out.print("  ");
                    for (int j = 0; j < tamanho; j++) {
                        System.out.print("----");
                        if (j < tamanho - 1) {
                            System.out.print("+");
                        }
                    }
                    System.out.println();
                }
            }
            System.out.println();
        }
    }

    static void configurarJogo() {
        System.out.println("Escolha o modo de jogo:");
        System.out.println("1 - Dois jogadores");
        System.out.println("2 - Contra a maquina");
        System.out.print("Escolha: ");

        while (true) {
            String entrada = scanner.nextLine().trim();
            if (ehNumeroValido(entrada, 1, 2)) {
                int modo = Integer.parseInt(entrada);
                modoMaquina = (modo == 2);
                break;
            }
            System.out.print("invalido, digita 1 ou 2: ");
        }
        System.out.println();
        System.out.println("Escolha o tamanho do tabuleiro:");
        System.out.println("1 - 3x3 (1-9)");
        System.out.println("2 - 5x5 (1-25)");
        System.out.println("3 - 7x7 (1-49)");
        System.out.print("Escolha: ");

        while (true) {
            String entrada = scanner.nextLine().trim();
            if (ehNumeroValido(entrada, 1, 3)) {
                int opcao = Integer.parseInt(entrada);
                switch (opcao) {
                    case 1 -> {
                        tamanho = 3;
                        sequenciaVitoria = 3;
                    }
                    case 2 -> {
                        tamanho = 5;
                        sequenciaVitoria = 4;
                    }
                    case 3 -> {
                        tamanho = 7;
                        sequenciaVitoria = 5;
                    }
                }
                break;
            }
            System.out.print("invalido, digita 1, 2 ou 3: ");
        }
        System.out.println();
        System.out.print("Nome do Jogador 1: ");
        nomeJogador1 = scanner.nextLine().trim();
        if (nomeJogador1.isEmpty()) {
            nomeJogador1 = "Jogador 1";
        }
        System.out.print("Simbolo do " + nomeJogador1 + " [padrao X]: ");
        String entradaSimbolo = scanner.nextLine().trim();
        if (entradaSimbolo.isEmpty()) {
            simboloJogador1 = 'X';
        } else {
            simboloJogador1 = Character.toUpperCase(entradaSimbolo.charAt(0));
        }
        if (modoMaquina) {
            nomeJogador2 = "Maquina";
            if (simboloJogador1 == 'O') {
                simboloJogador2 = 'X';
            } else {
                simboloJogador2 = 'O';
            }
            System.out.println("A maquina vai usar: " + simboloJogador2);

        } else {
            System.out.print("Nome do Jogador 2: ");
            nomeJogador2 = scanner.nextLine().trim();
            if (nomeJogador2.isEmpty()) {
                nomeJogador2 = "Jogador 2";
            }
            System.out.print("Simbolo do " + nomeJogador2 + " [padrao O]: ");
            while (true) {
                String entrada = scanner.nextLine().trim();
                if (entrada.isEmpty()) {
                    if (simboloJogador1 != 'O') {
                        simboloJogador2 = 'O';
                        break;
                    } else {
                        System.out.print("esse simbolo ja ta sendo usado, escolhe outro: ");
                        continue;
                    }
                }
                char c = Character.toUpperCase(entrada.charAt(0));
                if (c != simboloJogador1) {
                    simboloJogador2 = c;
                    break;
                } else {
                    System.out.print("esse simbolo ja ta sendo usado, escolhe outro: ");
                }
            }
        }

        System.out.println();
        System.out.println("Tudo certo! Boa sorte!");
        System.out.println();
    }

    static void executarPartida() {

        int turno = 0;
        int totalCelulas = tamanho * tamanho;

        System.out.println("=== NOVA PARTIDA ===");
        gerenciarTabuleiro("exibir");

        while (turno < totalCelulas) {

            boolean ehJogador1 = (turno % 2 == 0);

            String nomeAtual;
            if (ehJogador1) {
                nomeAtual = nomeJogador1;
            } else {
                nomeAtual = nomeJogador2;
            }

            char simboloAtual;
            if (ehJogador1) {
                simboloAtual = simboloJogador1;
            } else {
                simboloAtual = simboloJogador2;
            }

            if (modoMaquina && !ehJogador1) {
                System.out.println("\nVez da " + nomeAtual + " (" + simboloAtual + ")");
                jogadaMaquina(simboloAtual);
            } else {
                System.out.println("\nVez de " + nomeAtual + " (" + simboloAtual + ")");
                jogadaHumana(simboloAtual);
            }

            gerenciarTabuleiro("exibir");

            if (verificarVitoria(simboloAtual)) {
                System.out.println("PARABENS " + nomeAtual + "! Voce venceu!");
                if (ehJogador1) {
                    vitorias1++;
                } else {
                    vitorias2++;
                }
                return;
            }

            turno++;
        }

        System.out.println("EMPATE!");
        empates++;
    }

    static boolean validarJogada(int linha, int coluna) {

        if (linha < 0 || linha >= tamanho || coluna < 0 || coluna >= tamanho) {
            return false;
        }

        char c = tabuleiro[linha][coluna];
        if (c == simboloJogador1 || c == simboloJogador2) {
            return false;
        }

        return true;
    }

    static void jogadaHumana(char simbolo) {

        System.out.println("Digite a posicao (1-" + (tamanho * tamanho) + "): ");

        while (true) {
            System.out.print("> ");
            int posicao = scanner.nextInt();

            if (posicao < 1 || posicao > tamanho * tamanho) {
                System.out.println("Posicao invalida! Digite um numero entre 1 e " + (tamanho * tamanho));
                continue;
            }

            int linha = (posicao - 1) / tamanho;
            int coluna = (posicao - 1) % tamanho;

            if (validarJogada(linha, coluna)) {
                tabuleiro[linha][coluna] = simbolo;
                return;
            } else {
                System.out.println("Essa posicao ja ta ocupada! Tenta de novo.");
                gerenciarTabuleiro("exibir");
            }
        }
    }

    static void jogadaMaquina(char simbolo) {

        boolean achouPosicao = false;

        while (!achouPosicao) {
            int linha = random.nextInt(tamanho);
            int coluna = random.nextInt(tamanho);

            if (validarJogada(linha, coluna)) {
                achouPosicao = true;
                System.out.println("Maquina jogou na posicao " + (linha * tamanho + coluna + 1));
                tabuleiro[linha][coluna] = simbolo;
            }
        }
    }

    static boolean verificarVitoria(char simbolo) {
        for (int i = 0; i < tamanho; i++) {
            if (verificarSequencia(simbolo, i, 0, 0, 1)) {
                return true;
            }
        }
        for (int j = 0; j < tamanho; j++) {
            if (verificarSequencia(simbolo, 0, j, 1, 0)) {
                return true;
            }
        }
        for (int i = 0; i <= tamanho - sequenciaVitoria; i++) {
            for (int j = 0; j <= tamanho - sequenciaVitoria; j++) {
                if (verificarSequencia(simbolo, i, j, 1, 1)) {
                    return true;
                }
            }
        }
        for (int i = 0; i <= tamanho - sequenciaVitoria; i++) {
            for (int j = sequenciaVitoria - 1; j < tamanho; j++) {
                if (verificarSequencia(simbolo, i, j, 1, -1)) {
                    return true;
                }
            }
        }

        return false;
    }

    static boolean verificarSequencia(char simbolo, int linhaInicio, int colunaInicio,
                                      int dLinha, int dColuna) {
        int count = 0;
        int linha = linhaInicio;
        int coluna = colunaInicio;

        for (int k = 0; k < tamanho; k++) {
            if (linha < 0 || linha >= tamanho || coluna < 0 || coluna >= tamanho) {
                break;
            }
            if (tabuleiro[linha][coluna] == simbolo) {
                count++;
                if (count >= sequenciaVitoria) {
                    return true;
                }
            } else {
                count = 0;
            }
            linha += dLinha;
            coluna += dColuna;  linha += dLinha;
            coluna += dColuna;
        }
        return false;
    }

    static boolean ehNumeroValido(String texto, int min, int max) {
        for (int i = 0; i < texto.length(); i++) {
            if (!Character.isDigit(texto.charAt(i))) {
                return false;
            }
        }
        int numero = Integer.parseInt(texto);
        if (numero >= min && numero <= max) {
            return true;
        }
        return false;
    }
}
