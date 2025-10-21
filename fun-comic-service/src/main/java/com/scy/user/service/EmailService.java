package com.scy.user.service;


import com.scy.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.regex.Pattern;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private RedisUtil redisTemplate;

    private static final int VERIFICATION_CODE_LENGTH = 6;
    private static final long CODE_EXPIRE_MINUTES = 5;

    public String generateVerificationCode() {
        Random random = new Random();
        StringBuilder code = new StringBuilder(VERIFICATION_CODE_LENGTH);
        for (int i = 0; i < VERIFICATION_CODE_LENGTH; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();
    }

    public String sendVerificationCode(String email) {
        boolean email1 = isValidEmail(email);
        if (!email1){
            return "邮箱格式不对";
        }
        String code = generateVerificationCode();
        // 存储验证码到 Redis，设置5分钟过期时间
      redisTemplate.set(email,code,50000);
        // 发送邮件
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("3328397500@qq.com");
        message.setTo(email);
        message.setSubject("Your Verification Code");
        message.setText("Your verification code is: " + code);
        mailSender.send(message);
        return "已发送";
    }

    public boolean verifyCode(String email, String inputCode) {
        String storedCode = redisTemplate.get(email);
        if (storedCode != null && storedCode.equals(inputCode)) {
            // 验证成功，删除 Redis 中的验证码
            redisTemplate.delete(email);
            return true;
        }
        return false;
    }
    public boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }


}
