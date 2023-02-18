
import java.util.ArrayList;
import java.util.HashMap;

public class Seller {
    private String sellerName;
    private HashMap<Product, Integer> sellerProductList = new HashMap<Product, Integer>();


  /*  Seller()
    {
        Product product1 = new Product();
        product1.setProductID(1);
        product1.setProductName("Grape");
        product1.setProductPrice(67.950f);

        Product product2 = new Product();
        product2.setProductID(2);
        product2.setProductName("Orange");
        product2.setProductPrice(107f);

        Product product3 = new Product();
        product3.setProductID(3);
        product3.setProductName("Strawberry");
        product3.setProductPrice(120.5f);

        Product product4 = new Product();
        product4.setProductID(4);
        product4.setProductName("Apple");
        product4.setProductPrice(168.950f);

        Product product5 = new Product();
        product5.setProductID(5);
        product5.setProductName("Blueberry");
        product5.setProductPrice(100f);


        Product product6 = new Product();
        product6.setProductName("Kiwi");
        product6.setProductPrice(70.5f);



        sellerProductList.put(product1, 50);
        sellerProductList.put(product2, 54);
        sellerProductList.put(product3, 90);
        sellerProductList.put(product4, 14);
    }
*/
    public HashMap<Product, Integer> getSellerProductList() {
        return sellerProductList;
    }

    public void setSellerProductList(HashMap<Product, Integer> sellerProductList) {
        this.sellerProductList = sellerProductList;
    }
    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public void displaySellerProductList(Seller seller) {

        for (Product product : sellerProductList.keySet())
            System.out.print("Product Name: " + product.getProductName() + "  -  " + "Product Price: " + product.getProductPrice() + "  -  " + "Product Quantity: " + sellerProductList.get(product) + "\n");
    }

    public void addToSellerProductList(Product newProduct, int newProductQuantity) {

        sellerProductList.put(newProduct, newProductQuantity);
        System.out.println("The product is successfully added");
    }

    public void updateInSellerProductList(String productName, float newPrice) {
        for (Product product : sellerProductList.keySet())
            if(product.getProductName().equalsIgnoreCase(productName))
            {
                product.setProductPrice(newPrice);
                System.out.println("Price of '" + product.getProductName() + "' updated to " + product.getProductPrice());
                break;
            }
    }
    public void updateInSellerProductList(String productName, int newQuantity) {
        for (Product product : sellerProductList.keySet())
            if(product.getProductName().equalsIgnoreCase(productName))
            {
                sellerProductList.put(product, newQuantity);
               // System.out.println("Quantity of '" + product.getProductName() + "' updated to " + product.getTotalQuantity());
                break;
            }
    }
    public void deleteProductFromSellerProductList(Product productToDelete) {
        sellerProductList.remove(productToDelete);
        System.out.println("The chosen product is deleted");

    }



}
