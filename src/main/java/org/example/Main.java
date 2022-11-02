package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.tree.AbstractTreeNode;
import org.example.tree.TreeVO;
import org.example.util.TreeUtil;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<CustomTreeNode> dataSource = new ArrayList<CustomTreeNode>() {{
            add(new CustomTreeNode("1231231", "0", "第1组"));
            add(new CustomTreeNode("1231232", "0", "第2组"));
            add(new CustomTreeNode("1231233", "0", "第3组"));
            add(new CustomTreeNode("1231234", "1231231", "第4组"));
            add(new CustomTreeNode("1231235", "1231233", "第5组"));
            add(new CustomTreeNode("1231236", "1231232", "第6组"));
            add(new CustomTreeNode("1231237", "1231232", "第6组"));
            add(new CustomTreeNode("1231238", "1231236", "第6组"));
            add(new CustomTreeNode("1231239", "1231232", "第6组"));
            add(new CustomTreeNode("1231230", "12312300", "第6组"));
            add(new CustomTreeNode("12312300", "1231238", "第6组"));
        }};

        long startTime = System.currentTimeMillis();
        // TreeUtil<CustomTreeNode> treeTestTreeUtil = new TreeUtil<>(CustomTreeNode.class);
        TreeVO<CustomTreeNode> tree = TreeUtil.tree(null, "tree", "label", null, dataSource);
        System.out.println(System.currentTimeMillis() - startTime);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            System.out.println(objectMapper.writeValueAsString(tree));
        } catch (Exception ignored) {
        }
    }

    static class CustomTreeNode extends AbstractTreeNode<CustomTreeNode> {
        String imei, deviceId;

        public CustomTreeNode() {
        }

        public CustomTreeNode(String id, String pid, String name) {
            super(id, pid);
            setName(name);
        }

        public String getImei() {
            return imei;
        }

        public void setImei(String imei) {
            this.imei = imei;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }
    }
}