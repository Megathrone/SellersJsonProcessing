package processor.apis;

import lombok.NonNull;

/** Defined APIs for JSON Loaders */
public interface LoaderMethods {
  default String loadJsonDataFromURL(@NonNull String url) {
    return null;
  }
}
