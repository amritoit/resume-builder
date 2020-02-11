# --- Sample dataset

# --- !Ups
insert into persons (id, name,first_name,last_name,resume_name,profession,about_me,contact_id,created_at,updated_at)
values (1, 'Amritendu Mondal','Amritendu','Mondal','my_master_resume', 'Software Engineer','Experienced in Software Development and Design with over 8 years of experience. Excellent reputation for resolving problems, improving customer satisfaction, and driving overall operational improvements. Enthusiastic engineer eager to contribute to team success through hard work, attention to detail and excellent organizational skills. Clear understanding of Software designing and development. Motivated to learn, grow and excel.',
1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into contacts (id, person_id,mobile,email,city,state,zipcode,address,created_at,updated_at) values (1, 1,2355345345,'sample@gmail.com','chennai','TamilNadu','234432','Perungudi, OMR Street',CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into social_links (id, person_id, contact_id, name, value, created_at, updated_at) values (1,1,1,'twitter','amritoit',CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into social_links (id, person_id, contact_id, name, value, created_at, updated_at) values (2,1,1,'linkedin','https://www.linkedin.com/in/amritoit',CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into skills (id, person_id, name, proficiency, created_at, updated_at) values (1, 1, 'Java', 4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into skills (id, person_id, name, proficiency, created_at, updated_at) values (2, 1, 'Elasticsearch', 4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into skills (id, person_id, name, proficiency, created_at, updated_at) values  (3, 1, 'NLP', 3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into skills (id, person_id, name, proficiency, created_at, updated_at) values  (4, 1, 'ML', 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into work_infos (id, person_id, company_name, company_city, company_state, start_date, end_date, role, currently_working_here, created_at, updated_at)
values  (1, 1, 'Citicorp', 'Pune', 'Maharashtra', '2014-07-02', '2016-07-17', 'Software Development Engineer', 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into work_role_desc (id, person_id, work_info_id, description, created_at, updated_at)
values (1, 1, 1, 'Analysed the Citi Risk systems and modified the code to support new business requirements.', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into work_role_desc (id, person_id, work_info_id, description, created_at, updated_at)
values (2, 1, 1, 'Automated Citi investment banking quarterly reports, which Citi used to generate manually with help of 10 Citi banker in every quarter.', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into work_role_desc (id, person_id, work_info_id, description, created_at, updated_at)
values (3, 1, 1, 'Outside of my responsibility I had worked to maintain coding standard using static code analysis tool sonar.', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into work_role_desc (id, person_id, work_info_id, description, created_at, updated_at)
values (4, 1, 1, 'Our main application was in Java and microservices like pricing engine, reporting was written in C++.', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into work_infos (id, person_id, company_name, company_city, company_state, start_date, end_date, role, currently_working_here, created_at, updated_at)
values  (2, 1, 'Freshworks', 'Chennai', 'Tamilnadu', '2016-07-18', '2018-11-20', 'Senior Software Engineer', 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into work_role_desc (id, person_id, work_info_id, description, created_at, updated_at)
values (5, 1, 2, 'Led a team with 3 members and developed platform for search and filtering in www.freshsales.io.', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into work_role_desc (id, person_id, work_info_id, description, created_at, updated_at)
values (6, 1, 2, 'Contributed ideas and suggestions in team meetings with Software Engineers and Project Managers and delivered updates on deadlines, designs and enhancements.', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into work_role_desc (id, person_id, work_info_id, description, created_at, updated_at)
values (7, 1, 2, 'Module lead of Search and Filtering in Freshsales, Designed and developed the whole search request flow.', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into work_role_desc (id, person_id, work_info_id, description, created_at, updated_at)
values (8, 1, 2, 'Outside of my responsibility I had regularly worked for code coverage and made sure that the peers are having correct code coverage.This helped team to go for CI CD smoothly later.', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into work_role_desc (id, person_id, work_info_id, description, created_at, updated_at)
values (9, 1, 2, 'Was part of company recruitment team and interview candidate through out my role.', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());


insert into work_infos (id, person_id, company_name, company_city, company_state, start_date, role, currently_working_here, created_at, updated_at)
values  (3, 1, 'Freshworks', 'Chennai', 'Tamilnadu', '2016-07-18', 'Lead Data Scientist', 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into work_role_desc (id, person_id, work_info_id, description, created_at, updated_at)
values (10, 1, 3, 'Learnt NLP and ML and build various model for Freshdesk.', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into work_role_desc (id, person_id, work_info_id, description, created_at, updated_at)
values (11, 1, 3, 'Took all the models in production server and served them for various feature', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into work_role_desc (id, person_id, work_info_id, description, created_at, updated_at)
values (12, 1, 3, 'Module lead of Answer Bot model, one of the main bot engine in Freshdesk.', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into work_role_desc (id, person_id, work_info_id, description, created_at, updated_at)
values (13, 1, 3, 'Outside of my responsibility I had regularly worked with devops and dockerize the systems for scalability and effective use of infra.', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into work_role_desc (id, person_id, work_info_id, description, created_at, updated_at)
values (14, 1, 3, 'Was part of company recruitment team and interview candidate through out my role.', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());


insert into education (id, person_id, school_name, school_location, degree, field_of_study, start_date, end_date,currently_studying_here,description,created_at,updated_at)
values  (1, 1, 'BE College', 'Shibpur, Howrah', 'BE', 'CS', '2005-07-01', '2009-07-01', 0, 'Current name of this institute: Indian Institute of Engineering Science And Technology, Shibpur.', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into education (id, person_id, school_name, school_location, degree, field_of_study, start_date, end_date,currently_studying_here,description,created_at,updated_at)
values  (2, 1, 'Indian Institute of Technology', 'Madras', 'BE', 'CS', '2012-07-01', '2014-07-01', 0, null, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());


insert into additional_info (id, person_id, name, value, year, created_at, updated_at) values  (1, 1, 'Patent', 'Clustering and incremental way to find similar question.', 2020, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into additional_info (id, person_id, name, value, year, created_at, updated_at) values  (2, 1, 'Patent', 'Thankyou Detector, The powerful model to detect a thankyou note.', 2020, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());


# --- !Downs

delete from persons;
delete from contacts;
delete from social_links;
delete from skills;
delete from work_infos;
delete from work_role_desc;
delete from education;
delete from additional_info;