package processor.apis;

import java.io.Reader;
import java.util.List;
import jsonobject.Seller;
import jsonobject.Sellers;
import lombok.NonNull;

/** Defined APIs for JSON Processors */
public interface ProcessorMethods {
  default String loadJsonStringFromUrl(@NonNull String url) {
    return "";
  }

  default Sellers loadJsonObjectFromUrl(@NonNull String url) {
    return null;
  }

  default List<String> listDomainsFromObject(@NonNull Sellers sellers) {
    return null;
  }

  default List<String> listDomainsFromFile(@NonNull Reader reader) {
    return null;
  }

  default List<Seller> listCommonSellers(@NonNull Reader json1, @NonNull Reader json2) {
    return null;
  }

  default String loadJsonAndStore(@NonNull String url) {
    return "";
  }
}
