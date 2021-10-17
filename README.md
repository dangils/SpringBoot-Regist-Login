# SpringBoot-Regist-Login
스프링부트 로그인,회원가입 기능구현        

### 개념정리

MVC 패턴
MVC 는 Model, View, Controller의 약자로 하나의 애플리케이션, 프로젝트를 구성할 때 그 구성요소를 세가지의 역할로 구분한 패턴


@Controller은 Model 객체를 만들어 데이터를 담고 View를 찾는 것  
@RestController은 단순히 객체만을 반환하고 객체 데이터는 JSON 또는 XML 형식으로 HTTP 응답에 담아서 전송


#### @Controller와 @RestController의 차이
전통적인 Spring MVC 컨트롤러인 @Controller와 웹서비스의 컨트롤러인 @RestController의 주요한 차이점은
HTTP Response Body가 생성되는 방식이다.
좀 더 쉽게 말하자면 @Controller의 주용도는 View를 리턴하는 것이고, @RestController은 JSON/XML 타입의
HTTP 응답을 직접 리턴한다.

@Controller에서 View를 반환하는 경우
1. Client는 URI 형식으로 웹 서비스에 요청을 보낸다.
2. Mapping되는 Handler와 그 Type을 찾는 DispatcherServlet이 요청을 인터셉트한다.
3. Controller가 요청을 처리한 후에 응답을 DispatcherServlet으로 반환하고, DispatcherServelet은 View를
사용자에게 반환한다.


- 데이터흐름
Client -> Request -> Dispatcher Servlet -> Handler Mapping -> Controller -> View -> Dispatcher Servlet
-> Response -> Client  

@RestController에서 Json/Xml 형태로 객체 데이터를 반환하는 경우
1. Client는 URI 형식으로 웹 서비스에 요청을 보낸다.
2. Mapping되는 Hadnler와 그 Type을 찾는 DispatcherServlet이 요청을 인터셉트한다.
3. RestController는 해당 요청을 처리하고 데이터를 반환한다.  
  
------------------


### Spring Data JPA

    스프링 프레임워크에서 개발자가 JPA를 더 쉽고 편하게 사용할 수 있도록 지원하는 모듈이다.
    Spring Data JPA는 구현체나 JPA provider가 아닌데, hibernate 같은 JPA provider위에
    추상화 계층을 따로 두어 관리하는 라이브러리/프레임워크이다. Spring Data Jpa는 항상 Hibernate와 같은
    JPA provider가 필요하다.

    JPA(Java Persistent API)란?
    자바 어플리케이션에서 관계형 데이터베이스를 사용하는 방식을 정의한 인터페이스이다. 여기서 짚고 넘어가야 할 부분은
    JPA는 인터페이스라는 점이다. 자바 어플리케이션에서 관계형 데이터베이스를 어떻게 사용해야 하는지를 정의하는 방법일 뿐,
    즉, ORM 기술에 대한 명세이며 구현은 없다.

    Hibernate란?
    JPA라는 명세의 구현체 중 하나이며, JPA를 사용하기 위해서는 구현체가 있어야 하는데 반드시 Hibernate를 사용할 필요는
    없다. 본인이 직접 JPA를 구현해서 사용해도 되고, EclipseLink, DataNucleus 등 다른 JPA 구현체를 사용해도 된다.
    전세계적으로는 Hibernate가 주로 쓰이는 추세이다.

    ORM(Object-relational mapping)이란?
    ORM이란 객체와 관계형 데이터베이스를 매핑한다는 뜻이다. ORM은 자바 객체와 DB 테이블을 매핑함으로써 패러다임의
    불일치를 개발자 대신 해결해준다. RDB의 관계를 객체에 반영하며, 객체간의 관계를 바탕으로 SQL을 자동으로 생성한다.
    따라서, ORM을 이용하면 SQL Query가 아닌 메서드로 데이터를 조작할 수 있다. DB데이터와 Object 필드를 매핑하여
    객체를 통해 간접적으로 DB데이터를 다루어 Persistant API라고 할 수 있다.

    ***** JPA 동작과정
    JPA는 애플리케이션과 JDBC 사이에서 동작한다.
    개발자가 JPA를 사용하면, JPA 내부에서 JDBC API를 사용하여 SQL을 호출하여 DB와 통신한다.

    1. 저장과정
    만약 MemberDAO에서 객체를 저장하고 싶을 때 개발자는 JPA에 Member 객체를 넘긴다.
    1. Member 엔티티를 분석
    2. INSERT SQL을 생성
    3. JDBC API를 사용하여 SQL을 DB에 전송

    2. 조회과정
    만약 Member 객체를 조회하고 싶을 때 개발자는 Member의 pk값을 JPA 넘긴다.
    pk -> primary key, 우리가 사용하는 id값이 된다. @id 어노테이션이 붙은 것.
    1. 엔티티의 매핑 정보를 바탕으로 적절한 SELECT SQL을 생성한다.
    2. JDBC API를 사용하여 SQL을 DB에 전송
    3. DB로부터 결과를 반환
    4. 결과(ResultSet)를 객체에 모두 매핑
    이처럼 쿼리를 JPA가 만들어주기 때문에 Object와 RMB간의 패러다임의 불일치를 해결할 수 있다!

 



// Spring Data JPA에선 CRUD 처리를 위한 공통 인터페이스(JpaRepository)를 제공해주는데
// 사용자는 그저 JpaRepository를 상속받은 인터페이스만 작성하면 Spring Data JPA가 동적으로 구현체를 생성해서 주입.
// 따라서 개발자는 이를 상속받은 인터페이스만 작성하면 된다. 아래처럼.

// <엔티티, 식별자>
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // 이 클래스의 주요 메서드 기능
    // CRUD 비즈니스 로직을 수행하는 Service에서 볼 수 있는 메소드들. 저장, 조회, 수정, 삭제할 때 아래 메서드 사용!!

    /*
    1. save() : 새로운 엔티티는 저장하고 있는 엔티티는 병합!
    2. delete() : 엔티티 하나를 삭제. 내부에서는 EntitiManager.remove() 호출!
    3. findById(id) : 엔티티 하나를 조회, 내부에서는 EntitiManager.find() 호출!
    4. getOne(Id) : 엔티티를 프록시로 조회, EntitiManager.getReference() 호출!
    5. findALL(..) : 모든 엔티티를 조회, 정렬이나 페이징 조건 제공 가능!
     */




