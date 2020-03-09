package processor;

import com.google.gson.Gson;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import jsonobject.Seller;
import jsonobject.Sellers;
import lombok.NonNull;
import processor.apis.ProcessorMethods;
import processor.loader.SellersLoader;
import processor.parser.SellersParser;
import processor.persistence.SellersStorage;
import processor.utils.SellersUtils;

/** Parser API implementation */
public class SellersProcessor implements ProcessorMethods {
  private final SellersLoader sellersLoader;
  private final SellersParser sellersParser;

  public SellersProcessor() {
    this.sellersLoader = new SellersLoader();
    this.sellersParser = new SellersParser();
  }

  /**
   * @param @NonNull url
   * @return JSON string @Exception IllegalArgumentException
   *     <p>This method will load JSON data from given url, it will do url valid check
   */
  @Override
  public String loadJsonStringFromUrl(@NonNull String url) {
    String inputUrl = url;
    if (!isValid(inputUrl)) {
      throw new IllegalArgumentException("The JSON url is in valid, check it out.");
    }
    return sellersLoader.loadJsonDataFromURL(inputUrl);
  }

  /**
   * @param @NonNull url
   * @return JSON Java object @Exception IllegalArgumentException
   *     <p>This method will load JSON data from given url then return Sellers object
   */
  @Override
  public Sellers loadJsonObjectFromUrl(@NonNull String url) {
    String json = loadJsonStringFromUrl(url);
    return sellersParser.parseFromString(json);
  }

  /**
   * @param sellers
   * @return List of domains from given sellers.json Java object
   */
  @Override
  public List<String> listDomainsFromObject(@NonNull Sellers sellers) {
    return SellersUtils.listDomains(sellers);
  }

  /**
   * @param reader
   * @return List of domains from given sellers.json file
   */
  @Override
  public List<String> listDomainsFromFile(@NonNull Reader reader) {
    Sellers sellers = sellersParser.parseFromFile(reader);
    return SellersUtils.listDomains(sellers);
  }

  /**
   * @param json1
   * @param json2
   * @return List of common sellers from two given sellers.json file
   */
  @Override
  public List<Seller> listCommonSellers(@NonNull Reader json1, @NonNull Reader json2) {
    Sellers sellers1 = sellersParser.parseFromFile(json1);
    Sellers sellers2 = sellersParser.parseFromFile(json2);
    return SellersUtils.listCommonSellers(sellers1, sellers2);
  }

  /**
   * @param url
   * @return raw Json Data
   */
  @Override
  public String loadJsonAndStore(@NonNull String url) {
    String json = loadJsonStringFromUrl(url);
    SellersStorage sellersStorage = new SellersStorage();
    Connection connection = sellersStorage.getConnection();
    if (connection == null) {
      throw new NullPointerException("Couldn't get JDBC connection");
    }
    storeJsonToDB(connection, json);
    return json;
  }

  /**
   *
   * @param connection
   * @param rawJson
   * Stroing json into mysql
   */
  private void storeJsonToDB(Connection connection, String rawJson) {
    Gson gson = new Gson();
    Sellers sellers = gson.fromJson(rawJson, Sellers.class);

    try {
      String insertSellersSQL =
          "INSERT INTO "
              + "`sellers`.`sellers`(`contact_email`, `contact_address`, `version`, `seller`, `identifier`) "
              + "VALUES (?, ?, ?, ?, ?);";
      PreparedStatement st = connection.prepareStatement(insertSellersSQL);
      st.setString(1, sellers.getContact_email());
      st.setString(2, sellers.getContact_email());
      st.setString(3, sellers.getVersion());
      st.setString(4, gson.toJson(sellers.getSellers()));
      st.setString(5, gson.toJson(sellers.getIdentifiers()));
      st.execute();
      connection.close();
    } catch (SQLException e) {
      System.out.println(e.getCause().getMessage());
    }
  }

  /**
   * @param Sellers.json url
   * @return boolean value that simple determines if current url ends with ".json"
   */
  private boolean isValid(String url) {
    int len = url.length();
    if ((len - 5) >= 0 && url.substring((len - 5)).equals(".json")) {
      return true;
    }

    return false;
  }
}
