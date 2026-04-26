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

# Reflection — Assignment 7

Working on the GitHub Project Templates and Kanban Board implementation for the ZakaWise system introduced a new set of challenges compared to the previous assignment. While Assignment 6 focused more on planning through user stories and sprint structuring, this assignment required me to practically implement those plans using GitHub’s project management tools.

One of the main challenges was selecting the most appropriate GitHub project template. GitHub provides several templates such as Kanban, Team Planning, and Bug Tracker, and at first, it was not immediately clear which one best aligned with Agile principles and the needs of my system. I had to evaluate each template based on workflow structure, automation capabilities, and how well it supports iterative development. The Kanban template stood out because it provides a simple and clear visualization of tasks moving through different stages, which is ideal for Agile workflows. Unlike more complex templates, it allowed me to focus on tracking progress without unnecessary issues.

Customizing the Kanban board was another challenge. I had to go beyond the default columns and think about how my actual development process works. Adding columns such as "Testing" and "Blocked" required me to reflect on real-world software development practices. The "Testing" column ensures that tasks are not marked as complete before being verified, which aligns with quality assurance practices. The "Blocked" column helps identify issues that prevent progress, such as dependencies or technical problems. Deciding where tasks belong and maintaining consistency across the board required careful thought.

Linking user stories and tasks from Assignment 6 to the Kanban board also required attention to detail. I had to ensure that all previously defined user stories were properly represented and that tasks were clearly connected to those stories. This helped maintain traceability between planning and implementation. Assigning tasks, even as a solo developer, was important because it reflects how responsibilities would be distributed in a real team environment.

Comparing GitHub Projects with other tools such as Trello and Jira gave me a better understanding of project management tools in industry. GitHub Projects integrates directly with repositories and issues, which makes it very convenient for developers since everything is in one place. However, tools like Jira offer more advanced Agile features such as sprint tracking, reporting, and backlog management. Trello, on the other hand, is more user-friendly and visually simple but lacks deeper integration with development workflows. This comparison showed me that GitHub is a strong choice for developer-focused projects like ZakaWise.

Overall, this assignment improved my understanding of how Agile planning is implemented in practice. It showed me that creating user stories and sprint plans is only part of the process, and that visualizing and managing tasks through tools like Kanban boards is equally important. It also emphasised the importance of organization, workflow visibility, and continuous tracking in successful software development.

# Reflection — Assignment 8

Working on object state modeling and activity workflow modeling for the ZakaWise system was both challenging and insightful. This assignment required a deeper understanding of how the system behaves beyond just stated requirements and user stories.

One of the main challenges was determining the appropriate level of detail when defining states and activities. If too many states were included, the diagrams became complex and difficult to read. On the other hand, using too few states made the diagrams too simple and less useful for understanding system behavior. Finding a balance between detail and clarity required careful thought. For example, in the transaction state diagram, it was important to include validation and storage states without overcomplicating the flow.

Another challenge was aligning the diagrams with the Agile user stories created in Assignment 6. Agile focuses on iterative development and user value, while UML diagrams require a more structured representation of system behavior. Translating user stories such as “add transaction” into detailed workflows required identifying all steps, including validation, database storage, and system updates. This process helped me better understand how high-level requirements translate into actual system operations.

Creating activity diagrams was particularly useful in visualizing workflows. It allowed me to see how different components of the system interact, such as Firebase authentication, Spring Boot backend processing, and PostgreSQL database operations. Including decision points, such as validation checks, helped highlight how the system handles errors and alternative paths. Additionally, modeling workflows like dashboard generation showed how data flows through the system to produce meaningful outputs for users.

Comparing state diagrams and activity diagrams also improved my understanding of their different purposes. State diagrams focus on the lifecycle of an object, showing how it transitions between different states over time. For example, a transaction moves from created to validated to stored. In contrast, activity diagrams focus on processes and workflows, showing the sequence of actions required to complete a task. Both are important, but they provide different perspectives on the system.

Overall, this assignment strengthened my ability to model system behavior and improved my understanding of how different system components interact. It also reinforced the importance of aligning design models with requirements and user stories to ensure consistency and completeness. These skills are essential for designing scalable and maintainable systems like ZakaWise.


---

## Reflection - Asignment 9

Designing the domain model and class diagram for the ZakaWise system was one of the most challenging and important stages of the development process. This assignment required translating all previous work, including requirements, use cases, Agile planning, and behavioral diagrams, into a structured object-oriented design.

One of the main challenges I faced was identifying the correct level of abstraction for the domain entities. Initially, it was difficult to decide how many entities were needed and how detailed they should be. For example, I had to determine whether the dashboard should be treated as a separate entity or simply as a function of transactions. I decided to model it as a class because it has clear responsibilities such as generating reports and aggregating financial data.

Another challenge was defining relationships between entities. Understanding when to use association instead of composition required careful thinking. For example, a user “owns” transactions, budgets, and savings goals, but these entities can exist independently in the database. Therefore, I modeled these relationships as associations rather than composition. Additionally, linking transactions to budgets required careful consideration because not all transactions affect a budget, only expense transactions do.

Defining methods for each class was also challenging. Since this is a domain model, the focus is more on responsibilities rather than implementation. I had to ensure that each method reflects real system behavior, such as create(), update(), and delete() for transactions, and generateReport() for the dashboard. This helped ensure that the design aligns with actual system operations.

There were key decisions to be made on the design. One key decision was simplifying inheritance. Although it would be possible to create a base class such as “FinancialRecord” for transactions and budgets, I decided not to include it to keep the design simple and easy to understand. Another key decision was modeling the Database as a class, which is not always done in UML, but I included it to clearly represent data persistence in the system.

Overall, this assignment improved my understanding of object-oriented design and how to translate requirements into a structured system model. It highlighted the importance of relationships, responsibilities, and abstraction in building scalable systems. The process also emphasised how earlier stages of software development, such as requirements and use cases, directly influence system design. This experience will be valuable in developing real-world applications like ZakaWise.
