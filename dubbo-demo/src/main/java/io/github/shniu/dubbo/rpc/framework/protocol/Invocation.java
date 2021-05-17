package io.github.shniu.dubbo.rpc.framework.protocol;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author niushaohan
 * @date 2021/5/13 10
 */
public class Invocation implements Serializable {
    private String apiInterface;
    private String methodName;
    private Class<?>[] parameterTypes;
    private Object[] args;

    public Invocation(String apiInterface, String methodName, Class<?>[] parameterTypes, Object[] args) {
        this.apiInterface = apiInterface;
        this.methodName = methodName;
        this.parameterTypes = parameterTypes;
        this.args = args;
    }

    public String getApiInterface() {
        return apiInterface;
    }

    public void setApiInterface(String apiInterface) {
        this.apiInterface = apiInterface;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    @Override
    public String toString() {
        return "Invocation{" +
                "apiInterface='" + apiInterface + '\'' +
                ", methodName='" + methodName + '\'' +
                ", parameterTypes=" + Arrays.toString(parameterTypes) +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
