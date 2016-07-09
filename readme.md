# spring-framework-study

> [토비의 스프링 3.1](http://book.naver.com/bookdb/book_detail.nhn?bid=7006516) (이일민 저. 에이콘출판. 2010) 을 공부하며 책 내용 중 일부를 요약.

내가 처음 스프링을 공부하기 위해 사용한 교재는 토비의 스프링이였다. 뭐든 최대한 정석 또는 정석에 가까운 교재로 하나 하나 꼼꼼히 짚어가며 배워 가길 좋아 하기 때문인데, 이 토비의 스프링은 만만찮은 책이었다. '[내가 추천하는 프로그래머 필독서 50선](http://www.sangkon.com/2016/02/10/good_books_for_dev/)' 이라는 블로그 글에서도 토비의 스프링이 등장하는데, 이렇게 쓰였다.

> 스프링을 공부한다는 마음으로 읽기 보다는, 패턴의 진화과정을 여행한다는 마음으로 읽어보자. 아, 물론 포기하고 싶다는 마음은 누구나 가진다. 그러니 3일 읽고, 3일 포기하고, 3일 읽고, 3일 포기하고 반복하다 보면 다 읽을 수 있다. 책 아깝다고 모셔두지 말고 찢어서 들고 다니며 읽자. 어짜피 이 책은 이러지도(?) 저러지도(?) 못한다.

나 역시 이전에 4번 정도 토비의 스프링을 공부하다 포기하였다. 그래도 역시 밥벌이 하는 주 언어가 `Java` 인데 그만큼 애정도 많고, 아직 모르는 것도 많고, 공부할 것도 많은 언어이다. 그래서 다시 토비의 스프링 책을 펼친다. <br>내가 교재를 보며 따라한 예제 소스는 아래 `github`에 공유하고 있다. 교재에서 사용하는 버전과 관계 없이 나는 그냥 `Java8`, `Spring4.2` 환경에서 테스트 하고 있다.

> [https://github.com/iamkyu/spring-framework-study/tree/chap01]()

## 1장. 오브젝트와 의존관계

>소프트웨어 개발에서 끝이란 개념은 없다. 사용자의 비즈니스 프로세스와 그에 따른 요구사항은 끊임없이 바뀌고 발전한다. 애플리케이션이 기반을 두고 있는 기술도 시간이 지남에 따라 바뀌고, 운영되는 환경도 변화한다. (..) 그래서 개발자가 객체를 설계할 때 가장 염두해 둬야 할 사항은 미래의 변화를 어떻게 대비할 것인가이다. -P.60

### 관심사의 분리

**분리와 확장**을 고려한 설계는 변경이 일어났을 때, 작업을 최소화하고 문제가 발생 할 확률을 줄인다.

- 관심이 같은 것끼리는 하나의 객체 또는 친한 객체로 `>>>` 이 과정에서 중복 코드의 메소드 추출
- 다른 것은 가능한 떨어져 서로 영향을 주지 않도록 분리

### 클래스의 확장

#### 상속을 통한 확장의 문제

- 다중 상속을 지원하지 않는 자바
- 상하위 클래스 관계의 긴밀한 결합

#### 클래스 분리를 통한 확장의 문제

- 클라이언트(사용하는쪽)가 서비스(사용되는쪽)가 제공하는 클래스에 대해 구체적으로 알아야 함
- 자유로운 확장이 힘듬

#### 인터페이스를 통한 확장

두 개의 클래스가 서로 긴밀하게 연결되지 않도록 중간에 느슨한 연결고리를 추가.

- 개방폐쇄원칙: 확장에는 열려 있고 변경에는 닫혀 있는다.
- 높은 응집도: 변경이 일어날 때 모듈의 많은 부분이 함께 바뀐다.
- 낮은 결합도: 느슨한 연결, 서로 독립적이고 알 필요도 없다.

### 제어의 역전 (IoC)

일반적인 프로그램의 흐름은 모든 종류의 작업을 `사용하는 쪽` 에서 제어하는 구조. 제어의 역전에서는 이것을 거꾸로 뒤집는다.

#### 라이브러리 vs 프레임워크

- 라이브러리: 단지 동작하는 중에 필요한 기능이 있을 때 능동적으로 사용.
- 프레임워크: 모든 애플리케이션 코드가 프레임워크에 의해 사용 됨. 애플리케이션 코드는 프레임워크가 짜놓은 틀 안에서 수동적으로 동작.

#### 스프링의 IoC

- `bean` 스프링이 제어권을 가지고 직접 만들고 관계를 부여하는 오브젝트.
- `bean factory` 빈의 생성과 관계설정 같은 제어를 담당하는 IoC 오브젝트.
- `ApplicationContext` 스프링의 IoC 컨테이너.

```xml
ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
UserDao dao = context.getBean("userDao", UserDao.class);
```

#### ApplicationContext

빈 팩토리를 확장한 IoC 컨테이너.

- 클라이언트는 구체적인 팩토리 클래스를 알 필요가 없다.
- 오브젝트의 생명주기뿐만 아니라 오브젝트를 효과적으로 활용할 수 있는 다양한 기능 제공.
- 빈을 검색하는 다양한 방법을 제공(타입만으로 검색하거나 특정 애노테이션 설정).

#### 의존관계 주입(DI)

`A->B`일 때, A가 B에 의존하고 있지만, B는 A에 의존하지 않는다. B의 변화에 A는 영향을 받는다. A와 B 사이에 인터페이스를 구현하고 인터페이스에 대해 의존관계를 만들면 클래스간의 관계는 느슨해지며 변화에 영향을 덜 받는 상태가 된다.

#### 의존관계 주입

설계 시점에 알지 못했던 두 오브젝트의 관계를 맺도록 도와주는 제 3의 존재.

1. 생성자를 통한 의존관계 주입
2. 메소드를 통한 의존관계 주입
3. XML을 통한 의존관계 주입