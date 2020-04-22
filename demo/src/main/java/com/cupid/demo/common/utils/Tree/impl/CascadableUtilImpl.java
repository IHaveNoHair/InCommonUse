package com.cupid.demo.common.utils.Tree.impl;
import com.cupid.demo.common.utils.Tree.CascadableUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 树状结构快速排序
 * @param <T>
 */
public class CascadableUtilImpl<T extends CascadableUtil> {
    /**
     * 多根多级
     * @param unCascade 没有层级关系的待级联集合(也不能有层级关系，必须完全平铺)
     * @return List<T> cascade   有层级关系的结果集
     */
    public List<T> formatCascadeTree(List<T> unCascade) {
        if (unCascade == null) {
            return null;
        }
        List<T> cascade = new ArrayList<>();
        for (int i = 0; i < unCascade.size(); ++i) {
            putCellIntoTogether(unCascade.get(i), unCascade, cascade);
        }
        return cascade;
    }
    /**
     * 将元素放入集合中
     * @param cell      单个菜单
     * @param unCascade unCascade 没有层级关系的待级联集合
     * @param cascade   cascade 有层级关系的结果集
     */
    private void putCellIntoTogether(T cell, List<T> unCascade, List<T> cascade) {
        //无父级节点id,根节点
        if (cell.getParentId() == null) {
            cascade.add(cell);
            return;
        }
        //尝试找父级节点,并放入
        for (T cellTemp : unCascade) {
            if (cell.getParentId().equals(cellTemp.getsId()) ) {
                List<T> children = cellTemp.getChildren();
                if (children == null) {
                    children = new ArrayList<>(1);
                    cellTemp.setChildren(children);
                }
                children.add(cell);
                return;
            }
        }
        //剩下的一定是没有父节点的元素,直接加入cascade
        cascade.add(cell);
    }

}
