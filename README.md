# JPABlogProject

- JAVA : 17, Tomcat : v9.0, Spring Boot : v2.7.1, H2(DB) : v1.4.199 
- lombok : v1.18.24 , ModelMapper : v2.4.0
- Test : JUnit5
- <a href="https://iheese.github.io/project/2022/08/22/jpablogproject/" target="_blank">프로젝트 회고록</a>

<br>

- JPA와 Spring Boot를 이용한 블로그 프로젝트입니다.
- 유저별로 하나의 블로그를 생성할 수 있습니다. 
- H2 데이터베이스를 In-Memory로 설정하였습니다. 
- 회원 가입 기능은 구현하지 않았기 때문에 sql.init.mode: always 로 설정하여 프로젝트가 실행될 때 BlogUser에 일반 권한, 관리자 권한 데이터를 자동으로 입력하게 했습니다. 
> - ID : aaa, Password : aaa, 일반 권한
> - ID : test, Password : test, 관리자 권한(블로그 삭제 권한이 있습니다.) 
- 필드 주입 방식을 지양하고, 생성자 주입 방식을 지향했습니다.
- DTO와 Entity를 구분하였고 서비스 단에서 변환하는 로직을 추가했습니다.
- Entity 간 매핑을 사용하지 않아 Join을 없애 성능을 좋게 했습니다.(객체 필드에 각 id값을 추가해 직접 매핑)
- 서비스 클래스와 컨트롤러 클래스의 주요 메소드들에 대한 테스트 코드를 작성하여 코드의 확실성을 높이고 코드를 리팩토링하는 과정을 거쳤습니다.

<hr>

- 블로그 생성, 삭제요청, 삭제, 검색, 조회
- 카테고리 생성, 삭제, 조회
- 포스트 생성, 삭제, 전체 조회, 카테고리별 조회

<br>

![블로그메인](https://user-images.githubusercontent.com/88040158/185576658-888f2145-e3e2-4a26-a089-2ca321c43639.png)

- 프로그램을 시작하면 유저 데이터만 저장된 상태로 시작합니다.

<br>

![로그인](https://user-images.githubusercontent.com/88040158/185576639-9c4ae5d7-bd33-4fb5-bd51-4b638b643d50.png)

- ID : aaa, Password : aaa (일반 권한)
- ID : test, Password : test (관리자 권한)
- 두 가지 중 하나로 로그인 할 수 있습니다. 

<br>

![로그인 후 메인 화면](https://user-images.githubusercontent.com/88040158/185576638-a2f10fd7-6dd8-43ba-b53b-3a5bfa22a60a.png)

- 로그인을 하면 블로그를 등록할 수 있습니다.

<br>

![블로그 생성](https://user-images.githubusercontent.com/88040158/185576656-b121f004-f4d4-48e7-ae1b-5b508e1ce4fd.png)

- 블로그 제목만 정하여 생성할 수 있습니다.
- 블로그 제목 외 기본적인 데이터, 미분류 카테고리 하나가 자동으로 생성됩니다.

<br>

![볼로그 생성 후 메인화면](https://user-images.githubusercontent.com/88040158/185576640-56536f9b-3fdc-438d-9b9b-d060a4b39461.png)

- 블로그가 생성되면 블로그 목록을 확인할 수 있습니다.

![블로그 검색1](https://user-images.githubusercontent.com/88040158/185582740-a3d48b76-2a19-4ec6-8cf2-bed1ccc3e5bf.png)


![블로그 검색2](https://user-images.githubusercontent.com/88040158/185582743-6eee3dd9-37ce-4cde-9c8c-ecd27571d7fb.png)

- 블로그는 블로그 제목, 태그로 검색할 수 있습니다.
- 아래는 블로그 제목으로 test를 검색한 결과입니다. 

<br>

![블로그 메인화면](https://user-images.githubusercontent.com/88040158/185576650-5a96a8b5-ea04-48cb-9a83-5ed49f30f16a.png)

- 블로그에 들어오면 미분류 카테고리만 존재하게 됩니다.
- 블로그 관리에서 블로그 관리, 카테고리 관리, 포스트 관리를 할 수 있습니다.
- 블로그 메인은 현재 페이지를 의미합니다.  
- 미분류 카테고리 아래 JBlog 이미지를 클릭하면 전 블로그를 검색할 수 있는 페이지로 이동합니다.

<br>

![블로그 관리](https://user-images.githubusercontent.com/88040158/185576645-96dca918-9237-4206-ba9c-2369671f49d7.png)

- 블로그 제목, 블로그 태그를 수정할 수 있습니다.

<br>

![카테고리 등록](https://user-images.githubusercontent.com/88040158/185576621-6f8dc33e-37b7-4f3e-a9ea-8b4089f15a39.png)


![카테고리 수정](https://user-images.githubusercontent.com/88040158/185576626-e0347234-cfe0-498c-b33f-ae880bb391ec.png)

- 미분류 카테고리는 기본 제공되는 카테고리이기 때문에 삭제할 수 없습니다.
- 카테고리를 추가적으로 등록할 수 있으며 해당 카테고리를 클릭해 수정하거나, 삭제 버튼으로 삭제할 수 있습니다. 
- 카테고리를 삭제하면 속해 있던 포스트 목록도 같이 삭제됩니다. 

<br>

![글 등록](https://user-images.githubusercontent.com/88040158/185576635-a063d234-5907-4b2a-9512-124923d539cd.png)

- 글 등록을 누르면 글을 등록할 수 있으며 카테고리를 등록할 수 있습니다.

![포스트목록 조회](https://user-images.githubusercontent.com/88040158/185582728-edb1e579-c09d-4393-a870-d457a276a90e.png)


![카테고리별 포스트 조회](https://user-images.githubusercontent.com/88040158/185582745-6e026ab7-e675-46cf-929b-fdb2270dde16.png)

- 블로그 주인이 보았을 때는 수정/삭제가 가능하고 외부인은 수정/삭제 권한이 없습니다.
- 카테고리 목록을 클릭하면 카테고리별 포스트를 조회할 수 있습니다. 

<br>

![블로그 삭제 요청](https://user-images.githubusercontent.com/88040158/185576652-c6dc715d-8078-470c-8002-cb4ea7625aa6.png)

- 블로그 삭제는 관리자 권한만 가능하며, 일반 권한 유저은 삭제 요청만 가능합니다. 
- 삭제 요청을 할 경우 로그아웃되면서 초기 페이지로 이동합니다.

<br>

![관리자 로그인 삭제 요청 확인](https://user-images.githubusercontent.com/88040158/185576633-92829999-3197-433e-bb2c-6bbf4f79b45e.png)

- ID : tes, Password : test (관리자 권한) 로 로그인하면 aaa 회원의 블로그의 상태가 삭제 요청된 것을 볼 수 있고 삭제 버튼이 활성화된 것을 확인할 수 있습니다.

<br>

![블로그 삭제](https://user-images.githubusercontent.com/88040158/185576654-36d105b1-2dde-4987-9572-2421da962026.png)

- 관리자가 삭제 버튼을 누르면 블로그에 속했던 카테고리 목록, 포스트 목록이 모두 삭제됩니다.

<hr>

- 프로젝트 관련하여 더 많은 이야기는 프로젝트 회고록에 남기도록 하겠습니다.
- <a href="https://iheese.github.io/project/2022/08/22/jpablogproject/" target="_blank">프로젝트 회고록</a>

<br>

Reference :

- 백엔드 개발자 양성 과정 _ 패스트 캠퍼스, 채규태 강사님 강의의 개인 프로젝트 결과물입니다.
