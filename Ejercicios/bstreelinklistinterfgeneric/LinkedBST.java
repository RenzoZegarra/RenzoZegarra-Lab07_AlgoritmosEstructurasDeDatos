
package bstreelinklistinterfgeneric;

import Exceptions.*;
import bstreeInterface.BinarySearchTree;
import java.util.LinkedList;
import java.util.Queue;

public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E> {

    private class Node {
        public E data;
        public Node left;
        public Node right;

        public Node(E data) {
            this(data, null, null);
        }

        public Node(E data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    private Node root;

    public LinkedBST() {
        this.root = null;
    }

    // Métodos ya existentes: insertRec, deleteRec, searchRec, findMin, etc.

    // 01.a destroyNodes
    public void destroyNodes() throws ExceptionIsEmpty {
        if (root == null) throw new ExceptionIsEmpty();
        root = null;
    }

    // 01.b y 01.c countAllNodes y countNodes (nodos NO hoja)
    public int countAllNodes() {
        return countNonLeafNodes(root);
    }

    public int countNodes() {
        return countAllNodes();
    }

    private int countNonLeafNodes(Node node) {
        if (node == null || (node.left == null && node.right == null)) return 0;
        return 1 + countNonLeafNodes(node.left) + countNonLeafNodes(node.right);
    }

    // 01.d height(x) no recursivo
    public int height(E x) {
        Node current = root;
        while (current != null) {
            int cmp = x.compareTo(current.data);
            if (cmp < 0) current = current.left;
            else if (cmp > 0) current = current.right;
            else return heightIter(current);
        }
        return -1;
    }

    private int heightIter(Node node) {
        if (node == null) return -1;
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        int height = -1;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            height++;
            for (int i = 0; i < levelSize; i++) {
                Node current = queue.poll();
                if (current.left != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
            }
        }
        return height;
    }

    // 01.e amplitude(Nivel)
    public int amplitude(int level) {
        if (root == null) return 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int currentLevel = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            if (currentLevel == level) return levelSize;
            for (int i = 0; i < levelSize; i++) {
                Node node = queue.poll();
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            currentLevel++;
        }

        return 0;
    }

    // 02.a areaBST (hojas * altura del árbol)
    public int areaBST() {
        if (root == null) return 0;
        int height = heightIter(root);
        int leafCount = countLeafs();
        return height * leafCount;
    }

    private int countLeafs() {
        if (root == null) return 0;
        int count = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.left == null && node.right == null) count++;
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
        return count;
    }

    // 02.b drawBST (simulación simple con toString)
    public String drawBST() {
        return this.toString();
    }

    // 03. parenthesize
    public String parenthesize() {
        StringBuilder sb = new StringBuilder();
        parenthesize(root, sb, 0);
        return sb.toString();
    }

    private void parenthesize(Node node, StringBuilder sb, int level) {
        if (node == null) return;
        sb.append("\t".repeat(level)).append(node.data.toString());
        if (node.left != null || node.right != null) {
            sb.append("(\n");
            if (node.left != null) parenthesize(node.left, sb, level + 1);
            if (node.right != null) parenthesize(node.right, sb, level + 1);
            sb.append("\t".repeat(level)).append(")\n");
        } else {
            sb.append("\n");
        }
    }
}  
