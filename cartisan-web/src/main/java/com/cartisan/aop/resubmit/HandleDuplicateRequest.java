package com.cartisan.aop.resubmit;

/**
 * @author colin
 */
public class HandleDuplicateRequest {
//    public boolean isDuplicateRequest(){
//        String userId= "12345678";//用户
//        String method = "pay";//接口名
//        String dedupMD5 = new ReqDedupHelper().dedupParamMD5(req,"requestTime");//计算请求参数摘要，其中剔除里面请求时间的干扰
//        String KEY = "dedup:U=" + userId + "M=" + method + "P=" + dedupMD5;
//
//        long expireTime =  1000;// 1000毫秒过期，1000ms内的重复请求会认为重复
//        long expireAt = System.currentTimeMillis() + expireTime;
//        String val = "expireAt@" + expireAt;
//
//// NOTE:直接SETNX不支持带过期时间，所以设置+过期不是原子操作，极端情况下可能设置了就不过期了，后面相同请求可能会误以为需要去重，所以这里使用底层API，保证SETNX+过期时间是原子操作
//        Boolean firstSet = stringRedisTemplate.execute((RedisCallback<Boolean>) connection -> connection.set(KEY.getBytes(), val.getBytes(), Expiration.milliseconds(expireTime),
//                RedisStringCommands.SetOption.SET_IF_ABSENT));
//
//        final boolean isConsiderDup;
//        if (firstSet != null && firstSet) {
//            isConsiderDup = false;
//        } else {
//            isConsiderDup = true;
//        }
//
//        return isConsiderDup;
//    }
}
