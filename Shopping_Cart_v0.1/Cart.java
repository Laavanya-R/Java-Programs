import java.util.ArrayList;

public class Cart {
    Product productInCart = new Product();
    float productPriceInCart;
    int itemQuantityInCart;



    public Product getProductInCart() {
        return productInCart;
    }

    public void setProductInCart(Product productInCart) {
        this.productInCart = productInCart;
    }

    public float getProductPriceInCart() {
        return productPriceInCart;
    }

    public void setProductPriceInCart(float productPriceInCart) {
        this.productPriceInCart = productPriceInCart;
    }

    public int getItemQuantityInCart() {
        return itemQuantityInCart;
    }

    public void setItemQuantityInCart(int itemQuantityInCart) {
        this.itemQuantityInCart = itemQuantityInCart;
    }

}
