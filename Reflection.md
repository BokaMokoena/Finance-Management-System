# Reflection — ZakaWise

- One of the biggest challenges faced was balancing the different needs of stakeholders. For example, users require a simple and fast system, while tester might prioritize security and system stability. Implementing strong security measures such as Firebase authentication adds complexity but is necessary due to the sensitive nature of financial data.

- Another challenge was ensuring that the system remains scalable while still being feasible for a single developer within a limited timeframe. Lay offs had to be made between advanced features and simplicity.

- Additionally, translating stakeholder concerns into clear and measurable requirements required careful thinking to ensure that each requirement is specific, testable, and directly linked to user needs.

- Overall, this assignment improved understanding of how real-world systems must balance usability, performance, and security.

# Use case and test case reflection

Developing the use cases and test cases for ZakaWise was tricky because I had to stick to the requirements defined in the SystemRequirements.md without diverting away from those requirements. One of the main challenges was ensuring that each functional requirement from Assignment 4 was properly mapped into a use case that reflects real user behavior.

One of the challenges was coming up with valid and accurate actors because they needed to be real live actors/cases that could be traced back to the system and my requirements didn't specifically identify all of them accurate and I had to create new ones like new user which is an unregistered user and that is not on my requirements specifications.

Another challenge was ensuring that I align my relationships on the use cases correctly because I had to align them to case that they are linked with in real live and it was easy to miss and make a misalignment. Example is I nearly linked PosGreSQL with create dashboard/report since the transactions are stored in PosGres and that was inaccurate as the database won't be editing nor storing the report in the system but I will have a 3rd party AI to handle all the dashboard creation and analytics

Writing use case specifications also required careful thinking about edge cases. For instance, scenarios like notifications are often overlooked but are critical for system reliability. Including alternative flows ensures the system is robust and user-friendly.

Test case development was relatively straightforward once the use cases were defined. Each use case naturally translates into one or more test cases. However, defining non-functional tests such as performance and security required thinking beyond basic functionality. Since ZakaWise handles sensitive financial data, security testing (especially Firebase token validation) is essential.

Overall, this assignment helped bridge the gap between requirements and implementation. It demonstrated how structured modeling (use cases) and validation (test cases) ensure that a system is both functional and reliable. It also reinforced the importance of clarity and consistency across all project documentation.
