### 一、[JAR文件打包程序](https://docs.oracle.com/javase/tutorial/deployment/jar/build.html)

#### 1、使用 jar 命令基础知识

> ```sh
> -c  创建新档案
> -t  列出档案目录
> -x  从档案中提取指定的 (或所有) 文件
> -u  更新现有档案
> -v  在标准输出中生成详细输出
> -f  指定档案文件名
> -m  包含指定清单文件中的清单信息，指定 manifest 文件
> -n  创建新档案后执行 Pack200 规范化
> -e  为捆绑到可执行 jar 文件的独立应用程序
> 指定应用程序入口点
> -0  仅存储; 不使用任何 ZIP 压缩，不对文件进行压缩处理
> -P  保留文件名中的前导 '/' (绝对路径) 和 ".." (父目录) 组件
> -M  不创建条目的清单文件
> -i  为指定的 jar 文件生成索引信息 “jar -i App.jar”
> -C  更改为指定的目录并包含以下文件 "-C images ." 提取 images 目录的文件到当前位置
> ```

##### 1.1 jar 文件格式

> jar 文件是以 zip 文件格式打包的文件

##### 1.2 jar tool 常见命令

> | 操作                            | 命令                               |
> | ------------------------------- | ---------------------------------- |
> | 创建 jar 文件                   | jar cf jar-file input-file(s)      |
> | 查看 jar 文件内容               | jar tf jar-file                    |
> | 提取 jar 文件内容               | jar xf jar-file                    |
> | 提取 jar 文件中特定文件         | jar xf jar-file [archived-file(s)] |
> | 更新 jar 文件                   | jar uf jar-file input-file(s)      |
> | 执行 jar 文件（需要Main-Class） | java -jar jar-file                 |

- 创建 jar 文件

  jar cf jar-file input-file(s)

> | 参数          | 说明                                                         |
> | ------------- | ------------------------------------------------------------ |
> | c             | create 表示要创建一个 jar 文件                               |
> | f             | file 表示输出转文件，默认是 stdout                           |
> | jar-file      | 要生成的 jar 文件的名称，一般以 .jar 扩展名结尾              |
> | input-file(s) | 需要打包到 jar 文件中的内容，以空格分割的一个或多个文件列表<br />可以是目录，可以使用通配符 * 表示当前目录的所有文件 |
>
> 注意：c 和 f 选项可以任意位置，但不能有空格，该命令为 jar 文件生成一个默认清单文件，jar 文件中的元数据必须以 UTF8 编码
>
> 扩展参数：
>
> | 参数    | 说明                                                         |
> | ------- | ------------------------------------------------------------ |
> | v       | verbose 构建 jar 文件时在 stdout 输出详细信息，例：jar cvf App.jar * |
> | 0(zero) | 表示不希望压缩 jar 文件，例：jar cf0 App.jar *               |
> | M       | 表示不生成默认清单文件(META-INF/MANIFEST.MF)，例：jar cfM App.jar * |
> | m       | 使用指定清单文件中的清单信息，格式为 jar cmf jar-file manifest-file input-file(s)<br />注意：manifest 文件必须以换行符或回车符结尾，例：jar cfm App.jar manifest.text * |
> | i       | 创建 jar 文件的索引，例：jar i App.jar ，META-INF目录下生成 INDEX.LIST 文件 |
> | e       | entrypoint 入口点，创建或覆盖Main-Class清单文件中的属性值，该 e 选项可以创建（c）或更新（u） jar 文件时使用，例：jar cfe App.jar com.Application com/Application.class |
> | -C      | 更改目录 jar cf jar-file -C folder . 。将 folder 目录下的所有文件（.）打包到当前位置，例：jar cf App.jar -C folder . ，可将 . 替换为指定的文件名，则表示将指定文件打包到当前目录 |
> | -J      | 设置 JRE 运行 jar 文件时需要使用的指定 JVM 选项，功能类比 java -jar -Xmx等参数，例：jar cf App.jar -J -Xmx48m -J-Xms48m |

- 查看 jar 文件内容

  jar tf jar-file

> | 参数     | 说明                                                    |
> | -------- | ------------------------------------------------------- |
> | t        | 表示要查看 jar 文件的目录                               |
> | f        | 指定要查看的 jar 文件                                   |
> | jar-file | jar 文件的路径和名称                                    |
> | v        | 查看 jar 文件的详细信息（文件大小和修改日期等附加信息） |
>
> 此命令将 jar 文件的目录显示到 stdout

