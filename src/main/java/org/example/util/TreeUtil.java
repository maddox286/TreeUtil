package org.example.util;

import com.esotericsoftware.reflectasm.ConstructorAccess;
import org.example.tree.AbstractTreeNode;
import org.example.tree.TreeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 树结构的工具类
 * <br>
 * *******************      example     ******************
 * <br>
 * CustomTreeNode 继承 AbstractTreeNode<CustomTreeNode>  必须包含id、pid、label、name (一个平铺的树状结构) <br>
 * List<CustomTreeNode> dataSource <br>
 * TreeUtil<CustomTreeNode> treeTestTreeUtil = new TreeUtil<>(CustomTreeNode.class); <br>
 * CustomTreeNode tree = treeTestTreeUtil.tree(null, "treeName", "treeLabel", dataSource); <br>
 *
 * <br>
 * *******************      end         ******************
 * <br>
 *
 * @author maddox
 * @version 1.0
 * @date 2022/7/26
 **/
public class TreeUtil {

    private static final Logger log = LoggerFactory.getLogger(TreeUtil.class);

    private static final String FLAG_PID = "0";

    /**
     * 树的生成方法
     *
     * @param pid        父节点id
     * @param dataSource 数据源
     * @param treeName   树的名称
     * @param treeLabel  树的标签
     * @return 树状结构
     */
    // @SuppressWarnings("all")
    public static <T extends AbstractTreeNode<T>> TreeVO<T> tree(String pid, String treeName, String treeLabel, Object treeInfo, List<T> dataSource) {
        // 判断数据源
        if (Objects.isNull(dataSource) || dataSource.isEmpty()) {
            log.error("检查输入，数据源为空");
            // TODO 自定义异常
            // throw new Exception();
        }

        // 判断父节点id是否为空或空字符串
        if (checkValNull(pid)) {
            pid = FLAG_PID;
        }

        // 读取数据源类型
        Class<T> type = (Class<T>) dataSource.get(0).getClass();
        ConstructorAccess<T> access = ConstructorAccess.get(type);

        // 初始化树的根节点
        TreeVO<T> treeVO = new TreeVO<>();

        // 初始化树根节点参数
        treeVO.setName(treeName);
        treeVO.setLabel(treeLabel);
        /* 子节点树生成 */
        treeVO.setChildNodes(createTree(pid, dataSource, access));
        return treeVO;
    }

    /**
     * 生成树的内部递归方法
     *
     * @param pid           父节点id
     * @param childDataList 子数据列表
     * @return 子节点列表
     */
    private static <T extends AbstractTreeNode<T>> List<T> createTree(String pid, List<T> childDataList, ConstructorAccess<T> access) {
        List<T> result = new ArrayList<>();
        List<T> childNodeListBuffer = new ArrayList<>(childDataList);
        for (T childNode : childDataList) {
            // 获取节点的id 和 pid
            String idBuffer = childNode.getId();
            String pidBuffer = childNode.getPid();
            if (checkValNull(idBuffer) || checkValNull(pidBuffer)) {
                return null;
            }
            if (pid.equals(pidBuffer)) {
                T t = access.newInstance();
                BeanUtils.copyProperties(childNode, t);
                result.add(t);
                childNodeListBuffer.remove(childNode);
                // 递归查询
                t.setChildNodes(createTree(idBuffer, childNodeListBuffer, access));
            }
        }
        if (result.size() == 0) {
            return null;
        }
        return result;
    }

    /**
     * 字符串判空
     *
     * @param str str
     * @return boolean
     */
    public static boolean checkValNull(String str) {
        return Objects.isNull(str) || str.length() == 0;
    }
}
