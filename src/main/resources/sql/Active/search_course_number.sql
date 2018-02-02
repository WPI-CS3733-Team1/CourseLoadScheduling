SELECT courses.name, courses.dept_id, courses.course_number, sections.crn, sections.section_name, sections.section_id, sections.expected_population, sections.academic_year, sections.academic_term, sections.start_time, sections.end_time, sections.days_per_week, sections.course_location
FROM sections RIGHT OUTER JOIN courses
ON courses.id = sections.course_id
WHERE course_number =: courseNumber
ORDER BY course_id ASC;


