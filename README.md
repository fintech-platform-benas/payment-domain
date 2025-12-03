# Payment Domain

[![CI Build](https://github.com/fintech-platform-benas/payment-domain/actions/workflows/ci.yml/badge.svg)](https://github.com/fintech-platform-benas/payment-domain/actions/workflows/ci.yml)
[![Publish](https://github.com/fintech-platform-benas/payment-domain/actions/workflows/publish.yml/badge.svg)](https://github.com/fintech-platform-benas/payment-domain/actions/workflows/publish.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=payment-domain&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=payment-domain)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=payment-domain&metric=coverage)](https://sonarcloud.io/summary/new_code?id=payment-domain)

Shared domain models and value objects for PaymentChain platform.

## 📦 What's Inside

### Domain Models
- `Transaction` - Transaction aggregate root
- `Customer` - Customer aggregate root

### Value Objects
- `Money` - Amount with currency
- `IBAN` - International Bank Account Number with validation
- `Currency` - Currency enum (EUR, USD, GBP)

### Exceptions
- `BusinessRuleException` - Base business exception
- `InvalidIbanException` - Invalid IBAN format
- `InvalidAmountException` - Invalid money amount

## 🚀 Usage

### Maven Dependency
```xml
<dependency>
    <groupId>com.paymentchain</groupId>
    <artifactId>payment-domain</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

### Configure GitHub Packages Access

Add to your `~/.m2/settings.xml`:
```xml
<settings>
  <servers>
    <server>
      <id>github</id>
      <username>YOUR_GITHUB_USERNAME</username>
      <password>YOUR_GITHUB_TOKEN</password>
    </server>
  </servers>
</settings>
```

Add repository to your `pom.xml`:
```xml
<repositories>
    <repository>
        <id>github-payment-domain</id>
        <url>https://maven.pkg.github.com/fintech-platform-benas/payment-domain</url>
    </repository>
</repositories>
```

## 🏗️ Build Locally
```bash
# Build
mvn clean install

# Run tests
mvn test

# Generate coverage report
mvn test jacoco:report
# Report: target/site/jacoco/index.html
```

## 📋 Structure
```
src/main/java/com/paymentchain/domain/
├── model/
│   ├── Transaction.java
│   ├── Customer.java
│   └── valueobject/
│       ├── Money.java
│       ├── IBAN.java
│       └── Currency.java
└── exception/
    ├── BusinessRuleException.java
    ├── InvalidIbanException.java
    └── InvalidAmountException.java
```

## 🔗 Related Repositories

- [payment-events](https://github.com/fintech-platform-benas/payment-events) - Event definitions
- [payment-common](https://github.com/fintech-platform-benas/payment-common) - Common utilities
- [payment-transaction-service](https://github.com/fintech-platform-benas/payment-transaction-service) - Transaction microservice

## 📄 License

MIT License - Copyright (c) 2024 Benas

---

**Part of [PaymentChain Platform](https://github.com/fintech-platform-benas)**