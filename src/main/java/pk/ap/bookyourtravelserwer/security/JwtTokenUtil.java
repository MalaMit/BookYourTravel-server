package pk.ap.bookyourtravelserwer.security;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class JwtTokenUtil {
    private static String AUTHORIZATION_HEADER ="Authorization";

    public static String getTokenFromRequest(HttpServletRequest request){
        String authHeader = request.getHeader(AUTHORIZATION_HEADER);

        if(StringUtils.hasText(authHeader) && StringUtils.startsWithIgnoreCase(authHeader,"Bearer ")){
            return authHeader.replace("Bearer ","");
        }
        return null;
    }
}
