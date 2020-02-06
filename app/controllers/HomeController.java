package controllers;

import models.Person;
import models.PersonRepository;
import play.data.FormFactory;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import models.PersonRepository;
import play.data.FormFactory;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

import static play.libs.Json.toJson;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    private final PersonRepository personRepository;
    private final HttpExecutionContext ec;

    @Inject
    public HomeController(PersonRepository personRepository, HttpExecutionContext ec) {
        this.personRepository = personRepository;
        this.ec = ec;
    }

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(views.html.index.render());
    }
    public CompletionStage<Result> getPerson() {
        return personRepository
                .list()
                .thenApplyAsync(personStream -> ok(toJson(personStream.collect(Collectors.toList()))), ec.current());
//        return ok(toJson("{'name': 'Amritendu'}"));
    }

}
