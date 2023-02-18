import java.util.Map;
import java.util.Scanner;

public class BuyerActions {
    static Scanner sc = new Scanner(System.in);

    static void addProductToCartByBuyer(Buyer buyer) {

        ShoppingWebsiteMasterData.displayAvailableProducts();

        System.out.print("\nChoose a product from the above list of currently available products.\n\nEnter the product name : ");
        String productNameInCart = sc.next();

        System.out.print("Enter the Seller Name : ");
        String sellerName = sc.next();

        System.out.print("Enter the product Quantity : ");
        int itemQuantityInCart = sc.nextInt();

        sellerTraverse:
        for (Seller seller : ShoppingWebsiteMasterData.sellerDataBase) {
            for (Map.Entry<Product, Integer> entry : seller.getSellerProductList().entrySet()) {
                Product product = entry.getKey();
                Integer productAvailableQuantity = entry.getValue();

                if (product.getProductName().equalsIgnoreCase(productNameInCart) && seller.getSellerName().equalsIgnoreCase(sellerName)) {

                    //---------------check if the product is already in cart and update quantity if present------------------------
                    boolean productAlreadyInCart = false;
                    for (CartItem cartItem : buyer.getCartItems()) {
                        if (cartItem.getProductInCart().getProductName().equalsIgnoreCase(productNameInCart) && cartItem.getProductInCart().getSellerName().equalsIgnoreCase(sellerName)) {
                            productAlreadyInCart = true;

                            System.out.println("\nThis product is already in cart\nEnter 1 to UPDATE(Replace) existing quantity\nEnter 2 to ADD to the existing quantity");

                            int buyerChoiceToUpdateQuantity = sc.nextInt();

                            if (buyerChoiceToUpdateQuantity == 1) {

                                int actualAvailability = productAvailableQuantity + cartItem.getItemQuantityInCart();
                                if(actualAvailability >= itemQuantityInCart){
                                    buyer.updateInCart(productNameInCart, sellerName, itemQuantityInCart, product.getProductPrice());
                                    entry.setValue(actualAvailability - itemQuantityInCart);
                                }else {
                                    System.out.println("\nAvailability quantity of '" + productNameInCart + "' by '" + sellerName + "' is " + actualAvailability + " which is less than entered quantity. \nTry again with lesser quantity");
                                    //break sellerTraverse;
                                }

                            } else if (buyerChoiceToUpdateQuantity == 2) {

                                int newItemQuantityInCart = itemQuantityInCart + cartItem.getItemQuantityInCart();

                                if (productAvailableQuantity >= newItemQuantityInCart) {
                                    buyer.updateInCart(productNameInCart, sellerName, newItemQuantityInCart, product.getProductPrice());
                                    //ShoppingWebsiteMasterData.updateQuantityInSellerDatabase(productNameInCart, sellerName, newItemQuantityInCart);
                                    entry.setValue(productAvailableQuantity - itemQuantityInCart);
                                } else {
                                    System.out.println("\nAvailability quantity of '" + productNameInCart + "' by '" + sellerName + "' is " + productAvailableQuantity + " which is less than entered quantity. \nTry again with lesser quantity");
                                    //break sellerTraverse;
                                }

                            } else
                                System.out.println("Not valid input. No changes made to cart.");

                            break sellerTraverse;
                        }
                    }

                    //---------------add new product to cart if it is not already present in cart----------------------------------
                    if (!productAlreadyInCart) {
                        if(productAvailableQuantity >= itemQuantityInCart) {

                            CartItem newCartItem = new CartItem();
                            newCartItem.productInCart.setProductName(productNameInCart);
                            newCartItem.productInCart.setSellerName(sellerName);
                            newCartItem.setItemQuantityInCart(itemQuantityInCart);

                            newCartItem.setProductPriceInCart(product.getProductPrice() * newCartItem.getItemQuantityInCart());

                            buyer.addToCart(newCartItem);
                            productAvailableQuantity = productAvailableQuantity - itemQuantityInCart;
                            entry.setValue(productAvailableQuantity);

                        } else {
                            System.out.println("\nAvailability quantity of '" + productNameInCart + "' by '" + sellerName + "' is " + productAvailableQuantity + " which is less than entered quantity. \nTry again with lesser quantity");
                            //break sellerTraverse;
                        }
                        break sellerTraverse;
                    }


                }
            }
        }
        buyer.viewCart();
        buyer.calculateCartTotalValue();
    }
    static void updateProductCountInCartByBuyer(Buyer buyer){

        if(buyer.getCartItems().size() == 0)
        {
            System.out.println("\nThere are no items in your cart. Going back to menu.\n");
            return;
        }

        System.out.println("\nBelow is the product list in your cart\n");
        buyer.viewCart();

        System.out.print("Choose a product from the above list.\nEnter the product name : ");
        String productNameInCart = sc.next();

        System.out.print("Enter the Seller Name : ");
        String sellerName = sc.next();
        cartTraverse:
        for(CartItem cartItem : buyer.getCartItems()) {
            if (cartItem.getProductInCart().getProductName().equalsIgnoreCase(productNameInCart) && cartItem.getProductInCart().getSellerName().equalsIgnoreCase(sellerName)) {

                System.out.print("Enter the new product Quantity : ");
                int newItemQuantityInCart = sc.nextInt();

                System.out.print("Confirm update Y/N : ");
                String quantityUpdateConfirmation = sc.next();

                if (quantityUpdateConfirmation.equalsIgnoreCase("Y")) {
                    sellerTraverse:
                    for (Seller seller : ShoppingWebsiteMasterData.sellerDataBase)
                        for (Map.Entry<Product, Integer> entry : seller.getSellerProductList().entrySet()) {
                            Product product = entry.getKey();
                            Integer productAvailableQuantity = entry.getValue();
                            if (product.getProductName().equalsIgnoreCase(productNameInCart) && seller.getSellerName().equalsIgnoreCase(sellerName)) {

                                int actualAvailability = productAvailableQuantity + cartItem.getItemQuantityInCart();

                                if(actualAvailability >= newItemQuantityInCart)
                                {
                                    //cartItem.setItemQuantityInCart(newItemQuantityInCart);
                                    buyer.updateInCart(productNameInCart, sellerName, newItemQuantityInCart, product.getProductPrice());
                                    entry.setValue(actualAvailability - newItemQuantityInCart);
                                }else
                                    System.out.println("\nAvailable quantity of '" + productNameInCart + "' by '" + sellerName + "' is " + actualAvailability + " which is less than the entered quantity. \nTry again with lesser quantity");

                                break cartTraverse;
                            }
                        }
                }


            } else {
                System.out.print("This Product by the given seller is not present in cart. Try again later");
            }
        }
        buyer.viewCart();
        buyer.calculateCartTotalValue();

    }
    static void removeProductFromCartByBuyer(Buyer buyer){

        if(buyer.getCartItems().size() == 0)
        {
            System.out.println("\nThere are no items in your cart. Going back to menu.\n");
            return;
        }

        buyer.viewCart();

        System.out.print("\nChoose a product from the above list than you wish to remove.\nEnter the product name : ");
        String productNameInCart = sc.next(); //check if product name is in cart

        System.out.print("Enter the Seller Name : ");
        String sellerName = sc.next();

        boolean productPresentInCart = false;
        for(CartItem cartItem : buyer.getCartItems())
            if(cartItem.getProductInCart().getProductName().equalsIgnoreCase(productNameInCart) && cartItem.getProductInCart().getSellerName().equalsIgnoreCase(sellerName))
            {productPresentInCart = true; break;}

        if(productPresentInCart){
            buyer.deleteItemsFromCart(productNameInCart, sellerName);
            buyer.viewCart();
            buyer.calculateCartTotalValue();
        }
        else
            System.out.print("This Product by the given seller is not present in cart. Try again later");
    }
    static void buyProductsByBuyer(Buyer buyer){
        System.out.println("Payment made and Order Placed Successfully");

    }


}
