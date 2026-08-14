# Topology Inventory - Hexagonal Architecture Learning Project

## Overview

This is a learning project demonstrating **Hexagonal Architecture** (also known as Ports and Adapters) in Java. The project models a network topology inventory system that manages routers, switches, and networks with their associated specifications and relationships.

**Note:** This project is designed for educational purposes. It focuses on domain-driven design and architectural patterns rather than being a production-ready application.

## Project Structure

The project is organized as a Maven multi-module build:

```
topology-inventory/
├── domain/                    # Domain module (core business logic)
│   ├── src/main/java/         # Main source code
│   │   ├── module-info.java   # Module descriptor (Java 9+)
│   │   └── com/joser/topologyinventory/domain/
│   │       ├── entity/        # Domain entities (Router, Switch, etc.)
│   │       ├── vo/            # Value Objects (IP, Location, Model, etc.)
│   │       ├── service/       # Domain services
│   │       ├── specification/ # Specification pattern implementations
│   │       └── exception/     # Domain exceptions
│   └── src/test/java/         # Unit tests
├── pom.xml                    # Root POM
└── README.md                  # This file
```

## Domain Model

The project models a network infrastructure system with the following core concepts:

### Entities

- **Equipment**: Base abstract class for network devices
  - **Router**: Abstract base for routers with type information
    - **CoreRouter**: Central routers that connect to other routers
    - **EdgeRouter**: Edge routers that connect to switches
  - **Switch**: Network switches that manage network segments

### Value Objects

- **IP**: IP address representation with protocol type (IPv4/IPv6)
- **Network**: CIDR network blocks with protocol and naming
- **Location**: Geographic coordinates and address information
- **Model**: Equipment model enumeration
- **Vendor**: Equipment vendor enumeration
- **RouterType**: Router classification (CORE, EDGE)
- **SwitchType**: Switch layer classification (LAYER2, LAYER3)
- **Protocol**: Network protocol enumeration
- **Id**: Entity identifier wrapper

### Domain Services

- **RouterService**: Operations on routers (filtering, searching)
- **SwitchService**: Operations on switches (filtering, searching)
- **NetworkService**: Operations on networks (filtering, searching)

### Specification Pattern

The project implements the **Specification Pattern** to encapsulate business rules:

- **Specification<T>**: Interface for specifications
- **AbstractSpecification**: Base implementation with logical operators
- **AndSpecification**: Combines multiple specifications with AND logic
- **CIDRSpecification**: Validates CIDR notation
- **EmptyNetworkSpec**: Validates empty network checks
- **EmptyRouterSpec**: Validates empty router checks
- **EmptySwitchSpec**: Validates empty switch checks
- **NetworkAvailabilitySpec**: Validates network availability
- **NetworkEquivalenceSpec**: Validates network equivalence

### Domain Constraints

The domain enforces several business rules:

1. **Network Management**
   - A switch cannot have duplicate networks (same network address)
   - Networks must be validated using CIDR specifications

2. **Router Relationships**
   - A core router can connect to edge routers and other core routers
   - Routers must be in the same geographic location (country)
   - Two routers cannot have the same IP address

3. **Switch Management**
   - An edge router can connect to multiple switches
   - Switches must be in the same location as their parent router
   - Switches manage networks within their CIDR range

## Building the Project

### Prerequisites

- **Java 17+** (JDK)
- **Maven 3.6+**

### Compile

```bash
mvn clean compile
```

### Run Tests

```bash
mvn test
```

To run a specific test class:

```bash
mvn test -Dtest=DomainTest
```

To run a specific test method:

```bash
mvn test -Dtest=DomainTest#testMethodName
```

### Generate Test Reports

```bash
mvn surefire-report:report
```

Test reports are generated in `target/site/surefire-report.html`

## Key Learning Points

### 1. Hexagonal Architecture Principles

- **Domain-Centric Design**: Business logic is isolated in the domain module
- **Separation of Concerns**: Each package has a clear responsibility
- **Specification Pattern**: Business rules are encapsulated as reusable specifications

### 2. Value Objects

All value objects are immutable and represent domain concepts with no identity:
- IP addresses, locations, network models
- Proper equals/hashCode behavior for collections

### 3. Entity Relationships

- **Sealed Classes**: Router hierarchy uses Java sealed classes for type safety
- **Aggregate Roots**: CoreRouter and EdgeRouter act as aggregates managing their relationships
- **Composite Pattern**: Routers can contain other routers; routers contain switches

### 4. Domain Services

- Services provide filtered queries over collections of entities
- Predicates encapsulate filtering logic for reusability
- Functional programming patterns (lambdas, streams) for data manipulation

### 5. Exception Handling

- **GenericSpecificationException**: Thrown when business rules are violated
- Domain exceptions communicate failures to the application layer

## Test Coverage

The project includes comprehensive unit tests demonstrating:

- **Relationship Management**: Adding and removing routers/switches
- **Validation**: Network and equipment constraint enforcement
- **Filtering**: Querying equipment by type, vendor, location, model
- **Searching**: Finding specific equipment by ID
- **Exception Cases**: Validating that business rules prevent invalid operations

Example test scenarios:
- Adding networks to switches with duplicate address validation
- Connecting routers across different geographic locations (should fail)
- Filtering routers by type, vendor, and model
- Network protocol filtering

## Architecture Highlights

### Module Declaration (module-info.java)

```java
module domain {
    exports com.joser.topologyinventory.domain.entity;
    exports com.joser.topologyinventory.domain.service;
    exports com.joser.topologyinventory.domain.vo;
    exports com.joser.topologyinventory.domain.specification;
    requires static lombok;
}
```

This demonstrates Java Platform Module System (JPMS) usage for explicit API boundaries.

### Lombok Integration

The project uses Lombok for reducing boilerplate:
- `@Getter`: Automatic getter generation
- `@Builder`: Builder pattern implementation
- Configured as annotation processor in Maven

## Future Enhancements

As a learning project, potential areas for expansion:

1. **Infrastructure Layer**: Add persistence implementations (database adapters)
2. **Application Layer**: Add use cases/application services
3. **API Layer**: Add REST or gRPC interfaces
4. **Repository Pattern**: Implement repositories for data persistence
5. **Additional Specifications**: More complex business rule combinations

## Dependencies

- **Lombok 1.18.20**: Boilerplate reduction
- **JUnit 5**: Testing framework
- **Java 17**: Modern Java features (records, sealed classes, modules)

## Code Style

- Following Java naming conventions
- Immutable value objects
- Sealed classes for restricted inheritance
- Functional programming where applicable
- Builder pattern for complex object construction

## Learning Resources

This project teaches:
- Domain-driven design (DDD) fundamentals
- Hexagonal architecture principles
- Specification pattern implementation
- Java modules and strong encapsulation
- Test-driven development practices
- Clean architecture in Java

---

**Last Updated**: Chapter 6 - Domain Model Foundation
