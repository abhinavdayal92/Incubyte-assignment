# PowerShell script to start the Spring Boot backend
Write-Host "Starting Spring Boot Backend..." -ForegroundColor Green
cd backend
mvn spring-boot:run

