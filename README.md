# OOAD Projects
CSCI4448 Project 2

Names: Gyumin Youm, Freddy Rodriguez

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
