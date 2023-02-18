import java.util.Scanner;

public class SellerActions {
    static Scanner sc = new Scanner(System.in);
    static void addProductBySeller(Seller seller){

        String sellerName = seller.getSellerName();
        do {
            Product newProduct = new Product();

            System.out.print("Enter Product Name : ");
            newProduct.setProductName(sc.next());

            for (Product product : seller.getSellerProductList().keySet())
                if(product.getProductName().equalsIgnoreCase(newProduct.getProductName())) {
                    System.out.print("This product is already in your product list. Go back to menu if you wish to update the product");
                    return;
                }

            System.out.print("Enter Product Price : ");
            newProduct.setProductPrice(sc.nextFloat());

            newProduct.setSellerName(seller.getSellerName());

            System.out.print("Enter Product Quantity : ");
            int newProductQuantity = sc.nextInt();

            seller.addToSellerProductList(newProduct, newProductQuantity);

            ShoppingWebsiteMasterData.addNewProductForASeller(sellerName, newProduct, newProductQuantity);


            System.out.print("\nAdd more products? Y/N : ");

        }while(sc.next().equalsIgnoreCase("Y"));
        System.out.println("\nBelow is your product list after adding the product");
        seller.displaySellerProductList(seller);
        //ShoppingWebsiteMasterData.displayAvailableProductsForASeller(sellerName);

    }

    static void updateProductBySeller(Seller seller)
    {
        String sellerName = seller.getSellerName();
        System.out.println("\nBelow is your product list\n");
        seller.displaySellerProductList(seller);
        //ShoppingWebsiteMasterData.displayAvailableProductsForASeller(sellerName);

        System.out.print("\nChoose the product from the above list and \nEnter the product name : ");
        String productName = sc.next();
        boolean productPresent = false;


        for(Product product : seller.getSellerProductList().keySet())
            if(product.getProductName().equalsIgnoreCase(productName))
            {
                productPresent = true;
                System.out.print("Update price? Enter Y/N : ");
                String updatePriceConfirmation = sc.next();
                if(updatePriceConfirmation.equalsIgnoreCase("Y")) {
                    System.out.print("Enter Product Price : ");
                    float newPrice = sc.nextFloat();
                    seller.updateInSellerProductList(productName, newPrice);

                    ShoppingWebsiteMasterData.updatePriceInSellerDatabase(productName, sellerName, newPrice);
                }
                else if(updatePriceConfirmation.equalsIgnoreCase("N")){
                    System.out.print("\nProduct Price not updated");
                }
                else System.out.print("\nNot a valid confirmation. Product Price not updated");

                    System.out.print("\nUpdate Quantity? Enter Y/N : ");
                    String updateQuantityConfirmation = sc.next();
                    if (updateQuantityConfirmation.equalsIgnoreCase("Y")) {
                        System.out.print("Enter new Product Quantity : ");
                        int newQuantity = sc.nextInt();
                        seller.updateInSellerProductList(productName, newQuantity);

                        ShoppingWebsiteMasterData.updateQuantityInSellerDatabase(productName, sellerName, newQuantity);
                    }
                    else if(updateQuantityConfirmation.equalsIgnoreCase("N")){
                        System.out.print("Product Quantity not updated");
                    }
                    else System.out.print("Not a valid confirmation. Product Quantity not updated");


                break;
            }
        if(!productPresent)
            System.out.println("This product does not exist in your product list");

        System.out.println("\nBelow is your product list after the update");
        seller.displaySellerProductList(seller);
        //ShoppingWebsiteMasterData.displayAvailableProductsForASeller(sellerName);
    }

    static void removeProductBySeller(Seller seller)
    {
        String sellerName = seller.getSellerName();
        System.out.println("Below is your product list\n");
        seller.displaySellerProductList(seller);
        //ShoppingWebsiteMasterData.displayAvailableProductsForASeller(sellerName);
        System.out.print("Choose the product from the above list that you would like to delete. \nEnter the product name : ");
        String nameOfProductToDelete = sc.next();
        boolean productPresent = false;

        for(Product product : seller.getSellerProductList().keySet())
            if(product.getProductName().equalsIgnoreCase(nameOfProductToDelete))
            {
                productPresent = true;
                seller.deleteProductFromSellerProductList(product);

                ShoppingWebsiteMasterData.removeProductForASeller(sellerName, nameOfProductToDelete);
                break;
            }

        if(!productPresent)
                System.out.println("This product does not exist in your product list");

        System.out.println("\nBelow is your product list after removing the product");
        seller.displaySellerProductList(seller);
        //ShoppingWebsiteMasterData.displayAvailableProductsForASeller(sellerName);


    }
}
