CREATE TABLE IF NOT EXISTS jobs
(
    id INT GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(50) NOT NULL,
    status VARCHAR(20) NOT NULL,
    details TEXT NOT NULL,
    PRIMARY KEY (id)
    );

INSERT INTO jobs (name, status, details)
VALUES
       ('Junior Java Developer','New', 'Apply application design and development across multiple technology areas. Configure software products and systems. Develop product and platform prototypes. Develop proof of concepts across the technology stack. Drive consistent quality solution development and delivery.'),
       ('Software Development Engineer','In-Progress', 'Build solutions that reach millions of new customers all over the world, providing them with a world class shopping experience in their local language, payments, and currencies. Drive customer experience technology for supporting customers all over the world.'),
       ('Software Engineer','Done', 'Develop a strong understanding of relevant product area, codebase, and/or systems. Demonstrate proficiency in data analysis, programming and software engineering.'),
       ('Healthcare Consultant','In-Progress', 'Provide services related to disproportionate share hospital payments, uncompensated care, Medicare bad debts, wage index, graduate medical education/IME, settlement issues, PRRB Appeals, and other evolving special reimbursement projects.'),
       ('Lawyer','New', 'Provide guidance and advice on complex regulatory, compliance, and operational issues in the healthcare setting. Subject matters may include, but are not limited to pharmacy, the 340B Program, provider-based regulations, transactional work, healthcare venue options (free-standing ED’s, micro hospitals, ASC’s, etc.).'),
       ('OR Assistant','Done', 'Uses industrial cleaning agents and equipment following appropriate safety procedures. Clean and re-make stretchers/beds. Turnover all OR room between cases.'),
       ('Recruitment Assistant','Done', 'Sorting candidate applications based on work history. Supporting the Recruiters and Staffing Consultants. Ordering background checks. Ordering drug screens. Providing I-9 verification support.');