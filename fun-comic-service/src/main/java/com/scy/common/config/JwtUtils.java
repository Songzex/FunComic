package com.scy.common.config;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.scy.user.dao.UserMapper;
import com.scy.user.pojo.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtils {
    @Autowired
    private static UserMapper userMapper;

  /**、 固定使用一个密钥，且要保证密钥长度足够*/
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor("yourVeryLongSecretKeyForJwtValidation4321432143214321432143214321".getBytes());

    private static final long EXPIRATION_TIME = 864_000_000; // 令牌过期时间：10天

    // 生成 token
    public String generateToken(String email) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SECRET_KEY, SignatureAlgorithm.HS512)  // 使用 HS512 和固定的密钥
                .compact();
    }

    // 从 token 中获取 email 信息
    public static String getEmailFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)  // 使用同一个密钥来解析
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();  // 返回 token 中的主题（email）
    }

    // 验证 token
    public static boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)  // 同样使用这个密钥来验证 token
                    .build()
                    .parseClaimsJws(token);
            return true;  // 验证通过
        } catch (JwtException | IllegalArgumentException e) {
            // token 验证失败
            return false;
        }
    }

    /**
     * 获取用户ID
     * @param email
     * @return
     */
    public static Integer getuseridByToken(String email) {
       // String email = getEmailFromToken(ThreadLocalToken.getToken());
        Integer userid = userMapper.selectOne(new LambdaUpdateWrapper<User>().eq(User::getEmail, email)).getId();
        return userid;
    }
}
