# 스프링 자동 구성의 순서

## 자동 구성 인프라스트럭처 빈
1. 사용 기술 선택
2. Spring Initializr
3. Springboot Starter + Dependencies
4. @Autoconfigure(AutoConfiguration.imports)
5. @Conditional
6. 디폴트 자동 구성 인프라 빈
7. 프로퍼티 소스 applicaition.properties (외부설정 프로퍼티 적용)

## 유저 구성 애플리케이션 빈
1. @ComponentScan
2. 애플리케이션 로직 빈
3. @Configuration 커스텀 인프라 빈 -> 자동 구성 인프라 빈 오버라이딩
4. @Configuration 추가 인프라 빈

스프링 부트의 자동 구성 클래스와 빈 프로퍼티는 스프링 프레임워크 기술과 
표준 기술, 오픈소스 기술, 상용 기술에 대한 지원을 제공한다.

## 자동 구성 분석 방법
1. -Ddebug 옵션을 사용하여 자동 구성 빈을 로그로 확인
2. SpringBoot Reference Guide의 자동 구성 클래스 목록 확인
3. ConditionEvaluationReport 자동구성 클래스 Condition 결과 빈
4. @AutoConfiguration, @Conditional, Condition, @Bean 자동 구성 클래스와 조건, 빈 확인
5. ListableBeanFactory 등록된 빈 확인
6. Properties, Bind, Customizer, Configurer 프로퍼티 클래스와 바인딩
