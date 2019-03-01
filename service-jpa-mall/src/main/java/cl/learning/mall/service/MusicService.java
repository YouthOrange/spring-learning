package cl.learning.mall.service;

import cl.learning.mall.domain.Playlist;
import cl.learning.mall.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author : 常亮
 * @Date : 10:38 2019-02-01
 * @Description :
 */
@Service
public class MusicService {
    @Autowired
    private PlaylistRepository playlistRepository;

    public Integer savePlayList(List<Playlist> playlistList){
        return playlistRepository.saveAll(playlistList).size();
    }
}
