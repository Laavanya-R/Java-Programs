import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Buyer {
    private String buyerName;
    private boolean buyerActiveStatus = true;
    private ArrayList<Cart> cartItems = new ArrayList<Cart>();

    float cartTotalValue;

    void addToCartItems(Cart cart){
        cartItems.add(cart);
    }

    public ArrayList<Cart> getCartItems() {
        return cartItems;
    }

    void viewCartItems()
    {
        boolean itemsPresentInCart = false;
        System.out.println("\nBuyer's Cart : ");
        System.out.println("------------------------------------------------------------------------");
        for(Cart cart : cartItems)
        {
            System.out.println(cart.itemQuantityInCart + " quantity of '" + cart.productInCart.getProductName() + "' from '" + cart.productInCart.getSellerName() + "' @ Price ₹ " + cart.productPriceInCart);
            itemsPresentInCart = true;
        }
        System.out.println("------------------------------------------------------------------------");

        if(!itemsPresentInCart)
            System.out.println("\nThere are no items in your Cart\n");

    }

        void calculateCartTotalValue()
        {
            cartTotalValue = 0;
            for (Cart cart : cartItems)
            {
                cartTotalValue += cart.getProductPriceInCart();
            }
            System.out.println("\nTotal cart Value is ₹ " + cartTotalValue + "\n");
        }

    int updateInCartItems(String productNameInCart, String sellerName, int newItemQuantityInCart, float productUnitPrice)
    {
        int differenceInCartQuantityDuringUpdate = 0;
        for(Cart cart : cartItems)
            if(cart.getProductInCart().getProductName().equalsIgnoreCase(productNameInCart) && cart.getProductInCart().getSellerName().equalsIgnoreCase(sellerName))
            {
                differenceInCartQuantityDuringUpdate = cart.getItemQuantityInCart() - newItemQuantityInCart;

                cart.setItemQuantityInCart(cart.getItemQuantityInCart() - differenceInCartQuantityDuringUpdate);

                cart.setProductPriceInCart(cart.getItemQuantityInCart()*productUnitPrice);

                break;
            }

        //System.out.println("\nTotal cart Value is ₹ " + cartItems. + "\n");
        System.out.println("\nQuantity updated in cart");
        return differenceInCartQuantityDuringUpdate;
    }

    void deleteItemsFromCart(String productNameInCart, String sellerName)
    {
        int index = 0;
        for(Cart cart: cartItems)
            if(cart.getProductInCart().getProductName().equalsIgnoreCase(productNameInCart) && cart.getProductInCart().getSellerName().equalsIgnoreCase(sellerName))
                index = cartItems.indexOf(cart);

        cartItems.remove(index);
                //cartItems.remove(cart);
        System.out.println("\nItem successfully removed from cart");

    }
    }



