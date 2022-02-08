package com.company.bank.tree;

import java.util.Scanner;

public class TreeNode<TVal, TKey extends Comparable<TKey>> {
    public int height = 1;
    public TVal data;
    public TKey key;
    public TreeNode<TVal, TKey> left = null;
    public TreeNode<TVal, TKey> right = null;

    public TreeNode(TVal val, TKey key) {
        this.data = val;
        this.key = key;
    }

    public TVal get(TKey key) {
        if (key == this.key) return this.data;
        if (key.compareTo(this.key) < 0 && this.left != null) return this.left.get(key);
        if (key.compareTo(this.key) > 0 && this.right != null) return this.right.get(key);
        return null;
    }

    public TreeNode<TVal, TKey> find(TKey key) {
        if (key.compareTo(this.key) > 0)
            if (right != null) return right.find(key);
            else return null;
        else if (key.compareTo(this.key) < 0)
            if (left != null) return left.find(key);
            else return null;
        else
            return this;
    }

    public TreeNode<TVal, TKey> push(TVal val, TKey key) {
        TreeNode<TVal, TKey> temp;
        if (key.compareTo(this.key) > 0) {
            if (right == null) {
                height++;
                right = new TreeNode<TVal, TKey>(val, key);
                return right;
            }
            temp = right.push(val, key);
        } else if (key.compareTo(this.key) < 0) {
            if (left == null) {
                height++;
                left = new TreeNode<TVal, TKey>(val, key);
                return left;
            }
            temp = left.push(val, key);
        } else {
            return null;
        }
        balance();
        return temp;
    }

    public void balance() {
        left = balance(left);
        right = balance(right);
    }

    public TreeNode<TVal, TKey> balance(TreeNode<TVal, TKey> node) {
        adjustHeight();
        if (Utils.getBalance(node) > 1) {
            if (Utils.getBalance(node.right) >= 0)
                return node.rightTurn();
            else if (Utils.getBalance(node.left) < -1) {
                node.left = node.left.leftTurn();
                return node.rightTurn();
            }
        }
        if (Utils.getBalance(node) < -1) {
            if (Utils.getBalance(node.left) <= 0)
                return node.leftTurn();
            else if (Utils.getBalance(node.right) > 1) {
                node.right = node.right.rightTurn();
                return node.leftTurn();
            }
        }
        return node;
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void menu() {
        while (true) {
            clearScreen();
            System.out.println("Top: " + data);
            if (left != null)
                System.out.print("Left: " + left.data);
            else
                System.out.print("         ");
            if (right != null)
                System.out.println("     Right: " + right.data);
            else
                System.out.println("");
            int choice;
            System.out.println("0.Back, 1.Left, 2.Right");
            var scanner = new Scanner(System.in);
            choice = scanner.nextInt();
            switch (choice) {
                default:
                    return;
                case 1:
                    if (left != null) left.menu();
                    break;
                case 2:
                    if (right != null) right.menu();
                    break;
            }
        }
    }

    void echo() {
        if (left != null) left.echo();
        System.out.print(data + " ");
        if (right != null) right.echo();
    }

    void echo_h() {
        if (left != null) left.echo_h();
        System.out.println(data + " - " + height);
        if (right != null) right.echo_h();
    }

    void size(int n) {
        n++;
        if (left != null) left.size(n);
        if (right != null) right.size(n);
    }

    //                      Left rotation
    //             x					      y
    //            / \					     / \
    //           a   y           --.	    x   b
    //              / \					   / \
    //             z   b				  a   z
    TreeNode<TVal, TKey> leftTurn() {
        TreeNode<TVal, TKey> x = this;
        TreeNode<TVal, TKey> y = x.right;
        TreeNode<TVal, TKey> z = y.left;


        //Rotation
        y.left = x;
        x.right = z;

        //Adjust height
        x.adjustHeight();
        y.adjustHeight();

        return y;
    }

    //                      Right rotation
    //             x				      y
    //            / \				     / \
    //           y   a          --.    b   x
    //          / \					       / \
    //         b   z				      z   a
    TreeNode<TVal, TKey> rightTurn() {
        TreeNode<TVal, TKey> x = this;
        TreeNode<TVal, TKey> y = x.left;
        TreeNode<TVal, TKey> z = y.right;

        //Rotation
        y.right = x;
        x.left = z;

        //Adjust height
        x.adjustHeight();
        y.adjustHeight();

        return y;
    }

    void adjustHeight() {
        if (left == null && right == null) height = 1;
        else if (left == null) height = right.height + 1;
        else if (right == null) height = left.height + 1;
        else height = Utils.max(left.height, right.height) + 1;
    }
}
