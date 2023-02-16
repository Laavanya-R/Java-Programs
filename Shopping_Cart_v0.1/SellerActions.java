import java.util.Scanner;

public class SellerActions {
    static Scanner sc = new Scanner(System.in);
    static void addProductBySeller(Seller seller){

        do {
            Product product = new Product();

            System.out.print("Enter Product Name : ");
            product.setProductName(sc.next());
            System.out.print("Enter Product Price : ");
            product.setProductPrice(sc.nextFloat());
            System.out.print("Enter Product Quantity : ");
            product.setTotalQuantity(sc.nextInt());

            seller.addToSellerProductList(product);

            System.out.print("\nAdd more products? Y/N : ");

        }while(sc.next().equalsIgnoreCase("Y"));
        System.out.println("\nBelow is your product list after the update");
        seller.displaySellerProductList();

    }

    static void updateProductBySeller(Seller seller)
    {
        System.out.println("\nBelow is your product list\n");
        seller.displaySellerProductList();
        System.out.print("\nChoose the product from the above list and enter the product name : ");
        String productName = sc.next();
        boolean productPresent = false;

        for(Product product : seller.sellerProductList)
            if(product.getProductName().equalsIgnoreCase(productName))
            {
                System.out.print("Update price? Enter Y/N : ");
                String updatePriceConfirmation = sc.next();
                if(updatePriceConfirmation.equalsIgnoreCase("Y")) {
                    System.out.print("Enter Product Price : ");
                    float newPrice = sc.nextFloat();
                    seller.updateInSellerProductList(productName, newPrice);
                }

                System.out.print("Update Quantity? Enter Y/N : ");
                String updateQuantityConfirmation = sc.next();
                if(updateQuantityConfirmation.equalsIgnoreCase("Y")){
                    System.out.print("Enter new Product Quantity : ");
                    int newQuantity = sc.nextInt();
                    seller.updateInSellerProductList(productName, newQuantity);
                }
                productPresent = true;
                break;
            }
        if(!productPresent)
            System.out.println("This product does not exist in your product list");

        System.out.println("\nBelow is your product list after the update");
        seller.displaySellerProductList();
    }

    static void removeProductBySeller(Seller seller)
    {
        System.out.println("Below is your product list\n");
        seller.displaySellerProductList();
        System.out.print("Choose the product from the above list that you would like to delete. \nEnter the product name : ");
        String productName = sc.next();
        boolean productPresent = false;

        for(Product product : seller.sellerProductList)
            if(product.getProductName().equalsIgnoreCase(productName))
            {
                productPresent = true;
                seller.deleteProductFromSellerProductList(product);
                break;
            }

        if(!productPresent)
                System.out.println("This product does not exist in your product list");

        System.out.println("\nBelow is your product list after removing the product");
        seller.displaySellerProductList();


    }
}
