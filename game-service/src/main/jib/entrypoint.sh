#!/bin/sh

echo "The game service will start in ${APP_SLEEP}s..." && sleep ${APP_SLEEP}
exec java ${JAVA_OPTS} -noverify -XX:+AlwaysPreTouch -Djava.security.egd=file:/dev/./urandom -cp /app/resources/:/app/classes/:/app/libs/* "com.homework.game.GameServiceApplication"  "$@"
