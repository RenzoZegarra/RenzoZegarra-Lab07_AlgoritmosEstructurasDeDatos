package bstreelinklistinterfgeneric;

import Exceptions.*;
import bstreeInterface.BinarySearchTree;

public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E> {

    private class Node 
    {
        public E data;
        public Node left;
        public Node right;

        public Node(E data) 
        {
            this(data, null, null);
        }

        public Node(E data, Node left, Node right) 
        {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    private Node root;

    public LinkedBST() 
    {
        this.root = null;
    }

    @Override
    public void insert(E data) throws ItemDuplicated 
    {
        root = insertRec(root, data);
    }

    private Node insertRec(Node node, E data) throws ItemDuplicated 
    {
        if (node == null)
            return new Node(data);

        int cmp = data.compareTo(node.data);
        if (cmp < 0)
            node.left = insertRec(node.left, data);
        else if (cmp > 0)
            node.right = insertRec(node.right, data);
        else
            throw new ItemDuplicated();

        return node;
    }

    @Override
    public E search(E data) throws ItemNotFound 
    {
        Node result = searchRec(root, data);
        if (result == null)
            throw new ItemNotFound();
        return result.data;
    }

    private Node searchRec(Node node, E data) 
    {
        if (node == null)
            return null;
        int cmp = data.compareTo(node.data);
        if (cmp < 0)
            return searchRec(node.left, data);
        else if (cmp > 0)
            return searchRec(node.right, data);
        else
            return node;
    }

    @Override
    public void delete(E data) throws ExceptionIsEmpty 
    {
        if (root == null)
            throw new ExceptionIsEmpty();
        root = deleteRec(root, data);
    }

    private Node deleteRec(Node node, E data) 
    {
        if (node == null) return null;

        int cmp = data.compareTo(node.data);
        if (cmp < 0)
            node.left = deleteRec(node.left, data);
        else if (cmp > 0)
            node.right = deleteRec(node.right, data);
        else 
        {
            if (node.left == null)
                return node.right;
            else if (node.right == null)
                return node.left;

            Node min = findMin(node.right);
            node.data = min.data;
            node.right = deleteRec(node.right, min.data);
        }

        return node;
    }

    //Encuentra el valor minimo del subarbol
    private Node findMin(Node node) 
    {
        while (node.left != null)
            node = node.left;
        return node;
    }

    //Encontrar el valor maximo del subarbol
    private Node finMax(Node node)
    {  
        
        return node;
    }

    @Override
    public boolean isEmpty() 
    {
        return root == null;
    }

    //----------------------------------

    //Implementaacion del recorrido In - Orden
    @Override
    public String toString() 
    {
        StringBuilder sb = new StringBuilder();
        inOrderToString(root, sb);
        return sb.toString();
    }


    
    private void inOrderToString(Node node, StringBuilder sb) 
    {
        if (node != null) 
        {
            inOrderToString(node.left, sb);
            sb.append(node.data.toString()).append(" ");
            inOrderToString(node.right, sb);
        }
    }

    //Implementacion del recorrido Pre - Orden
    public String preOrden() {
        StringBuilder sb = new StringBuilder();
        preOrderToString(root, sb);
        return sb.toString();
    }

    private void preOrderToString(Node node, StringBuilder sb) {
        if (node != null) {
            sb.append(node.data.toString()).append(" ");
            preOrderToString(node.left, sb);
            preOrderToString(node.right, sb);
    }
}

    // Recorrido Post - Orden
    public String postOrden() {
        StringBuilder sb = new StringBuilder();
        postOrderToString(root, sb);
        return sb.toString();
    }

    private void postOrderToString(Node node, StringBuilder sb) {
    if (node != null) {
        postOrderToString(node.left, sb);
        postOrderToString(node.right, sb);
        sb.append(node.data.toString()).append(" ");
    }
}

}
