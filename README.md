# Strategy To Build the System from Day 1 to Go-Live

## Day 1 - Day 10: Planning and Requirements Gathering(Sprint 0)

- **Meet with Stakeholders:** Engage with the product owner, Scrum Master, business team, and relevant stakeholders to understand current pain points, future requirements, and desired features.
- **Requirement Analysis:** Conduct thorough analysis of existing system architecture, identify bottlenecks, scalability issues, and areas for improvement.
- **Generate High-level Requirements:** From the requirements analysis, propose a requirement specification document containing user stories and use-case specifications. 
- **Define Tech Stack:** Collaborate with the team to select the most suitable back-end technologies considering scalability, performance, security, and ease of maintenance. 
- **Create an architecture haiku document of the system:** Summarize the key components, constraints, requirements, as well as design and deployment decision of the system into a single page document. 
- **Establish Development Workflow:** Implement Agile methodologies such as Scrum or Kanban, set up version control (e.g., Git), and establish CI/CD pipelines for automated testing and deployment, and create a backlog for the entire project to capture all requirements and prioritize tasks effectively.

## Day 11 – Day 76: Recurring Sprint to Implement each Feature in the System Domain (5 sprints in total)

### Day 1 of the sprint:

- **Review the backlog with the team:** Ensure it reflects the latest requirements and priorities.
- **Define sprint goal:** Align with the overall project objectives.
- **Break down user stories:** Into smaller, actionable tasks and assign these tasks to team members.
- **Estimate effort required:** For each task using techniques such as story points or time-based estimates.
- **Select tasks for the sprint:** Ensuring workload is balanced and achievable.

### Day 2 – 6 of the sprint:

- **Perform Object Oriented Analysis and Design**
- **Update API documentation**
- **Perform Test Driven Development:** Write unit and integration tests for the new feature before implementing it.

### Day 7-12 of the sprint:

- **Developers work on implementing tasks:** Following coding standards, best practices, and design guidelines.
- **Perform peer code reviews:** Promote knowledge sharing and maintain code quality.
- **Integrate code changes:** Frequently into the main codebase.
- **Automated testing:** Ensure code quality and reliability.
- **Track sprint progress:** Using burndown charts or other metrics.
- **Be flexible:** Accommodate changes or new insights.

### Day 13-14 of the sprint:

- **Sprint Review:** Showcase completed work to stakeholders and gather feedback.
- **Sprint Retrospective:** Reflect on the sprint as a team and discuss process improvements.
- **Update Documentation:** Ensure all documentation is up-to-date and accessible.

## Day 76 – 90 (Go-Live): Full Deployment and Support (Sprint 6)

- **Full Deployment:** Roll out the updated solution to all users.
- **User Training and Support:** Provide training sessions and documentation.
- **User Acceptance Testing (UAT):** Conduct final UAT sessions.
- **Celebration and Recognition:** Recognize team efforts.

# Spring Boot Application Details

## API Documentation

- **API Documentation Path:** /api-docs
- **Swagger UI Path:** /swagger-ui/index.html

## Main APIs

### Customer Entity:

- **CREATE :** http://localhost:8080/customers/create
- **READ :** http://localhost:8080/customers/customer/{customerId}
- **READ ALL:** http://localhost:8080/customers/  
  

