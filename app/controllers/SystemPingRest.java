package controllers;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.Global;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SystemPingRest extends Controller {

  private static final String PATTERN = "yyyy-MM-d'T'HH:mm:ssZ";

  public static Result status() throws IOException {
    PingResponse responsePing = new PingResponse();
    long start = System.nanoTime();
    responsePing.system = buildinfo.BuildInfo.name();
    responsePing.version = buildinfo.BuildInfo.version();
    responsePing.env = "Not known";
    responsePing.status_time = new SimpleDateFormat(PATTERN).format(new Date().getTime());
    responsePing.release_time = new SimpleDateFormat(PATTERN).format(new Date(buildinfo.BuildInfo.builtAtMillis()));
    responsePing.started_time = new SimpleDateFormat(PATTERN).format(Global.getStartTime());
    responsePing.latency = (System.nanoTime() - start) / 1000000L;
    return ok(Json.toJson(responsePing));
  }
}
