
import java.util.ArrayList;

public class Seller {
    String sellerName;
    String sellerUserID;
    ArrayList<Product> sellerProductList = new ArrayList<>();

    Seller()
    {

        //default product list - for every seller object created
        Product product1 = new Product();
        product1.setProductName("Grape");
        product1.setProductPrice(67.950f);
        product1.setTotalQuantity(20);

        Product product2 = new Product();
        product2.setProductName("Orange");
        product2.setProductPrice(100f);
        product2.setTotalQuantity(30);

        Product product3 = new Product();
        product3.setProductName("Strawberry");
        product3.setProductPrice(120.5f);
        product3.setTotalQuantity(40);

        sellerProductList.add(product1);
        sellerProductList.add(product2);
        sellerProductList.add(product3);
    }


    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public ArrayList<Product> getSellerProductList() {
        return sellerProductList;
    }


    public void displaySellerProductList() {
        //PrintStream stream = new PrintStream(System.out);
        //stream.print(sellerProductList);
        for(int i = 0; i< sellerProductList.size(); i++)
        {
            System.out.print("Product Name: " + sellerProductList.get(i).getProductName() + "  -  " + "Product Price: " + sellerProductList.get(i).getProductPrice() + "  -  " + "Product Quantity: " + sellerProductList.get(i).getTotalQuantity() + "\n");
        }
    }

    public void addToSellerProductList(Product product) {
        sellerProductList.add(product);
    }
    public void updateInSellerProductList(String productName, float newPrice) {
        for(Product product : sellerProductList)
            if(product.getProductName().equalsIgnoreCase(productName))
            {
                product.setProductPrice(newPrice);
                System.out.println("Price of " + product.getProductName() + " updated to " + product.getProductPrice());
                break;
            }
    }
    public void updateInSellerProductList(String productName, int newQuantity) {
        for(Product product : sellerProductList)
            if(product.getProductName().equalsIgnoreCase(productName))
            {
                product.setTotalQuantity(newQuantity);
                System.out.println("Quantity of " + product.getProductName() + " updated to " + product.getTotalQuantity());
                break;
            }
    }
    public void deleteProductFromSellerProductList(Product product) {
        sellerProductList.remove(product);
        System.out.println("The chosen product is deleted");

    }



}
