package bstreelinklistinterfgeneric;

import Exceptions.*;
import java.util.Scanner;

public class Prueba {
    public static void main(String[] args) {
        LinkedBST<Integer> bst1 = new LinkedBST<>();
        LinkedBST<Integer> bst2 = new LinkedBST<>();
        Scanner sc = new Scanner(System.in);

        try {
            bst1.insert(400);
            bst1.insert(100);
            bst1.insert(700);

            bst2.insert(300);
            bst2.insert(100);
            bst2.insert(500);

            System.out.println("Árbol 1: " + bst1);
            System.out.println("Árbol 2: " + bst2);

            System.out.println("Área BST1: " + bst1.areaBST());
            System.out.println("Área BST2: " + bst2.areaBST());

            System.out.println("¿Misma área? " + sameArea(bst1, bst2));

            System.out.println("Parenthesize árbol 1:\n" + bst1.parenthesize());

        } catch (Exception e) {
            System.out.println("Excepción: " + e.getMessage());
        }
    }

    public static boolean sameArea(LinkedBST<Integer> a1, LinkedBST<Integer> a2) {
        return a1.areaBST() == a2.areaBST();
    }
}
