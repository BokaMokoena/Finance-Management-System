![My Image](https://raw.githubusercontent.com/BokaMokoena/Finance-Management-System/main/Use%20case%20ZakaWise.png)

# Use Case Specification

## 1. Register/Login User
**Actor:** User  
**Description:** User authenticates via Firebase  
**Precondition:** Internet connection  
**Postcondition:** User is logged in and token issued  

**Basic Flow:**  
 User enters email/password or Google login  
 Firebase authenticates  
 Token is generated  
 User accesses dashboard  

## 2. Manage Profile
**Actor:** User  
**Precondition:** User logged in  
**Postcondition:** Profile updated  

**Basic Flow:**  
 User opens profile  
 Edits details (income, currency, profile picture)  
 Saves changes  

## 3. Add Transaction
**Actor:** User  
**Precondition:** Logged in  
**Postcondition:** Transaction stored  

**Basic Flow:**  
 Enter amount, category, type  
 Submit  
 System saves  

## 4. Edit/Delete Transaction
**Actor:** User  
**Precondition:** Added transaction  
**Postcondition:** Transaction updated/deleted  

**Basic Flow:**  
Select transaction  
Edit or delete  
System updates database  

## 5. Manage Budget
**Actor:** User 
**Precondition:** Created budget  
**Postcondition:** Budget updated  

**Basic Flow:**  
Create budget  
Set category & limit  
System tracks usage  

## 6. Manage Savings Goals
**Actor:** User  
**Precondition:** Created savings goal
**Postcondition:** Update/view savings goals  

**Basic Flow:**  
Create goal  
Enter target amount/date  
System tracks progress 

## 7. Create Dashboard
**Actor:** Analytic generator  
**Precondition:** Transaction added  
**Postcondition:** View report  

**Basic Flow:**  
Retrieve transactions
Create report(charts and graphs)
Display dasboard/report

## 8. View Dashboard
**Actor:** User  
**Precondition:** Dashboard created  
**Postcondition:** View report  

**Basic Flow:**  
Open dashboard  
View income/expenses  
View charts   

## 9. Search & Filter Transactions
**Actor:** User  
**Precondition:** Have a transaction  
**Postcondition:** Transaction found  

**Basic Flow:**  
Enter filters (date/category)  
System retrieves results  
