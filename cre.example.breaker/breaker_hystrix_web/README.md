   ## @HystrixCommand：参数说明
   public @interface HystrixCommand {

            // HystrixCommand 命令所属的组的名称：默认注解方法类的名称
            String groupKey() default "";

            // HystrixCommand 命令的key值，默认值为注解方法的名称
            String commandKey() default "";

            // 线程池名称，默认定义为groupKey
            String threadPoolKey() default "";
            // 定义回退方法的名称, 此方法必须和hystrix的执行方法在相同类中
            String fallbackMethod() default "";
            // 配置hystrix命令的参数
            HystrixProperty[] commandProperties() default {};
            // 配置hystrix依赖的线程池的参数
             HystrixProperty[] threadPoolProperties() default {};

            // 如果hystrix方法抛出的异常包括RUNTIME_EXCEPTION，则会被封装HystrixRuntimeException异常。我们也可以通过此方法定义哪些需要忽略的异常
            Class<? extends Throwable>[] ignoreExceptions() default {};

            // 定义执行hystrix observable的命令的模式，类型详细见ObservableExecutionMode
            ObservableExecutionMode observableExecutionMode() default ObservableExecutionMode.EAGER;

            // 如果hystrix方法抛出的异常包括RUNTIME_EXCEPTION，则会被封装HystrixRuntimeException异常。此方法定义需要抛出的异常
            HystrixException[] raiseHystrixExceptions() default {};

            // 定义回调方法：但是defaultFallback不能传入参数，返回参数和hystrix的命令兼容
            String defaultFallback() default "";
        }