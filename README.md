# ShopCartApp-LITG
ShopCart is an app aimed to help users plan their next shopping trip easily. You can form a group with some members or be alone, it doesn't matter. Just keep on adding the items to the list whenever you remember to buy something along with their quantity and category. This way, while shopping, you can choose shop by category for a better and planned shopping process. You can just click the items that you have bought and press shop. A special feature of this app is that you can also have a look at the previously bought items.

DATABASE:
There are 5 table structures.
1) Totalmembers:
Columns: 1)MID (Member Id)
         2)MName (Member Name)
         3)Password
         4)GroupExists (Y/N) (default:N)
2) Groups:
Columns: 1)GID
         2)Gpassword
         3)Admin
3) Groupmember:
Columns: 1)GID
         2)MID
4) Cart<gid>:
Columns: 1)Items  
         2)Quantity 
         3)Unit 
         4)Category
         5)Bought
  This group is created everytime a group is created. It is specific to one group only.
 5) Histcart<gid>:
Columns: 1)Items  
         2)Quantity 
         3)Unit 
         4)Category
         5)Bought
  This group is created everytime a group is created.  It is specific to one group only.
