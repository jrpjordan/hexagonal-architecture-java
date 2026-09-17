# Graph Report - topology-inventory  (2026-09-16)

## Corpus Check
- Corpus is ~9,196 words - fits in a single context window. You may not need a graph.

## Summary
- 451 nodes · 1619 edges · 17 communities (11 shown, 6 thin omitted)
- Extraction: 95% EXTRACTED · 5% INFERRED · 0% AMBIGUOUS · INFERRED: 79 edges (avg confidence: 0.81)
- Token cost: 0 input · 0 output

## Community Hubs (Navigation)
- Router Domain Model
- Router Data Objects
- Switch Domain Model
- Switch Specifications
- Cucumber Integration Tests
- Network Management Port
- Framework Test Builders
- Domain Unit Tests
- UUID Type Conversion
- BDD Step Definitions
- Test Execution
- Application Module
- Project Root
- Domain Package

## God Nodes (most connected - your core abstractions)
1. `Id` - 75 edges
2. `Switch` - 66 edges
3. `Router` - 58 edges
4. `IP` - 51 edges
5. `Network` - 43 edges
6. `Vendor` - 42 edges
7. `Model` - 40 edges
8. `Location` - 39 edges
9. `Equipment` - 35 edges
10. `EdgeRouter` - 34 edges

## Surprising Connections (you probably didn't know these)
- `RouterManagementH2Adapter` --implements--> `RouterManagementOutputPort`  [EXTRACTED]
  framework/src/main/java/com/joser/topologyinventory/framework/adapters/output/h2/RouterManagementH2Adapter.java → application/src/main/java/com/joser/topologyinventory/application/ports/output/RouterManagementOutputPort.java
- `NetworkManagementGenericAdapter` --references--> `NetworkManagementUseCase`  [EXTRACTED]
  framework/src/main/java/com/joser/topologyinventory/framework/adapters/input/generic/NetworkManagementGenericAdapter.java → application/src/main/java/com/joser/topologyinventory/application/usecases/NetworkManagementUseCase.java
- `RouterManagementGenericAdapter` --references--> `RouterManagementUseCase`  [EXTRACTED]
  framework/src/main/java/com/joser/topologyinventory/framework/adapters/input/generic/RouterManagementGenericAdapter.java → application/src/main/java/com/joser/topologyinventory/application/usecases/RouterManagementUseCase.java
- `SwitchManagementGenericAdapter` --references--> `RouterManagementUseCase`  [EXTRACTED]
  framework/src/main/java/com/joser/topologyinventory/framework/adapters/input/generic/SwitchManagementGenericAdapter.java → application/src/main/java/com/joser/topologyinventory/application/usecases/RouterManagementUseCase.java
- `SwitchManagementGenericAdapter` --references--> `SwitchManagementUseCase`  [EXTRACTED]
  framework/src/main/java/com/joser/topologyinventory/framework/adapters/input/generic/SwitchManagementGenericAdapter.java → application/src/main/java/com/joser/topologyinventory/application/usecases/SwitchManagementUseCase.java

## Import Cycles
- None detected.

## Communities (17 total, 6 thin omitted)

### Community 0 - "Router Domain Model"
Cohesion: 0.13
Nodes (25): Override, RouterManagementInputPort, RouterManagementUseCase, CoreRouter, RouterFactory, Router, IP, Location (+17 more)

### Community 1 - "Router Data Objects"
Cohesion: 0.09
Nodes (36): IPData, LocationData, ModelData, XYZ0001, XYZ0002, XYZ0003, XYZ0004, NetworkData (+28 more)

### Community 2 - "Switch Domain Model"
Cohesion: 0.07
Nodes (18): Equipment, GenericSpecificationException, AbstractSpecification, AndSpecification, Override, CIDRSpecification, Override, EmptyRouterSpec (+10 more)

### Community 3 - "Switch Specifications"
Cohesion: 0.09
Nodes (19): Override, SwitchManagementInputPort, SwitchManagementOutputPort, SwitchManagementUseCase, ApplicationTestData, EdgeRouter, Switch, SwitchService (+11 more)

### Community 4 - "Cucumber Integration Tests"
Cohesion: 0.07
Nodes (13): NetworkAdd, NetworkCreate, RouterAdd, RouterCreate, RouterRemove, SwitchAdd, SwitchCreate, SwitchRemove (+5 more)

### Community 5 - "Network Management Port"
Cohesion: 0.08
Nodes (11): Override, NetworkManagementInputPort, RouterManagementOutputPort, NetworkManagementUseCase, NetworkRemove, NetworkService, Network, Protocol (+3 more)

### Community 6 - "Framework Test Builders"
Cohesion: 0.09
Nodes (10): RouterManagementGenericAdapter, SwitchManagementGenericAdapter, FrameworkTestData, MethodOrderer.OrderAnnotation, NetworkTest, RouterTest, MethodOrderer.OrderAnnotation, SwitchTest (+2 more)

### Community 7 - "Domain Unit Tests"
Cohesion: 0.20
Nodes (3): RouterService, DomainTest, org.junit.jupiter.api.Test

### Community 8 - "UUID Type Conversion"
Cohesion: 0.38
Nodes (5): Override, UUIDTypeConverter, org.eclipse.persistence.mappings.converters.Converter, org.eclipse.persistence.mappings.DatabaseMapping, org.eclipse.persistence.sessions.Session

### Community 9 - "BDD Step Definitions"
Cohesion: 0.25
Nodes (4): Given, Then, StepDefinitions, When

### Community 10 - "Test Execution"
Cohesion: 0.60
Nodes (5): RunCucumberTest, org.junit.platform.suite.api.ConfigurationParameter, org.junit.platform.suite.api.IncludeEngines, org.junit.platform.suite.api.SelectClasspathResource, org.junit.platform.suite.api.Suite

## Knowledge Gaps
- **34 isolated node(s):** `com.joser:application`, `domain`, `XYZ0001`, `XYZ0002`, `XYZ0003` (+29 more)
  These have ≤1 connection - possible missing edges or undocumented components. (Counts symbols only; 50 node(s) total have ≤1 connection when file, concept and rationale nodes are included.)
- **6 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `Switch` connect `Switch Specifications` to `Router Domain Model`, `Router Data Objects`, `Switch Domain Model`, `Cucumber Integration Tests`, `Network Management Port`, `Framework Test Builders`, `Domain Unit Tests`?**
  _High betweenness centrality (0.124) - this node is a cross-community bridge._
- **Why does `Id` connect `Switch Specifications` to `Router Domain Model`, `Router Data Objects`, `Switch Domain Model`, `Cucumber Integration Tests`, `Network Management Port`, `Framework Test Builders`, `Domain Unit Tests`?**
  _High betweenness centrality (0.113) - this node is a cross-community bridge._
- **Why does `Router` connect `Router Domain Model` to `Router Data Objects`, `Switch Domain Model`, `Switch Specifications`, `Cucumber Integration Tests`, `Network Management Port`, `Framework Test Builders`, `Domain Unit Tests`?**
  _High betweenness centrality (0.079) - this node is a cross-community bridge._
- **What connects `com.joser:application`, `domain`, `XYZ0001` to the rest of the system?**
  _34 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `Router Domain Model` be split into smaller, more focused modules?**
  _Cohesion score 0.12836438923395446 - nodes in this community are weakly interconnected._
- **Should `Router Data Objects` be split into smaller, more focused modules?**
  _Cohesion score 0.08619777895293496 - nodes in this community are weakly interconnected._
- **Should `Switch Domain Model` be split into smaller, more focused modules?**
  _Cohesion score 0.06666666666666667 - nodes in this community are weakly interconnected._