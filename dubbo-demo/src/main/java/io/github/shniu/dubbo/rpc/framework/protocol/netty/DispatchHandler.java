package io.github.shniu.dubbo.rpc.framework.protocol.netty;

import io.github.shniu.dubbo.rpc.framework.protocol.Invocation;
import io.github.shniu.dubbo.rpc.framework.register.LocalRegister;
import io.netty.channel.ChannelHandlerContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author niushaohan
 * @date 2021/5/13 10
 */
public class DispatchHandler implements WorkerHandler {

    @Override
    public void handle(ChannelHandlerContext ctx, Invocation invocation) {
        Object obj = LocalRegister.get(invocation.getApiInterface());
        try {
            Method method = obj.getClass().getMethod(invocation.getMethodName(), invocation.getParameterTypes());
            Object result = method.invoke(obj, invocation.getArgs());
            System.out.println("invoke result is " + result);

            ctx.channel().writeAndFlush(result);
            System.out.println("Write finished.");
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
