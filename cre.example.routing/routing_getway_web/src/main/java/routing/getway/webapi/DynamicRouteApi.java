package routing.getway.webapi;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.filter.factory.HystrixGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.StripPrefixGatewayFilterFactory;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import routing.getway.models.FilterInfo;
import routing.getway.models.PredicateInfo;
import routing.getway.models.RouteInfo;
import routing.getway.service.impl.DynamicRouteServiceImpl;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @动态路由
 */
@RestController
@RequestMapping("/route")
public class DynamicRouteApi {
    @Autowired
    private DynamicRouteServiceImpl dynamicRouteService;
    //增加路由
    @PostMapping("/add")
    public String add(@RequestBody RouteInfo gwdefinition) {
        System.out.println("增加路由:"+ JSON.toJSONString(gwdefinition));
        RouteDefinition definition = assembleRouteDefinition(gwdefinition);
        this.dynamicRouteService.add(definition);
        return  "ok";
    }
    //删除路由
    @DeleteMapping("/routes/{id}")
    public Mono<ResponseEntity<Object>> delete(@PathVariable String id) {
        dynamicRouteService.delete(id);
        return null;
    }
    //把传递进来的参数转换成路由对象
    private RouteDefinition assembleRouteDefinition(RouteInfo gwdefinition) {

        RouteDefinition definition = new RouteDefinition();

        // ID
        definition.setId(gwdefinition.getId());

        // Predicates
        List<PredicateDefinition> pdList = new ArrayList<>();
        for (PredicateInfo gpDefinition: gwdefinition.getPredicates()) {
            PredicateDefinition predicate = new PredicateDefinition();
            predicate.setArgs(gpDefinition.getArgs());
            predicate.setName(gpDefinition.getName());
            pdList.add(predicate);
        }
        definition.setPredicates(pdList);

        // Filters
        List<FilterDefinition> fdList = new ArrayList<>();
        for (FilterInfo gfDefinition: gwdefinition.getFilters()) {
            FilterDefinition filter = new FilterDefinition();
            //StripPrefix;
           // HystrixGatewayFilterFactory;
            filter.setName(gfDefinition.getName());
            filter.setArgs(gfDefinition.getArgs());
            fdList.add(filter);
        }
        definition.setFilters(fdList);

        // URI
        URI uri = UriComponentsBuilder.fromUriString(gwdefinition.getUri()).build().toUri();
        definition.setUri(uri);

        return definition;
    }
}
