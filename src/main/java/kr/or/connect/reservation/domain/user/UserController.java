package kr.or.connect.reservation.domain.user;

import kr.or.connect.reservation.domain.user.dto.User;
import kr.or.connect.reservation.domain.user.dto.UserRequest;
import kr.or.connect.reservation.domain.user.dto.UserResponse;
import kr.or.connect.reservation.utils.UtilConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RestController
@RequestMapping(path = "/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserResponse join(@Valid UserRequest userRequest) {
        return userService.join(userRequest);
    }

    @PostMapping("/session")
    public UserResponse login(HttpServletRequest request, @Valid UserRequest userRequest) {
        UserResponse userResponse = new UserResponse();
        User user = userService.login(userRequest.getEmail(), userRequest.getPassword());

        HttpSession session = request.getSession();
        session.setAttribute(UtilConstant.LOGIN_ID, user.getEmail());

        userResponse.setUserId(user.getUserId());
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        userResponse.setType(user.getType());
        return userResponse;
    }

    @DeleteMapping("/session")
    public Map<String, String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        Map<String, String> map = new HashMap<>();
        map.put(UtilConstant.LOGIN_ID, "expired");
        return map;
    }
}
