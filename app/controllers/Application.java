package controllers;

import models.Store;
import play.Logger;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.index;

import java.io.FileInputStream;

public class Application extends Controller {

  public static Result index() {
    return ok(index.render("Your new application is ready."));
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
      }
      catch (Exception e) {
        Logger.info("Cannot store file", e);
        return badRequest("{\"error\": \"Cannot store file\"}");
      }
    }
    else {
      return badRequest("{\"error\": \"No file to store\"}");
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
    return ok(Store.fetchFile(key));
  }

  /**
   * Remove a stored document
   *
   * @param key the key of the document
   */
  public static Result remove(String key) {
    Store.removeFile(key);
    return ok();
  }
}
