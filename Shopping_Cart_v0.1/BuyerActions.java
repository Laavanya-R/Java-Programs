import java.util.Scanner;

public class BuyerActions {
    static Scanner sc = new Scanner(System.in);

    static void addProductToCartByBuyer(Buyer buyer){

        ShoppingWebsiteMasterData.displayAvailableProducts();

        System.out.print("\nChoose a product from the above list of currently available products.\n\nEnter the product name : ");
        String productNameInCart = sc.next();

        System.out.print("Enter the Seller Name : ");
        String sellerName = sc.next();

        System.out.print("Enter the product Quantity : ");
        int itemQuantityInCart = sc.nextInt();

        //get unit price of the product
        float unitPrice = 0.0f;
        for (Product product : ShoppingWebsiteMasterData.completeProductList.get(sellerName))
            if (product.getProductName().equalsIgnoreCase(productNameInCart))
            {unitPrice = product.getProductPrice(); break;}

        //check if the product is already in cart - update quantity if so
        boolean productAlreadyInCart = false;
        for(Cart cart1: buyer.getCartItems())
            if(cart1.getProductInCart().getProductName().equalsIgnoreCase(productNameInCart) && cart1.getProductInCart().getSellerName().equalsIgnoreCase(sellerName))
            {
                buyer.updateInCartItems(productNameInCart, sellerName, itemQuantityInCart, unitPrice);
                productAlreadyInCart = true;
                break;
            }
        if(!productAlreadyInCart)
        {
            for (Product product : ShoppingWebsiteMasterData.completeProductList.get(sellerName))
                if (product.getProductName().equalsIgnoreCase(productNameInCart)) {
                    if (product.getAvailableQuantity() >= itemQuantityInCart) {

                        Cart cart = new Cart();
                        cart.productInCart.setProductName(productNameInCart);
                        cart.productInCart.setSellerName(sellerName);
                        cart.setItemQuantityInCart(itemQuantityInCart);

                        cart.setProductPriceInCart(product.getProductPrice() * cart.getItemQuantityInCart());

                        buyer.addToCartItems(cart);
                        product.setAvailableQuantity(product.getAvailableQuantity() - itemQuantityInCart);

                        break;
                    }
                    else {
                        System.out.println("\nAvailability quantity of '" + productNameInCart + "' by '" + sellerName + "' is " + product.getAvailableQuantity() + " which is less than entered quantity. \nTry again with lesser quantity");
                        break;
                    }
                }
        }

        //System.out.println("\nBelow is the product list in your cart\n");
        buyer.viewCartItems();
        buyer.calculateCartTotalValue();
    }
    static void updateProductCountInCartByBuyer(Buyer buyer){
        System.out.println("\nBelow is the product list in your cart\n");
        buyer.viewCartItems();

        System.out.print("Choose a product from the above list.\nEnter the product name : ");
        String productNameInCart = sc.next();

        System.out.print("Enter the Seller Name : ");
        String sellerName = sc.next();

        for(Cart cart: buyer.getCartItems())
            if(cart.getProductInCart().getProductName().equalsIgnoreCase(productNameInCart) && cart.getProductInCart().getSellerName().equalsIgnoreCase(sellerName))
            {

                System.out.print("Enter the new product Quantity : ");
                int newItemQuantityInCart = sc.nextInt();

                System.out.print("Confirm update Y/N : ");
                String quantityUpdateConfirmation = sc.next();

                if (quantityUpdateConfirmation.equalsIgnoreCase("Y")) {
                    for (Product product : ShoppingWebsiteMasterData.completeProductList.get(sellerName))
                        if (product.getProductName().equalsIgnoreCase(productNameInCart)) {
                            if (product.getAvailableQuantity() >= newItemQuantityInCart) {

                                int differenceInCartQuantityDuringUpdate = buyer.updateInCartItems(productNameInCart, sellerName, newItemQuantityInCart, product.getProductPrice());
                                product.setAvailableQuantity(product.getAvailableQuantity() + differenceInCartQuantityDuringUpdate);
                                break;
                            } else
                                System.out.println("\nAvailability quantity of '" + productNameInCart + "' by '" + sellerName + "' is " + product.getAvailableQuantity() + " which is less than entered quantity. \nTry again with lesser quantity");
                        }
                }

                //System.out.println("\nBelow is the product list in your cart after update\n");
                buyer.viewCartItems();
                buyer.calculateCartTotalValue();
            }
        else {
                System.out.print("This Product by the given seller is not present in cart. Try again later");
            }


    }
    static void removeProductFromCartByBuyer(Buyer buyer){
        System.out.println("\nBelow is the product list in your cart\n");
        buyer.viewCartItems();

        System.out.print("Choose a product from the above list than you wish to remove.\nEnter the product name : ");
        String productNameInCart = sc.next(); //check if product name is in cart

        System.out.print("Enter the Seller Name : ");
        String sellerName = sc.next();

        boolean productPresentInCart = false;
        for(Cart cart: buyer.getCartItems())
            if(cart.getProductInCart().getProductName().equalsIgnoreCase(productNameInCart) && cart.getProductInCart().getSellerName().equalsIgnoreCase(sellerName))
            {productPresentInCart = true; break;}

        if(productPresentInCart){
            buyer.deleteItemsFromCart(productNameInCart, sellerName);
            //System.out.println("\nBelow is the product list in your cart after removal\n");
            buyer.viewCartItems();
            buyer.calculateCartTotalValue();
        }
        else
            System.out.print("This Product by the given seller is not present in cart. Try again later");
    }
    static void buyProductsByBuyer(Buyer buyer){
        System.out.println("Payment made and Order Placed Successfully");

    }


}
