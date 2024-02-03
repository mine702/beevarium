package b203.varium.follow.controller;

import b203.varium.follow.service.FollowRelationService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/follow")
public class FollowController {

    private final FollowRelationService followRelationService;

    public FollowController(FollowRelationService followRelationService) {
        this.followRelationService = followRelationService;
    }

    // 방송국이 보는 자기 팔로워들
    @Transactional(readOnly = true)
    @GetMapping("/view/{stationId}")
    public void viewFollower(@PathVariable int stationId) {

    }

    // 유저가 보는 자신의 구독목록
    @Transactional(readOnly = true)
    @GetMapping("/view")
    public void viewFollowing() {

    }

    @GetMapping("/regist/{stationId}")
    public void registFollow(@PathVariable int stationId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());
    }

    @DeleteMapping("/deleteB/{stationId}")
    public void deleteFollow(@PathVariable int stationId) {

    }

    @DeleteMapping("/deleteF/{stationId}")
    public void deleteFollower(@PathVariable int stationId, @RequestParam int userNo) {

    }
}
