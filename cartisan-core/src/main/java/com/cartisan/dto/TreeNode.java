package com.cartisan.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author colin
 */
@Getter
public class TreeNode {
    private String id;

    private String name;
    private String parentId;

    public TreeNode(String id, String name, String parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    @Setter
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<TreeNode> children;

    public static List<TreeNode> buildTree(List<TreeNode> nodes) {
        Multimap<String, TreeNode> treeNodeMap = ArrayListMultimap.create();
        nodes.forEach(node -> treeNodeMap.put(node.getParentId(), node));

        return buildTree("0", treeNodeMap);
    }

    private static List<TreeNode> buildTree(String parentId, Multimap<String, TreeNode> treeNodeMap) {
        final List<TreeNode> nodes = (List<TreeNode>)treeNodeMap.get(parentId);
        nodes.forEach(node->node.setChildren(buildTree(node.getId(), treeNodeMap)));

        return nodes;
    }
}
