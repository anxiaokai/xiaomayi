package com.tencent.wxcloudrun.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tencent.wxcloudrun.model.ReplyRequestModel;
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
  public String signature(@RequestBody ReplyRequestModel replyRequestModel) {
    logger.info("参数 = replyRequestModel:{},openai-token:{},model:{}",replyRequestModel,openAiToken,openModel);
    ObjectMapper mapper = defaultObjectMapper();
    Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, Integer.parseInt(port)));
    logger.info("代理 = host:{},port:{}",host,port);
    OkHttpClient client = defaultClient(openAiToken, Duration.ofSeconds(10))
            .newBuilder()
            .proxy(proxy)
            .build();
    Retrofit retrofit = defaultRetrofit(client, mapper);
    OpenAiApi api = retrofit.create(OpenAiApi.class);
    OpenAiService service = new OpenAiService(api);

    CompletionRequest completionRequest = CompletionRequest.builder()
            .prompt(replyRequestModel.getContent())
            .model(openModel)
            .echo(true)
            .build();

    String result = service.createCompletion(completionRequest).getObject();
    logger.info("返回值:{}",result);
    return "success";
  }
}
