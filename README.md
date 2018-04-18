# PointOfSale
## Implementation of simple point of sale. 

In this project user can pick any available product from the shelf and scan its bar code using the Bar-code Scanner. After scanning, bar code is send to the computer, which compares it with bar code placed in computer's database. If the product is found in database, its name and price is printed on LCD (Console). If the product is not found, error message "Product not found" is printed on LCD. If the code is empty, error message "Invalid bar-code" is printed on LCD. After entering "exit" command, receipt is printed on the printer (PrinterReceipt.txt file) containing a list of all previously successfully scanned items names and prices as well as total sum to be paid for all items; the total sum is also printed on LCD.

To test "Product not found" scan "Blender". To test "Invalid bar-code" use Orange or any other not listed item.
