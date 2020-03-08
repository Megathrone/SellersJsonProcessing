package processor.parser;

import com.google.gson.Gson;
import java.io.Reader;
import jsonobject.Sellers;
import lombok.NonNull;
import processor.apis.ParserMethods;

/** Parser API implementation */
public class SellersParser implements ParserMethods {
  private final Gson gson;

  public SellersParser() {
    this.gson = new Gson();
  }

  /**
   * @param json
   * @return Sellers Java object
   *     <p>By using Gson library to deserialize a JSON string to Java object
   */
  @Override
  public Sellers parseFromString(@NonNull String json) {
    Sellers sellers = gson.fromJson(json, Sellers.class);
    return sellers;
  }

  /**
   * @param reader
   * @return Sellers Java object
   *     <p>By using Gson library to deserialize a JSON file to Java object
   */
  @Override
  public Sellers parseFromFile(@NonNull Reader reader) {
    Sellers sellers = gson.fromJson(reader, Sellers.class);
    return sellers;
  }
}
