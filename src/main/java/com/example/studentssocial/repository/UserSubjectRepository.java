
package com.example.studentssocial.repository;


        import com.example.studentssocial.entity.User;
        import com.example.studentssocial.entity.UserSubject;
        import org.springframework.data.repository.CrudRepository;
        import org.springframework.stereotype.Repository;

        import javax.transaction.Transactional;
        import java.util.List;
        import java.util.Optional;

@Repository
@Transactional
public interface UserSubjectRepository extends CrudRepository<UserSubject,Long> {
        Optional<UserSubject> findById(Long id);
        List<UserSubject> findAllBySubjectId(Long id);
        List<UserSubject> findAllByUserId(Long id);
        List<UserSubject> findAllByUserIdAndAndSubjectId(Long userId, Long subjectId);
        void deleteAllByUserId(Long userId);
}

