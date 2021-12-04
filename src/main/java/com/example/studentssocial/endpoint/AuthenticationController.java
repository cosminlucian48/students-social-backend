//package com.example.studentssocial.endpoint;
//
//import com.example.studentssocial.models.AuthenticationRequest;
//import com.example.studentssocial.models.AuthenticationResponse;
//import com.example.studentssocial.service.MyUserDetailsService;
//import com.example.studentssocial.util.JwtUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//public class AuthenticationController {
////    @Autowired
////    private AuthenticationManager authenticationManager;
////
////    @Autowired
////    private MyUserDetailsService userDetailsService;
////
////    @Autowired
////    private JwtUtil jwtTokenUtil;
//
//    @GetMapping("/hello")
//    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
//        return String.format("Hello %s!", name);
//    }
//
////    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
////    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
////        try {
////            authenticationManager.authenticate(
////                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
////            );
////        }catch (BadCredentialsException e){
////            throw new Exception("Incorrect username or password",e);
////        }
////        final UserDetails userDetails = userDetailsService
////                .loadUserByUsername(authenticationRequest.getUsername());
////        final String jwt = jwtTokenUtil .generateToken(userDetails);
////
////        return ResponseEntity.ok(new AuthenticationResponse(jwt));
////    }
//}
