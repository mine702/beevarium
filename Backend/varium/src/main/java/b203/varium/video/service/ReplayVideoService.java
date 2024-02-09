package b203.varium.video.service;

import b203.varium.broadcastStation.repository.BroadcastStationRepository;
import b203.varium.video.dto.VideoFileDTO;
import b203.varium.video.dto.ReplayVideoDTO;
import b203.varium.video.entity.VideoFile;
import b203.varium.video.entity.ReplayVideo;
import b203.varium.video.repository.ReplayVideoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReplayVideoService {

    private final ReplayVideoRepository replayVideoRepository;
    private final BroadcastStationRepository broadcastStationRepository;

    public List<ReplayVideoDTO> getReplayVideos(String streamerId) {
        int stationId = broadcastStationRepository.findByUser_UserId(streamerId).getId();
        List<ReplayVideo> list = replayVideoRepository.findAllByBroadcastStation_Id(stationId);
        List<ReplayVideoDTO> result = new ArrayList<>();

        for (ReplayVideo replay : list) {
            ReplayVideoDTO videoDTO = new ReplayVideoDTO();
            videoDTO.setId(replay.getId());
            videoDTO.setTitle(replay.getVideoTitle());
            videoDTO.setViewers(replay.getVideoViewers());
            videoDTO.setReplayVideoTextUrl("none");
            videoDTO.setCreatedDate(replay.getCreatedDate());

            VideoFile file = replay.getFile();
            VideoFileDTO fileData = new VideoFileDTO();
            fileData.setFilepath(file.getFilePath());
            fileData.setOriginFileName(file.getOriginName());
            fileData.setSaveFileName(file.getSavedName());

            videoDTO.setFileInfo(fileData);
            result.add(videoDTO);
        }

        return result;
    }

//    public void saveReplayInfo(String filePath, String ) {
//
//    }
}

