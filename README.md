# 1suck2jo Shopping Mall TeamProject

## Springboot Jpa Security를 이용한 CRUD ROLE

## List
1. UserDetailSecurity
2. WebSecurity
3. MainController
4. MemberController
5. Dto
6. Entity
7. Repository
8. Role
9. MemberService1
10. MemberService2
11. Header

## Table <!-- omit in toc -->

- [UserDetailSecurity](#UserDetailSecurity)
- [WebSecurity](#WebSecurity)
- [MainController](#MainController)
- [MemberController](#MemberController)
- [Dto](#Dto)
- [Entity](#Entity)
- [Repository](#Repository)
- [MemberService](#MemberService)
  - [MemberService1](#MemberService1)
  - [MemberService2](#MemberService2)
- [Header](#Header)

## UserDetailSecurity
![UserDetailSecurity](https://user-images.githubusercontent.com/106312692/223304847-f5a0da4e-b92d-4ce5-90fb-f76179401d05.PNG)
```UserDetailSecurity
    @Override                           
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<MemberEntity> memberEmail = memberRepository.findByEmail(email);

        if (!memberEmail.isPresent()) {
            throw new UsernameNotFoundException("사용자가 없습니다.");
        }
        MemberEntity memberEntity = memberEmail.get(); 
        return User.builder()
                .username(memberEntity.getEmail())
                .password(memberEntity.getPassword())
                .roles(memberEntity.getRole().toString())
                .build();
    }
}
```
**Spring Security**에서 사용자의 정보를 담는 **UserDetails** 인터페이스를 만들고

DB에서 유저 정보를 직접 가져오는 인터페이스를 만들어 보았습니다

## WebSecurity
![WebSecurity](https://user-images.githubusercontent.com/106312692/223305078-fe6c4aae-2927-43e4-ac02-d23f8d93f30c.PNG)
```WebSecurity
@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf().disable(); 
        http.userDetailsService(userDetailSecurity);
  
        http.authorizeHttpRequests()
                .antMatchers("/","/item/main,/login,/join","board/main").permitAll() 
                .antMatchers("/member/**").authenticated()                            
                .antMatchers("/member/**").hasAnyRole("MEMBER","ADMIN")        
                .antMatchers("/admin/**").hasRole("ADMIN");                   
      
        http.formLogin()
                .loginPage("/login")                            
                .loginProcessingUrl("/login")                       
                .usernameParameter("email")                         
                .passwordParameter("password")
                .defaultSuccessUrl("/")                            
                .failureForwardUrl("/member/fail")         
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))  
                .logoutSuccessUrl("/");                                               
        return http.build();
    }
    @Bean 
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
```

**WebSecurity**클레스를 생성하고 **Springboot Security**를 이용하여 

권한설정을 통한 페이지 접근,로그인, 로그아웃,비밀번호 암호화를 설정해 보았습니다

## MainController
![MainController](https://user-images.githubusercontent.com/106312692/223305232-3084378d-ad07-4384-97b5-3fb206ebe225.PNG)
```MainController
@GetMapping({"/", "", "/index"})
    public String index() {
        return "/pages/main";
    }

    @GetMapping("/join")                               
    public String join(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "/pages/member/join";
    }
    @PostMapping("/join")                             
    public String joinPost(@Valid MemberDto memberDto,
                           BindingResult result) {
        if (result.hasErrors()) {
            return "/pages/member/join";
        }
        memberService.insertMember(memberDto);
        System.out.println("회원가입 성공");
        return "redirect:/login";
    }
    @PostMapping("/emailChecked")                     
    public @ResponseBody int nameChecked(
            @RequestParam String email) {
        int rs = memberService.findByUserNameDo(email);
        return rs;
    }
    @GetMapping("/login")                             
    public String login(Model model) {
        System.out.println("로그인 성공");
        return "/pages/member/login";
    }
}
```
아무 권한 없이 이용할 수 있는 **MainController**입니다 

기본페이지 이동, 회원가입페이지이동, 회원가입이 성공하면 로그인페이지로이동설정

회원가입시 DB에 있는 email과 중복인지아닌지 판별기능, Security로그인페이지 이동등을 만들어보았습니다

## MemberController
![MemberController](https://user-images.githubusercontent.com/106312692/223305324-e54bfe0d-8ebd-45c1-90a8-82ba25296f4c.PNG)
```MemberController
@GetMapping("/mypage/{email}")                          
    public String membermypage(@PathVariable("email") String email, Model model) {
        MemberDto memberDto = memberService.memberDetail(email);
        model.addAttribute("member", memberDto);
        return "/pages/member/mypage";
    }
    @GetMapping("/update/{email}")                           
    public String info(@PathVariable("email") String email, Model model) {
        MemberDto memberDto = memberService.memberDetail(email);
        model.addAttribute("member", memberDto);
        return "/pages/member/update";
    }
    @PostMapping("/update")                              
    public String updatePost(@ModelAttribute MemberDto memberDto) {
        memberService.updateOk(memberDto);
        return "redirect:/";
    }
    @GetMapping("/delete/{no}")                           
    public String delete(@PathVariable(value = "no") Long no) {
        int rs = memberService.deleteOk(no);
        if (rs == 1) {
            System.out.println("회원탈퇴 실패");
            return null;
        }
        System.out.println("회원탈퇴 성공");
        return "redirect:/logout";
    }
}
```

회원가입을 하여 **member**라는 권한을 부여받은 계정들만 이용할수 있는 **MemberContorller**입니다 

회원가입할때 작성한 내 정보들을 가져와 보여주는 mypage

작성했던 정보를 수정할 수 있는 회원수정페이지와 회원수정을 실행시키는 인터페이스

회원탈퇴를 할수있는 회원탈퇴기능 등을 만들어 보았습니다

## Dto
![Dto](https://user-images.githubusercontent.com/106312692/223305358-da4293ad-50ce-4a9b-a715-c2e8462457bb.PNG)
```Dto
public class MemberDto {
    private Long no;
    @NotBlank(message = "이메일을 입력 해주세요")
    private String email;
    @NotBlank(message = "비밀번호를 입력 해주세요. 4글자이상 10글자 이하로 입력하세요")
    private String password;
    @NotBlank(message = "배송주소를 입력 해주세요")
    private String address;
    @NotBlank(message = "이름을 입력해주세요")
    private String userName;
    @NotBlank(message = "핸드폰 번호를 입력해주세요")
    private String phone;
    private Role role;
    private LocalDateTime createTime; 
    private LocalDateTime updateTime;
    public MemberDto(MemberEntity memberEntity) {
    }
```

계층간 데이터 교환을 위해 사용하는 객체 **DTO** 입니다

회원 가입시 입력칸에 공백이 발생시 & 잘못된 입력을 하였을시 에러메세지를 출력하도록 설정 해보았습니다

## Entity
![Entity](https://user-images.githubusercontent.com/106312692/223305413-ff735ad6-9b1b-4aad-a2de-37425f4a8f81.PNG)
```Entity
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_no")
    private Long no;
    @Column(nullable = false,unique = true)
    private String email;
    private String password;
    private String address;
    private String userName;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Role role;
```

**DB에 저장되고 관리하는 Entity입니다**

회원가입시 입력한 정보들을 Entity를 통해 DB에 저장됩니다. 

권한은 자동으로 member로 부여받게 설정해두었습니다

## Repository
![Repository](https://user-images.githubusercontent.com/106312692/223305532-3e0f1c68-ef9c-481d-934a-9b94cb307dfd.PNG)
```Repository
public interface MemberRepository extends JpaRepository<MemberEntity,Long> {

    Optional<MemberEntity> findByEmail(String email);
```

**JpaRepository**를 상속받도록 함으로써 기본적인 동작이 모두 가능해지고

**MemberEntity**를 메서드의 대상으로 지정하였습니다

**Entity**에 의해 생성된 DB에 접근하는 메서드 **findByEmail**을 만들어 보았습니다 

## Role
![Role](https://user-images.githubusercontent.com/106312692/223305585-d2227d66-191f-4582-856f-a5bb58c7f238.PNG)
```Role
public enum Role {
    ADMIN,MEMBER
```

이번 프로젝트에 권한은 **MEMBER** ,**ADMIN** 이 두가지로만 하기로 협의하여 생성하였습니다

## MemberService1
![MemberService1](https://user-images.githubusercontent.com/106312692/223305644-4e26bd58-c4e0-455c-87a8-54dc9afa44af.PNG)
```MemberService1
 @Transactional  
    public void insertMember(MemberDto memberDto) {
        MemberEntity memberEntity= MemberEntity.memberEntity(memberDto,passwordEncoder);
        memberRepository.save(memberEntity);
    }
    @Transactional      
    public int findByUserNameDo(String email) {
        Optional<MemberEntity> memberEntity =memberRepository.findByEmail(email);
        if(memberEntity.isPresent()){
            return 0;
        }else {
            return 1;
        }
    }
```

회원가입시 DB에 저장되는 회원의 비밀번호가 암호화되게 설정하였고

회원가입중 입력한 **email**이 **DB**에 저장되어 있는 **email** 정보와 중복되는지 확인하는 서비스를 설정하였습니다

## MemberService2
![MemberService2](https://user-images.githubusercontent.com/106312692/223305690-a90f16a1-1e17-4a00-a79e-fda7b11c7c2e.PNG)
```MemberService2
public MemberDto memberDetail(String email) {
        Optional<MemberEntity> memberEntity=memberRepository.findByEmail(email);
        if (!memberEntity.isPresent()){
            return null;
        }
        MemberDto memberDto=MemberDto.updateMemberDto(memberEntity.get());
        return memberDto;
    }
    @Transactional 
    public void updateOk(MemberDto memberDto) {
        MemberEntity memberEntity=MemberEntity.updateMemberEntity(memberDto,passwordEncoder);
        memberRepository.save(memberEntity);
    }
    @Transactional
    public int deleteOk(Long id) {
        MemberEntity memberEntity = memberRepository.findById(id).get();
        memberRepository.delete(memberEntity);
        if(memberRepository.findById(id)!=null){
            return 0;
        }
        return 1;
    }
}
```

회원조회, 회원수정, 회원삭제 Service를 만들어 보았습니다

## Header
![Header](https://user-images.githubusercontent.com/106312692/223305722-434685d5-005a-48d8-8d1f-d408b638ce70.PNG)
```Header
                    <li class="li-last" sec:authorize="isAnonymous()">
                        <a th:href="@{/login}"></a>
                    </li>
                    
                    <li class="li-last" sec:authorize="isAuthenticated()">
                        <a th:href="@{|/member/mypage/${#authentication.principal.username}|}"></a>
                    </li>
```

비로그인시에 a태그를 누르면 로그인 페이지로 이동하게 설정하였고

**Security** 로그인완료시 로그인페이지로 이동하였던 a태그를 클릭하면

로그인한 **email**의 정보를 불러와 **mypage**로 이동하게 설정하였습니다

