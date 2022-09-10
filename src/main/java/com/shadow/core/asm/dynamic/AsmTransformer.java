package com.shadow.core.asm.dynamic;

import com.shadow.core.AbstractTransformer;
import com.shadow.utils.BaseConstants;
import com.shadow.utils.CommonConstants;
import com.shadow.utils.FileUtils;
import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.tree.ClassNode;
import jdk.internal.org.objectweb.asm.tree.MethodNode;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.Map;

public class AsmTransformer extends AbstractTransformer implements ClassFileTransformer {

    private MethodNode methodNode;

    public AsmTransformer(Map<String, String> resolveArgs, MethodNode methodNode) {
        super(resolveArgs);
        this.methodNode = methodNode;
    }

    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {
        if (className.equals(getInnerClassName())) {
            try {
                // 1、读取 class buffer
                ClassReader cr = new ClassReader(classfileBuffer);
                // 2、从 class buffer -> class node
                // 默认 ASM5 ，支持 JDK8版本
                ClassNode cn = new ClassNode(CommonConstants.ASM_API_VERSION);
                cr.accept(cn, ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);
                // 3、transform
                // 3.1 删除方法
                cn.methods.removeIf(mn -> mn.name.equals(getArgs().get(CommonConstants.METHOD_NAME)) && mn.desc.equals(BaseConstants.O_SSO));
                // 3.2 添加方法
                cn.methods.add(this.methodNode);
                // 4、class node -> class writer
                ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
                cn.accept(cw);
                // 5、debug
                if (isDebug()) {
                    FileUtils.writeBytes(getInnerClassName() + CommonConstants.CLASS_SUFFIX, cw.toByteArray());
                }
                // 6、write to bytes
                return cw.toByteArray();
            } catch (Exception e) {
                System.out.println("ASM handle attach " + this.getClass().getSimpleName() + " Job Agent error " + e.getMessage());
                e.printStackTrace();
            }
        }
        return classfileBuffer;
    }
}
