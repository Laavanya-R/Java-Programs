import java.util.*;

public class ShoppingWebsiteMasterData {
    static ArrayList<Seller> sellerDataBase = new ArrayList<>();

    public static boolean isValidBuyer(String buyerName) {
        List<String> buyerNames = Arrays.asList("buyer1", "buyer2", "buyer3");
        for (String buyerNameInArray : buyerNames)
            if (buyerNameInArray.equalsIgnoreCase(buyerName))
                return true;
        return false;
    }

    public static boolean isValidSeller(String sellerName) {
        //List<String> sellerNames = Arrays.asList("seller1", "seller2", "seller3");
        //return sellerNames.contains(sellerName);
        for (Seller seller : sellerDataBase)
            if (seller.getSellerName().equalsIgnoreCase(sellerName))
                return true;

        return false;
    }

    static void setSellerDatBase() {

        Product product1 = new Product();
        product1.setProductName("Grape");
        product1.setProductPrice(67.950f);

        Product product2 = new Product();
        product2.setProductName("Orange");
        product2.setProductPrice(107f);

        Product product3 = new Product();
        product3.setProductName("Strawberry");
        product3.setProductPrice(120.5f);

        Product product4 = new Product();
        product4.setProductName("Apple");
        product4.setProductPrice(168.950f);

        Product product5 = new Product();
        product5.setProductName("Blueberry");
        product5.setProductPrice(100f);


        Product product6 = new Product();
        product6.setProductName("Kiwi");
        product6.setProductPrice(70.5f);

        Seller seller1 = new Seller();
        seller1.setSellerName("seller1");
        HashMap<Product, Integer> sellerProductList1 = new HashMap<Product, Integer>();
        sellerProductList1.put(product1, 50);
        sellerProductList1.put(product2, 50);
        seller1.setSellerProductList(sellerProductList1);
        sellerDataBase.add(seller1);

        Seller seller2 = new Seller();
        seller2.setSellerName("seller2");
        HashMap<Product, Integer> sellerProductList2 = new HashMap<Product, Integer>();
        sellerProductList2.put(product2, 70);
        sellerProductList2.put(product3, 700);
        sellerProductList2.put(product4, 45);
        sellerProductList2.put(product5, 700);
        seller2.setSellerProductList(sellerProductList2);
        sellerDataBase.add(seller2);

        Seller seller3 = new Seller();
        seller3.setSellerName("seller3");
        HashMap<Product, Integer> sellerProductList3 = new HashMap<Product, Integer>();
        sellerProductList3.put(product2,34);
        sellerProductList3.put(product3,45);
        sellerProductList3.put(product5,20);
        sellerProductList3.put(product6, 12);
        seller3.setSellerProductList(sellerProductList3);
        sellerDataBase.add(seller3);
    }

    static void displayAvailableProducts() {
        System.out.println("\nProduct List : ");

        for (Seller seller : sellerDataBase) {
            seller.getSellerProductList().forEach((k, v) ->
                    {
                        if (v != 0)
                            System.out.println(k.getProductName() + " at ₹ " + k.getProductPrice() + " per Kg from " + seller.getSellerName());
                    }
            );
            System.out.println();
        }

     /*   for(Map.Entry<Product, Integer>> entry: seller.sellerProductList.entrySet())
            for(Product product : entry.getValue())
                if(product.getAvailableQuantity()>0)
                    System.out.println(product.getProductName() + " at ₹ " + product.getProductPrice() + " per Kg from " + entry.getKey());
   */
    }

    static void displayAvailableProductsForASeller(String sellerName) {
        System.out.println("\nProduct List of '" + sellerName + "' in DB : ");

        for (Seller seller : sellerDataBase)
            if (seller.getSellerName().equalsIgnoreCase(sellerName)) {
                seller.getSellerProductList().forEach((k, v) ->
                        {
                            if (v != 0)
                                System.out.println(k.getProductName() + " at ₹ " + k.getProductPrice() + " per Kg from " + seller.getSellerName());
                        }
                );
            }

    }

    static void updateQuantityInSellerDatabase(String productName, String sellerName, int quantityToUpdate) {
        for (Seller seller : sellerDataBase) {
            for (Map.Entry<Product, Integer> entry : seller.getSellerProductList().entrySet()) {
                Product product = entry.getKey();
                if (product.getProductName().equalsIgnoreCase(productName) && seller.getSellerName().equalsIgnoreCase(sellerName))
                    entry.setValue(quantityToUpdate);
            }
        }
    }

    static void updatePriceInSellerDatabase(String productName, String sellerName, float priceToUpdate) {
        for (Seller seller : sellerDataBase) {
            for (Map.Entry<Product, Integer> entry : seller.getSellerProductList().entrySet()) {
                Product product = entry.getKey();

                if (product.getProductName().equalsIgnoreCase(productName) && seller.getSellerName().equalsIgnoreCase(sellerName))
                    product.setProductPrice(priceToUpdate);
            }
        }
    }

    static void addNewProductForASeller(String sellerName, Product newProduct, int newProductQuantity) {

        for (Seller seller : sellerDataBase) {
            if (seller.getSellerName().equalsIgnoreCase(sellerName)) {
                seller.getSellerProductList().put(newProduct, newProductQuantity);
                break;
            }

        }

    }
  /*  static void updateProductForASeller(String sellerName, Product newProduct, int newProductQuantity) {

        for (Seller seller : sellerDataBase) {
            if (seller.getSellerName().equalsIgnoreCase(sellerName)) {
                seller.getSellerProductList().put(newProduct, newProductQuantity);
                break;
            }

        }

    }

   */


    static void removeProductForASeller(String sellerName, String productName) {
        for (Seller seller : sellerDataBase) {
            for (Map.Entry<Product, Integer> entry : seller.getSellerProductList().entrySet()) {
                Product product = entry.getKey();
                if (product.getProductName().equalsIgnoreCase(productName) && seller.getSellerName().equalsIgnoreCase(sellerName)) {
                    seller.getSellerProductList().remove(product);
                    break;
                }


            }
        }
    }
}