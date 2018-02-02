SELECT users_sections_links.user_id, sections.* 
FROM users_sections_links JOIN sections
ON users_sections_links.section_id = sections.id
WHERE (users_sections_links.user_id =: userID);



