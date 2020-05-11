package routing.getway.service;


import routing.getway.models.RouteInfo;

import java.util.List;

/**
 * @动态路由服务
 */
public interface IDynamicRouteService {
    /**
     * @保存路由
     */
    void save(RouteInfo route);
    /**
     * @删除路由
     */
    void delete(String id);
    /**
     * @获取路由
     */
    RouteInfo get(String id);
    /**
     * @保存路由
     */
    List<RouteInfo> list();
}