- 提取 jar 文件内容 & 提取指定内容

  jar xf jar-file & jar xf jar-file [archived-file(s)]

> | 参数             | 说明                                                         |
> | ---------------- | ------------------------------------------------------------ |
> | x                | 表示从 jar 文件提取文件                                      |
> | f                | 指定要提取的是 jar 文件                                      |
> | jar-file         | jar 文件的路径和名称                                         |
> | archived-file(s) | 是一个可选参数，从存档文件中提取文件列表，空格分割，文件不存在时不会做任何处理，当此参数不存在时将提取所有文件 |
>
> 注意：当提取文件时，将覆盖与提取文件相同路径名称的任何现有文件

- 更新 jar 文件

  jar uf jar-file input-file(s)

> | 参数          | 说明                                              |
> | ------------- | ------------------------------------------------- |
> | u             | update 表示更新现有 jar 文件                      |
> | f             | 指定要更新的是 jar 文件                           |
> | jar-file      | 要更新的 jar 文件路径和名称                       |
> | input-file(s) | 要添加到 jar 文件中的一个或多个文件，空格分割列表 |
>
> 注意：存档中已有的与添加的文件具有相同路径名称的任何文件都将被覆盖
>
> 可以使用 -C 参数来改变文件的目录

- 执行 jar 文件（需要 Main-Class）

  java -jar jar-file

> 注意：要指示哪个类是应用程序的入口点，即清单文件 manifest中的 Main-Class指定程序的入口全限定类名

##### 1.3 命令选项

> - 创建 jar 文件
>
>   ```sh
>   jar c[efmMnv0] [entrypoint] [jarfile] [manifest] [-C dir] file... [-J options...] [@arg-file...]
>   ```
>
> - 更新 jar 文件
>
>   ```sh
>   jar u[efmMnv0] [entrypoint] [jarfile] [manifest] [-C dir] file... [-J options...] [@arg-file...]
>   ```
>
> - 提取 jar 文件
>
>   ```sh
>   jar x[vf] [jarfile] file... [-J options...] [@arg-file...]
>   ```
>
> - 查看 jar 文件
>
>   ```sh
>   jar t[vf] [jarfile] file... [-J options...] [@arg-file...]
>   ```
>
> - 给 jar 文件添加索引
>
>   ```sh
>   jar i jarfile [-J options...] [@arg-file...]
>   ```

#### 2、[使用清单文件基础知识](https://docs.oracle.com/javase/tutorial/deployment/jar/manifestindex.html)

> 创建 jar 文件时，会自动创建默认的清单，jar 文件的清单MANIFEST 使得 jar 文件支持广泛的功能，包括电子签名、版本控制、封装密封等，清单是一个特殊文件，可以包含有关打包在 jar 文件中的文件信息

- 默认清单文件

  创建 jar 文件时，自动生成默认的清单文件 META-INF/MANIFEST.MF，清单文件内容如下：

  ```tex
  Manifest-Version: 1.0
  Created-By: 1.8.0_191 (Oracle Corporation)
  
  ```

  > Manifest-Version: 清单版本
  >
  > Created-By：创建者

- 修改清单文件

  在创建 jar 文件期间，可以使用 m 命令行选项将自定义信息添加到清单中，需要准备一个清单文件，其中包含你需要添加到清单中的信息，然后使用 jar 工具的 m 选项将文件中的信息添加到清单中，命令如下：

  ```sh
  jar cfm jar-file manifest-addition input-file(s)
  ```

  > 警告：创建清单的文本文件必须以换行符或回车符结尾
  >
  > 注意：m 和 f 选项必须与相应参数的顺序相同

- 设置程序入口点

  如果要使用 jar 方式启动应用程序，则需要指定哪个类是应用程序的入口点，使用清单中的 Main-Class 来指定应用程序入口类，格式如下：

  ```sh
  Main-Class: com.shadow.Application
  ```

  启动应用程序命令如下：这样 Main-Class 指定的 main 方法将会得到执行

  ```sh
  java -jar JAR-Name
  ```

  两种途径添加 Main-Class 到清单文件

  - 创建文本文件方式

    如：manifest.txt，输入以下内容，以换行符结尾

    ```sh
    Main-Class: com.shadow.Application
    
    ```

    执行打包命令

    ```sh
    jar cfm App.jar manifest.txt com/*
    ```

    jar 文件中的 META-INF/MANIFEST.MF中的文件内容如下：

    ```sh
    Manifest-Version: 1.0
    Created-By: 1.8.0_191 (Oracle Corporation)
    Main-Class: com.shadow.Application
    
    ```

    执行 jar 文件

    ```sh
    java -jar App.jar
    ```

  - 命令行参数方式

    e 选项指定 Main-Class

    ```sh
    jar cfe App.jar com.shadow.Application com/shadow/Application.class
    ```

