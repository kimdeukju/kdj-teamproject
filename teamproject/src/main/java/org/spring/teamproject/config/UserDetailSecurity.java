//package org.spring.teamproject.config;
//
//
//import lombok.RequiredArgsConstructor;
//import org.spring.teamproject.entity.MemberEntity;
//import org.spring.teamproject.repository.MemberRepository;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class UserDetailSecurity implements UserDetailsService {
//
//    private final MemberRepository memberRepository;
//
//
//    @Override                               //로그인할 id
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//                                                    //id 정해지면 레파지토리에서 쿼리메소드 생성(findBy??)
//        Optional<MemberEntity> memberId = memberRepository.findByUsername(username);
//
//        MemberEntity memberEntity = memberId.get();
//        return User.builder()
//                .username(memberEntity.getUserName())
//                .password(memberEntity.getPassword())
//                .roles(memberEntity.getRole().toString())
//                .build();
//    }
//}
