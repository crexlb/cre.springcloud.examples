package routing.zull.filter;

import javax.swing.*;

public enum FilterType {
    /**
     * @路由之前
     */
    PRE,
    /**
     * @路由之时
     */
    ROUTING,
    /**
     * @路由之后
     */
    POST,
    /**
     * @发送错误调用
     */
    ERROR;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
