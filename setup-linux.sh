#!/usr/bin/env bash
# Prepara Ubuntu/Debian para correr aydsii/tp2: JDK 25, MariaDB y esquema de la BD.
# Uso: ./setup-linux.sh        (pide sudo una vez para apt/MariaDB)
set -euo pipefail
cd "$(dirname "$0")"

JDK_DIR="$HOME/.jdks/jdk-25.0.4.1+1"

echo "==> Paquetes del sistema"
sudo apt-get update
sudo apt-get install -y git curl ca-certificates mariadb-server

echo "==> JDK 25 (Temurin) en $JDK_DIR"
if [ ! -x "$JDK_DIR/bin/java" ]; then
  mkdir -p "$HOME/.jdks"
  curl -fsSL -o /tmp/jdk25.tar.gz "https://api.adoptium.net/v3/binary/latest/25/ga/linux/x64/jdk/hotspot/normal/eclipse"
  tar -xzf /tmp/jdk25.tar.gz -C "$HOME/.jdks" && rm /tmp/jdk25.tar.gz
fi
if ! grep -q "$JDK_DIR" "$HOME/.bashrc"; then
  printf '\n# JDK 25 (Spring Boot / aydsii)\nexport JAVA_HOME="%s"\nexport PATH="$JAVA_HOME/bin:$PATH"\n' "$JDK_DIR" >> "$HOME/.bashrc"
fi

echo "==> MariaDB: root sin contraseña por TCP + esquema tp2"
sudo systemctl enable --now mariadb
sudo mariadb -e "ALTER USER 'root'@'localhost' IDENTIFIED VIA mysql_native_password USING PASSWORD(''); FLUSH PRIVILEGES;"
mariadb -u root < tp2/db/schema.sql

echo "==> mvnw ejecutable"
chmod +x tp2/mvnw

echo
echo "Listo. Abrí una terminal nueva (o: source ~/.bashrc) y corré:"
echo "  cd tp2 && ./mvnw spring-boot:run    # http://localhost:8081/swagger-ui.html"
