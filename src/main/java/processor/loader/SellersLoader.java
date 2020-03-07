package processor.loader;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.NonNull;
import processor.apis.LoaderMethods;

/** Loader API implementation */
public class SellersLoader implements LoaderMethods {
  private final HttpClient client;

  public SellersLoader() {
    this.client = HttpClient.newHttpClient();
  }

  /**
   * @param url
   * @return Sellers JSON data from given URL @Exception IOException, InterruptedException
   *     <p>By using JDK11 native HttpClient to retrieve JSON data
   */
  @Override
  public String loadJsonDataFromURL(@NonNull String url) {

    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
    HttpResponse<String> response = null;

    try {
      response = client.send(request, HttpResponse.BodyHandlers.ofString());
    } catch (IOException ioe) {
      throw new RuntimeException("Can't get response from URL");
    } catch (InterruptedException ie) {
      throw new RuntimeException("Process has been interrupted");
    }

    return response.body();
  }
}
