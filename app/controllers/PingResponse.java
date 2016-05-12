package controllers;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PingResponse {

  public String status_time;
  public String system;
  public String env;
  public String version;
  public String release_time;
  public String started_time;
  public Long latency;
}
