#!/bin/bash
#
# Einfaches Beispiel, wie man ein Vue-Frontend 
# - baut (npm run build)
# - das Ergebnis aus Vueprojekt-Unterordner 'dist' in Spring 'src/main/resources/static' kopiert
# - damit das Spring-Projekt baut
# - Ergebnis ist ein ausfuehrbares jar (in Spring 'build/libs'), welches das Vue-Frontend enthaelt
# - Vue-UI ist ueber den Spring-Tomcat direkt erreichbar (http://localhost:9090), Port siehe application.properties im Spring-Projekt

HERE="$(pwd)"
VUEPROJEKT="client"
VUEDIR="$HERE/$VUEPROJEKT"
VUEDISTDIR="$VUEDIR/dist"

SPRINGPROJEKT="server"
SPRINGDIR="$HERE/$SPRINGPROJEKT"
SPRINGRESTEILPFAD="src/main/resources"
SPRINGRESDIR="$SPRINGDIR/$SPRINGRESTEILPFAD"

echo "== Vue-Anwendung '$VUEPROJEKT' per 'npm install' polstern =="
cd "$VUEDIR" || exit 1
echo
if ! npm install | cat ; then
    echo >&2 "FEHLER - Vue-Anwendung ist nicht npm-install-bar, Abbruch"
    exit 2
fi


echo "== Vue-Anwendung '$VUEPROJEKT' wird mit 'npm run build' gebaut =="
[ -d dist ] && rm -rf dist
echo
if ! npm run build | cat ; then
    echo >&2 "FEHLER - Vue-Anwendung ist unbaubar, Abbruch"
    exit 2
fi

if ! [ -f "$VUEDISTDIR/index.html" ] ; then
    echo >&2 "FEHLER - Bei Vue-Build wurde keine 'dist/index.html' angelegt? Abbruch"
    exit 2
fi

echo
echo "== Spring-Anwendung '$SPRINGPROJEKT' wird gebaut =="
if ! [ -d "$SPRINGRESDIR" ]; then
    echo >&2 "FEHLER - Spring-Resourcenordner $SPRINGRESTEILPFAD existiert nicht, Abbruch"
    exit 3
fi

echo
echo "--- Kopiere Vue-dist-Ordner aus $VUEPROJEKT in $SPRINGRESTEILPFAD/static-Ordner von $SPRINGPROJEKT"
echo
if ! cp -rv "$VUEDISTDIR"/* "$SPRINGRESDIR/static"; then
    echo >&2 "FEHLER - Vue-Build ist nicht in static-Ordner des Spring-Projekts kopierbar, Abbruch"
    exit 3
fi

echo
echo "--- Baue Spring-Projekt $SPRINGPROJEKT mit './gradlew build'"
echo
cd "$SPRINGDIR" || exit 5
if ! ./gradlew clean build ; then
    echo >&2 "FEHLER - Spring-Anwendung ist unbaubar, Abbruch"
    exit 3
fi

echo
echo "Wie schoen - gebildete Anwendung im Spring $SPRINGPROJEKT/build/libs-Ordner"
echo
ls -lh "$SPRINGDIR/build/libs"
echo
echo "Sie koennen die komplette Anwendung starten mit"
echo
echo "    java -jar" $(basename "$SPRINGDIR/build/libs"/*.jar)
echo
echo "Beim Bebrowsen bitte den Port aus der application.properties nutzen, sofern dort gesetzt, sonst Default-Port 8080"
grep "server.port" "$SPRINGDIR/src/main/resources/application.properties" 2>&-
echo
