package routing.getway.base;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

public interface IBuildRouteLocator {
    RouteLocator build(RouteLocatorBuilder builder);
}
