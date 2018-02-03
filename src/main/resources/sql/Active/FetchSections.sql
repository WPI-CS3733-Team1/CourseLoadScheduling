SELECT users_sections_links.section_id, sections.*
FROM sections RIGHT OUTER JOIN users_sections_links
ON sections.id <> users_sections_links.section_id
ORDER BY course_id ASC;
