package bstreelinklistinterfgeneric;
import Exceptions.*;
import java.util.Scanner;

public class Prueba {
    public static void main(String[] args) 
    {
        //Variable y objetos
        LinkedBST<Integer> bst = new LinkedBST<>();
        Scanner sc = new Scanner(System.in);
        int elemento_Eliminar;
        int elemento_Buscar;

        try 
        {
            //Insercion de elementos
            bst.insert(400);
            bst.insert(100);
            bst.insert(700);
            System.out.println("Árbol in-orden: " + bst); //100 400 700
            System.out.println("Recorrido Pre-Orden: " + bst.preOrden()); //400 100 700
            System.out.println("Recorrido Post-Orden: " + bst.postOrden()); //100 700 400


            System.out.print("Elemento a buscar: ");
            elemento_Buscar = sc.nextInt();
            System.out.println("Elemento encontradp: " + bst.search(elemento_Buscar));
            
            System.out.print("Eliminar elemento: ");
            elemento_Eliminar = sc.nextInt();
            bst.delete(elemento_Eliminar);
            System.out.println("Árbol después de la eliminacion: " + bst);
        } 
        catch (ItemDuplicated | ItemNotFound | ExceptionIsEmpty e) 
        {
            System.out.println("Excepción: " + e.getMessage());
        }
    }
}
