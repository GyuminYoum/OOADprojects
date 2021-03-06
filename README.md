# OOAD Projects
CSCI4448 Project 2/3/4

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

Project 3:

Assumptions:
- Only one clerk is supposed to be sick per day, and their sick status clears the next day.
- No clerk gets sick twice in a row.
- When clerk buys item from customer, the item has a chance of being already tuned,equalized or adjusted. 
- An item being damaged and removed from inventory is also counted as an item being damaged by the tracker.
- Items sold via SellAccessories() method are also counted towards items sold in the tracker.

UML diagram changes:
- Added Sell method to Store class
- Added Factory create random Items
- Simplified our Decorator into a single class that extends Stringed class
- Observer/Logger/Tracker completely reworked to implement interfaces more efficiently and also allow for possible expansion in the future

Project 4:

Assumptions:
- The extra credit charts pop up after the user chooses to end interaction, closing one pop-up closes both charts so look at both before closing either pop-up.
- The two stores are not progressing concurrently. This is to say that the code performs 1 action for 1 store and the moves on to the next store to perform the same action.
- Item sold by user is chosen at random.
- Item bought by user is chosen at random among the items in the store's inventory.
- The duration is randomly determined. 
- The duration of the store simulation can't end on any number divisible by 7 because user can't come in to the store on a Sunday, so in such case get another random number between 10-30.
- askTime returns real time in Mountain time.
- Commands won't work unless the store is specified by the user (no default store).
- Since the chart was an extra credit opportunity, all the relevent arrayLists used to store necessary data are not included in the UML diagram.
- The last tracker to be printed by each store has the header "Final: " instead of "Day #: " to indicate that those are the final values for the tracker.
  - The trackers printed in the final summary will also say "Final: " 
- Tracker is eager loaded, logger is lazy loaded 
- Tests class not included in UML diagram because it wasn't directly a part of the simulation


UML diagram changes:
- Added Chart class to generate charts
- Changed parameters for Writer and Observer methods to keep track of each store separately
- Added Store attribute to Items
- Added classes for GuitarKit parts
- Added methods to GuitarKitFactories
