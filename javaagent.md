## Load-Time

- 在Manifest部分，需要定义`Premain-Class`属性。

  在Agent Class部分，需要定义`premain`方法。下面是`premain`的两种写法：

  ```
  public static void premain(String agentArgs, Instrumentation inst);
  public static void premain(String agentArgs);
  ```

  在运行的时候，需要配置`-javaagent`选项加载Agent Jar：

  ```
  java -cp ./target/classes/ -javaagent:./target/TheAgent.jar sample.Program
  ```

  在运行的过程当中，先执行Agent Class的`premain`方法，再执行Application的`main`方法。

## Dynamic

- 在Manifest部分，需要定义`Agent-Class`属性。

  在Agent Class部分，需要定义`agentmain`方法。下面是`agentmain`的两种写法：

  ```
  public static void agentmain(String agentArgs, Instrumentation inst);
  public static void agentmain(String agentArgs);
  ```

  在运行的时候，需要使用Attach机制加载Agent Jar。

  在运行的过程当中，一般Application的`main`方法已经开始执行，而Agent Class的`agentmain`方法后执行。

- 

- 第一点，Agent Jar的三个组成部分：Manifest、Agent Class和ClassFileTransformer。

- 第二点，对Load-Time Instrumentation和Dynamic Instrumentation有一个初步的理解。

  - Load-Time Instrumentation: `Premain-Class` —> `premain()` —> `-javaagent`
  - Dynamic Instrumentation: `Agent-Class` —> `agentmain()` —> Attach





- 第一点，Attach API位于

  ```plaintext
  com.sun.tools.attach
  ```

  包：

  - 在Java 8版本，`com.sun.tools.attach`包位于`JDK_HOME/lib/tools.jar`文件。
  - 在Java 9版本之后，`com.sun.tools.attach`包位于`jdk.attach`模块（`JDK_HOME/jmods/jdk.attach.jmod`文件）。

- 第二点，在`com.sun.tools.attach`包当中，重要的类有三个：`VirtualMachine`（核心功能）、`VirtualMachineDescriptor`（三个属性）和`AttachProvider`（底层实现）。

- 第三点，使用

  ```plaintext
  VirtualMachine
  ```

  类分成三步：

  - 第一步，与target VM建立连接，获得一个`VirtualMachine`对象。
  - 第二步，使用`VirtualMachine`对象，可以将Agent Jar加载到target VM上，也可以从target VM读取一些属性信息。
  - 第三步，与target VM断开连接。

# Load-Time VS. Dynamic Agent

## 1. 虚拟机数量

 Load-Time Instrumentation只涉及到一个虚拟机

 Dynamic Instrumentation涉及到两个虚拟机 

## 2. 时机不同

在进行Load-Time Instrumentation时，会执行Agent Jar当中的`premain()`方法；`premain()`方法是先于`main()`方法执行，此时Application当中使用的**大多数类还没有被加载**。

在进行Dynamic Instrumentation时，会执行Agent Jar当中的`agentmain()`方法；而`agentmain()`方法是往往是在`main()`方法之后执行，此时Application当中使用的**大多数类已经被加载**。

## 3. 能力不同

Load-Time Instrumentation可以做很多事情：添加和删除字段、添加和删除方法等。

Dynamic Instrumentation做的事情比较有限，大多集中在对于方法体的修改。

## 4. 线程不同

 Load-Time Instrumentation是运行在`main`线程里 

 Dynamic Instrumentation是运行在`Attach Listener`线程里 

## 5. Exception处理

当Load-Time Instrumentation时，出现异常，会报告错误信息，并且停止执行，退出虚拟机。

当Dynamic Instrumentation时，出现异常，会报告错误信息，但是不会停止虚拟机，而是继续执行。