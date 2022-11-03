## 使用依赖
**reflectasm**：反射

## 使用方法

需要自己继承抽象类，补充数据源实体类的其他参数

```java
/**
 * 抽象节点的标志位
 */
abstract class AbstractNodeFlag { String id, pid; }

/**
 * 抽象节点的子节点列表
 * @param <T> 数据源实体类的类型
 */
abstract class AbstractTreeNode<T> extends AbstractNodeFlag { List<T> childNodes; }

/**
 * 数据源实体类
 */
class CustomTreeNode extends AbstractTreeNode<CustomTreeNode> {
    /* 实体类属性 忽略 */
}

class TreeVO<T> extends AbstractTreeNode<T> implements Serializable {
    // 树的信息
    Object treeInfo;
}

class Test {
    public static void main(String[] args) {
        // 完善树的信息 并添加数据源
        Object treeInfo;
        // 父节点id  默认为0
        String pid;
        TreeVO<CustomTreeNode> tree = TreeUtil.tree(pid, "treeName", "treeLabel", treeInfo, dataSource);
    }
}
```
