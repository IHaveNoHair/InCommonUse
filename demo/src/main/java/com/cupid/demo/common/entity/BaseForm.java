package com.cupid.demo.common.entity;

/**
 * 抽象表单BaseForm
 * @author cupid
 * @date 2020/04/21
 * @param <T>
 */
public abstract class BaseForm<T> {
    /**
     * 获取实例，
     * @return 返回实体类
     */
    public abstract T buildEntity();
}
