SELECT sections.crn, sections.section_name, sections.section_id, sections.expected_population, sections.academic_year, sections.academic_term, sections.start_time, sections.end_time, sections.days_per_week, sections.course_location, courses.course_abrv, courses.name, courses.dept_id, courses.course_number 
FROM courses RIGHT OUTER JOIN sections
ON courses.id = sections.course_id
WHERE end_time = :endTime
ORDER BY course_id ASC;


