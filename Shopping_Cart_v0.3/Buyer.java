import java.util.ArrayList;
import java.util.Map;

public class Buyer {
    private String buyerName;
    private boolean buyerActiveStatus = true;
    private ArrayList<CartItem> cart = new ArrayList<CartItem>();

    float cartTotalValue;

    void addToCart(CartItem cartItem){
        cart.add(cartItem);
    }

    public ArrayList<CartItem> getCartItems() {
        return cart;
    }

    void viewCart()
    {
        boolean itemsPresentInCart = false;
        System.out.println("\nBuyer's Cart : ");
        System.out.println("------------------------------------------------------------------------");
        for(CartItem cartItem : cart)
        {
            System.out.println(cartItem.itemQuantityInCart + " quantity of '" + cartItem.productInCart.getProductName() + "' from '" + cartItem.productInCart.getSellerName() + "' @ Price ₹ " + cartItem.productPriceInCart);
            itemsPresentInCart = true;
        }
        System.out.println("------------------------------------------------------------------------");

        if(!itemsPresentInCart)
            System.out.println("\nThere are no items in your Cart\n");

    }

    void calculateCartTotalValue()
    {
        cartTotalValue = 0;
        for (CartItem cartItem : cart)
        {
            cartTotalValue += cartItem.getProductPriceInCart();
        }
        System.out.println("\nTotal cart Value is ₹ " + cartTotalValue + "\n");
    }

    void updateInCart(String productNameInCart, String sellerName, int newItemQuantityInCart, float productUnitPrice)
    {
     /*   //get unit price of the product
        float unitPrice = 0.0f;

        for(Seller seller : ShoppingWebsiteMasterData.sellerDataBase)
            for(Map.Entry<Product, Integer> entry : seller.getSellerProductList().entrySet()) {
                Product product = entry.getKey();
                Integer quantity = entry.getValue();
                if(product.getProductName().equalsIgnoreCase(productNameInCart) && seller.getSellerName().equalsIgnoreCase(sellerName))
                    {unitPrice = product.getProductPrice(); break;}
            }
*/
        for(CartItem cartItem : cart)
            if(cartItem.getProductInCart().getProductName().equalsIgnoreCase(productNameInCart) && cartItem.getProductInCart().getSellerName().equalsIgnoreCase(sellerName))
            {
                cartItem.setItemQuantityInCart(newItemQuantityInCart);
                cartItem.setProductPriceInCart(cartItem.getItemQuantityInCart()*productUnitPrice);
                break;
            }

        System.out.println("\nQuantity updated in cart");
    }

    void deleteItemsFromCart(String productNameInCart, String sellerName)
    {
        int index = 0;
        for(CartItem cartItem : cart)
            if(cartItem.getProductInCart().getProductName().equalsIgnoreCase(productNameInCart) && cartItem.getProductInCart().getSellerName().equalsIgnoreCase(sellerName))
                index = cart.indexOf(cartItem);

        cart.remove(index);
                //cart.remove(cartItem);
        System.out.println("\nItem successfully removed from cart");

    }
    }



