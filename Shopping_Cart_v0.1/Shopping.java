import java.util.Scanner;

public class Shopping {

    static Scanner sc = new Scanner(System.in);
    public static void main(String[] n) {
        ShoppingWebsiteMasterData.setShoppingWebsiteMasterData();
        //ShoppingWebsiteMasterData.displayAvailableProducts();

        System.out.println();

        System.out.print("Welcome to Online Shopping \n Enter '1' for Seller\n Enter '2' for Buyer\n Choice : ");
        int maxLoginCount = 3;
        do {
            int loginUserChoice = sc.nextInt();
            if (loginUserChoice == 1)
                {sellerMenu(); break;}
            else if (loginUserChoice == 2)
                {buyerMenu(); break;}
            else {
                if (maxLoginCount > 1)
                    System.out.print("\n Not a valid choice. \n Enter '1' for Seller\n Enter '2' for Buyer\n Choice : ");
                else if (maxLoginCount == 1)
                    System.out.print("\nMaximum of 3 attempts only. Try again later");
            }

            maxLoginCount--;
        }while(maxLoginCount>0);

        sc.close();
    }
    static void sellerMenu()
    {
        int maxLoginCount = 3;
        do {
            System.out.print("\nEnter User ID - same as Seller Name : "); //similar to User Authentication
            String sellerName = sc.next();

            if (ShoppingWebsiteMasterData.isValidSeller(sellerName)) {
                do {
                    Seller seller = new Seller();
                    System.out.print("Enter \n    '1' to Add product \n    '2' to Update product \n    '3' to Remove a product\n    Choice:");
                    int sellerActionChoice = sc.nextInt();
                    if (sellerActionChoice == 1)
                        SellerActions.addProductBySeller(seller);
                    if (sellerActionChoice == 2)
                        SellerActions.updateProductBySeller(seller);
                    if (sellerActionChoice == 3)
                        SellerActions.removeProductBySeller(seller);
                    System.out.print("\nAdd more products or update or delete product? Y/N : ");
                }while(sc.next().equalsIgnoreCase("Y"));

                System.out.print("\nThank You! You are now logged out");
                break;
            }
            else {
                if (maxLoginCount > 1)
                    System.out.print("Not a valid user - try again");
                else if (maxLoginCount == 1)
                    System.out.print("\nMaximum of 3 Login attempts only. Try again later");
            }
            maxLoginCount--;
        }while(maxLoginCount>0);
    }
    static void buyerMenu()
    {
       System.out.print("\nEnter User ID - same as Buyer Name : "); //similar to User Authentication
        String buyerName = sc.next();

        int maxLoginCount = 3;
        shopping:
        do {
        if(ShoppingWebsiteMasterData.isValidBuyer(buyerName)) {
            Buyer buyer = new Buyer();
            boolean userWantsToShopMore = false;

            do {
                /*System.out.print("Enter \n    '1' to View cart \n    '2' to Add new products to Cart \n    Choice:");
                int buyerActionChoice = sc.nextInt();

                if (buyerActionChoice == 1)
                    buyer.viewCartItems();

                if (buyerActionChoice == 2)
                    BuyerActions.addProductToCartByBuyer(buyer);

                System.out.print("More Actions - ");
                 */
                System.out.print("\nEnter \n    '1' to View cart \n    '2' to Add products to Cart \n    '3' to Update a product count \n    '4' to Remove a product from cart\n    '5' to Exit\n    Choice : ");
                int buyerAction2ndChoice = sc.nextInt();
                if (buyerAction2ndChoice == 1)
                    buyer.viewCartItems();
                else if (buyerAction2ndChoice == 2)
                    BuyerActions.addProductToCartByBuyer(buyer);
                else if (buyerAction2ndChoice == 3)
                    BuyerActions.updateProductCountInCartByBuyer(buyer);
                else if (buyerAction2ndChoice == 4)
                    BuyerActions.removeProductFromCartByBuyer(buyer);
                else if (buyerAction2ndChoice == 5)
                    {System.out.print("\nExiting. Thanks for shopping!"); break shopping;}
                else
                    System.out.print("\nNot a valid choice");

                System.out.print("\nWould you like to ADD products? Update or Remove products? Go back to Menu? Y/N : ");
                String doesUserWantsToShopMore = sc.next();

                if(doesUserWantsToShopMore.equalsIgnoreCase("Y"))
                    userWantsToShopMore = true;

                else if (doesUserWantsToShopMore.equalsIgnoreCase("N"))
                {
                    userWantsToShopMore = false;
                    if(buyer.getCartItems().size() == 0)
                    {
                        System.out.print("Going back to Menu!");
                    }
                    else {
                        System.out.print("Would you like to proceed to payment? Y/N : ");
                        String doesUserWantsToProceedPayment = sc.next();
                        if (doesUserWantsToProceedPayment.equalsIgnoreCase("Y"))
                            BuyerActions.buyProductsByBuyer(buyer);
                        else if (doesUserWantsToProceedPayment.equalsIgnoreCase("N"))
                            System.out.print("Not Proceeding to payment. Try again later");
                    }
                }
                else
                    System.out.print("Not a valid input. Going back to Menu. ");

            }while(userWantsToShopMore);
        }
        else
        {
            if (maxLoginCount > 1)
                System.out.print("Not a valid user - try again");
            else if (maxLoginCount == 1)
                System.out.print("\nMaximum of 3 Login attempts only. Try again later");
        }
        maxLoginCount--;
        }while(maxLoginCount>0);
    }
}
