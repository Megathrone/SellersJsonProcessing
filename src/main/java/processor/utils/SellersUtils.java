package processor.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.List;
import java.util.Set;
import jsonobject.Seller;
import jsonobject.Sellers;
import lombok.NonNull;

/** Sellers JSON processor utils */
public class SellersUtils {

  /**
   * @param sellers
   * @return List of domains from given sellers
   */
  public static List<String> listDomains(@NonNull Sellers sellers) {
    List<String> domains = Lists.newArrayList();
    for (Seller seller : sellers.getSellers()) {
      String currValue = seller.getDomain();
      domains.add(currValue);
    }

    return domains;
  }

  /**
   * @param sellers1
   * @param sellers2
   * @return List of common sellers from two given sellers
   *     <p>Find intersection from two list, using Guava HashSet
   */
  public static List<Seller> listCommonSellers(Sellers sellers1, Sellers sellers2) {
    Set<String> sellerSetFromSellers1 = Sets.newHashSet();
    Set<String> sellerSetFromSellers2 = Sets.newHashSet();
    List<Seller> commonSellers = Lists.newArrayList();

    for (Seller seller : sellers1.getSellers()) {
      sellerSetFromSellers1.add(seller.getSeller_id());
    }
    for (Seller seller : sellers2.getSellers()) {
      if (sellerSetFromSellers1.contains(seller.getSeller_id())) {
        commonSellers.add(seller);
      }
    }
    return commonSellers;
  }
}
