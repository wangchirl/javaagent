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

public class AsmTransformer extends AbstractTransformer implements ClassFileTransformer {

    private MethodNode runMethodNode;

    private MethodNode crudMethodNode;

    public AsmTransformer(MethodNode runMethodNode, MethodNode crudMethodNode) {
        super();
        this.runMethodNode = runMethodNode;
        this.crudMethodNode = crudMethodNode;
    }

    @Override
    protected boolean handlerMatched(Class<?> handler) {
        return getJobType().name().equalsIgnoreCase(handler.getSimpleName().replace(CommonConstants.ASM_HANDLER_NAME_SUFFIX, ""));
    }

    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {
        if (className.equals(getInnerClassName())) {
            try {
                // 1、read class buffer
                ClassReader cr = new ClassReader(classfileBuffer);
                // 2、class buffer -> class node
                // asm api version
                ClassNode cn = new ClassNode(CommonConstants.ASM_API_VERSION);
                cr.accept(cn, ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);
                // 3、transform
                // 3.1 delete exist method
                cn.methods.removeIf(mn -> mn.name.equals(getArgs().getMethodName()) && mn.desc.equals(BaseConstants.O_SSO));
                cn.methods.removeIf(mn -> mn.name.equals(CommonConstants.DEFAULT_CRUD_METHOD_NAME) && mn.desc.equals(BaseConstants.O_ISS));
                // 3.2 add method
                cn.methods.add(this.runMethodNode);
                cn.methods.add(this.crudMethodNode);
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
