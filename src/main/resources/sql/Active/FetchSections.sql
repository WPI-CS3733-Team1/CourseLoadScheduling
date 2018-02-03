SELECT users_sections_links.section_id, sections.*
FROM sections RIGHT OUTER JOIN users_sections_links
ON sections.id <> users_sections_links.section_id


WHERE course_id IN

(
	SELECT course_id
	FROM courses
	WHERE dept_id =:deptID 
)
ORDER BY course_id ASC;