- 将类添加到 jar 文件的类路径

  需要在 jar 文件中引入其他 jar 文件中的类，可以在清单文件中通过指定 Class-Path 来指定引用的 jar 文件，格式如下：

  ```sh
  Class-Path: jar1-name jar2-name directory/jar3-name
  ```

  通过在清单文件中指定依赖的 jar 文件，可以避免在启动应用时指定 -classpath 参数（-Djava.ext.dirs="."）

  注意：Class-Path 标头指向本地网络上的类或 jar 文件，而不是 jar 文件中的 jar 文件或通过 internet 协议访问的类

  例如：在 App.jar 中使用了 Putil.jar 中的类

  - 编写清单文件 manifest.txt

    ```sh
    Main-Class: com.shadow.Application
    Class-Path: Putil.jar
    ```

  - 执行命令

    ```sh
    jar cfm App.jar manifest.txt com/*
    ```

    打包后 jar 文件清单内容如下：

    ```tex
    Manifest-Version: 1.0
    Created-By: 1.8.0_191 (Oracle Corporation)
    Main-Class: com.shadow.Application
    Class-Path: Putil.jar
    
    ```

  - 测试案例

    - com.shadow.Application

      ```java
      package com.shadow;
      import com.helper.Putils;
      
      public class Application {
          public static void main(String[] args) {
              Putils.print(args);
          }
      }
      ```

    - com.helper.Putils

      ```java
      package com.helper;
      
      public class Putils {
          public static void print(String[] args) {
              System.out.println("Putls -> print : ");
              for (String arg : args) {
                  System.out.println(arg);
              }
          }
      }
      ```

    - 打包 com.helper.Putils 到 Putil.jar

      ```sh
      jar cf Putil.jar com/helper/*.class
      ```

    - 打包 com.shadow.Application 到 App.jar [添加 Class-Path]

      清单文件 manifest.txt

      ```sh
      Main-Class: com.shadow.Application
      Class-Path: Putil.jar
      
      ```

      打包命令

      ```sh
      jar cfm App.jar manifest.txt com/shadow/*.class
      ```

    - 运行

      ```sh
      java -jar App.jar # 成功运行
      ```

    可以自行测试不加入 Class-Path ，运行 jar 包时肯定出错

- 设置包版本信息

  可以在 jar 文件的清单中包含版本信息，可使用以下标头信息：

  | header                 | 说明             |
  | ---------------------- | ---------------- |
  | Name                   | 规范的名称       |
  | Specification-Title    | 规范的标题       |
  | Specification-Version  | 规范的版本       |
  | Specification-Vendor   | 规范的供应商     |
  | Implementation-Title   | 实现的标题       |
  | Implementation-Version | 实现的内部版本号 |
  | Implementation-Vendor  | 实现的供应商     |

- jar 文件密封

  该包中定义的所有类都必须归档在同一个 jar 文件中，以确保软件中类之间的版本一致性，在清单文件中设置如下内容：指定由该 jar 定义的所有包都是密封的，除非在每个包基础上被覆盖

  ```sh
  Sealed: true
  ```

  可在 jar 文件中指定密封的包，如下表示密封两个包

  ```sh
  Name: com/shadow/
  Sealed: true
  Name: com/helper/
  Sealed: true
  ```

#### 3、使用 jar 包相关的 API

##### 3.1 JarRunner 测试类

