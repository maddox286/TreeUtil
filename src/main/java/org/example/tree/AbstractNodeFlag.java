package org.example.tree;

/**
 * 抽象节点标志位，定义节点需要的标志位
 *
 * @author maddox
 */
public abstract class AbstractNodeFlag {
    String id, pid;

    public AbstractNodeFlag() {
    }

    public AbstractNodeFlag(String id, String pid) {
        this.id = id;
        this.pid = pid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AbstractNodeFlag{" +
                "id='" + id + '\'' +
                ", pid='" + pid + '\'' +
                '}';
    }
}