# OOAD Projects
CSCI4448 Project 2

Names: Gyumin Youm, Freddy Rodriguez, Henock Zemenfes

JDK: 8

Implementations/Assumptions:
- Attributes are randomly determined when an order is placed for out-of-stock items
- PurchasePrice is proportional to condition, however price is still determined randomly
  - Excellent: 100% of purchasePrice
  - Very good: 50% of purchasePrice
  - Good: 33% of purchasePrice
  - Fair: 25% of purchasePrice
  - Poor: 20% of purchasePrice

UML diagram changes:
- Redesigned Store class and methods to keep track of 
  - inventory
  - sold items
  - ordered items
  - Clerk currently on shift
  - cash register 
- Added FNMS class for initializing and running our simulation
- Added name and num variables to Customer class to improve output readability

Project 3:
Assumptions:
- Only one clerk is supposed to be sick per day, and their sick status clears the next day.
- No clerk gets sick twice in a row.
- when clerk buys item from customer, the item has a chance of being already tuned,equalized or adjusted. 

UML diagram changes:
- Added Sell method to Store class
- Added Factory create random Items
- Simplified our Decorator into a single class that extends Stringed class
