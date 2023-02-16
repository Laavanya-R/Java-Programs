import java.util.*;

public class ShoppingWebsiteMasterData {

    //List<String> buyerNames = Arrays.asList("buyer1", "buyer2", "buyer3");
    //List<String> sellerNames = Arrays.asList("seller1", "seller2", "seller3"); //change to object list
    static HashMap<String, ArrayList<Product>> completeProductList = new HashMap<>();

    public static boolean isValidBuyer(String buyerName)
    {
        List<String> buyerNames = Arrays.asList("buyer1", "buyer2", "buyer3"); //change to object list
        return buyerNames.contains(buyerName);
    }

    public static boolean isValidSeller(String sellerName)
    {
        //List<String> sellerNames = Arrays.asList("seller1", "seller2", "seller3"); //change to object list
        //return sellerNames.contains(sellerName);
        //if(completeProductList.containsKey(sellerName))
            return completeProductList.containsKey(sellerName);
    }
    static void setShoppingWebsiteMasterData(){


        Product product1 = new Product();
        product1.setProductName("Grape");
        product1.setProductPrice(67.950f);
        product1.setTotalQuantity(20);
        product1.setAvailableQuantity(12);

        Product product2 = new Product();
        product2.setProductName("Orange");
        product2.setProductPrice(107f);
        product2.setTotalQuantity(30);
        product2.setAvailableQuantity(22);

        Product product3 = new Product();
        product3.setProductName("Strawberry");
        product3.setProductPrice(120.5f);
        product3.setTotalQuantity(40);
        product3.setAvailableQuantity(33);

        Product product4 = new Product();
        product4.setProductName("Apple");
        product4.setProductPrice(168.950f);
        product4.setTotalQuantity(20);
        product4.setAvailableQuantity(15);

        Product product5 = new Product();
        product5.setProductName("Blueberry");
        product5.setProductPrice(100f);
        product5.setTotalQuantity(30);
        product5.setAvailableQuantity(27);

        Product product6 = new Product();
        product6.setProductName("Kiwi");
        product6.setProductPrice(70.5f);
        product6.setTotalQuantity(40);
        product6.setAvailableQuantity(0);

        completeProductList.computeIfAbsent("seller1", v ->new ArrayList<>()).add(product1);
        completeProductList.computeIfAbsent("seller1", v ->new ArrayList<>()).add(product2);
        completeProductList.computeIfAbsent("seller1", v ->new ArrayList<>()).add(product3);
        completeProductList.computeIfAbsent("seller1", v ->new ArrayList<>()).add(product4);
        completeProductList.computeIfAbsent("seller2", v ->new ArrayList<>()).add(product5);
        completeProductList.computeIfAbsent("seller2", v ->new ArrayList<>()).add(product1);
        completeProductList.computeIfAbsent("seller2", v ->new ArrayList<>()).add(product2);
        completeProductList.computeIfAbsent("seller3", v ->new ArrayList<>()).add(product1);
        completeProductList.computeIfAbsent("seller3", v ->new ArrayList<>()).add(product2);
        completeProductList.computeIfAbsent("seller3", v ->new ArrayList<>()).add(product3);
        completeProductList.computeIfAbsent("seller3", v ->new ArrayList<>()).add(product5);
        completeProductList.computeIfAbsent("seller3", v ->new ArrayList<>()).add(product6);

    }

    static void displayAvailableProducts()
    {
        System.out.println("\nProduct List : ");
        for(Map.Entry<String, ArrayList<Product>> entry: completeProductList.entrySet())
            for(Product product : entry.getValue())
                if(product.getAvailableQuantity()>0)
                    System.out.println(product.getProductName() + " at ₹ " + product.getProductPrice() + " per Kg from " + entry.getKey());
   }
}
