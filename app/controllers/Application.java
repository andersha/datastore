package controllers;

import models.Store;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import utils.FileInfo;
import views.html.index;

import java.io.FileInputStream;
import java.util.List;

public class Application extends Controller {

  public static Result index() {
    List<FileInfo> files = Store.listFiles();
    return ok(index.render(files));
  }

  /**
   * Store document
   *
   * @param key the name of the document to store
   */
  public static Result store(String key) {
    Http.MultipartFormData body = request().body().asMultipartFormData();
    Http.MultipartFormData.FilePart dokument = body.getFile("document");
    if (dokument != null) {
      dokument.getContentType();
      try {
        Store.storeFile(key, new FileInputStream(dokument.getFile()));
        Logger.info("Storing file at " + key);
      }
      catch (Exception e) {
        Logger.info("Cannot store file", e);
        return badRequest(Json.toJson(new Error("Cannot store file")));
      }
    }
    else {
      return badRequest(Json.toJson(new Error("No file to store")));
    }
    return ok();
  }

  /**
   * Fetch a stored document
   *
   * @param key the key of the document
   * @return document
   */
  public static Result fetch(String key) {
    Logger.info("File fetched at " + key);
    return ok(Store.fetchFile(key));
  }

  /**
   * Remove a stored document
   *
   * @param key the key of the document
   */
  public static Result remove(String key) {
    if (Store.removeFile(key)) {
      Logger.info("Removed file at " + key);
    }
    else {
      Logger.warn("Could not remove file at " + key);
    }
    return ok();
  }
}
