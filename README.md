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
- 源码以开源，可根据自己需求进行改造，比如加入请求参数的处理
- 后续版本将使用 ASM 来对项目进行改造
- 目前只实现了 load time 的实现方式
- attach 方式将在后续进行开发

#### 3、针对测试仓库进行测试的相关说明

- xxl job 测试

  ```sh
  # 启动项目
  -javaagent:F:\source\javaagent\target\SuperAgent-jar-with-dependencies.jar=XXL,com.shadow.controller.AgentBaseController
  ```

  ```sh
  # 执行定时任务 [xxl2] 为定时任务JOB NAME
  localhost:50084/shadow/api/system//agent/run/xxl2
  ```

  

- quartz job 测试

  ```sh
  # 启动项目
  -javaagent:F:\source\javaagent\target\SuperAgent-jar-with-dependencies.jar=QUARTZ,com.shadow.controller.AgentBaseController
  ```

  ```sh
  # 执行定时任务 [quartzTriggerKey] 为 Trigger Bean，[quartzJob]为 job detail ID
  localhost:50084/shadow/api/system//agent/run/quartzTriggerKey?params=quartzJob
  ```

  

- spring job 测试

  ```sh
  # 启动项目
  -javaagent:F:\source\javaagent\target\SuperAgent-jar-with-dependencies.jar=SPRING,com.shadow.controller.AgentBaseController
  ```

  ```sh
  # 执行定时任务 [spring2] 为 spring 定时任务的方法名称
  localhost:50084/shadow/api/system//agent/run/spring2
  ```

  

- simple 自定义 job 测试

  ```sh
  # 启动项目
  -javaagent:F:\source\javaagent\target\SuperAgent-jar-with-dependencies.jar=SIMPLE,com.shadow.controller.AgentBaseController
  ```

  ```sh
  # 执行定时任务 [simple1] 为 spring 容器 Bean 名称
  localhost:50084/shadow/api/system//agent/run/simple1
  ```

  