```java
package com.helper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class JarRunner {

    public static void main(String[] args) {
        if (args.length < 1) {
            usage();
        }
        URL url = null;
        try {
            url = new URL(args[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        JarClassLoader loader = new JarClassLoader(url);
        String name = null;
        try {
            name = loader.getMainClassName();
        } catch (IOException e) {
            fatal("error while loading JAR file");
            e.printStackTrace();
        }
        if (name == null) {
            fatal("Specified jar file does not contain a 'Main-Class' manifest attribute");
        }
        String[] newArgs = new String[args.length - 1];
        System.arraycopy(args, 1, newArgs, 0, newArgs.length);

        try {
            loader.invokeClass(name, newArgs);
        } catch (ClassNotFoundException e) {
            fatal("Class Not Found");
        } catch (NoSuchMethodException e) {
            fatal("Class does not define a 'main' method : " + name);
        } catch (Exception e) {
            fatal(e.getMessage());
            e.printStackTrace();
        }
    }

    private static void fatal(String info) {
        System.out.println(info);
        System.exit(1);
    }

    private static void usage() {
        fatal("Usage : java JarRunner url [args...]");
    }
}
```

##### 3.2 JarClassLoader

```java
package com.helper;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.*;
import java.util.jar.Attributes;

public class JarClassLoader extends URLClassLoader {

    private URL url;

    public JarClassLoader(URL url) {
        super(new URL[]{url});
        this.url = url;
    }

    public String getMainClassName() throws IOException {
        URL u = new URL("jar", "", this.url + "!/");
        System.out.println("Test URL => " + u);
        JarURLConnection urlConnection = (JarURLConnection) u.openConnection();
        Attributes mainAttributes = urlConnection.getMainAttributes();
        return mainAttributes != null ? mainAttributes.getValue(Attributes.Name.MAIN_CLASS) : null;
    }

    public void invokeClass(String name, String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> aClass = loadClass(name);
        Method main = aClass.getMethod("main", new Class[]{args.getClass()});
        main.setAccessible(true);
        int modifiers = main.getModifiers();
        if(main.getReturnType() != Void.class || !Modifier.isStatic(modifiers) ||
                !Modifier.isPublic(modifiers)) {
            throw new NoSuchMethodException("main");
        }
        main.invoke(null, new Object[]{args});
    }
}
```

##### 3.3 底层支持类 API -> java.util.jar.*

> - java.net.JarURLConnection
>
> - java.util.jar.JarFile
>
> - java.util.jar.Manifest -> 负责解析清单文件 META-INF/MANIFEST.MF 的内容
>
> - java.util.jar.Attributes
>
> - java.util.jar.Attributes.NAME -> 可在清单文件中定义的标头信息在这个类中体现
>
>   ```java
>   public static final Name MANIFEST_VERSION = new Name("Manifest-Version");
>   
>   public static final Name SIGNATURE_VERSION = new Name("Signature-Version");
>   
>   public static final Name CONTENT_TYPE = new Name("Content-Type");
>   
>   public static final Name CLASS_PATH = new Name("Class-Path");
>   
>   public static final Name MAIN_CLASS = new Name("Main-Class");
>   
>   public static final Name SEALED = new Name("Sealed");
>   
>   public static final Name EXTENSION_LIST = new Name("Extension-List");
>   
>   public static final Name EXTENSION_NAME = new Name("Extension-Name");
>   
>   @Deprecated
>   public static final Name EXTENSION_INSTALLATION = new Name("Extension-Installation");
>   
>   public static final Name IMPLEMENTATION_TITLE = new Name("Implementation-Title");
>   
>   public static final Name IMPLEMENTATION_VERSION = new Name("Implementation-Version");
>   
>   public static final Name IMPLEMENTATION_VENDOR = new Name("Implementation-Vendor");
>   
>   @Deprecated
>   public static final Name IMPLEMENTATION_VENDOR_ID = new Name("Implementation-Vendor-Id");
>   
>   @Deprecated
>   public static final Name IMPLEMENTATION_URL = new Name("Implementation-URL");
>   
>   public static final Name SPECIFICATION_TITLE = new Name("Specification-Title");
>   
>   public static final Name SPECIFICATION_VERSION = new Name("Specification-Version");
>   
>   public static final Name SPECIFICATION_VENDOR = new Name("Specification-Vendor");
>   ```

##### 3.4 测试

> - 编译 JarRunner 和 JarClassLoader
>
>   ```sh
>   javac JarRunner
>   javac JarClassLoader
>   ```
>
> - 搭建本地 Kiftd 文件系统 -> 青阳网络文件服务
>
> - 上传打包好的 App.jar 文件
>
> - 下载 -> 获取链接
>
> - 命令测试
>
>   ```sh
>   java JarRunner http://localhost:8080/.... 测试
>   ```
>
>   































 

























































