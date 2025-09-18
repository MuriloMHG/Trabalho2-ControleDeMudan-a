package com.sces.app;

import com.sces.controller.ProductController;
import com.sces.domain.Product;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        var controller = new ProductController();
        System.out.println("=== SCES | Cadastro de Produto (US#1) ===");
        System.out.println("1) Cadastrar novo produto");
        System.out.println("0) Sair");

        try (var sc = new Scanner(System.in)) {
            while (true) {
                System.out.print("\nEscolha: ");
                String opt = sc.nextLine().trim();

                if ("0".equals(opt)) {
                    System.out.println("Encerrando...");
                    break;
                } else if ("1".equals(opt)) {
                    try {
                        System.out.print("Nome: ");
                        String name = sc.nextLine();

                        System.out.print("Descrição (opcional): ");
                        String desc = sc.nextLine();

                        System.out.print("Quantidade inicial (inteiro >= 0): ");
                        int qty = Integer.parseInt(sc.nextLine().trim());

                        Product p = controller.register(name, desc, qty);
                        System.out.printf("OK! Produto cadastrado. ID=%d | Nome=%s | Qtd=%d%n",
                                p.getId(), p.getName(), p.getQuantity());
                    } catch (NumberFormatException e) {
                        System.out.println("ERRO: quantidade deve ser um número inteiro.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("ERRO: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("ERRO inesperado: " + e.getMessage());
                    }
                } else {
                    System.out.println("Opção inválida.");
                }
            }
        }
    }
}