package com.loca.mallstu.service.impl;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.loca.mallstu.bean.po.DTO.BatchOperateResultDTO;
import com.loca.mallstu.bean.po.UserPO;
import com.loca.mallstu.common.CommonResult;
import com.loca.mallstu.common.ResultCode;
import com.loca.mallstu.service.MultithreadingService;
import com.loca.mallstu.utils.AsyncThreadPoolUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author wangHeng
 * @date  2021-04-16 14:52
 */
@Slf4j
@Service
public class MultithreadingServiceImpl implements MultithreadingService {

    /**
     * 自定义线程工厂,可以设置线程名前缀、是否守护线程、优先级等属性
     */
    private static ThreadFactory factory = new ThreadFactoryBuilder().build();

    /**
     * 在开发过程中最好自定义线程池 根据实际情况设置核心线程、最大线程、存活时间、工作队列、线程工厂、拒绝策略
     * 线程池执行流程
     * 核心线程--》工作队列--》最大线程--》拒绝策略
     */
    private static ExecutorService executor = new ThreadPoolExecutor(3, 5, 1,
            TimeUnit.MINUTES, new ArrayBlockingQueue<>(5), factory);

    public static List<BatchOperateResultDTO> result = Lists.newArrayList();

    @Override
    public CommonResult<List<BatchOperateResultDTO>> createUserBatchCallable(List<UserPO> users) {
        List<Future<BatchOperateResultDTO>> futures = Lists.newArrayList();
        long start = System.currentTimeMillis();
        users.forEach(user -> {
            Future<BatchOperateResultDTO> future = AsyncThreadPoolUtil.BATCH_APPROVE_POOL.submit(new CreateUser(user));
            futures.add(future);
        });
        log.info("总耗时={}ms", System.currentTimeMillis() - start);
        List<BatchOperateResultDTO> resultList = futures.stream().map(future -> {
            try {
                BatchOperateResultDTO result = future.get();
                return result;
            } catch (Exception e) {
                log.error("error:{}", e);
                return BatchOperateResultDTO.builder().message("插入失败").build();
            }
        }).collect(Collectors.toList());
        return CommonResult.success(resultList);
    }

    @Override
    public CommonResult<List<BatchOperateResultDTO>> createUserBatchCompletableFuture(List<UserPO> users) {
        handleCompletableFuture(users);
        return CommonResult.success(result);
    }

    private void handleCompletableFuture(List<UserPO> users) {
        CompletableFuture[] cfs = users.stream().map(user -> CompletableFuture.supplyAsync(() -> handle(user), executor)
                .whenComplete((r, e) -> completeTask(r))).toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(cfs).join();
    }

    private void completeTask(UserPO user) {
        log.info("保存完客户信息后,再给该客户=={}==Email和SMS, 线程名=={}", user.getName(), Thread.currentThread().getName());
        result.add(BatchOperateResultDTO.builder().userName(user.getName()).message(ResultCode.SUCCESS.getMessage()).build());
    }

    @SneakyThrows
    private static UserPO handle(UserPO user) {
        log.info("首先保存客户=={}==信息, 线程名=={}", user.getName(), Thread.currentThread().getName());
        Thread.sleep(5000);
        return user;
    }

}
