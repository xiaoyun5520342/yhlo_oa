package com.yhlo.oa.util;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @create: 2022-04-22 09:18
 * @description: 获取页面节点信息工具类
 **/
public class NodeUtil {

    public static ArrayList<Node> getAllButtonNodes(Parent root) {
        ArrayList<Node> buttonNodes = new ArrayList<>();
        ArrayList<Node> nodes = getAllNodes(root);
        for (Node node : nodes) {
            if (node instanceof Button) {
                buttonNodes.add(node);
            }
        }
        return buttonNodes;
    }

    public static ArrayList<Node> getAllTextFiledNodes(Parent root) {
        ArrayList<Node> textFiledNodes = new ArrayList<>();
        ArrayList<Node> nodes = getAllNodes(root);
        for (Node node : nodes) {
            if (node instanceof TextField) {
                textFiledNodes.add(node);
            }
        }
        return textFiledNodes;
    }

    private static ArrayList<Node> getAllNodes(Parent root) {
        ArrayList<Node> nodes = new ArrayList<>();
        addAllDescendents(root, nodes);
        return nodes;
    }

    private static void addAllDescendents(Parent parent, ArrayList<Node> nodes) {
        List<Node> children = Collections.EMPTY_LIST;
        if (parent instanceof ButtonBar) {
            children = ((ButtonBar) parent).getButtons();
        } else if (parent instanceof TabPane) {
            for (Tab tab : ((TabPane) parent).getTabs()) {
                Node tabContent = tab.getContent();
                if (tabContent instanceof Parent) {
                    addAllDescendents((Parent) tab.getContent(), nodes);
                }
            }
        } else {
            children = parent.getChildrenUnmodifiable();
        }

        for (Node node : children) {
            nodes.add(node);
            if (node instanceof Parent) {
                addAllDescendents((Parent) node, nodes);
            }
        }
    }
}
