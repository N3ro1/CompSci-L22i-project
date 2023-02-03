# CompSci-L22i-project
## Documentation

### Target assessment level
The target assessment level of this work is 3.
### Specification
What does the program do?
The program is a banking system which
1.	Asks if a person is a part of the bank
2.	If no directs them to create an account
3.	If yes asks them to login using account number 
4.	After login the program asks if the person would like to deposit or withdraw money and acts accordingly
5.	The accounts with name, accountNumber and balance are stored in a text file
6.	The program is also able to read the text file and determine if the account number provided matches one in the file
7.	The program updates the balance of a person in the text file if they deposit or withdraw money
The user supplies the program with simple yes/no, deposit/withdraw or entering their name answers. All of the inputs are taken from the keyboard. Input is also taken from the text file when it is being read. 
#### Data format
The text file to which the user is writing consists of lines, each line containing: 
name, accountNumber, balance
All of them are stored as single words. When numbers in file need to read by the program parseInt() function is called to read them. 
### Correctness and exception handling
#### Typical test case
The person does not have an account with the bank and would like to create one. 
![image](https://user-images.githubusercontent.com/122979300/216600303-93e18675-3960-4076-8d4f-71d40b365a26.png)

This is if they don’t want to open an account.
![image](https://user-images.githubusercontent.com/122979300/216600382-a41394ff-b974-4cd5-9bc9-75c716c30490.png)

This is if they already have an account with the bank.
![image](https://user-images.githubusercontent.com/122979300/216600438-59b85713-fde8-4bd5-90c3-b12adca2d989.png)

Then if you want to deposit money.
![image](https://user-images.githubusercontent.com/122979300/216600500-93ec042d-d4d7-495d-bea0-efef850d062b.png)

And lastly if you withdraw money.
![image](https://user-images.githubusercontent.com/122979300/216600537-0b1ba94b-9e63-422f-b147-5064089e2f59.png)

All of the data is also stored in the bank_accounts.txt file.
![image](https://user-images.githubusercontent.com/122979300/216600598-4df06992-0d2f-451e-b1f7-f5848948a4d7.png)


#### Exception handling
The following are all the possible exceptions / special cases and the way they are handled.
1.	If input is not yes/no the program asks the user to answer with yes/no
2.	If input is not deposit/withdraw similar prompt is made
3.	If the user inputs a wrong account number the program says: Invalid account number. Please enter a valid number. 
4.	If the withdraw amount exceeds the users balance the program says: Incufficient funds. 
5.	If you write a name with two words the program breaks. I was not able to fix that case. 
6.	The readLines function will catch an exception if the file is not able to be read.
7.	Similarly the updateLines function will catch an exception if it is not able to update the balances in the file. 
### Resource management
The buffered reader resource is opened with the try-with-resources statement and is therefore closed automatically when the program no longer needs them, even in the case of an exception. 
### Reflection
Reflecting on the work it would have been useful to create a class to store the details of each bank account. 

### Program file
Attached in the repository
