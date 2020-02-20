package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import dao.ResumeDTO;
import handlers.ResumeHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

import static play.libs.Json.toJson;

//TODO: Do proper exception handling using a class
//TODO: Add one validation method before forwarding the request to handler.
public class ResumeController extends Controller {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ResumeHandler handler;

    private final HttpExecutionContext ec;

    @Inject
    public ResumeController(ResumeHandler handler, HttpExecutionContext ec) {
        this.handler = handler;
        this.ec = ec;
    }

    public CompletionStage<Result>  getPerson(Long personId) {
        long start = System.currentTimeMillis();
        CompletionStage<ResumeDTO> resumeDTO = handler.getResumeData(personId);
        logger.info("resume data fetched successfully:" + toJson(resumeDTO));
        CompletionStage<Result> resultCompletionStage =  resumeDTO.thenApply(s -> ok(toJson(resumeDTO)));
        logger.error("Time taken to complete the fetch:{} ms", System.currentTimeMillis() - start);
        return resultCompletionStage;
    }

    public CompletionStage<Result>  addPerson(Http.Request request) {
        JsonNode json = request.body().asJson();
        logger.warn("request received to create resume:" + json);
        final ResumeDTO resumeDTO = Json.fromJson(json.get("result"), ResumeDTO.class);
        logger.warn("restored DTO object:" + resumeDTO.getBasic());
        CompletionStage<ResumeDTO> resumeDTOCompletionStage = handler.createResume(resumeDTO);
        return resumeDTOCompletionStage.thenApplyAsync(s->ok(toJson("\"status\":\"success\"")));
    }
}
