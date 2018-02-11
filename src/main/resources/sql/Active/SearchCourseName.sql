SELECT courses.course_abrv, courses.name, courses.dept_id, courses.course_number, sections.id, sections.crn, sections.section_name, sections.section_id, sections.expected_population, sections.required_frequency, sections.academic_year, sections.academic_term, sections.start_time, sections.end_time, sections.days_per_week, sections.course_location
FROM sections RIGHT OUTER JOIN courses
ON courses.id = sections.course_id
WHERE name = :courseName
ORDER BY course_id ASC;


