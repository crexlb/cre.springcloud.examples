package routing.getway;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.web.util.UriComponentsBuilder;
import routing.getway.models.FilterInfo;
import routing.getway.models.PredicateInfo;
import routing.getway.models.RouteInfo;

import java.net.URI;
import java.util.*;

public class TestRouting {
    Gson gson = new Gson();

    @Test
    public void testRoute2(){

        RouteInfo definition = new RouteInfo();
        PredicateInfo predicate = new PredicateInfo();
        Map<String, String> predicateParams = new HashMap<>(8);
        definition.setId("jd_route");
        predicate.setName("Path");
        predicateParams.put("pattern", "/jd");
        predicate.setArgs(predicateParams);
        definition.setPredicates(Arrays.asList(predicate));
        String uri="http://www.jd.com";
        //URI uri = UriComponentsBuilder.fromHttpUrl("http://www.baidu.com").build().toUri();
        definition.setUri(uri);
        System.out.println(JSON.toJSONString(definition));

        RouteDefinition definition1 = new RouteDefinition();
        PredicateDefinition predicate1 = new PredicateDefinition();
        Map<String, String> predicateParams1 = new HashMap<>(8);
        definition1.setId("baidu_route");
        predicate1.setName("Path");
        predicateParams1.put("pattern", "/baidu");
        predicate1.setArgs(predicateParams1);
        definition1.setPredicates(Arrays.asList(predicate1));
        URI uri1 = UriComponentsBuilder.fromHttpUrl("http://www.baidu.com").build().toUri();
        definition1.setUri(uri1);
        System.out.println();
        System.out.println(JSON.toJSONString(definition1));
    }
    @Test
    public void testRoute3(){
        RouteDefinition definition2 = new RouteDefinition();
        definition2.setId("api_d");
        definition2.setUri(UriComponentsBuilder.fromHttpUrl("http://localhost:8761").build().toUri());

        FilterDefinition filter2=new FilterDefinition();
        Map<String, String> filterParams2 = new HashMap<>(8);
        filterParams2.put("name","myerror");
        filterParams2.put("fallbackUri","forward:/error");
        filter2.setName("hystrix");
        filter2.setArgs(filterParams2);

        PredicateDefinition predicate2 = new PredicateDefinition();
        Map<String, String> predicateParams2 = new HashMap<>(8);
        predicateParams2.put("pattern", "/api/d/**");
        predicate2.setName("Path");
        predicate2.setArgs(predicateParams2);

        definition2.setPredicates(Arrays.asList(predicate2));
        definition2.setFilters(Arrays.asList(filter2));
        System.out.println();
        System.out.println(JSON.toJSONString(definition2));
    }
    @Test
    public void testRouteInfo() {
        List<FilterInfo> filters = new ArrayList<FilterInfo>();
        List<PredicateInfo> predicates = new ArrayList<PredicateInfo>();
        PredicateInfo pr = new PredicateInfo("path");
        HashMap hm = new HashMap<String, String>();
        hm.put("pattern", "/jd");
        pr.setArgs(hm);
        predicates.add(pr);
        RouteInfo r = new RouteInfo();
        r.setId("jd_route");
        r.setOrder(0);
        r.setUri("http://localhost:8761");
        r.setFilters(filters);
        r.setPredicates(predicates);
        String json = gson.toJson(r);
       System.out.println(json);
    }
}
