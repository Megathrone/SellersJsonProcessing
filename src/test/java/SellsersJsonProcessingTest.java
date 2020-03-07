import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import jsonobject.Seller;
import jsonobject.Sellers;
import org.junit.jupiter.api.Test;
import processor.SellersProcessor;
import processor.loader.SellersLoader;
import processor.parser.SellersParser;
import processor.utils.SellersUtils;

public class SellsersJsonProcessingTest {
  private final SellersProcessor processor = new SellersProcessor();
  private final SellersParser parser = new SellersParser();
  private final SellersLoader loader = new SellersLoader();
  private final Gson gson = new Gson();

  @Test
  void loadJson() throws IOException, InterruptedException {
    String url = "https://bcdn.grmtas.com/plugin/ga_sellers.json";
    String url2 = "https://aps.amazon.com/sellers.json";
    String url3 = "https://aps.amazon.com/sellers.jso";
    var response =
        HttpClient.newHttpClient()
            .send(
                HttpRequest.newBuilder().uri(URI.create(url2)).build(),
                HttpResponse.BodyHandlers.ofString());

    Sellers sellers = gson.fromJson(response.body(), Sellers.class);
    assertEquals(response.body(), processor.loadJsonStringFromUrl(url2));
    assertEquals(sellers, processor.loadJsonObjectFromUrl(url2));

    assertThrows(
        IllegalArgumentException.class,
        () -> {
          processor.loadJsonStringFromUrl(url3);
        });

    assertEquals(
        "Can't get response from URL",
        assertThrows(
                RuntimeException.class,
                () -> {
                  loader.loadJsonDataFromURL("http://127.0.0.1");
                })
            .getMessage());
    assertEquals(
        "Process has been interrupted",
        assertThrows(
            RuntimeException.class,
            () -> {
              Thread t = new Thread(new Runnable(){
                public void run(){
                  loader.loadJsonDataFromURL(url);
                }
              });
              t.start();
              t.interrupt();
            })
            .getMessage());
  }

  @Test
  void listDomains() throws IOException, InterruptedException {
    String url = "https://bcdn.grmtas.com/plugin/ga_sellers.json";
    var response =
        HttpClient.newHttpClient()
            .send(
                HttpRequest.newBuilder().uri(URI.create(url)).build(),
                HttpResponse.BodyHandlers.ofString());
    Sellers sellers = gson.fromJson(response.body(), Sellers.class);

    List<String> domains = Lists.newArrayList();
    for (Seller seller : sellers.getSellers()) {
      String currValue = seller.getDomain();
      if (currValue != null || !currValue.isEmpty()) {
        domains.add(currValue);
      } else {
        domains.add("");
      }
    }

    Reader reader1 =
        Files.newBufferedReader(
            Paths.get(
                ""));

    assertEquals(domains, processor.listDomainsFromObject(sellers));
    assertEquals(domains, processor.listDomainsFromFile(reader1));
    Reader reader2 =
        Files.newBufferedReader(
            Paths.get(
                ""));
    Sellers sellers1 = parser.parseFromFile(reader2);
    List<String> domains2 = Lists.newArrayList();
    for (Seller seller : sellers1.getSellers()) {
      domains2.add(seller.getDomain());
    }
    Reader reader3 =
        Files.newBufferedReader(
            Paths.get(
                ""));
    assertEquals(domains2, processor.listDomainsFromFile(reader3));
  }

  @Test
  void listCommon() throws IOException, InterruptedException {
    Reader reader1 =
        Files.newBufferedReader(
            Paths.get(
                ""));
    Reader reader2 =
        Files.newBufferedReader(
            Paths.get(
                ""));

    List<Seller> commonSellers = Lists.newArrayList();

    Sellers sellers1 = parser.parseFromFile(reader1);
    Sellers sellers2 = parser.parseFromFile(reader2);
    commonSellers = SellersUtils.listCommonSellers(sellers1, sellers2);

    Reader reader3 =
        Files.newBufferedReader(
            Paths.get(
                ""));
    Reader reader4 =
        Files.newBufferedReader(
            Paths.get(
                ""));
    assertEquals(commonSellers, processor.listCommonSellers(reader3, reader4));
  }

}
