SELECT users_sections_links.user_id, sections.course_id, courses.course_abrv, courses.course_number
FROM users_sections_links JOIN sections ON users_sections_links.section_id = sections.id
JOIN courses ON sections.course_id = courses.id
WHERE users_sections_links.user_id = :userID;
