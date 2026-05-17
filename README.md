# Sentinel — Server Monitoring Dashboard

**Rebuild of my IHK Abschlussprojekt (Fachinformatiker für Anwendungsentwicklung, 2025)**

A full-stack server monitoring application that collects system metrics and errors from multiple servers and displays them in a clean, real-time dashboard.

## Features
- Real-time server status overview (CPU, memory, disk, heartbeats)
- Centralized error logging and severity classification
- Microservice-style architecture (Scout → Sentinel → Dashboard)
- Responsive Vue 3 dashboard with Tailwind CSS
- REST API built with Spring Boot

## Tech Stack
**Backend**
- Java 17 + Spring Boot 4.0
- Spring Data JPA + H2 (easy to switch to PostgreSQL)
- Oracle Certified Professional, Java SE 17

**Frontend**
- Vue 3 + Vite
- Tailwind CSS

## How to Run

### Backend
```bash
cd sentinel-server-monitoring
.\mvnw spring-boot:run

Project Background
This is a complete rebuild of my original IHK final project "Sentinel – Serverüberwachung".
The goal was to create a monitoring tool that helps detect and respond to server issues faster.