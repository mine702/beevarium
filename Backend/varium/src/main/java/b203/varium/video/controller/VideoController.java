package b203.varium.video.controller;

import b203.varium.video.dto.VideoDTO;
import b203.varium.video.entity.ClipVideo;
import b203.varium.video.entity.ReplayVideo;
import b203.varium.video.entity.Video;
import b203.varium.video.service.ClipVideoService;
import b203.varium.video.service.ReplayVideoService;
import b203.varium.video.service.VideoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/video")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;
    private final ReplayVideoService replayVideoService;
//    private final ClipVideoService clipVideoService;


    // 특정 방송국의 모든 다시보기 영상 조회
//    @GetMapping("/replay/{broadcastStationNo}")
//    public ResponseEntity<List<VideoDTO>> getAllReplayVideosByBroadcastStationNo(@PathVariable Integer broadcastStationNo) {
//        List<ReplayVideo> replayVideos = replayVideoService.getAllReplayVideosByBroadcastStationNo(broadcastStationNo);
//        List<VideoDTO> videoDTOs = replayVideos.stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(videoDTOs);
//    }

// 저장?
//    @PostMapping("/create")
//    public void createVideo(VideoDTO videoDTO) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println("user name : " + auth.getName());

//        Video videoRequest = convertToEntity(videoDTO);
//        Video savedVideo = videoService.saveVideo(videoRequest);
//        VideoDTO videoResponse = convertToDTO(savedVideo);
//        return ResponseEntity.ok(videoResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoDTO> getVideoById(@PathVariable Integer id) {
        return videoService.getVideoById(id)
                .map(this::convertToDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/hi")
    public ResponseEntity<List<VideoDTO>> getAllVideos() {
        List<Video> videos = videoService.getAllVideos();
        List<VideoDTO> videoDTOs = videos.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(videoDTOs);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVideo(@PathVariable Integer id) {
        videoService.deleteVideo(id);
        return ResponseEntity.ok().build();
    }

// DTO 변환 메소드 (이미 있는 메소드 활용)
private VideoDTO convertToDTO(Video video) {
    VideoDTO videoDTO = new VideoDTO();
    videoDTO.setId(video.getVideoNo());
    videoDTO.setTitle(video.getVideoTitle());
    videoDTO.setViewers(video.getVideoViewers());
    videoDTO.setUrl(video.getVideoUrl());
    videoDTO.setImgUrl(video.getVideoImgUrl());

    if (video instanceof ReplayVideo) {
        ReplayVideo replayVideo = (ReplayVideo) video;
        videoDTO.setReplayVideoTextUrl(replayVideo.getReplayVideoTextUrl());
        // ReplayVideo에 특화된 다른 필드가 있다면 여기서 설정
    } else if (video instanceof ClipVideo) {
        ClipVideo clipVideo = (ClipVideo) video;
        videoDTO.setUserNo(clipVideo.getUser().getUserNo());
        // ClipVideo에 특화된 다른 필드가 있다면 여기서 설정
    }

    return videoDTO;
}




//    // DTO 변환 메소드
//    private VideoDTO convertToDTO(Video video) {
//        VideoDTO videoDTO = new VideoDTO();
//        videoDTO.setId(video.getVideoNo());
//        videoDTO.setTitle(video.getVideoTitle());
//        videoDTO.setViewers(video.getVideoViewers());
//        videoDTO.setUrl(video.getVideoUrl());
//        videoDTO.setImgUrl(video.getVideoImgUrl());
//        // 추가 필드가 있다면 여기에 매핑 로직 추가
//        return videoDTO;
//    }

//    private Video convertToEntity(VideoDTO videoDTO) {
//        Video video;
//        switch (videoDTO.getType()) {
//            case "ClipVideo":
//                video = new ClipVideo();
//                break;
//            case "ReplayVideo":
//                video = new ReplayVideo();
//                break;
//            default:
//                throw new IllegalArgumentException("Invalid video type");
//        }
//
//        video.setVideoNo(videoDTO.getId());
//        video.setVideoTitle(videoDTO.getTitle());
//        video.setVideoViewers(videoDTO.getViewers());
//        video.setVideoUrl(videoDTO.getUrl());
//        video.setVideoImgUrl(videoDTO.getImgUrl());
//        // 추가 필드가 있다면 여기에 매핑 로직 추가
//
//        return video;
//    }


    // Additional endpoint methods
}
