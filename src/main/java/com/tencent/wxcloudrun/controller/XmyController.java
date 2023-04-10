package com.tencent.wxcloudrun.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tencent.wxcloudrun.model.ReplyRequestModel;
import com.tencent.wxcloudrun.model.ReplyResponseTextModel;
import com.theokanning.openai.OpenAiApi;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Retrofit;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.time.Duration;
import java.util.Objects;

import static com.theokanning.openai.service.OpenAiService.*;

@RestController
public class XmyController {
  private static final Logger logger = LoggerFactory.getLogger(XmyController.class);

  @Value("${openai.token}")
  private String openAiToken;

  @Value("${openai.model}")
  private String openModel;

  @Value("${openai.host:api.openai.com}")
  private String host;

  @Value("${openai.port:80}")
  private String port;

  /**
   * 验证接口
   * @return
   */
  @PostMapping(value = "/reply")
  public ReplyResponseTextModel signature(@RequestBody ReplyRequestModel replyRequestModel) throws Exception {
    logger.info("参数 = replyRequestModel:{}",replyRequestModel);
    ReplyResponseTextModel replyResponseTextModel = new ReplyResponseTextModel();
    replyResponseTextModel.setToUserName(replyRequestModel.getFromUserName());
    replyResponseTextModel.setFromUserName(replyRequestModel.getToUserName());
    replyResponseTextModel.setCreateTime(String.valueOf(System.currentTimeMillis()/1000));
    replyResponseTextModel.setMsgType(replyRequestModel.getMsgType());

    String content = replyRequestModel.getContent();
    if("IO模型思考题".equals(content)){
      replyResponseTextModel.setContent("不浪费服务器的资源就是高性能。不论服务器本身性能多低，只要充分发挥了服务器的性能，对程序来说就叫高性能。");
    }else {
      replyResponseTextModel.setContent("留言已收到，稍后回复您~~~");
    }
    logger.info("返回值:{}",replyResponseTextModel);
    return replyResponseTextModel;
  }
}
