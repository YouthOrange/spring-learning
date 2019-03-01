package cl.learning.mall.repository;

import cl.learning.mall.domain.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author : 常亮
 * @Date : 10:36 2019-02-01
 * @Description :
 */
@Repository
public interface PlaylistRepository extends JpaRepository<Playlist,Long> {
}
