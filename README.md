### javaagent 技术

#### 1、技术点

- javaagent
- javassist
- xxl job
- quartz job
- spring job
- simple 自定义 job

#### 2、说明

- 定时任务开发完成后，如果要进行测试时，需要修改定时任务时间，不能立即执行，测试不方便
- 项目针对以上问题做了针对以上定时任务实现方式的代理实现，利用 javaagent 技术，无侵入
- 此项目可直接打包为 jar 包
- 可结合测试仓库进行测试，测试仓库地址 ：https://github.com/wangchirl/scheduler.git
- 源码以开源，可根据自己需求进行改造
- 后续版本将使用 ASM 、Byte buddy 来对项目进行改造
- 目前实现了 load time 、dynamic 的 javassist 实现方式，其他方式将在后续进行实现

#### 3、针对测试仓库进行测试的相关说明

> 参数说明：
>
> ##### Load time 参数：
>
> - jobType ：定时任务类型 -> XXL、QUARTZ、SPRING、SIMPLE【必须】
> - ctlClass ：暴露 HTTP 请求的 controller 全限类名【必须】
> - iocFieldName ：注入 controller 的 Spring IOC 容器的字段名称，默认 $$$springIoc$$$【可选】
> - tlClass ：支持 ThreadLocal 参数传递的全限类名【可选】
> - tlFieldName ：ThreadLocal 所在类定义的字段名，tlClass 存在时生效【可选】
> - methodName ：暴露 HTTP 请求的方法的名称，有默认值【可选】
> - methodBody ：暴露 HTTP 请求的方法体，已提供以上四种定时任务实现方式【可选】
> - httpUri ：暴露 HTTP 请求的 URI 前缀，会拼接上 /{taskKey} ，默认 /agent/run【可选】
> - debug ：是否 debug 模式，次模式方便调试，可生成对应的增强的 controller 类信息文件【可选】
>
> ##### Dynamic time 参数：
>
> - originJobType ：原定时任务类型 -> XXL、QUARTZ、SPRING、SIMPLE【必须】
>
> > <font color=red>特别说明：除 httpUri 参数外，其他参数必须和 Load time 的保持一致</font>
> >
> > - jobType ：定时任务类型 -> XXL、QUARTZ、SPRING、SIMPLE【必须】
> > - ctlClass ：暴露 HTTP 请求的 controller 全限类名【必须】
> > - iocFieldName ：注入 controller 的 Spring IOC 容器的字段名称，默认 $$$springIoc$$$【可选】
> > - tlClass ：支持 ThreadLocal 参数传递的全限类名【可选】
> > - tlFieldName ：ThreadLocal 所在类定义的字段名，tlClass 存在时生效【可选】
> > - methodName ：暴露 HTTP 请求的方法的名称，有默认值【可选】
> > - methodBody ：暴露 HTTP 请求的方法体，已提供以上四种定时任务实现方式【可选】
> > - debug ：是否 debug 模式，此模式方便调试，可生成对应的增强的 controller 类信息文件【可选】

- xxl job 测试

  ```sh
  # 启动项目
  -javaagent:F:\source\javaagent\target\SuperAgent-jar-with-dependencies.jar=jobType=XXL&ctlClass=com.shadow.controller.AgentBaseController&debug=true&tlClass=com.shadow.supports.framework.ScheduleService&tlFieldName=JOB_PARAMETERS_THREAD_LOCAL
  ```

  ```sh
  # 执行定时任务 [xxl2] 为定时任务JOB NAME
  localhost:50084/shadow/api/system/agent/run/xxl2?params=王钦测试Xxl
  ```

  

- quartz job 测试

  ```sh
  # 启动项目
  -javaagent:F:\source\javaagent\target\SuperAgent-jar-with-dependencies.jar=jobType=QUARTZ&ctlClass=com.shadow.controller.AgentBaseController&debug=true&tlClass=com.shadow.supports.framework.ScheduleService&tlFieldName=JOB_PARAMETERS_THREAD_LOCAL
  ```

  ```sh
  # 执行定时任务 [quartzTriggerKey] 为 Trigger Bean，[quartzJob]为 job detail ID
  localhost:50084/shadow/api/system/agent/run/quartzTriggerKey?params=quartzJob
  ```

  > Quartz 定时任务存在特殊性:
  >
  > - 除了指定 taskKey 的 trigger 外，还需要额外指定 job detail 的 ID，此处使用了 params 请求参数来传递 job detail 的值
  > - 其参数传递不能使用 ThreadLocal 方式，需要使用其 JobDataMap 的方式

- spring job 测试

  ```sh
  # 启动项目
  -javaagent:F:\source\javaagent\target\SuperAgent-jar-with-dependencies.jar=jobType=SPRING&ctlClass=com.shadow.controller.AgentBaseController&debug=true&tlClass=com.shadow.supports.framework.ScheduleService&tlFieldName=JOB_PARAMETERS_THREAD_LOCAL
  ```

  ```sh
  # 执行定时任务 [spring2] 为 spring 定时任务的方法名称
  localhost:50084/shadow/api/system//agent/run/spring2?params=王钦测试Spring
  ```

  

- simple 自定义 job 测试

  ```sh
  # 启动项目
  -javaagent:F:\source\javaagent\target\SuperAgent-jar-with-dependencies.jar=jobType=SIMPLE&ctlClass=com.shadow.controller.AgentBaseController&debug=true&tlClass=com.shadow.supports.framework.ScheduleService&tlFieldName=JOB_PARAMETERS_THREAD_LOCAL
  ```

  ```sh
  # 执行定时任务 [simple1] 为 spring 容器 Bean 名称
  localhost:50084/shadow/api/system//agent/run/simple1?王钦测试Simple
  ```


#### 4、最全的 agent 请求参数传递

> ```sh
> -javaagent:F:\source\javaagent\target\SuperAgent-jar-with-dependencies.jar=jobType=XXL&ctlClass=com.shadow.controller.AgentBaseController&tlClass=com.shadow.supports.framework.ScheduleService&tlFieldName=JOB_PARAMETERS_THREAD_LOCAL&methodName=testMethod&iocFieldName=ioc&httpUri=/test/run&debug=true
> ```
>
> 说明：
>
> - 定时任务类型为 XXL
> - 暴露 HTTP 的 controller 是 com.shadow.controller.AgentBaseController
> - ThreadLocal 参数传递的类是 com.shadow.supports.framework.ScheduleService
> - ThreadLocal 对象的字段名称是 JOB_PARAMETERS_THREAD_LOCAL
> - 暴露 HTTP 接口方法名称是 testMethod
> - 注入到 controller 类中的 Spring IOC 容器字段名称是 ioc
> - HTTP 请求路径是 当前 controller 的 path + /test/run/{taskKey}
> - debug 是 true，会在当前位置生成所增强的 controller 的字节码文件

#### 5、postman 相关测试在同目录下的 `javaagent+schedule项目POSTMAN测试.json`



#### 6、Attach 机制 - 可动态替换定时任务类型

>  测试类：com.shadow.agent.AttachAgentTest
>
> ```sh
> originJobType=xxl&jobType=simple&ctlClass=com.shadow.controller.AgentBaseController&methodName=testMethod&iocFieldName=ioc&tlClass=com.shadow.supports.framework.ScheduleService&tlFieldName=JOB_PARAMETERS_THREAD_LOCAL&debug=true
> ```