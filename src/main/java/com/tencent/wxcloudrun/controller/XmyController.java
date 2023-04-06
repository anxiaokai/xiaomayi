package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.model.ReplyRequestModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
  @PostMapping(value = "/reply")
  public String signature(@RequestBody ReplyRequestModel replyRequestModel) {
    logger.info("参数 = replyRequestModel:{}",replyRequestModel);
    return "success";
  }
}
