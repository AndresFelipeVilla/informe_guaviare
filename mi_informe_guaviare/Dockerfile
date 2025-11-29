# --- Etapa de Construcción (Build Stage) ---
# Usa una imagen de Maven con Java 21 para compilar el proyecto
FROM eclipse-temurin:21-jdk-jammy as builder
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY src ./src
# Compila el proyecto y crea el archivo .jar
RUN ./mvnw clean install -DskipTests

# --- Etapa de Ejecución (Run Stage) ---
# Usa una imagen más ligera solo con Java para ejecutar la aplicación
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
# Copia el .jar creado en la etapa anterior (referenciando el alias 'builder')
COPY --from=builder /app/target/*.jar app.jar
# Expone el puerto que usará la aplicación
EXPOSE 8084
# Comando para iniciar la aplicación cuando el contenedor arranque
ENTRYPOINT ["java", "-jar", "app.jar"]