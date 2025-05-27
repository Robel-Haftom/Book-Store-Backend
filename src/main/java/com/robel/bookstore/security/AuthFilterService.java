package com.robel.bookstore.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Service
public class AuthFilterService extends OncePerRequestFilter {

    @Autowired
    private JwtUtilityService jwtUtilityService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        String jwt = "";
        String userName;
//        String path = request.getRequestURI();
//        String method = request.getMethod();

//
//        if(path.startsWith("/api/v1/auth")){
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        if((path.startsWith("/api/v1/books") ||
//                path.startsWith("/api/v1/categories") ||
//                path.startsWith("/images/**"))
//                && method.equals("GET")){
//            filterChain.doFilter(request, response);
//            return;
//        }


        final String header = request.getHeader("Authorization"); // id jwt token is from the auth header as 'Bearer <token>

        if(header == null || !header.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        jwt = header.substring(7);  // get jwt token from the header


//        Cookie[] cookies = request.getCookies();
//        if(cookies != null){
//            for(Cookie cookie : cookies){
//                if(cookie.getName().equals("token")){
//                    jwt = cookie.getValue();
//                    break;
//                }
//            }
//        }

//        if(cookies == null || jwt.isEmpty()){
//            filterChain.doFilter(request, response);
//            return;
//        }

        userName = jwtUtilityService.extractUsername(jwt);

        if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
            if(jwtUtilityService.isValidToken(jwt, userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );
                usernamePasswordAuthenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
        }

}
