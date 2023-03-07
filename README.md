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

## UserDetailSecurity
![UserDetailSecurity](https://user-images.githubusercontent.com/106312692/223304847-f5a0da4e-b92d-4ce5-90fb-f76179401d05.PNG)

**Spring Security**에서 사용자의 정보를 담는 **UserDetails** 인터페이스를 만들고

DB에서 유저 정보를 직접 가져오는 인터페이스를 만들어 보았습니다

## WebSecurity
![WebSecurity](https://user-images.githubusercontent.com/106312692/223305078-fe6c4aae-2927-43e4-ac02-d23f8d93f30c.PNG)

**WebSecurity**클레스를 생성하고 **Springboot Security**를 이용하여 

권한설정을 통한 페이지 접근,로그인, 로그아웃,비밀번호 암호화를 설정해 보았습니다

## MainController
![MainController](https://user-images.githubusercontent.com/106312692/223305232-3084378d-ad07-4384-97b5-3fb206ebe225.PNG)

아무 권한 없이 이용할 수 있는 **MainController**입니다 

기본페이지 이동, 회원가입페이지이동, 회원가입이 성공하면 로그인페이지로이동설정

회원가입시 DB에 있는 email과 중복인지아닌지 판별기능, Security로그인페이지 이동등을 만들어보았습니다

## MemberController
![MemberController](https://user-images.githubusercontent.com/106312692/223305324-e54bfe0d-8ebd-45c1-90a8-82ba25296f4c.PNG)

회원가입을 하여 **member**라는 권한을 부여받은 계정들만 이용할수 있는 **MemberContorller**입니다 

회원가입할때 작성한 내 정보들을 가져와 보여주는 mypage

작성했던 정보를 수정할 수 있는 회원수정페이지와 회원수정을 실행시키는 인터페이스

회원탈퇴를 할수있는 회원탈퇴기능 등을 만들어 보았습니다

## Dto
![Dto](https://user-images.githubusercontent.com/106312692/223305358-da4293ad-50ce-4a9b-a715-c2e8462457bb.PNG)

계층간 데이터 교환을 위해 사용하는 객체 **DTO** 입니다

회원 가입시 입력칸에 공백이 발생시 & 잘못된 입력을 하였을시 에러메세지를 출력하도록 설정 해보았습니다

## Entity
![Entity](https://user-images.githubusercontent.com/106312692/223305413-ff735ad6-9b1b-4aad-a2de-37425f4a8f81.PNG)

**DB에 저장되고 관리하는 Entity입니다**

회원가입시 입력한 정보들을 Entity를 통해 DB에 저장됩니다. 

권한은 자동으로 member로 부여받게 설정해두었습니다

## Repository
![Repository](https://user-images.githubusercontent.com/106312692/223305532-3e0f1c68-ef9c-481d-934a-9b94cb307dfd.PNG)

**JpaRepository**를 상속받도록 함으로써 기본적인 동작이 모두 가능해지고

**MemberEntity**를 메서드의 대상으로 지정하였습니다

**Entity**에 의해 생성된 DB에 접근하는 메서드 **findByEmail**을 만들어 보았습니다 

## Role
![Role](https://user-images.githubusercontent.com/106312692/223305585-d2227d66-191f-4582-856f-a5bb58c7f238.PNG)

이번 프로젝트에 권한은 **MEMBER** ,**ADMIN** 이 두가지로만 하기로 협의하여 생성하였습니다

## MemberService1
![MemberService1](https://user-images.githubusercontent.com/106312692/223305644-4e26bd58-c4e0-455c-87a8-54dc9afa44af.PNG)

회원가입시 DB에 저장되는 회원의 비밀번호가 암호화되게 설정하였고

회원가입중 입력한 **email**이 **DB**에 저장되어 있는 **email** 정보와 중복되는지 확인하는 서비스를 설정하였습니다

## MemberService2
![MemberService2](https://user-images.githubusercontent.com/106312692/223305690-a90f16a1-1e17-4a00-a79e-fda7b11c7c2e.PNG)

회원조회, 회원수정, 회원삭제 Service를 만들어 보았습니다

## Header
![Header](https://user-images.githubusercontent.com/106312692/223305722-434685d5-005a-48d8-8d1f-d408b638ce70.PNG)

비로그인시에 a태그를 누르면 로그인 페이지로 이동하게 설정하였고

**Security** 로그인완료시 로그인페이지로 이동하였던 a태그를 클릭하면

로그인한 **email**의 정보를 불러와 **mypage**로 이동하게 설정하였습니다
