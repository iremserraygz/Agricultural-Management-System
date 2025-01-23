Agricultural Management System
Project Details
Course: COMP 1112 Object Oriented Programming
Semester: Spring 2022
Project: #2
Due Date: June 1, 2022, 23:59
Instructor's Note: "The bearing of a child takes nine months, no matter how many women are assigned." - Fred Brooks
Description
The goal of this project is to design and implement an Agricultural Crop Management System. This system involves managing three main entities:
1.	Crop (abstract class, divided into two types: Fruit and Vegetable)
2.	Store (stores only Fruits in cooler rooms, manages storage capacity)
3.	Supplier (manages crops, buys and sells fruits)
The project must handle operations such as importing/exporting crops, storing fruits, managing supplier budgets, and ensuring all interactions are in line with the system’s rules.
________________________________________
Entities and Functionalities
1. Crop (Abstract Class)
Attributes:
•	name: Name of the crop.
•	weight: Weight in kilograms.
•	cultivatedSeason: Season in which the crop was cultivated.
Methods:
•	Constructor(s): Initialize attributes (immutable).
•	Abstract Methods: 
o	toString(): Must be implemented by subclasses.
o	consumeIt(): “Vegetables are cooked” and “Fruits are consumed raw”.
o	storeIt(): Stores the crop in a store. Only valid for Fruits; for Vegetables, throws CanNotBeStoredException.
________________________________________
2. Fruit (Subclass of Crop, Implements Comparable Interface)
Additional Attributes:
•	taste: Taste of the fruit.
•	price: Price per kilogram.
•	CropKeeper: Instance of Store or Supplier.
Notes:
•	Fruits can be stored, bought, or sold.
________________________________________
3. Vegetable (Subclass of Crop, Implements Comparable Interface)
Additional Attributes:
•	cultivatedRegion: Region where the vegetable is cultivated.
•	CropKeeper: Instance of Store or Supplier.
Notes:
•	Vegetables cannot be stored in stores; they are kept in sheds or field booths.
________________________________________
4. Store
Attributes:
•	ID: Unique identifier, starts with “5”.
•	name: Name of the store.
•	maxCapacityArea: Total storage area (in square meters).
•	usedCapacityArea: Occupied storage area.
•	KGperSquareMeter: Kilograms per square meter (default: 10).
•	fruitList: List of stored fruits (unique fruits only).
Methods:
•	availableCapacity(): Calculates free storage capacity.
•	canBeStored(Fruit f): Checks if a fruit can be stored.
•	importCrop(Fruit f): Adds a fruit to storage. Throws CapacityNotEnoughException if not enough space.
•	exportCrop(Fruit f): Removes a fruit from storage. Throws FruitNotFoundException if not found.
Notes:
•	Only Fruits can be stored, and identical fruits are merged (e.g., Red Apple and Green Apple are distinct, but identical Red Apples are combined).
________________________________________
5. Supplier
Attributes:
•	ID: Unique identifier, starts with “1”.
•	name: Name of the supplier.
•	budget: Supplier’s total money in liras.
•	cropList: List of crops owned by the supplier.
Methods:
•	buyCrop(Crop c): Buys a crop from the store if the budget allows. Throws SupplierHasNotEnoughMoneyException or FruitNotAvailableException if conditions are not met.
•	sellCrop(Crop c): Sells a crop to a store. Throws FruitNotFoundException if the crop is not available.
Notes:
•	Fruits are stored in refrigerators; vegetables are stored in field booths.
________________________________________
Main Menu Flow
When the program runs, the following menu is displayed:
1.	Display all suppliers and their crop lists (including how they are stored and consumed).
2.	Display all stores and their fruit lists (including how they are stored and consumed).
3.	Buy a fruit for a Supplier and add it to a store’s fruit list.
4.	Sell a fruit from a Supplier to a store.
5.	Remove a fruit from a store.
6.	Remove a crop from a Supplier.
7.	Add a crop (to a store or a Supplier).
8.	Show the remaining budget of a Supplier.
9.	Show the remaining capacity of a store.
10.	Quit.
The program will continue running until the user selects 0 to quit.
________________________________________
File Structure
Input Files:
1.	Suppliers.txt: 
2.	[Supplier name, Supplier ID, budget]
3.	ArazMeyve, 1133, 1000
4.	AylarTarim, 1298, 1500
5.	HasanBey, 1322, 200
6.	ZehraCiftci, 1429, 1250
7.	Stores.txt: 
8.	[Store name, Store ID, maxCapacityArea(m2), KGperSquareMeter]
9.	Migros, 5343, 1000, 12
10.	File, 5967, 1200, 10
11.	Crops.txt: 
12.	[Name, type, kilogram, season/city, taste/price, ID of cropKeeper]
13.	RedApple, fruit, 45, winter, sweet, 3, 1133
14.	Orange, fruit, 50, autumn, sour, 4, 5967
15.	Kiwi, fruit, 10, autumn, sour, 10, 1133
16.	Parsley, vegetable, 25, Samsun, 1429
17.	Mint, vegetable, 15, Adana, 1429
18.	GreenApple, fruit, 25, winter, sweet, 6, 1133
19.	Orange, fruit, 20, autumn, sour, 4, 1322
20.	GreenApple, fruit, 5, winter, sweet, 6, 5343
21.	GreenBeans, vegetable, 22, Bursa, 1322
22.	Banana, fruit, 63, summer, sweet, 12, 5343
________________________________________
Notes
•	Implement exception handling for all required operations.
•	Feel free to add additional attributes or methods if needed.
•	Ensure proper file reading and object creation at the start of the program.
Good luck!

