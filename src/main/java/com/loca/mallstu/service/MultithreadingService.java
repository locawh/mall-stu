package com.loca.mallstu.service;

import com.loca.mallstu.bean.dto.BatchOperateResultDTO;
import com.loca.mallstu.bean.po.UserPO;
import com.loca.mallstu.common.CommonResult;

import java.util.List;

/**
 * @author wangHeng
 * @date  2021-04-16 14:52
 */
public interface MultithreadingService {

    /**
     * <h2>通过实现Callable接口方式多线程处理(有返回值)</h2>
     * 虽然使用了多线程，但由于Future.get()方法是阻塞方法，同时又在for循环中获取，所以每次线程池内只有一个线程执行。相比不使用线程，只算是异步执行
     *
     * @param users
     * @return com.loca.mallstu.common.CommonResult<java.util.List < com.loca.mallstu.bean.dto.BatchOperateResultDTO>>
     * @author wangheng
     * @date 2021/4/16 11:23
     */
    CommonResult<List<BatchOperateResultDTO>> createUserBatchCallable(List<UserPO> users);

    /**
     * 使用JDK8新接口CompletableFuture，支持多个任务同时执行，支持源生API，支持异常处理
     *
     * @param users
     * @return com.loca.mallstu.common.CommonResult<java.util.List < com.loca.mallstu.bean.dto.BatchOperateResultDTO>>
     * @author wangheng
     * @date 2021/4/16 15:02
     */
    CommonResult<List<BatchOperateResultDTO>> createUserBatchCompletableFuture(List<UserPO> users);


}
