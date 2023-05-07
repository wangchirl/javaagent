### 项目介绍

#### 1、抛出问题

- 在基于 JAVA （SpringBoot）开发定时任务时，一般的情况下会使用以下几种进行实现
  - Xxl
  - Quartz
  - Spring 注解 @Scheduled
- 定时任务开发完成后，需要针对定时任务进行测试，一般的测试思路
  1. 写 http 接口对外暴露进行测试，可能需要给每一个定时任务都需要写一个接口
  2. 一般定时任务是基于 Cron 表达式写在配置文件中，通过修改定配合重启服务进行测试

> 以上发现基于上面的测试方式很麻烦且很费时，浪费大量开发和测试时间

#### 2、解决思路

- 分析常见的定时任务实现原理，找到存储定时任务的地方（源码阅读）: 针对 Xxl、Quartz、Spring 注解 @Scheduled 进行源码分析
- 针对不同实现的定时任务暴露2个 http 接口，可对其进行立即执行和CRUD操作（接口定义）:  约定对外暴露 http 接口的入参出参
- 暴露的 http 接口可针对定时任务进行传参测试（可先写死）: 在 controller 中写死逻辑
- 做到代码无侵入（最终目标）: 使用 javaagent 技术进行代理实现

#### 4、实现技术

- javaagent
- Javassist、ASM、ByteBuddy
- Xxl、Spring、Quartz、Simple（基于Spring） 定时任务

#### 5、使用说明

1. git 拉取项目

   ```sh
   git clone git@github.com:wangchirl/javaagent.git
   ```

2. 进入项目使用 maven 打包

   ```sh
   cd javaagent
   mvn clean package -Dmaven.test.skip=true
   ```

3. git 拉取测试项目

   ```sh
   git clone git@github.com:wangchirl/scheduler.git
   ```

4. 进入项目使用 maven 打包

   ```sh
   cd scheduler
   mvn clean package -Dmaven.test.skip=true
   ```

5. 进入测试项目target目录下，执行脚本

   ```sh
   java -javaagent:本地路径(替换为自己的)\javaagent\target\SuperAgent-jar-with-dependencies.jar=ctlClass=com.shadow.controller.AgentBaseController,jobType=spring -jar shadow.jar
   ```

6. 测试定时任务立即执行及CRUD

   ```sh
   # 执行定时任务 [spring2] 为 spring 定时任务的方法名称
   # 立即执行 请求参数为 test Spring
   curl localhost:50084/shadow/api/system/agent/run/spring2?params=test%20Spring
   # 修改 cron 表达式为 0/3 * * * * ?
   curl localhost:50084/shadow/api/system/agent/crud/1/spring1?cron=0/3%20*%20*%20*%20*%20?
   ```

7. 可在本地自行导入 [postman 测试文件](./javaagent+schedule项目POSTMAN测试.json) 进行测试

#### 6、特别说明

> 接口说明：
>
> - 立即执行路径：/agent/run/{taskKey}
> - CRUD路径：/agent/crud/{operation}/{taskKey}
>
> > 动态参数说明:
> >
> > - taskKey（字符串）
> >
> >   定时任务名称
> >
> > - operation （数字）
> >
> >   - 0 删除定时任务
> >   - 1 添加定时任务
> >   - 2 更新定时任务
> >   - 其他数字 查询定时任务
>
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
> - logger ：打印 agent 中的一些提示信息【可选】
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
> > - logger ：打印 agent 中的一些提示信息【可选】

- xxl job 测试

  ```sh
  # 启动项目
  java -javaagent:本地路径(替换为自己的)\javaagent\target\SuperAgent-jar-with-dependencies.jar=ctlClass=com.shadow.controller.AgentBaseController,jobType=xxl -jar shadow.jar
  ```

  ```sh
  # 执行定时任务 [xxl2] 为定时任务JOB NAME
  curl localhost:50084/shadow/api/system/agent/run/xxl2?params=testXxl
  # XXL 只有查询实现，因为其调度器和执行器是分离的
  curl localhost:50084/shadow/api/system/agent/crud/1/1
  ```

  

