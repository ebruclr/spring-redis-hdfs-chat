# Real-Time Data & Image System with Spring Boot, Hadoop HDFS, Redis, and Ollama LLM

This project integrates high-performance in-memory caching, distributed file storage, relational database management, and a local Large Language Model (LLM) for interactive chatting within a Spring Boot web application.

## Features
- Spring Boot backend architecture.
- Spring Data JPA & MySQL integration for relational data management and dynamic JOIN queries.
- Redis caching layer to speed up read operations and data retrieval.
- Apache Hadoop HDFS integration for scalable binary image storage and streaming.
- Spring AI & Ollama local server integration for real-time chatting with Llama 3.2.
- Thymeleaf web interface supporting CRUD operations, file uploads, and interactive AI chat.

## Tech Stack
- Java 17 / Spring Boot 3.2.5
- MySQL & Spring Data JPA
- Apache Hadoop HDFS (v3.3.4)
- Redis & Spring Data Redis (Caching)
- Spring AI & Ollama (Llama 3.2)
- Thymeleaf, HTML5, CSS3, JavaScript
- Gradle

## Configuration
Check `src/main/resources/application.properties` for database, cache, HDFS, and server configurations

## How to Run
- Ensure that MySQL, Redis, Hadoop HDFS, and Ollama (with Llama 3.2) services are running locally.
- Clone the repository and run the project using Gradle Wrapper from your terminal: ./gradlew bootRun
- Open your browser and navigate to:
  - Orders & Management Dashboard: http://localhost:8080/
  - AI Chat Page: http://localhost:8080/chat
