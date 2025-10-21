package com.scy.business.user;

import com.scy.api.R;
import com.scy.api.Result;
import com.scy.business.user.service.AppUserService;
import com.scy.common.annotation.Login;
import com.scy.common.annotation.LoginUser;
import com.scy.common.config.JwtUtils;
import com.scy.modles.user.DeviceIdRequest;
import com.scy.modles.user.EditUserInfo;
import com.scy.modles.user.UserInfo;
import com.scy.user.pojo.User;
import com.scy.user.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController("adminUserController")
@RequestMapping("/app")
@CrossOrigin
@Slf4j
public class UserController {

//    private static final Map<String, String> map = new ConcurrentHashMap<String, String>();

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private AppUserService userService;
    @Autowired
    private  JwtUtils jwtUtils;


    @Autowired
    private EmailService emailService;



    @PostMapping("/create")
    @Operation(
            summary = "创建用户",
            description = "创建用户，返回用户信息"
    )
    public Result<UserInfo> creteYou(@RequestBody DeviceIdRequest deviceId) {
        if (deviceId.getDeviceId() == null){
            return new Result<UserInfo>().ok("设备标识为空");
        }else {
            UserInfo userInfo = userService.createUser(deviceId.getDeviceId());
            return new Result<UserInfo>().ok(userInfo);
        }
    }


    @GetMapping("/updateUser")
    @Operation(
          summary = "修改信息",
          description = "用户修改个人信息"
    )
    @Login
    public Result<UserInfo> updateUser( @LoginUser User user, @RequestBody EditUserInfo deviceId) {
        log.info("用户修改信息");
        log.info("用户信息: " + user);
        log.info("设备标识: " + deviceId);
        UserInfo userInfo=  userService.updateUser(user,deviceId);
        return new Result<UserInfo>().ok(userInfo);

    }

    @GetMapping("/getEmailCode")
    @Operation(
            summary = "邮箱验证码发送",
            description = "邮箱验证码发送"
    )
    public R  getEmailCode(@RequestParam("email")  String email) {
        emailService.sendVerificationCode(email);
        return R.ok("邮箱验证码已发送");
    }
    @GetMapping("/bindEmail")
    @Operation(
            summary = "绑定邮箱",
            description = "用户绑定邮箱。"
    )
    @Login
    public Result<UserInfo> bindEmail (@LoginUser User user, String email, String code) {
        boolean b = emailService.verifyCode(email, code);
        if (b) {
            user.setEmail(email);
            String s = userService.bindEmail(user);
            return new Result<UserInfo>().ok(s);
        }
        return new Result<UserInfo>().ok(" try again !");

    }



//    @GetMapping("/clearState")
//    public Result<Object> clearUserloginState(String email) {
//            redisTemplate.opsForHash().put("login-State",email,null);
//            return Result.success("OUT SUCCESS");
//
//    }
//    @GetMapping("/GetCode")
//    public ResponseResult<String> getUserById(@RequestParam("email")  String email) {
//        emailService.sendVerificationCode(email);
//        return ResponseResult.success("邮箱验证码已发送");
//    }
//
//    /**
//     * 获取好友
//     * Auth：me
//     * @param userid
//     * @return
//     */
//    @GetMapping("/GetFriends")
//    public ResponseResult<List<Integer>> GetFriends(@RequestParam("userid")  Integer userid) {
//        List<Integer> friend = userconService.getFriend(userid);
//        return ResponseResult.success(friend);
//    }
//    @GetMapping("/verifyToken")
//    @CrossOrigin
//    public ResponseResult<Boolean> verifyToken(@RequestParam("token") String token) {
//        boolean b = JwtUtils.validateToken(token);
//        return ResponseResult.success(b);
//    }
//
//    /**
//     * 轮播图
//     * **/
//    @GetMapping("/swiper")
//    public ResponseResult<List<Comic>> swiper() {
//        List<Comic> comics = comicService.getnewComic();
//        return ResponseResult.success(comics);
//    }
//
//    /**
//     * 获取好友
//     * @return
//     */
//    @GetMapping("/getfriends")
//    public ResponseResult<List<User>> getfriends(@RequestParam("email") String email) {
//        List<User> users = userService.getfriends(email);
//        return ResponseResult.success(users);
//    }
//    /**
//     * 金币增加
//     */
//    @GetMapping("/Getjinbi")
//    public ResponseResult<String> Getjinbi() {
//        System.out.println("........金币到账了");
//        return ResponseResult.success("金币到账了");
//    }
//
//    @GetMapping("/Gethostriy")
//    public ResponseResult< List<ChatHoristry>> Gethostriy(@RequestParam("fromTo") String fromTo) {
//        List<ChatHoristry> list = userService.gethostriy(fromTo);
//        return ResponseResult.success(list);
//    }
//
//    /**
//     * 修改信息用户
//     * @param userData
//     * @return
//     */
//    @PostMapping("/updateuserdata")
//    public ResponseResult<User> addhostiy(@RequestBody UserData userData) {
//        if(userData.getUserid()==null){
//            return ResponseResult.fail("信息丢失请重新登录");
//        }
//        User user =userService.updatauser(userData);
//        return ResponseResult.success(user);
//   }
   //获取自己的收藏

   //添加自己的收藏资源

}
