package br.com.tiagoiwamoto.profileadmin.core.repository;

import br.com.tiagoiwamoto.profileadmin.core.domain.CourseCategoryDomain;
import br.com.tiagoiwamoto.profileadmin.core.domain.CourseDomain;
import br.com.tiagoiwamoto.profileadmin.core.repository.out.CourseMetric;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CourseRepository extends IRepository<CourseDomain, Long> {

    Optional<CourseDomain> findByUuid(UUID uuid);
    List<CourseDomain> findAllByCourseCategory(CourseCategoryDomain courseCategoryDomain);
    @Query(value = "select c.* from tbl_courses c order by c.end_date desc limit 10", nativeQuery = true)
    List<CourseDomain> findTopLatestCourses();
    @Query(value = "select c.school school, sum(c.duration) as total from tbl_courses c group by c.school", nativeQuery = true)
    List<CourseMetric> findCourseByDuration();


}
