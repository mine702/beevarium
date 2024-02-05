package b203.varium.broadcasting.controller;

import b203.varium.broadcasting.dto.ReqDTO;
import b203.varium.broadcasting.service.BroadcastingService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@RestController
@RequestMapping("/live")
public class BroadcastingController {

    private final BroadcastingService broadcastingService;

    public BroadcastingController(BroadcastingService broadcastingService) {
        this.broadcastingService = broadcastingService;
    }

    @PostMapping("/start")
    public ResponseEntity<Map<String, String>> createBroadcasting(ReqDTO reqDTO) {
        return ResponseEntity.ok(broadcastingService.startBroadcasting(reqDTO.getStationId(),
                reqDTO.getBroadcastingTitle(), reqDTO.getBroadcastingUrl(),
                reqDTO.getBroadcastingImgUrl(), reqDTO.getTagList()));
    }

    // 사용자가 구독중인 스트리머 실시간 방송 목록
    @GetMapping("/list")
    public void viewBroadcastingList() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.info("user name : " + auth.getName());


    }

    @GetMapping("/end/{stationId}")
    public ResponseEntity<Map<String, String>> deleteBroadcasting(@PathVariable int stationId) {

        return ResponseEntity.ok(broadcastingService.endBroadcasting(stationId));
    }
}
