package processor.apis;

import java.io.Reader;
import jsonobject.Sellers;
import lombok.NonNull;

/** Defined APIs for JSON Parser */
public interface ParserMethods {
  default Sellers parseFromFile(@NonNull Reader reader) {
    return null;
  }

  default Sellers parseFromString(@NonNull String json) {
    return null;
  }
}
