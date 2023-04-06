package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.model.ReplyRequestModel;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class XmyController {
  private static final Logger logger = LoggerFactory.getLogger(XmyController.class);

  @Value("${openai.token}")
  private String openAiToken;

  /**
   * 验证接口
   * @return
   */
  @PostMapping(value = "/reply")
  public String signature(@RequestBody ReplyRequestModel replyRequestModel) {
    logger.info("参数 = replyRequestModel:{},openai-token:{}",replyRequestModel,openAiToken);
    OpenAiService service = new OpenAiService(openAiToken);
    CompletionRequest completionRequest = CompletionRequest.builder()
            .prompt(replyRequestModel.getContent())
            .model("gpt-3.5-turbo")
            .echo(true)
            .build();
    service.createCompletion(completionRequest).getChoices().forEach(System.out::println);
    return "success";
  }
}
