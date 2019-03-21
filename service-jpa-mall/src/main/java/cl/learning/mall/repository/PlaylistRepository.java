package cl.learning.mall.repository;

import cl.learning.mall.domain.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author : 常亮
 * @Date : 10:36 2019-02-01
 * @Description :
 */
@Repository
public interface PlaylistRepository extends JpaRepository<Playlist,Long> {
    @Query("select distinct p.id from Playlist p where p.title in (select p.title from Playlist p group by p.title having (count(1)>1))")
    List<Long> filterByTitle();
}
