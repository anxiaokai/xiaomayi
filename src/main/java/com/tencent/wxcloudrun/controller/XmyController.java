package com.tencent.wxcloudrun.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * index控制器
 */
@Controller

public class XmyController {
  private static final Logger logger = LoggerFactory.getLogger(XmyController.class);
  /**
   * 验证接口
   * @return
   */
  @GetMapping
  public String signature(String signature,String timestamp,String nonce,String echostr) {
    logger.info("参数 = signature:{},timestamp:{},nonce:{},echostr:{}",signature,timestamp,nonce,echostr);
    return "echostr";
  }

}
