//package com.cartisan.common.controllers;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * <p>Title: HeartbeatController</p>
// * <p>Description: </p>
// *
// * @author colin
// */
//@Api(tags = "心跳检查")
//@Slf4j
//@RestController
//@RequestMapping(value = "/heartbeat")
//public class HeartbeatController {
//    @ApiOperation("服务状态")
//    @RequestMapping(value = "/api", method = RequestMethod.GET)
//    public String apiStatus() {
//
//        return "success";
//
////        HeartbeatResponse heartbeatResponse = new HeartbeatResponse();
////        heartbeatResponse.setStatus(true);
////        heartbeatResponse.setMessage("API服务可用。 -- " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
////
////        return heartbeatResponse;
//    }
//}
