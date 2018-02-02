SELECT sections.crn, sections.section_name, sections.section_id, sections.expected_population, sections.academic_year, sections.academic_term, sections.start_time, sections.end_time, sections.days_per_week, sections.course_location, courses.name, courses.dept_id, courses.course_number 
FROM courses RIGHT OUTER JOIN sections
ON courses.id = sections.course_id
WHERE academic_year =: academicYear
ORDER BY course_id ASC;


