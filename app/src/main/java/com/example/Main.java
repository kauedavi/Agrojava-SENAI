package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner env = new Scanner(System.in);

        double[] volumeChuvaDiario = new double[7];
        boolean dadosChuvaCadastrados = false;

        double[][] umidadePorcento = new double[4][4];
        boolean dadosUmidadeCadastrados = false;

        String[] diasDaSemana = {
                "Segunda-feira", "Terça-feira", "Quarta-feira",
                "Quinta-feira", "Sexta-feira", "Sábado", "Domingo"
        };

        int opcaoMenu = 0;

        do {

            System.out.println("1 - Cadastrar Dados");
            System.out.println("2 - Exibir Mapa do Campo");
            System.out.println("3 -Relatório de Alertas de Irrigação");
            System.out.println("4 - Sair");

            opcaoMenu = env.nextInt();

            switch (opcaoMenu) {
                case 1:
                    System.out.println("CADASTRO DE DADOS");

                    System.out.println("Registro de Chuvas Semanal");

                    for (int dia = 0; dia < 7; dia++) {
                        System.out.print("Informe o volume de chuva para " + diasDaSemana[dia] + ": ");
                        volumeChuvaDiario[dia] = env.nextDouble();
                    }

                    dadosChuvaCadastrados = true;

                    System.out.println("Registro de Umidade");
                    for (int linha = 0; linha < 4; linha++) {
                        for (int coluna = 0; coluna < 4; coluna++) {
                            System.out.print("Informe a umidade [" + linha + "][" + coluna + "] (%): ");
                            umidadePorcento[linha][coluna] = env.nextDouble();
                        }
                    }
                    dadosUmidadeCadastrados = true;
                    break;

                case 2:
                    System.out.println("relatorio:");

                    if (dadosChuvaCadastrados) {
                        double somaChuva = 0;
                        double maiorIndiceChuva = volumeChuvaDiario[0];
                        int diaMaiorChuvaIndex = 0;

                        for (int i = 0; i < 7; i++) {
                            somaChuva += volumeChuvaDiario[i];
                            if (volumeChuvaDiario[i] > maiorIndiceChuva) {
                                maiorIndiceChuva = volumeChuvaDiario[i];
                                diaMaiorChuvaIndex = i;
                            }
                        }

                        double mediaSemanalChuva = somaChuva / 7.0;

                        System.out.println("Resumo:");
                        System.out.print("Média semanal de chuva:" + mediaSemanalChuva);
                        System.out.print("Dia com maior índice de chuva:" + diasDaSemana[diaMaiorChuvaIndex] + " "
                                + maiorIndiceChuva);
                    } else {
                        System.out.println("Dados de chuva ainda não foram cadastrados.");
                    }

                    if (dadosUmidadeCadastrados) {
                        System.out.println("Matriz de Umidade do Solo:");
                        System.out.println("    Col 0    Col 1    Col 2    Col 3");
                        for (int linha = 0; linha < 4; linha++) {
                            System.out.print("Linha " + linha + " ");
                            for (int coluna = 0; coluna < 4; coluna++) {
                                System.out.print(" | " + umidadePorcento[linha][coluna] + " | ");
                            }
                            System.out.println();
                        }
                    } else {
                        System.out.println("Aviso: Dados de umidade ainda não foram cadastrados.");
                    }
                    break;

                case 3:
                    System.out.println("relatorio alertas");
                    if (!dadosUmidadeCadastrados) {
                        System.out.println("Cadastre os dados de umidade primeiro");
                    } else {
                        boolean algumAlerta = false;
                        System.out.println("umidade abaixo de 30%:");

                        for (int linha = 0; linha < 4; linha++) {
                            for (int coluna = 0; coluna < 4; coluna++) {
                                double umidadeAtual = umidadePorcento[linha][coluna];
                                if (umidadeAtual < 30.0) {
                                    System.out.println("ALERTA: seotr [" + linha + "][" + coluna + "] -> Umidade: "
                                            + umidadeAtual + " (CRÍTICO)");
                                    algumAlerta = true;
                                }
                            }
                        }

                        if (!algumAlerta) {
                            System.out.println("Todos os setores estão com níveis adequados de umidade.");
                        }
                    }
                    break;

                case 4:
                    System.out.println("Fim da operaçao.");
                    break;

                default:
                    System.out.println("Escolha um valor entre 1 e 4.");
                    break;
            }

        } while (opcaoMenu != 4);

    }
}