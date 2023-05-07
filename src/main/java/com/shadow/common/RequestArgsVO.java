package com.shadow.common;

public class RequestArgsVO {

    /**
     * 定时任务类型 -> XXL、QUARTZ、SPRING、SIMPLE【必须】
     */
    private String jobType;

    /**
     * 暴露 HTTP 请求的 controller 全限类名【必须】
     */
    private String ctlClass;

    /**
     * 注入 controller 的 Spring IOC 容器的字段名称，默认 $$$springIoc$$$【可选】
     */
    private String iocFieldName;

    /**
     * 支持 ThreadLocal 参数传递的全限类名【可选】
     */
    private String tlClass;

    /**
     * ThreadLocal 所在类定义的字段名，tlClass 存在时生效【可选】
     */
    private String tlFieldName;

    /**
     * 暴露 HTTP 请求的方法的名称，有默认值【可选】
     */
    private String methodName;

    /**
     * 暴露 HTTP 请求的方法体，已提供以上四种定时任务实现方式【可选】
     */
    private String methodBody;

    /**
     * 暴露 CRUD HTTP 请求的方法体，已提供以上四种定时任务实现方式【可选】
     */
    private String crudMethodBody;

    /**
     * 暴露 HTTP 请求的 URI 前缀，会拼接上 /{taskKey} ，默认 /agent/run【可选】
     */
    private String httpUri;

    /**
     * 是否 debug 模式，次模式方便调试，可生成对应的增强的 controller 类信息文件【可选】
     */
    private Boolean debug;

    /**
     * 打印 agent 中的一些提示信息【可选】
     */
    private Boolean logger;

    /**
     * 代理方式：javassist、ASM、byteBuddy，默认 javassist【可选】
     */
    private String proxyType;

    /**
     * 是否开启 CRUD 操作，默认开启【可选】
     */
    private Boolean crud;

    /**
     * simple job crud【可选】
     */
    private String crudFieldName;

    /**
     * 原 job type 【loadTime无效，只针对 dynamicTime必须】
     */
    private String originJobType;

    public String getOriginJobType() {
        return originJobType;
    }

    public void setOriginJobType(String originJobType) {
        this.originJobType = originJobType;
    }

    public String getCrudFieldName() {
        return crudFieldName;
    }

    public void setCrudFieldName(String crudFieldName) {
        this.crudFieldName = crudFieldName;
    }

    public Boolean getDebug() {
        return debug;
    }

    public void setDebug(Boolean debug) {
        this.debug = debug;
    }

    public Boolean getLogger() {
        return logger;
    }

    public void setLogger(Boolean logger) {
        this.logger = logger;
    }

    public Boolean getCrud() {
        return crud;
    }

    public void setCrud(Boolean crud) {
        this.crud = crud;
    }

    public String getProxyType() {
        return proxyType;
    }

    public void setProxyType(String proxyType) {
        this.proxyType = proxyType;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getCtlClass() {
        return ctlClass;
    }

    public void setCtlClass(String ctlClass) {
        this.ctlClass = ctlClass;
    }

    public String getIocFieldName() {
        return iocFieldName;
    }

    public void setIocFieldName(String iocFieldName) {
        this.iocFieldName = iocFieldName;
    }

    public String getTlClass() {
        return tlClass;
    }

    public void setTlClass(String tlClass) {
        this.tlClass = tlClass;
    }

    public String getTlFieldName() {
        return tlFieldName;
    }

    public void setTlFieldName(String tlFieldName) {
        this.tlFieldName = tlFieldName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodBody() {
        return methodBody;
    }

    public void setMethodBody(String methodBody) {
        this.methodBody = methodBody;
    }

    public String getCrudMethodBody() {
        return crudMethodBody;
    }

    public void setCrudMethodBody(String crudMethodBody) {
        this.crudMethodBody = crudMethodBody;
    }

    public String getHttpUri() {
        return httpUri;
    }

    public void setHttpUri(String httpUri) {
        this.httpUri = httpUri;
    }

}
