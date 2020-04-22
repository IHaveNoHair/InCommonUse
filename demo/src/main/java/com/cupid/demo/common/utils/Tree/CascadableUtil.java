package com.cupid.demo.common.utils.Tree;

import java.util.List;

public interface CascadableUtil<T> {


    /**
     * 获取唯一id(获取一个元素的唯一标识)
     *
     * @return String
     */
    public String getsId();

    /**
     * 获取父级id(获取一个元素的父级元素id)
     *
     * @return String
     */
    public String getParentId();

    /**
     * 获取所有子节点集合(获取一个元素所有子节点的集合,用于获取集合后向其中补充元素)
     *
     * @return List<T>
     */
    public List<T> getChildren();

    /**
     * 设置所有子节点集合(设置一个元素所有的子元素,主要用于初始化为null的集合)
     *
     * @param children List<T>
     */
    public void setChildren(List<T> children);

}
