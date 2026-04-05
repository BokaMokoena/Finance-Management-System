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

# Agile and sprint planning refelection

Working on the Agile planning for the ZakaWise system as a solo developer acting as both product owner and Scrum master presented several challenges, particularly in prioritization, estimation, and balancing technical and user-centered requirements.

One of the main challenges was prioritization. Since I am the only stakeholder, I had to simulate real-world decision-making by considering what would deliver the most value to a user. It was tempting to include all features, such as notifications and budget alerts, in the first sprint. However, applying the MoSCoW technique forced me to critically evaluate what is essential for a Minimum Viable Product (MVP). I realized that without core features like authentication, transaction management, and dashboard visualization, the system would not provide meaningful value. This made me to prioritize features that the system critical need and cannot function without and users will not be satisfied without them.

Another challenge was effort estimation. Assigning story points required me to think in terms of complexity rather than time, which was initially difficult. For example, implementing the dashboard and analytics seemed straightforward conceptually, but when considering data aggregation, report generation using transactions without the likes of PowerBI, and visualization, the complexity increased significantly. This made me more aware of hidden technical challenges and the importance of breaking down tasks into smaller, manageable units. I also had to be realistic with time as coding won't be straightforward nor easy due to bugs that I will encounter.

Aligning Agile practices with the system requirements was also challenging. The functional requirements were written in a traditional structured format, while Agile emphasizes flexibility and iterative delivery. Translating these requirements into user stories required careful interpretation to ensure that each story delivered user value and remained testable. Ensuring that each story followed the INVEST criteria helped maintain quality and clarity.

Additionally, managing multiple roles alone introduced a level of internal resistance. As a developer, I wanted to focus on implementation details, but as a product owner, I had to think from a user’s perspective. Balancing these viewpoints was not easy, but it improved my understanding of both technical and business aspects of the system.

Overall, this exercise demonstrated the importance of structured planning in Agile development. It showed that Agile is important in planning ahead and prioritizing the requirements because as a developer I would have started with the simplest functionality but that doesn't bring value to the user but as a scrum master I had to prioritize what the user needs.
