# CQRS-premium-architecture

```java
com.example.cqrs_premium
        │
        ├── application
        │   ├── command
        │   │   ├── dto
        │   │   │   └── CreateOrderCommand.java
        │   │   └── service
        │   │       └── OrderCommandService.java
        │   │
        │   └── query
        │       ├── dto
        │       │   └── OrderSummaryDto.java
        │       ├── repository
        │       │   └── OrderQueryRepository.java
        │       └── service
        │           └── OrderQueryService.java
        │
        ├── domain
        │   ├── model
        │   │   ├── Order.java
        │   │   ├── Customer.java
        │   │   ├── OrderItem.java
        │   │   ├── Product.java
        │   │   └── OrderStatus.java
        │   │
        │   └── repository
        │       ├── OrderRepository.java
        │       ├── CustomerRepository.java
        │       └── ProductRepository.java
        │
        ├── infrastructure
        │   ├── config
        │   │   └── RedisConfig.java
        │   │
        │   ├── persistence
        │   │   ├── jpa
        │   │   │   ├── entity
        │   │   │   │   ├── OrderJpaEntity.java
        │   │   │   │   ├── CustomerJpaEntity.java
        │   │   │   │   ├── OrderItemJpaEntity.java
        │   │   │   │   └── ProductJpaEntity.java
        │   │   │   │
        │   │   │   └── repository
        │   │   │       ├── JpaOrderRepository.java
        │   │   │       ├── JpaCustomerRepository.java
        │   │   │       └── JpaProductRepository.java
        │   │   │
        │   │   ├── mongodb
        │   │   │   ├── document
        │   │   │   │   └── OrderDocument.java
        │   │   │   │
        │   │   │   └── repository
        │   │   │       └── MongoOrderQueryRepository.java
        │   │   │
        │   │   ├── redis
        │   │   │   └── RedisOrderRepository.java
        │   │   │
        │   │   ├── repository
        │   │   │   └── impl
        │   │   │       ├── OrderRepositoryImpl.java
        │   │   │       ├── CustomerRepositoryImpl.java
        │   │   │       ├── ProductRepositoryImpl.java
        │   │   │       └── OrderQueryRepositoryImpl.java
        │   │   │
        │   │   └── mapper
        │   │       ├── OrderMapper.java
        │   │       ├── CustomerMapper.java
        │   │       ├── ProductMapper.java
        │   │       └── OrderItemMapper.java
        │   │
        │   └── messaging
        │       ├── event
        │       │   └── OrderCreatedEvent.java
        │       │
        │       └── handler
        │           └── OrderEventHandler.java
        │
        ├── presentation
        │   └── api
        │       ├── OrderCommandController.java
        │       └── OrderQueryController.java
        │
        ├── CqrsPremiumApplication.java
        │
        └── resources
        └── application.properties
```

이 코드는 CQRS 프리미엄 모델의 인프라스트럭처와 프레젠테이션 계층을 구현합니다. 주요 특징은 다음과 같습니다:

JPA를 사용한 명령 모델 영속성
MongoDB를 사용한 쿼리 모델 영속성
Redis를 사용한 캐싱
이벤트 기반 동기화
RESTful API 엔드포인트

이 구조는 읽기와 쓰기 작업을 독립적으로 확장할 수 있게 하며, 각 모델을 해당 작업에 최적화할 수 있게 합니다.


---
OrderItemRepository 가 없는 이유??

집약근(Aggregate Root) 패턴:
DDD에서는 주로 집약근을 통해 엔티티에 접근합니다. Order는 OrderItem의 집약근으로, OrderItem은 Order를 통해서만 접근하고 관리됩니다.
불변성 유지:
Order를 불변 객체로 만들면서, OrderItem도 Order의 일부로 취급됩니다. 따라서 OrderItem을 독립적으로 저장하거나 수정할 필요가 없어집니다.
단순화:
OrderItem을 위한 별도의 리포지토리를 만들면 시스템이 더 복잡해질 수 있습니다. Order를 통해 OrderItem을 관리하면 코드가 더 단순해집니다.
일관성 유지:
Order와 OrderItem을 항상 함께 처리함으로써 데이터의 일관성을 더 쉽게 유지할 수 있습니다.

그러나 특정 상황에서는 OrderItemRepository가 필요할 수 있습니다:

복잡한 쿼리:
OrderItem에 대한 복잡한 쿼리가 필요한 경우.
성능 최적화:
특정 OrderItem만 조회하는 경우가 많다면, 성능 최적화를 위해 별도의 리포지토리가 필요할 수 있습니다.
독립적인 생명주기:
OrderItem이 Order와 독립적인 생명주기를 가진다면 별도의 리포지토리가 필요할 수 있습니다.