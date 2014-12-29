package controllers;

import exceptions.ErrorMessage;
import models.Store;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import utils.FileInfo;
import views.html.index;

import java.io.FileInputStream;
import java.io.InputStream;
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
        return ok();
      }
      catch (Exception e) {
        Logger.info("Cannot store file at " + key, e);
        return badRequest(Json.toJson(ErrorMessage.cannotStore));
      }
    }
    else {
      Logger.info("Tried to store at " + key + " without a file");
      return badRequest(Json.toJson(ErrorMessage.noFile));
    }
  }

  /**
   * Fetch a stored document
   *
   * @param key the key of the document
   * @return document
   */
  public static Result fetch(String key) {
    try {
      InputStream content = Store.fetchFile(key);
      Logger.debug("File fetched at " + key);
      return ok(content);
    }
    catch (Exception e) {
      Logger.info("Cannot fetch file at " + key, e);
      return badRequest(Json.toJson(ErrorMessage.cannotFetch));
    }
  }

  /**
   * Remove a stored document
   *
   * @param key the key of the document
   */
  public static Result remove(String key) {
    if (Store.removeFile(key)) {
      Logger.info("Removed file at " + key);
      return ok();
    }
    else {
      Logger.info("Could not remove file at " + key);
      return badRequest(Json.toJson(ErrorMessage.cannotDelete));
    }
  }
}
