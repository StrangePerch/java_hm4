package com.company.bank.tree;

class Utils {
    static public <T extends Comparable<T>> T max(T a, T b) {
        return (a.compareTo(b) > 0) ? a : b;
    }

    static public <TVal, TKey extends Comparable<TKey>> int getBalance(TreeNode<TVal, TKey> node) {
        if (node == null) return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    static public <TVal, TKey extends Comparable<TKey>> int getHeight(TreeNode<TVal, TKey> node) {
        if (node == null) return 0;
        else {
            node.adjustHeight();
            return node.height;
        }
    }
}

public class AVLTree<TVal, TKey extends Comparable<TKey>> {

    TreeNode<TVal, TKey> root_ = null;

    public AVLTree() {

    }

    public TreeNode<TVal, TKey> begin() {
        return root_;
    }

    public boolean isEmpty() {
        return this.root_ == null;
    }

    public void echo() {
        root_.echo();
    }

    public void echo_h() {
        root_.echo_h();
    }

    public void menu() {
        root_.menu();
    }

    public TreeNode<TVal, TKey> push(TVal data, TKey key) {
        if (root_ == null) {
            root_ = new TreeNode<TVal, TKey>(data, key);
            return root_;
        }
        TreeNode<TVal, TKey> temp = root_.push(data, key);
        root_.adjustHeight();
        var balance = Utils.getBalance(root_);
        if (balance < -1) {
            root_ = root_.leftTurn();
        }
        if (balance > 1) {
            root_ = root_.rightTurn();
        }
        return temp;
    }

    public TreeNode<TVal, TKey> find(TKey key) {
        if (root_ == null) return null;
        return root_.find(key);
    }

    public TVal get(TKey key) {
        if (this.root_ != null)
            return this.root_.get(key);
        return null;
    }

    public int size() {
        int size = 0;
        root_.size(size);
        return size;
    }
};
