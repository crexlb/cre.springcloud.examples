package routing.getway.models;

import java.util.ArrayList;
import java.util.List;

/**
 * @路由器
 */
public class RouteInfo {
    //路由的Id
    private String id;
    //路由断言集合配置
    private List<PredicateInfo> predicates = new ArrayList<>();
    //路由过滤器集合配置
    private List<FilterInfo> filters = new ArrayList<>();
    //路由规则转发的目标uri
    private String uri;
    //路由执行的顺序
    private int order = 0;
    //此处省略get和set方法

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<PredicateInfo> getPredicates() {
        return predicates;
    }

    public void setPredicates(List<PredicateInfo> predicates) {
        this.predicates = predicates;
    }

    public List<FilterInfo> getFilters() {
        return filters;
    }

    public void setFilters(List<FilterInfo> filters) {
        this.filters = filters;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
