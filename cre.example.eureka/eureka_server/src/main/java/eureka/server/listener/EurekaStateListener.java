package eureka.server.listener;

import com.netflix.appinfo.InstanceInfo;
import org.springframework.cloud.netflix.eureka.server.event.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 服务注册中心状态监控
 */
@Component
public class EurekaStateListener {

    @EventListener
    public void listen(EurekaInstanceCanceledEvent event) {
        System.out.println("【"+event.getAppName()+"】服务已下线");
    }

    @EventListener
    public void listen(EurekaInstanceRegisteredEvent event) {
        InstanceInfo instanceInfo = event.getInstanceInfo();
        System.out.println("【"+instanceInfo.getAppName()+"】服务进行注册");
    }

    @EventListener
    public void listen(EurekaInstanceRenewedEvent event) {
        System.out.println("【"+event.getAppName()+"】服务进行续约");
    }

    @EventListener
    public void listen(EurekaRegistryAvailableEvent event) {
        System.out.println("注册中心启动，启动时间："+System.currentTimeMillis());
    }

    @EventListener
    public void listen(EurekaServerStartedEvent event) {
        System.out.println("注册中心服务端启动，启动时间："+System.currentTimeMillis());
    }
}