- quartz job 测试

  ```sh
  # 启动项目
  java -javaagent:本地路径(替换为自己的)\javaagent\target\SuperAgent-jar-with-dependencies.jar=ctlClass=com.shadow.controller.AgentBaseController,jobType=quartz -jar shadow.jar
  ```

  ```sh
  # 执行定时任务 [quartzTriggerKey] 为 Trigger Bean，[quartzJob]为 job detail ID
  curl -H "Content-Type:application/json"  -X POST  --data "{\"name\":\"shadow\",\"age\":20}" localhost:50084/shadow/api/system/agent/run/quartzTriggerKey?params=测试
  # 修改 cron 表达式为 0/3 * * * * ?
  curl localhost:50084/shadow/api/system/agent/crud/1/quartzTriggerKey@quartzJob?cron=0/3%20*%20*%20*%20*%20?
  ```

  > Quartz 定时任务存在特殊性:
  >
  > - 除了指定 taskKey 的 trigger 外，还需要额外指定 job detail 的 ID，quartzTriggerKey@quartzJob
  > - 其参数传递不能使用 ThreadLocal 方式，需要使用其 JobDataMap 的方式

- spring job 测试

  ```sh
  # 启动项目
  java -javaagent:本地路径(替换为自己的)\javaagent\target\SuperAgent-jar-with-dependencies.jar=ctlClass=com.shadow.controller.AgentBaseController,jobType=spring -jar shadow.jar
  ```

  ```sh
  # 执行定时任务 [spring2] 为 spring 定时任务的方法名称
  # 立即执行 请求参数为 test Spring
  curl localhost:50084/shadow/api/system/agent/run/spring2?params=test%20Spring
  # 修改 cron 表达式为 0/3 * * * * ?
  curl localhost:50084/shadow/api/system/agent/crud/1/spring1?cron=0/3%20*%20*%20*%20*%20?
  ```

  

- simple 自定义 job 测试

  ```sh
  # 启动项目
  java -javaagent:本地路径(替换为自己的)\javaagent\target\SuperAgent-jar-with-dependencies.jar=ctlClass=com.shadow.controller.AgentBaseController,jobType=simple -jar shadow.jar
  ```

  ```sh
  # 执行定时任务 [simple1] 为 spring 容器 Bean 名称
  curl localhost:50084/shadow/api/system//agent/run/simple1?params=testSimple
  # 修改 cron 表达式为 0/3 * * * * ?
  curl localhost:50084/shadow/api/system/agent/crud/1/simple2?cron=0/3%20*%20*%20*%20*%20?
  ```


#### 7、最全的 agent 请求参数传递

> ```sh
> java -javaagent:本地路径(替换为自己的)\javaagent\target\SuperAgent-jar-with-dependencies.jar=ctlClass=com.shadow.controller.AgentBaseController,tlClass=com.shadow.supports.framework.ScheduleService,tlFieldName=JOB_PARAMETERS_THREAD_LOCAL,debug=true,logger=true,httpUri=/test/run,methodName=testMethod,iocFieldName=ioc,crudFieldName=xxxx,crud=true,proxyType=asm,jobType=simple -jar shadow.jar
> ```
>
> 说明：
>
> - jobType ：定时任务类型为 XXL
> - proxyType ：asm 表示使用 ASM 代理，可选 buddy 和 javassist
> - crud ：true 表示暴露 CRUD 接口
> - crudFieldName ：crud 需要使用的字段名称，避免和代理类字段冲突使用 xxxx
> - iocFieldName ：spring ioc 的字段名称，避免和代理类中 spring ioc 名称的冲突使用 ioc
> - methodName ：立即执行接口的方法名称，避免和代理类方法名称冲突使用 testMethod
> - httpUri ：立即执行接口的路径，避免和代理类路径冲突使用 /test/run/{taskKey}
> - logger ：true 表示打印代理过程中的提示信息
> - debug ：true 表示生成代理后的 class 文件
> - tlFieldName ：定义的 ThreadLocal 的字段名称 JOB_PARAMETERS_THREAD_LOCAL
> - tlClass ：定义 ThreadLocal 的类名称 com.shadow.supports.framework.ScheduleService
> - ctlClass ：代理的 controller 类 com.shadow.controller.AgentBaseController



#### 8、Attach 机制 - 可动态替换定时任务类型

>  测试类：com.shadow.agent.AttachAgentTest
>
>  - IDEA 测试
>
>    - 加入启动参数
>
>    ```sh
>    # IDEA vm options
>    -javaagent:本地路径(替换为自己的)\javaagent\target\SuperAgent-jar-with-dependencies.jar=jobType=xxl,ctlClass=com.shadow.controller.AgentBaseController,jobType=spring,logger=true
>    ```
>
>    - 执行 com.shadow.agent.AttachAgentTest
>
>    > 注意：originJobType 的值是原始定时任务类型，支持重复操作
>
>  - jar 测试
>
>    - 启动参数
>
>    ```sh
>    java -javaagent:本地路径(替换为自己的)\javaagent\target\SuperAgent-jar-with-dependencies.jar=jobType=xxl,ctlClass=com.shadow.controller.AgentBaseController,jobType=spring,logger=true -jar shadow.jar
>    ```